package models;

import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by julien on 9/21/14.
 */

@Entity
public class Item extends Model {
    @Id
    @Constraints.Min(10)
    public Long id;

    @Constraints.Required
    public String title;

    @Constraints.Required
    public String link;

    @Constraints.Required
    public String description;

//    @ManyToOne(cascade=CascadeType.ALL)
//    public Channel channel;

//    public String author;
//    public String category;
//    public String comments;
//    public String enclosure;
//    public String guid;
//    public String pubDate;
//    public String source;

//    @OneToMany(cascade=CascadeType.ALL)
//    public List<Channel> channels;

    public static Model.Finder<Long, Item> find = new Model.Finder<Long, Item>(
            Long.class, Item.class
    );

}
