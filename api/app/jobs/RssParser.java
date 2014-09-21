package jobs;

import java.io.*;
import java.util.Iterator;
import java.util.List;

import org.jdom2.*;
import org.jdom2.input.*;

public class RssParser {

    static org.jdom2.Document document;
    static Element channel;

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
}
