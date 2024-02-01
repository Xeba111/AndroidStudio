package com.example.proyecto6_galaxies;


import java.net.MalformedURLException;
import java.net.URL;


public class Galaxy {
    public final String title;
    public final String tIcon;
    public final String lIcon;
    public final String id;

    public Galaxy(String title, String iconName){
        this.title = title;
        this.tIcon = "https://cdn.spacetelescope.org/archives/images/thumb300y/" + iconName + ".jpg";
        this.lIcon = "https://cdn.spacetelescope.org/archives/images/large/" + iconName + ".jpg";
        this.id = iconName;
    }

}
