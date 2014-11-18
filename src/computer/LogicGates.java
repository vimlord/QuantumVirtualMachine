/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package computer;

import java.util.ArrayList;

/**
 *
 * @author WHS-D4B1W8
 */
public class LogicGates {
    
    ///////////////////////
    //CLASSIC LOGIC GATES//
    ///////////////////////
    
    public static void NOT(Qubit q){
        PauliX(q);
        PauliY(q);
        PauliZ(q);
    }
    
    
    ///////////////////////
    //QUANTUM LOGIC GATES//
    ///////////////////////
    
    /**
     * Works like a NOT gate by rotating the x-axis by pi radians.
     * @param q The Qubit
     */
    public static void PauliX(Qubit q){
        q.X *= -1;
        QuantumComputer.getRegister().performEntanglementChange(QuantumComputer.getRegister().indexOf(q));
    }
    
    /**
     * Works like a NOT gate by rotating the y-axis by pi radians.
     * @param q The Qubit
     */
    public static void PauliY(Qubit q){
        q.Y *= -1;
        QuantumComputer.getRegister().performEntanglementChange(QuantumComputer.getRegister().indexOf(q));
    }
    
    /**
     * Works like a NOT gate by rotating the z-axis by pi radians.
     * @param q The Qubit
     */
    public static void PauliZ(Qubit q){ 
        q.Z *= -1;
        QuantumComputer.getRegister().performEntanglementChange(QuantumComputer.getRegister().indexOf(q));
    }
    
    /**
     * Swaps two Qubits
     * @param a The first Qubit
     * @param b The second Qubit
     */
    public static void Swap(Qubit a, Qubit b){
        int indexA = QuantumComputer.getRegister().indexOf(b), 
            indexB = QuantumComputer.getRegister().indexOf(b);
        
        Qubit helper = new Qubit(a.X, a.Y, a.Z);
        
        QuantumComputer.getRegister().getQubits()[indexA] = b;
        QuantumComputer.getRegister().getQubits()[indexB] = helper;
        
        ArrayList<Integer[]> pairs = QuantumComputer.getRegister().getEntengledIndexPairs();
        
        for(Integer[] pair : pairs){
            for(int i = 0; i < 2; i++)
                if(pair[i] == indexA)
                    pair[i] = indexB;
                else if(pair[i] == indexB)
                    pair[i] = indexA;
        }
        
        QuantumComputer.getRegister().performEntanglementChange(indexA);
        QuantumComputer.getRegister().performEntanglementChange(indexB);
        
    }
    
    /**
     * Performs a CCNOT Logic Gate. If the value of X for a an b is 1, it will flip c's X value.
     * @param a
     * @param b
     * @param c
     */
    public static void CCNOT_X(Qubit a, Qubit b, Qubit c){
        double prob = (1.0 + a.X)/2.0;
        double randVal;
        boolean result;
        do{
            result = Qubit.random(4) <= prob;
            randVal = 10 * Qubit.random(4);
        } while(randVal < 9);
        
        if(result)
            CNOT_X(b,c);
    }
    
    /**
     * Performs a CCNOT Logic Gate. If the value of Y for a an b is 1, it will flip c's X value.
     * @param a
     * @param b
     * @param c
     */
    public static void CCNOT_Y(Qubit a, Qubit b, Qubit c){
        double prob = (1.0 + a.Y)/2.0;
        double randVal;
        boolean result;
        do{
            result = Qubit.random(4) <= prob;
            randVal = 10 * Qubit.random(4);
        } while(randVal < 9);
        
        if(result)
            CNOT_Y(b,c);
    }
    
    /**
     * Performs a CCNOT Logic Gate. If the value of X for a an b is 1, it will flip c's Z value.
     * @param a
     * @param b
     * @param c
     */
    public static void CCNOT_Z(Qubit a, Qubit b, Qubit c){
        double prob = (1.0 + a.Z)/2.0;
        double randVal;
        boolean result;
        do{
            result = Qubit.random(4) <= prob;
            randVal = 10 * Qubit.random(4);
        } while(randVal < 9);
        
        if(result)
            CNOT_Z(b,c);
    }
    
    
    
    
    /**
     * Performs a CNOT Logic Gate. If the value of X for a is 1, it will flip b's X value.
     * @param a
     * @param b
     */
    public static void CNOT_X(Qubit a, Qubit b){
        double prob = (1.0 + a.X)/2.0;
        double randVal;
        boolean result;
        do{
            result = Qubit.random(4) <= prob;
            randVal = 10 * Qubit.random(4);
        } while(randVal < 9);
        
        if(result)
            PauliX(b);
    }
    
    /**
     * Performs a CNOT Logic Gate. If the value of Y for a is 1, it will flip b's Y value.
     * @param a
     * @param b
     */
    public static void CNOT_Y(Qubit a, Qubit b){
        double prob = (1.0 + a.Y)/2.0;
        double randVal;
        boolean result;
        do{
            result = Qubit.random(4) <= prob;
            randVal = 10 * Qubit.random(4);
        } while(randVal < 9);
        
        if(result)
            PauliY(b);
    }
    
    /**
     * Performs a CNOT Logic Gate. If the value of Z for a is 1, it will flip b's Z value.
     * @param a
     * @param b
     */
    public static void CNOT_Z(Qubit a, Qubit b){
        double prob = (1.0 + a.Z)/2.0;
        double randVal;
        boolean result;
        do{
            result = Qubit.random(4) <= prob;
            randVal = 10 * Qubit.random(4);
        } while(randVal < 9);
        
        if(result)
            PauliZ(b);
    }
    
    /**
     * Performs a CSWAP Logic Gate. If the value of X for a is 1, it will perform a Swap operation on b and c.
     * @param a
     * @param b
     * @param c
     */
    public static void CSWAP_X(Qubit a, Qubit b, Qubit c){
        double prob = (1.0 + a.X)/2.0;
        double randVal;
        boolean result;
        do{
            result = Qubit.random(4) <= prob;
            randVal = 10 * Qubit.random(4);
        } while(randVal < 9);
        
        if(result)
            Swap(b,c);
    }
    
    /**
     * Performs a CSWAP Logic Gate. If the value of Y for a is 1, it will perform a Swap operation on b and c.
     * @param a
     * @param b
     * @param c
     */
    public static void CSWAP_Y(Qubit a, Qubit b, Qubit c){
        double prob = (1.0 + a.Y)/2.0;
        double randVal;
        boolean result;
        do{
            result = Qubit.random(4) <= prob;
            randVal = 10 * Qubit.random(4);
        } while(randVal < 9);
        
        if(result)
            Swap(b,c);
    }
    
    /**
     * Performs a CSWAP Logic Gate. If the value of Z for a is 1, it will perform a Swap operation on b and c.
     * @param a
     * @param b
     * @param c
     */
    public static void CSWAP_Z(Qubit a, Qubit b, Qubit c){
        double prob = (1.0 + a.Z)/2.0;
        double randVal;
        boolean result;
        do{
            result = Qubit.random(4) <= prob;
            randVal = 10 * Qubit.random(4);
        } while(randVal < 9);
        
        if(result)
            Swap(b,c);
    }
    
    
}
