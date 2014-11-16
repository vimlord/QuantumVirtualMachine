/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package interpreter;

import java.io.*;

/**
 *
 * @author WHS-D4B1W8
 */
public class ScriptLauncher {
    
    public static void main(String[] args) throws FileNotFoundException, IOException{
        
        //If a file isn't found (generally, if using an IDE), this variable will be the location where the program looks for the script.
        String debugLocation = "user-defined directory";
        
        //Attempts to get a filename. Otherwise, it uses the script.txt file.
        String name;
        try{
            name = args[0];
        } catch(Exception e){
            //The name of the file, if it isn't found.
            name = "script.txt";
        }
        
        //Gets the location of the scriptLauncher class
        String location;
        try{
            //Sets the file location to the String at index 1.
            location = args[1];
        } catch(Exception e){
            //Grabs and adjusts the location of the file based on the Jar file's location.
            try {
                String fileLocation = ScriptLauncher.class.getResource("ScriptLauncher.class").getPath();
                location = fileLocation.substring(6, fileLocation.indexOf("QuantumComputerSimulation.jar"));
            } catch(Exception ex){
                //This is a specific line of code that is used as a debug to manually set the script's location.
                location = debugLocation;
            }
        }
        
        
        
        //Builds the BufferedReader that reads the program
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(location + name));
        } catch(FileNotFoundException fnfe){
            System.exit(1);
        }
        
        
        //Holds the line number
        int num = 1;
        
        //Holds the line and used for error checking.
        String line = br.readLine();
        
        //Loop that reads the program.
        while(line != null){
            //Reads the line.
            
            try{
                //Attempts to interpret the line.
                Interpreter.interpretLine(line);
            } catch(Exception e){
                //If it can't, it informs the user of the location of the line number of the error.
                System.err.println("Error on line " + num + ":");
                //Outputs where the problem occured in the error.
                e.printStackTrace();
            }
            //Increments the line number
            num++;
            
            line = br.readLine();
        }
    }
        
}

