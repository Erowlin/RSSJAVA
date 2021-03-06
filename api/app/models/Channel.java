package models;

import java.util.*;
import javax.persistence.*;

import jobs.RssParser;
import play.Logger;
import play.data.format.*;
import play.data.validation.*;
import play.db.ebean.Model;

@Entity
public class Channel extends Model {
    @Id
    @Constraints.Min(10)
    public Long id;

//    @Constraints.Required
    public String title;

    @Constraints.Required
    public String link;

//    @Constraints.Required
    @Column(columnDefinition = "TEXT")
    public String description;

    @OneToMany(cascade=CascadeType.ALL)
    public List<Item> items;

    @ManyToOne(cascade=CascadeType.ALL)
    public User user;

    public String imageUrl;

    public int unread;


    public Channel(String title, String link, String description, User user, String imageUrl, int unread) {
        this.user = user;
    }

    public void updateInfos(RssParser rss) {
        this.title = rss.getTitle();
        this.description = rss.getDescription();
        this.link = rss.getLink();
        this.imageUrl = rss.getImageUrl();
        this.items = rss.getItems();
        this.unread = items.size();
        Logger.debug("Number of items saved: " + items.size());

        this.save();
    }

    public void updateUnread() {
        List<Item> items = this.items;
        int size = items.size();
        this.unread = 0;
        for(int i = 0; i < size; i++) {
            if (items.get(i).read == false)
                this.unread++;
        }
        this.save();
    }

//    public String language;
//    public String copyright;
//    public String managingEditor;
//    public String webMaster;
//
//    @Formats.DateTime(pattern="dd/MM/YY")
//    public Date pubDate;

//    @Formats.DateTime(pattern="dd/MM/YY")
//    public Date lastBuildDate;

//    @OneToMany(cascade=CascadeType.ALL)
//    public List<ChannelCategory> category;

//    public String generator;
//    public String docs;
//    public String cloud;
//
//    public Integer ttl;
//    public String image;
//    public String rating;
//    public String textInput;

    //skipHours
    //skipDays

    public static Model.Finder<Long, Channel> find = new Model.Finder<Long, Channel>(
            Long.class, Channel.class
    );

}
