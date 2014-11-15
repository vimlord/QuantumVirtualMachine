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
    
}
