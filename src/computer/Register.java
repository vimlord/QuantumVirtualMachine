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
        qubits = new Qubit[size];
        for(int i = 0; i < size; i++)
            qubits[i] = new Qubit();
        if(entangled != null)
            entangledPairs.addAll(entangled);
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
        if(entangled != null)
            entangledPairs.addAll(entangled);
        for(Qubit qu : qubits)
            performEntanglementChange(qu);
    }
    
    public void performEntanglementChange(Qubit q){
        for(int i = 0; i < qubits.length; i++)
            if(qubits[i].equals(q))
                performEntanglementChange(i);
    }
    
    public void performEntanglementChange(int changedIndex){
        Qubit temp = qubits[changedIndex];
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
