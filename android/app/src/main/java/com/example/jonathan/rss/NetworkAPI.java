package com.example.jonathan.rss;

import android.widget.Toast;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.zip.GZIPInputStream;

/**
 * Created by Jonathan on 16-Nov-14.
 */

public class NetworkAPI {
    private static NetworkAPI mInstance = null;
    public String token;
    public String address;
    ArrayList<Feed> feeds = new ArrayList<Feed>();
    private DefaultHttpClient httpclient = new DefaultHttpClient();

    private NetworkAPI(){
    }

    public static NetworkAPI getInstance(){
        if(mInstance == null)
        {
            mInstance = new NetworkAPI();
        }
        return mInstance;
    }

    public void updateFeeds()
    {
        // /channels
        feeds.clear();
        JSONArray arr = null;
        try {
            arr = new JSONArray(get("/channels"));
            for (int i = 0; i < arr.length(); ++i) {
                JSONObject obj = null;
                obj = arr.getJSONObject(i);
                Feed feed = new Feed(obj.getInt("id"), obj.getString("title"), obj.getString("link"));
                feeds.add(feed);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public ArrayList<Item> updateFeed(int id)
    {
        ArrayList<Item> items = new ArrayList<Item>();
        try {
            JSONArray arr = new JSONArray(get("/channels/" + String.valueOf(id) + "/items"));
            for (int i = 0; i < arr.length(); ++i) {
                JSONObject obj = null;
                obj = arr.getJSONObject(i);
                Item item = new Item(obj.getString("title"), obj.getString("description"),
                        obj.getString("link"), obj.getString("link"));
                items.add(item);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return items;
    }

    public void newFeed(String url) {
        JSONObject obj = new JSONObject();
        try {
            obj.put("link", url);
            post("/channels/new", obj);
        } catch (JSONException ex) {
            ex.printStackTrace();
        }
    }

    public boolean login(String email, String pass) {
        JSONObject obj = new JSONObject();
        try {
            obj.put("email", email);
            obj.put("password", pass);
            obj = post("/users/access", obj);
            if (obj == null)
                return false;
            token = obj.getString("token");
        } catch (JSONException ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }
    public void logout() {
        token = "";
    }

    public boolean register(String email, String pass) {
        JSONObject obj = new JSONObject();
        try {
            obj.put("email", email);
            obj.put("password", pass);
            obj = post("/users/new", obj);
            if (obj == null)
                return false;
            token = obj.getString("token");
        } catch (JSONException ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    private String get(String url) {
        try {
            HttpGet httpgetreq = new HttpGet("http://" + address + url);
            httpgetreq.addHeader("Token", token);
            HttpResponse httpresponse = httpclient.execute(httpgetreq);
            HttpEntity resultentity = httpresponse.getEntity();
            InputStream inputstream = resultentity.getContent();
            String resultstring = convertStreamToString(inputstream);
            inputstream.close();
            return resultstring;
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private JSONObject post(String url, JSONObject obj) {
        try {
            HttpPost httppostreq = new HttpPost("http://" + address + url);
            StringEntity se = new StringEntity(obj.toString());
            se.setContentType("application/json;charset=UTF-8");
            se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json;charset=UTF-8"));
            httppostreq.setEntity(se);
            HttpResponse httpresponse = httpclient.execute(httppostreq);
            HttpEntity resultentity = httpresponse.getEntity();
            InputStream inputstream = resultentity.getContent();
            String resultstring = convertStreamToString(inputstream);
            inputstream.close();
            System.out.println(resultstring);
            JSONObject recvdjson = new JSONObject(resultstring);
            return recvdjson;
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String convertStreamToString(InputStream is) {
        String line = "";
        StringBuilder total = new StringBuilder();
        BufferedReader rd = new BufferedReader(new InputStreamReader(is));
        try {
            while ((line = rd.readLine()) != null) {
                total.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return total.toString();
    }
}