package com.example.jonathan.rss;

/**
 * Created by Jonathan on 08-Nov-14.
 */
public class Item {
    public int id;
    public String title;
    public String description;
    public String link;
    public String pubDate;

    public Item(String t, String d, String l, String p)
    {
        title = t;
        description = d;
        link = l;
        pubDate = p;
    }
}
