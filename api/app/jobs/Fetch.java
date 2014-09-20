package jobs;

import com.ning.http.client.*;
import play.mvc.Http;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.Future;

public class Fetch {
    public static void run() {
        RssParser rss = new RssParser();
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        try {
            Future<Response> f = asyncHttpClient.prepareGet("http://www.lemonde.fr/afrique/rss_full.xml").execute();
            Response r = f.get();
            InputStream is = new ByteArrayInputStream(r.getResponseBody("UTF-8").getBytes());
            rss.run(is);
            System.out.println(rss.getTitle());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
