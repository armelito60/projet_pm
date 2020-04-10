package com.example.projet_pm;

import java.net.URL;
import java.util.List;

public class Match {

    private String title;
    private String embed;
    private URL url;
    private URL thumbmail;
    private String date;
    private Side1 side1;
    private Side2 side2;
    private Competition competition;
    private List<Videos> videos;

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

    public Side1 getSide1() {
        return side1;
    }

    public Side2 getSide2() {
        return side2;
    }

    public Competition getCompetition() {
        return competition;
    }

    public List<Videos> getVideos() {
        return videos;
    }
}
