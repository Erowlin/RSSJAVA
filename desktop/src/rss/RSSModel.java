package rss;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author francklavisse
 */
public class RSSModel {
    
    public RSSModel() {
        _index = -1;
        _channels = new java.util.Vector<channel>();
        _names = new javax.swing.DefaultListModel();
        try {
            _parser = new RSSController();
        } catch (IOException ex) {
            Logger.getLogger(RSSModel.class.getName()).log(Level.SEVERE, null, ex);
        }


    }

    public void userInscription(String username, char[] password) {
        try {
            clean();
            _parser.userInscription(username, password);
        } catch (IOException e) {
            
        }
    }
    
    public void userLogin(String username, char[] password) {
        System.out.println("login success");
        try {
            _parser.userLogin(username, password);
//              clean();
            //clean();
              //  System.out.println("login success");
                //_parser.init();
              //  _parser.parseElement(_channels, _names);
//            }
        } catch (IOException e) {
            
        }
    }
    
    public void pushChannel(String urlToParse) throws IOException {
        _parser.parseUrl(urlToParse);
        _parser.init();
        clean();
        _parser.parseElement(_channels, _names);
/**        channel c = new channel();
        _parser.parseTab(c);
        System.out.println(c.getName());
        _names.addElement(c.getName());
        _channels.add(c); **/
    }
    
    public void setIndex(int value) {
        _index = value;
    }
    
    public void clean() { _channels.removeAllElements(); _names.removeAllElements(); }
    
    public channel currentChannel() {
        if (_index == -1) {
            return null;
        }
        return _channels.get(_index);
    }
    
    public javax.swing.DefaultListModel names() {
        return _names;
    }
   
    private RSSController _parser;
    private int _index;
    private java.util.Vector<channel> _channels;
    private javax.swing.DefaultListModel _names;
}
