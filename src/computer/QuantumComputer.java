/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package computer;

/**
 *
 * @author WHS-D4B1W8
 */
public class QuantumComputer {
    private static Register register;
    
    /**
     * Grabs the current Quantum Register.
     * @return The Quantum Register.
     */
    public static Register getRegister(){ return register; }
    
    /**
     * Sets a new Quantum Register.
     * @param r The new Quantum Register.
     */
    public static void setRegister(Register r){ register = r; }
    
}
