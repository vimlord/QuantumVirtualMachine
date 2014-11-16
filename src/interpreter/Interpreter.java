/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package interpreter;

import computer.*;
import java.util.ArrayList;

/**
 *
 * @author WHS-D4B1W8
 */
public class Interpreter {
    
    /**
     * Takes the program and runs it
     * @param args The program
     * @precondition There are no errors in the program
     */
    public static void interpretProgram(String[] args){
        for(String s : args)
            interpretLine(s);
    }
    
    public static void interpretLine(String arg){
        
        String line = arg;
        while(line.indexOf(" ") == 0)
            line = line.substring(1);
        
        if(line.indexOf("//") == 0)
            return;
        
        //Program initializer; required code
        if(line.indexOf("#") == 0){
            int size = Integer.parseInt(line.substring(1,line.indexOf("|")));
            line = line.substring(line.indexOf("|")+1);
            
            //Builds the list of Entanglements
            ArrayList<Integer[]> entangled = new ArrayList<>();
            while(line.length() > 0){
                String pair = line.substring(1,line.indexOf("}"));
                Integer[] addition = new Integer[2];
                addition[0] = Integer.parseInt(pair.substring(0,pair.indexOf("^")));
                addition[1] = Integer.parseInt(pair.substring(pair.indexOf("^")+1));
                
                line = line.substring(line.indexOf("}") + 1);
                entangled.add(addition);
            }
            //Creates the Quantum Register
            QuantumComputer.setRegister(new Register(size,entangled));
            System.out.println("Loaded Quantum Register of size " + size + " with " + entangled.size() + " entanglements.");
            for(Integer[] val : entangled)
                System.out.println("@" + val[0] + "^@" + val[1]);
        } 
        
        ///////////////
        //Logic Gates//
        ///////////////
        
        //CNOT X
        else if(line.indexOf("CNOTX(@") == 0){
            String param = line.substring(7,line.indexOf(")"));
            int A = Integer.parseInt(param.substring(0,param.indexOf(",")));
            int B = Integer.parseInt(param.substring(param.indexOf("@")+1));
            LogicGates.CNOT_X(QuantumComputer.getRegister().getQubit(A), QuantumComputer.getRegister().getQubit(B));
        } 
        //CNOT Y
        else if(line.indexOf("CNOTY(@") == 0){
            String param = line.substring(7,line.indexOf(")"));
            int A = Integer.parseInt(param.substring(0,param.indexOf(",")));
            int B = Integer.parseInt(param.substring(param.indexOf("@")+1));
            LogicGates.CNOT_Y(QuantumComputer.getRegister().getQubit(A), QuantumComputer.getRegister().getQubit(B));
        } 
        //CNOT Z
        else if(line.indexOf("CNOTZ(@") == 0){
            String param = line.substring(7,line.indexOf(")"));
            int A = Integer.parseInt(param.substring(0,param.indexOf(",")));
            int B = Integer.parseInt(param.substring(param.indexOf("@")+1));
            LogicGates.CNOT_Z(QuantumComputer.getRegister().getQubit(A), QuantumComputer.getRegister().getQubit(B));
        } 
        
        
        //PauliX
        else if(line.indexOf("PauliX(@") == 0){
            String param = line.substring(8,line.indexOf(")"));
            int index = Integer.parseInt(param);
            LogicGates.PauliX(QuantumComputer.getRegister().getQubit(index));
        } 
        //PauliY
        else if(line.indexOf("PauliY(@") == 0){
            String param = line.substring(8,line.indexOf(")"));
            int index = Integer.parseInt(param);
            LogicGates.PauliY(QuantumComputer.getRegister().getQubit(index));
        } 
        //PauliZ
        else if(line.indexOf("PauliZ(@") == 0){
            String param = line.substring(8,line.indexOf(")"));
            int index = Integer.parseInt(param);
            LogicGates.PauliZ(QuantumComputer.getRegister().getQubit(index));
        } 
        //Swap Gate
        else if(line.indexOf("Swap(@") == 0){
            String param = line.substring(6,line.indexOf(")"));
            int A = Integer.parseInt(param.substring(0,param.indexOf(",")));
            int B = Integer.parseInt(param.substring(param.indexOf("@")+1));
            LogicGates.Swap(QuantumComputer.getRegister().getQubit(A), QuantumComputer.getRegister().getQubit(B));
        }
        //Outputs value
        else if(line.indexOf("Output(") == 0)
            System.out.print(getValue(line.substring(7,line.length()-1)));
        //Outputs value and then a newline
        else if(line.indexOf("OutputLn(") == 0)
            System.out.println(getValue(line.substring(9,line.length()-1)));
        else if(line.indexOf("end") == 0){
            System.exit(0);
        }
            
        
    }
    
    /**
     * Method used to find the output value of a given argument.
     * @param arg The argument, method call, etc.
     * @return The Object to output
     */
    public static Object getValue(String arg){
        
        Object result = getIntValue(arg);
        if(result != null)
            return result;
        
        result = getStringValue(arg);
        if(result != null)
            return result;
        
        
        return null;
    }
    
    /**
     * Determines the integer value matching the argument, if one exists.
     * @param arg The argument.
     * @return The integer value, or null.
     */
    public static Integer getIntValue(String arg){
        String code = arg;
        if(code.indexOf("MeasureX(@") == 0){
            return QuantumComputer.getRegister().getQubit(Integer.parseInt(code.substring(10, code.indexOf(")")))).measureX();
        } else if(code.indexOf("MeasureY(@") == 0){
            return QuantumComputer.getRegister().getQubit(Integer.parseInt(code.substring(10, code.indexOf(")")))).measureY();
        } else if(code.indexOf("MeasureZ(@") == 0){
            return QuantumComputer.getRegister().getQubit(Integer.parseInt(code.substring(10, code.indexOf(")")))).measureZ();
        }
        
        return null;
    }

    /**
     * Determines the String value matching the argument, if one exists.
     * @param arg The argument.
     * @return The String value, or null
     * @Unimplemented This method will always return null.
     */
    public static Object getStringValue(String arg) {
        return null;
    }
    
}
