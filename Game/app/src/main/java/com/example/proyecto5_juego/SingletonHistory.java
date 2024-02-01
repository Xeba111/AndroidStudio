package com.example.proyecto5_juego;
import java.util.*;

public class SingletonHistory {
    ArrayList<String> historyList;
    int scores[] = new int[3];

    private static SingletonHistory ourInstance = new SingletonHistory();

    public static SingletonHistory getInstance() {
        return ourInstance;
    }

    private SingletonHistory() {
        historyList = new ArrayList<String>();
    }

    public ArrayList<String> getHistoryList() {
        return historyList;
    }

    public void clear() {
//        historyList.clear();
        scores = new int[]{0, 0, 0};
    }


    public void add(String history) {
        historyList.add(history);
    }

    public void incrementScore(int position){
        scores[position]++;
    }

}
