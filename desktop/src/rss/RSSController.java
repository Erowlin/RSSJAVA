/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rss;

import java.io.IOException;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.*;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author francklavisse
 */
public class RSSController {
    public RSSController() throws IOException {
    }
    
    public void init() throws IOException {
       try {
            _inputs = new java.util.Vector<String>();
            URL obj = new URL("http://localhost:9000/channels");
            
            HttpURLConnection co = (HttpURLConnection) obj.openConnection();
            co.setRequestProperty("Content-Type", "application/json");
            try (DataOutputStream wr = new DataOutputStream(co.getOutputStream())) {
                wr.writeBytes("access_token=" + _token);
                wr.flush();
                wr.close();
            } catch (IOException e) {}
            BufferedReader in = new BufferedReader(new InputStreamReader(
                                                       co.getInputStream()));
            
            String str;
            do {
                str = in.readLine();
                if (str != null) {
                    _inputs.addElement(str);
                }
            } while (str != null);
            in.close();
        } catch (MalformedURLException ex) {
            Logger.getLogger(RSSController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int userLogin(String username, char[] password) throws IOException {
            URL objtest = new URL("http://localhost:9000/users/access");
            HttpURLConnection con = (HttpURLConnection) objtest.openConnection();
            con.setRequestMethod("POST");
            String params = "{\"email\": \"";
            params += username;
            params += "\", \"password\": \"";
            params += password;
            params += "\"}";
//            con.setDoOutput(true);
            
            con.setRequestProperty("Content-Type", "application/json"); 
            con.setRequestProperty("charset", "utf-8");
            con.setRequestProperty("Content-Length", "" + Integer.toString(params.getBytes().length));
            try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
                wr.writeBytes(params);
                wr.flush();
                wr.close();
            } 
 
            int responseCode = con.getResponseCode();
            System.out.println("Response Code : " + responseCode);  
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
 
            while ((inputLine = in.readLine()) != null) {
		response.append(inputLine);
            }
            in.close();
            
            _token = response.toString();
            
            System.out.println(response.toString());
            return responseCode;
    }
    
    public int userInscription(String username, char[] password) throws MalformedURLException, IOException {
            URL objtest = new URL("http://localhost:9000/users/new");
            HttpURLConnection con = (HttpURLConnection) objtest.openConnection();
            con.setRequestMethod("POST");
            String params = "{\"email\": \"";
            params += username;
            params += "\", \"password\": \"";
            params += password;
            params += "\"}";
            con.setDoOutput(true);
            
            con.setRequestProperty("Content-Type", "application/json"); 
            con.setRequestProperty("charset", "utf-8");
            con.setRequestProperty("Content-Length", "" + Integer.toString(params.getBytes().length));
            try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
                wr.writeBytes(params);
                wr.flush();
                wr.close();
            } 
 
            int responseCode = con.getResponseCode();
 
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
 
            while ((inputLine = in.readLine()) != null) {
		response.append(inputLine);
            }
            in.close();
            JSONParser parser = new JSONParser();
        try {
            JSONObject array = (JSONObject) parser.parse(response.toString());
            _token = (String) array.get("token");
            System.out.println(_token);
        } catch (ParseException ex) {
            Logger.getLogger(RSSController.class.getName()).log(Level.SEVERE, null, ex);
        }
            return responseCode;
            
    }
    
    public void parseUrl(String urlToParse) throws IOException {
        try {
//            URL obj = new URL(urlToParse);
            URL obj = new URL("http://localhost:9000/channels/new");
            /**HttpURLConnection co = (HttpURLConnection) obj.openConnection();
            co.setRequestProperty("Content-Type", "application/json");
            co.setRequestProperty("JsonStub-User-Key", "7a965af9-41b7-4f6b-a505-140d36c465d5");
            co.setRequestProperty("JsonStub-Project-Key", "226d6d25-f070-4b3b-8b81-ae52834528a4");
            BufferedReader in = new BufferedReader(new InputStreamReader(
                                                       co.getInputStream()));
            _input = new String();
            String str;
            str = in.readLine();
            while (str != null) {
                _input += str;
                str = in.readLine();
            }
            in.close();
            
            obj = new URL(urlToParse); **/
            
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("POST");
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            System.out.println(_token);
            wr.writeBytes("access_token=" + _token + "&link=" + urlToParse);
            wr.flush();
            wr.close();
 
            int responseCode = con.getResponseCode();
            System.out.println("Response Code : " + responseCode);         
        } catch (MalformedURLException ex) {
            Logger.getLogger(RSSController.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    
    public void parseElement(java.util.Vector<channel> tabChannels, javax.swing.DefaultListModel names) {
        if (_inputs.size() > 0) {
            String input = _inputs.get(0);
            JSONParser parser = new JSONParser();
            String jsonText = input;
            try {
             JSONArray array = (JSONArray)parser.parse(jsonText);
                 tabChannels.removeAllElements();
             for (int i = 0; i < array.size(); ++i) {
                 channel c = new channel();
                 JSONObject obj = (JSONObject)array.get(i);
                 Object[] tab = obj.values().toArray();
                 System.out.println(tab);
                 JSONArray arr = (JSONArray)obj.get("items");
                 c.setName((String)obj.get("title"));
                 for (int j = 0; j < arr.size(); ++j) {
                     JSONObject o = (JSONObject)arr.get(j);
                     c.push((String)o.get("description"));
                 } 
                 tabChannels.addElement(c);
                 names.addElement((String)tab[3]);
//                 return (c);
                 
             }
            } catch (ParseException pe){
          }      
            
        } else {
        }
    }
    
    public void parseTab (channel c) {
        System.out.println("parse tab");
        JSONParser parser = new JSONParser();
        System.out.println(_input);
        String jsonText = _input;
        try {
         JSONArray array = (JSONArray)parser.parse(jsonText);
         System.out.println(array.size());
         for (int i = 0; i < array.size(); ++i) {
             JSONObject obj = (JSONObject)array.get(i);
             Object[] tab = obj.values().toArray();
             JSONArray arr = (JSONArray)tab[4];
             c.setName((String)tab[3]);
             for (int j = 0; j < arr.size(); ++j) {
                 JSONObject o = (JSONObject)arr.get(j);
                 System.out.println((String)o.get("description"));
                 c.push((String)o.get("description"));
             } 
         }
        } catch (ParseException pe){
      }      
    }
    
    private java.lang.String _token;
    private java.lang.String _test;
    private java.lang.String _input;
    private java.util.Vector<String> _inputs;
}
