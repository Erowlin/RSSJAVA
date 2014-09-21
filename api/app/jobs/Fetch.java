package jobs;

import com.ning.http.client.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.Future;


public class Fetch {


    public static Future<RssParser> getRssParser(String url) {
        try {
            AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
            Future<RssParser> f = asyncHttpClient.prepareGet(url).execute(
                    new AsyncCompletionHandler<RssParser>(){

                        @Override
                        public RssParser onCompleted(Response response) throws Exception{
                            RssParser rss = new RssParser();
                            InputStream is = new ByteArrayInputStream(response.getResponseBody("UTF-8").getBytes());
                            rss.run(is);
                            return rss;
                        }

                        @Override
                        public void onThrowable(Throwable t){
                            // Something wrong happened.
                        }
                    });
            return f;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void run() {
        ArrayList<String> urls = new ArrayList<>();
        urls.add("http://www.lemonde.fr/afrique/rss_full.xml");
        urls.add("http://www.lemonde.fr/ameriques/rss_full.xml");
        urls.add("http://www.lemonde.fr/sciences/rss_full.xml");
        ArrayList<Future<RssParser>> futures = new ArrayList<>();

        Iterator<String> url_it = urls.iterator();
        while(url_it.hasNext()) {
            futures.add(getRssParser(url_it.next()));
        }

        Iterator<Future<RssParser>> response_it = futures.iterator();
        while(response_it.hasNext()) {
            try {
                RssParser rss = response_it.next().get();
                if (rss != null) {
                    System.out.println(rss.getTitle() + " --- " + rss.getLink()  + " --- " + rss.getDescription());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
