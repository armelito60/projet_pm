package com.example.projet_pm;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Match {

    private String title;
    private String embed;
    private URL url;
    private URL thumbmail;
    private String date;
    private ArrayList<side1> side1;
    private ArrayList<side2> side2;
    private ArrayList<competition> competition;
    private ArrayList<videos> videos;

    public String getTitle() {
        return title;
    }

    public String getEmbed() {
        return embed;
    }

    public URL getUrl() {
        return url;
    }

    public URL getThumbmail() {
        return thumbmail;
    }

    public String getDate() {
        return date;
    }

    public ArrayList<side1> getSide1() {
        return side1;
    }

    public ArrayList<side2> getSide2() {
        return side2;
    }

    public ArrayList<competition> getCompetition() {
        return competition;
    }

    public ArrayList<videos> getVideos() {
        return videos;
    }
}
