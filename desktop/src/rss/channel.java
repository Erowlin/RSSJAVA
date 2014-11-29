/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rss;

/**
 *
 * @author francklavisse
 */
public class channel {
    public channel() {
        _name = new String();
        _model = new javax.swing.DefaultListModel();
    }
    
    /*
    * add an element to the channel
    */
    public void push(String element) {
        _model.addElement(element);
    }
  
    /*
    * set the name of the channel
    */
    public void setName(String name) {
        _name = name;
    }    
    
    public String getName() {
        return (_name);
    }
    
    public javax.swing.DefaultListModel getModel() { 
        return _model; 
    }
    
    private javax.swing.DefaultListModel _model;
    private String _name;
}
