package com.example.lottery;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText etContent;
    private EditText etAmount;
    private FloatingActionButton fab;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        findViews();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringBuilder sb = new StringBuilder();
                String content = etContent.getText().toString();
                String amount = etAmount.getText().toString();

                Reader reader = new Reader(content);
                reader.parseContent();//轉換內容
                ArrayList<String> allContent = reader.getAllContent();//取得集合

                if (content.equals("")) {
                    Snackbar.make(view, "請輸入內容再進行抽籤", Snackbar.LENGTH_SHORT).show();
                } else if (!amount.matches("\\d")) {
                    Snackbar.make(view, "請輸入抽籤數量", Snackbar.LENGTH_SHORT).show();
                } else if (Integer.parseInt(amount) > allContent.size()) {
                    Snackbar.make(view, "沒有那麼多籤可以抽", Snackbar.LENGTH_SHORT).show();
                } else {
                    for (int i = 0; i < Integer.parseInt(amount); i++) {
                        sb.append((i + 1) + " -> " + allContent.get(i) + "\n");
                    }
                }

                tvResult.setText(sb.toString());
                //收起鍵盤
                InputMethodManager keyBoard = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                keyBoard.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        });
    }

    private void findViews() {
        etContent = findViewById(R.id.content);
        etAmount = findViewById(R.id.amount);
        fab = findViewById(R.id.fab);
        tvResult = findViewById(R.id.result);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_reset) {
            etContent.setText("");
            etAmount.setText("");
            tvResult.setText("");
        }
        return super.onOptionsItemSelected(item);
    }
}
