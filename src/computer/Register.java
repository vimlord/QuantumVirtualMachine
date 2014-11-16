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
public class Register {
    
    private final Qubit[] qubits;
    private final ArrayList<Integer[]> entangledPairs = new ArrayList<>();
    
    /**
     *
     * @param size The list size.
     * @param entangled The list of the indexes of entangled Qubit pairs, or null if there are none.
     * @precondition The elements of the list of entangled indexes must all be integer arrays of length 2.
     */
    public Register(int size, ArrayList<Integer[]> entangled){
        //Creates the list
        qubits = new Qubit[size];
        
        //Makes sure all of them are random qubits
        for(int i = 0; i < size; i++)
            qubits[i] = new Qubit();
        
        //Adds the index list of entangled pairs of qubits.
        if(entangled != null)
            entangledPairs.addAll(entangled);
        
        //Makes sure that all of the Qubits are properly adjusted for Entanglement.
        for(Qubit qu : qubits)
            performEntanglementChange(qu);
    }
    
    /**
     *
     * @param q The Qubits.
     * @param entangled The list of entangled pairs, or null if there are none.
     * @precondition The elements of the list of entangled indexes must all be integer arrays of length 2.
     */
    public Register(Qubit[] q, ArrayList<Integer[]> entangled){
        qubits = q;
        
        //Adds the index list of entangled pairs of qubits.
        if(entangled != null)
            entangledPairs.addAll(entangled);
        
        //Makes sure that all of the Qubits are properly adjusted for Entanglement.
        for(Qubit qu : qubits)
            performEntanglementChange(qu);
    }
    
    /**
     * Enacts Entanglement on a Qubit, if it is in the Register
     * @param q The Qubit
     */
    public void performEntanglementChange(Qubit q){
        for(int i = 0; i < qubits.length; i++)
            if(qubits[i].equals(q))
                performEntanglementChange(i);
    }
    
    /**
     * Enacts Entanglement in all applicable situations.
     * @param changedIndex The index that is being compared.
     */
    public void performEntanglementChange(int changedIndex){
        Qubit temp = qubits[changedIndex];
        
        //Makes sure the two Qubits are opposiites
        for(Integer[] pair : entangledPairs){
            if(pair[0] == changedIndex){
                Qubit changed = qubits[pair[1]];
                changed.X = -temp.X;
                changed.Y = -temp.Y;
                changed.Z = -temp.Z;
            } else if(pair[1] == changedIndex){
                Qubit changed = qubits[pair[0]];
                changed.X = -temp.X;
                changed.Y = -temp.Y;
                changed.Z = -temp.Z;
            }
                
        }
    }
    
    /**
     * Returns the index of the Qubit provided.
     * @param q The Qubit
     * @return The index of the Qubit
     */
    public int indexOf(Qubit q){
        for(int i = 0; i < qubits.length; i++)
            if(qubits[i].equals(q))
                return i;
        return -1;
    }
    
    
    public Qubit[] getQubits(){ return qubits; }
    public Qubit getQubit(int index){ return qubits[index]; }
    
    public ArrayList<Integer[]> getEntengledIndexPairs(){ return entangledPairs; }
    public Integer[] getEntangledIndexPair(int index){ return entangledPairs.get(index); }
    
}
