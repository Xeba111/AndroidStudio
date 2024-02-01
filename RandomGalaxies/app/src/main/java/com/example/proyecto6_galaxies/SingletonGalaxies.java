package com.example.proyecto6_galaxies;

import android.graphics.Bitmap;
import java.util.*;

public class SingletonGalaxies {

    public Map<String, Bitmap> bitmaps = new HashMap<>();
    public Map<String,Galaxy> galaxyMap ;
    public List<Galaxy> galaxyList;
    private static SingletonGalaxies ourInstance = new SingletonGalaxies();

    public static SingletonGalaxies getInstance() {
        return ourInstance;
    }

    private SingletonGalaxies() {
        galaxyMap = new HashMap<>();
        galaxyList = new ArrayList<>();
    }

}
