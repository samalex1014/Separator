/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smith.tech;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Samuel
 */
public class FileWork {
    /**
     *  Checks if directory exists; if not, creates the directory and files
     * @param arg Array, with [0] being the directory path, and [1+] any working
     *              files
     */
    public static void createDir(String [] arg) {
        File theDir = new File(arg[0]);
        int length = arg.length;

        // if the directory does not exist, create it
        if (!theDir.exists()) {
            System.out.println("Creating program files");
            boolean result = false;

            try{
                theDir.mkdir();
                result = true;
            } 
            catch(SecurityException se){
                //handle it
            }        
            if(result) {    
                System.out.println("DIR created");  
            }
        }
        
        //Starts at arg[1], first file name, and uses arg[0] as directory
        for(int i = 1; i < (arg.length); i++)
        {
            //sets file as arg[0] (directory) arg[i] (filename)
            Path file = Paths.get(arg[0]+arg[i]);
            
            try {
                Files.createDirectories(file.getParent());
                Files.createFile(file);
                System.out.print(arg[0] + arg[i] + " created");
            } catch (FileAlreadyExistsException x){
                System.err.format("file named %s" + " already exists%n", file);
            } catch (IOException x) {
                System.err.format("createFile error: %s%n", x);
            }
                
        }
    }
    
    
    /**
     * Opens files
     * @param path location of the file
     * @return  String array of the file
     * @throws IOException 
     */
    public static String[] OpenFile(File path) throws IOException /*throws IOException*/ {
        //Opens the files
        FileReader fr = new FileReader(path);
        BufferedReader textReader = new BufferedReader(fr);
        
        //Read the number of lines and create a string array of the same length
        int numberOfLines = readLines(path);
        String[] textData = new String[numberOfLines];
        
        //Cycle through file, adding lines to the array
        for (int i=0; i < numberOfLines; i++) {
            textData[i] = textReader.readLine();
        }
        
        textReader.close();
        return textData;
    }
    
    /**
     * Opens files
     * @param path String of the file location
     * @return String array of the file 
     * @throws IOException 
     */
    public static String[] OpenFile(String path) throws IOException /*throws IOException*/ {
        
        FileReader fr = new FileReader(path);
        BufferedReader textReader = new BufferedReader(fr);
        
        
        int numberOfLines = readLines(path);
        String[] textData = new String[numberOfLines];
        
        //Cycle through the file, adding lines to the String array
        for (int i=0; i < numberOfLines; i++) {
            textData[i] = textReader.readLine();
        }
        
        textReader.close();
        return textData;
    }
    
    //Purpose: Read data files and return the number of items
    /**
     * Reads data files and returns the number of lines
     * @param path File object of the file location
     * @return Integer of number of lines in file
     * @throws FileNotFoundException
     * @throws IOException 
     */
    static int readLines(File path) throws FileNotFoundException, IOException /*throws IOException*/ {
        
        FileReader file_to_read = new FileReader(path);
        BufferedReader bf = new BufferedReader (file_to_read);
        
        String aLine;
        int numberOfLines = 0;
        
        while ((aLine = bf.readLine()) != null) {
            numberOfLines++;
        }
        
        bf.close();
        
        return numberOfLines;
    }
    
    /**
     * Reads data files and returns the number of lines
     * @param path String object of file location
     * @return Integer of number of lines in file
     * @throws FileNotFoundException
     * @throws IOException 
     */
    static int readLines(String path) throws FileNotFoundException, IOException /*throws IOException*/ {
        
        FileReader file_to_read = new FileReader(path);
        BufferedReader bf = new BufferedReader (file_to_read);
        
        String aLine;
        int numberOfLines = 0;
        
        while ((aLine = bf.readLine()) != null) {
            numberOfLines++;
        }
        
        bf.close();
        
        return numberOfLines;
    }
    
    /**
     * Writes contents of String array to file
     * @param path File object of file location
     * @param data String array of data to write
     */
    static void saveInfo(File path, String[] data){
        try {
            FileWriter fw = new FileWriter(path.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            
            for (int i = 0; i<data.length; i++)
            {
                bw.write(data[i]);
                bw.newLine();
                System.out.println(data[i]);
            }
            
            bw.close();
            fw.close();
        } catch (IOException ex) {
            Logger.getLogger(FileWork.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
