/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smith.tech;

import com.sun.deploy.util.StringUtils;
import java.util.ArrayList;

/**
 *
 * @author Samuel
 */

public class User {
    /**
     * Array of all of the user's information
     */
    protected String[] info;
    
    /**
     * Location of name in {@link info}
     */
    protected final int nameSpot = 0;
    /**
     * Location of username in {@link info}
     */
    protected final int unSpot = 1;
    /**
     * Location of separation date in {@link info}
     */
    protected final int sepSpot = 2;
    /**
     * Location of department in {@link info}
     */
    protected final int deptSpot = 3;
    /**
     * Location of email in {@link info}
     */
    protected final int emailSpot = 4;
    /**
     * Location of desk phone number in {@link info}
     */
    protected final int phoneSpot = 5;
    /**
     * Location of cell phone number in {@link info}
     */
    protected final int cellSpot = 6;
    /**
     * Location of extension in {@link info}
     */
    protected final int extSpot = 7;
    
    
    /**
     * Array of statuses of various services
     */
    protected char[] states;
    /**
     * Date the name was removed from Notes Database
     */
    protected String notesDate;
    
    /**
     * Two value array, both must be '-' to be able to remove user
     */
    protected char[] presence;
    
    /**
     * Location of RSA status in {@link states}
     */
    protected final int rsa = 0;
    /**
     * Location of Notes status in {@link states}
     */
    protected final int notes = 1;
    /**
     * Location of Home Folder status in {@link states}
     */
    protected final int folder = 2;
    /**
     * Location of Unity status in {@link states}
     */
    protected final int unity = 3;
    /**
     * Location of Call Manager status in {@link states}
     */
    protected final int cm = 4;
    /**
     * Location of Webex status in {@link states}
     */
    protected final int webex = 5;
    /**
     * Location of RightFax status in {@link states}
     */
    protected final int rf = 6;
    /**
     * Location of Skype status in {@link states}
     */
    protected final int skype = 7;
    
    //service status constants
    private final char NOTCHECKED = 'n';
    private final char NOTPRESENT = '-';
    private final char REMOVED = 'x';    
    
    //separated status variables
    private int ID;
    private boolean processed;
    private boolean exported;
    
    /**
     * Sets the states to beginning state
     */
    private void setStates() {
        for (int i = 0; i < states.length; i++)
        {
            states[i] = NOTCHECKED;
        }
    }
    
    /**
     *  Sets up a user with information from an array list
     * 
     * @param newStuff array list with actual information from file
     */
    public User(ArrayList <String> newStuff) {
        info = new String[8];
        for(int i = 0; i < newStuff.size(); i++) {
            info[i] = newStuff.get(i);
        }
        
        setStates();
    }
    
    public User() {
        info = new String[8];
        states = new char [8];
        setStates();
    }
    
    /**
     *  Create a new user with information from String array
     * @param dets String array with correct information
     */
    public User(String[] dets) {
        //Initialize variables
        info = new String[8];
        states = new char[10];
        setStates();
        
        //if statements to prevent accessing arrays out of bounds
        if (dets.length == info.length)
        {
            for(int i = 0; i < info.length; i++)
            {
                info[i] = dets[i];
            }
        }
        
        else if (dets.length < info.length)
        {
            for(int i = 0; i<dets.length; i++)
            {
                info[i]=dets[i];
            }
        }
        
        else{
            for(int i = 0; i<info.length; i++)
            {
                info[i]=dets[i];
            }
        }
        
        //check if extension spot is empty and fill it if so
        if(info[extSpot].equals("")){
            int begin = info[phoneSpot].length() - 4;
            int end = info[phoneSpot].length() - 1;
            info[extSpot] = info[phoneSpot].substring(begin, end);
        }
        
    }
    
    /**
     * Tests whether the username of the given user contains a sequence of
     *  characters
     * @param item Character string to find in username
     * @return True if username contains characters, false if not
     */
    public boolean unContains(String item) {
        boolean flag = false;
        
        if(info[unSpot].contains(item)) {
            flag = true;
        }
        
        return flag;
    }
    
    /**
     * Retrieves name
     * @return name
     */
    public String getName() {
        return info[nameSpot];
    }
    /**
     * Retrieves username
     * @return username
     */
    public String getUN() {
        return info[unSpot];
    }
    
    /**
     *  Checks if item is exported
     * @return 0 if it is, 1 if it is not
     */
    public int isExported() {
        if (exported)
            return 0;
        else
            return 1;
    }
    
    /**
     *  Checks if item is processed
     * @return 0 if it is, 1 if it is not
     */
    public int isProcessed() {
        if (processed)
            return 0;
        else
            return 1;
    }
    
    /**
     *  Returns data for saving from user
     * @return String of information
     */
    public String returnData() {
        String output = new String();
        
        for (int i = 0; i < info.length; i++)
        {
            if (i != (info.length -1))
                output = output+info[i]+",";
            else
                output = output+info[i];
        }
        
        return output;
    }
}
