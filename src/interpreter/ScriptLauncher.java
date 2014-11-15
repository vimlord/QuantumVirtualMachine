/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package interpreter;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author WHS-D4B1W8
 */
public class ScriptLauncher {
    
    public static void main(String[] args) throws FileNotFoundException, IOException{
        
        String name;
        try{
            name = args[0];
        } catch(Exception e){
            name = "script.txt";
        }
        
        BufferedReader br = new BufferedReader(new FileReader("src/resources/script.txt"));
        
        /*Object[] lines = (br.lines().toArray());
        
        for(Object line : lines)
            Interpreter.interpretLine((String)(line));
        */
        String line = "";
        while(line != null){
            line = br.readLine();
            if(line != null)
                Interpreter.interpretLine(line);
        }
    }
        
}

