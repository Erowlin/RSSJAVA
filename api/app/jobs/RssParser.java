package jobs;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import models.Channel;
import models.Item;
import org.jdom2.*;
import org.jdom2.input.*;
import play.Logger;

public class RssParser {

    static org.jdom2.Document document;
    static Element channel;
//    static Element items;

    public void run(InputStream inputStream) {
        SAXBuilder sxb = new SAXBuilder();
        try {
            document = sxb.build(inputStream);
        }
        catch(Exception e){
            e.printStackTrace();
        }

//        racine = document.getRootElement();
//        System.out.println(racine);

        channel = document.getRootElement().getChildren("channel").get(0);
    }

    public String getTitle() {
        return (channel.getChild("title").getText());
    }

    public String getDescription() {
        return (channel.getChild("description").getText());
    }

    public String getLink() {
        return (channel.getChild("link").getText());
    }

    public List<Item> getItems() {
        Item item;
        Element itemElement;
        List<Element> itemElements = channel.getChildren("item");
        List<Item> items = new LinkedList<Item>();
        Iterator<Element> item_it = itemElements.iterator();
        while(item_it.hasNext()) {
            itemElement = item_it.next();
            item = new Item();

            item.title = itemElement.getChild("title").getText();
            item.link = itemElement.getChild("link").getText();
            item.description = itemElement.getChild("description").getText();

            items.add(item);
        }
        return (items);
    }
}
