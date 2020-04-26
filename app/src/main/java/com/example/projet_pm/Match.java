package com.example.projet_pm;

import java.net.URL;
import java.util.List;

public class Match {

    private String title;
    private String embed;
    private URL url;
    private URL thumbmail;
    private String date;
    private com.example.projet_pm.side1 side1;
    private com.example.projet_pm.side2 side2;
    private com.example.projet_pm.competition competition;
    private List<com.example.projet_pm.videos> videos;

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

    public com.example.projet_pm.side1 getSide1() {
        return side1;
    }

    public com.example.projet_pm.side2 getSide2() {
        return side2;
    }

    public com.example.projet_pm.competition getCompetition() {
        return competition;
    }

    public List<com.example.projet_pm.videos> getVideos() {
        return videos;
    }
}
