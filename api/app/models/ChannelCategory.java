package models;

import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class ChannelCategory extends Model {

    @ManyToOne
    public Channel channel;
}
