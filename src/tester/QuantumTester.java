/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tester;

import computer.*;
import interpreter.Interpreter;
import java.util.Scanner;

/**
 *
 * @author WHS-D4B1W8
 */
public class QuantumTester {
    
    
    public static void main(String[] args){
        //Scanner
        Scanner in = new Scanner(System.in);
        
        String input = "";
        
        
        while(!input.equals("exit")){
            System.out.print("\nInput: ");
            
            //This line of code allows my Simulated Quantum Scripting Language (SQSL)
            //to be used. It is commented out by default because it isn't easy to use.
            Interpreter.interpretLine(in.nextLine());
            
            /*
            input = in.nextLine().toLowerCase();
            
            if(input.equals("measure"))
                measureQubitCommand();
            if(input.equals("new register"))
                generateRegisterCommand();
            
            
            if(input.equals("exit"))
                System.exit(0);
            //*/
        }
        
    }
    
    public static void generateRegisterCommand(){
        Scanner in = new Scanner(System.in);
        //Inputs register size
        System.out.print("Input register size: ");
        int size = in.nextInt();
        //Error check
        while(size <= 0){
            System.err.println("\nInvalid register size " + size + " not permitted.");
            System.out.print("Input register size: ");
            size = in.nextInt();
        }
        
        //Creates and reports on a new Quantum Register
        QuantumComputer.setRegister(new Register(size,null));
        System.out.println("\nCreated Quantum register of size " + size + ".\n");
        
    }
    
    public static void measureQubitCommand(){
        
        Scanner in = new Scanner(System.in);

        System.out.print("Choose index to measure (0 to " + (QuantumComputer.getRegister().getQubits().length - 1) + "): ");
        int index = in.nextInt();

        in.nextLine();
        System.out.print("Choose axis to measure (X/Y/Z): ");
        String input = in.nextLine().toLowerCase();

        //System.out.println(QuantumComputer.getRegister().getQubit(index));

        if(input.equals("x")){
            //Measures the qubit
            int measurement = QuantumComputer.getRegister().getQubit(index).measureX();
            //Displays the result. The signum is there to make the period work; only 1 and -1 are ever returned.
            System.out.println("\nMeasuring qubit at index " + index + " on the X axis returned a value of " + measurement + ".\n");
        } if(input.equals("y")){
            //Measures the qubit
            int measurement = QuantumComputer.getRegister().getQubit(index).measureY();
            //Displays the result. The signum is there to make the period work; only 1 and -1 are ever returned.
            System.out.println("\nMeasuring qubit at index " + index + " on the Y axis returned a value of " + measurement + ".\n");
        } if(input.equals("z")){
            //Measures the qubit
            int measurement = QuantumComputer.getRegister().getQubit(index).measureZ();
            //Displays the result. The signum is there to make the period work; only 1 and -1 are ever returned.
            System.out.println("\nMeasuring qubit at index " + index + " on the Z axis returned a value of " + measurement + ".\n");
        }
        
    }

}
