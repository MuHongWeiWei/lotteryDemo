package com.example.lottery;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Reader {
    private String content;
    private ArrayList<String> allContent = new ArrayList<>();

    Reader(String content) {
        this.content = content;
    }

    public ArrayList<String> getAllContent() {
        //把所得到的內容打亂
        Collections.shuffle(allContent);
        return allContent;
    }

    private void setAllContent(ArrayList<String> allContent) {
        this.allContent = allContent;
    }

    public void parseContent() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(content.getBytes())));
        try {
            String line = reader.readLine();
            while (line != null) {
                allContent.add(line);
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
