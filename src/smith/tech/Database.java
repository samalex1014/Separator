/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smith.tech;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import smith.tech.User;
import smith.tech.FileWork;
/**
 *
 * @author Samuel
 */
public class Database {
    //hold database
    ArrayList <User> data;
    int unProItems = 0;
    int unExItems = 0;
    
    /**
     *  Alternate constructor
     * @param dirInfo location of the data files
     */
    Database(String[] dirInfo) {
        //main data item
        data = new ArrayList();

        //File location
        String loc = dirInfo[0] + dirInfo[1];
        
        //initial input data
        String[] input;
        String parsing = new String();
        String delims = ",";
        
        //create the directories
        FileWork.createDir(dirInfo);
        
        try {
            //Assign the file to a string array
            input = FileWork.OpenFile(loc);

            
            for (int i = 0; i < input.length; i++)
            {
                //select a line from the file
                parsing = input[i];
                
                //assign separated information to string array
                String[] details = parsing.split(delims);
                
                //create new user with information from file
                User add = new User(details);
                
                //add user to database
                data.add(add);
                
                //update information
                unProItems++;
                unExItems++;
            }
        } catch (IOException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Finds the User at a given index 
     * @param i Index of user to retrieve
     * 
     * @return  User from the database
     */
    public User get(int i) {
        User selected;
        
        if (i < data.size() && i >= 0) {
            selected = data.get(i);
        }
        else {
            selected = new User();
            System.out.println("Illegal index in retrieving item from database!!!");
        }
        
        return selected;
    }
    
    /**
     * Finds the size of the database
     * @return length of {@link data}
     */
    public int size() {
        int length = data.size();
        
        return length;
    }
    
        /**
         *  Loads a file into the database
         * @param loc File location
         * @throws IOException 
         */
        public void load(File loc) throws IOException {
            //Holds the file
            String[] input;
            
            //Assign the file to the variable
            input = FileWork.OpenFile(loc);
            System.out.println("Opened selected object, with " + input.length + " lines."); //DEBUG
        
            /*
            *   Cycle through the file and add items to database
            */
            for (int i = 1; i < input.length; i++)
            {
                //Begin variables
                //User newInfo = new User();
                String delims = ",";
                
                String[] info = input[i].split(delims);
                
                
                ArrayList <String> actualInfo = new ArrayList();
                
                //add each item to the ArrayList to be able to remove junk
                for (int j = 0; j<info.length; j++)
                {
                    actualInfo.add(info[j]);
                }
            
                //remove junk data
                actualInfo.remove(9);
                actualInfo.remove(3);
                actualInfo.remove(0);
            
                /*Set the information from the entry to a user to be able to add
                **  to database as user
                */
                User newInfo = new User(actualInfo);
            
                data.add(newInfo);
                unProItems++;
            }
        
            System.out.println("Input loaded");
    }
        
        /**
         * 
         * @return a string number of items processed
         */
        public String updateProcessed() {
            //set unProcessed items to zero
            unProItems = 0;
            
            //Cycle through the database and get whether it's processed
            for(int i = 0; i < data.size(); i++)
            {
                unProItems = unProItems + data.get(i).isProcessed();
            }
            
            return Integer.toString(unProItems);
        }
        
        /**
         * 
         * @return a string number of items exported
         */
        public String updateExported() {
            //set unExported items to zero
            unExItems = 0;
            
            //Cycle through the database and get whether it's exported
            for (int i = 0; i < data.size(); i++)
            {
                unExItems = unExItems + data.get(i).isExported();
            }
            
            return Integer.toString(unExItems);
        }
        
        /**
         * 
         * @return returns integer of unProcessed numbers
         */
        public int updateProgressP() {
            updateProcessed();
            
            return unProItems;
        }
        
        /**
         * 
         * @return returns integer of unExported numbers
         */
        public int updateProgressE() {
            updateExported();
            
            return unExItems;
        }
        
        /**
         * 
         * @param loc location of save file
         */
        public void save(String[] loc) {
            //Creates a string array the size of the database
            String[] info = new String[data.size()];
            
            //Creates a file with the location of the save file
            File file = new File(loc[0]+loc[1]);
            
            //Add each item to string array to be saved
            for(int i = 0; i < info.length; i++)
            {
                info[i] = data.get(i).returnData();
            }
            
            FileWork.saveInfo(file, info);
        }
}
