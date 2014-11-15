/*
 * The Qubit class stores a Qubit, which can exist as a superposition of boolean states.
 */

package computer;

import tester.QuantumTester;

/**
 *
 * @author WHS-D4B1W8
 */
public class Qubit {
    protected double X,Y,Z;
    
    /**
     *
     * @param x
     * @param y
     * @param z
     * @precondition -1 <= x <= 1
     * @precondition -1 <= y <= 1
     * @precondition -1 <= z <= 1
     * @precondition (x^2 + y^2 + z^2) == 1
     */
    protected Qubit(double x, double y, double z){
        double magnitude = Math.sqrt(Math.pow(x,2) + Math.pow(y,2) + Math.pow(z,2));
        X = x/magnitude;
        Y = y/magnitude;
        Z = z/magnitude;
    }
    
    /**
     * Creates a random Qubit
     */
    public Qubit(){
        this((2.0 * random(4) - 1.0),(2.0 * random(4) - 1.0),(2.0 * random(4) - 1.0));
    }
    
    /**
     * Performs a measurement on the Qubit's x-axis.
     * @return The result
     */
    public int measureX(){
        double prob = (1.0 + X)/2.0;
        double randVal;
        boolean result;
        do{
            result = random(4) <= prob;
            randVal = 10 * random(4);
        } while(randVal < 9);
        
        
        double newVal;
        if(result) newVal = 1;
        else newVal = -1;
        
        X = newVal;
        Y = 0;
        Z = 0;
        
        QuantumComputer.getRegister().performEntanglementChange(this);
        
        if(result) return 1; else return 0;
    }
    
    /**
     * Performs a measurement on the Qubit's y-axis.
     * @return The result
     */
    public int measureY(){
        double prob = (1.0 + Y)/2.0;
        double randVal;
        boolean result;
        do{
            result = random(4) <= prob;
            randVal = 10 * random(4);
        } while(randVal < 9);
        
        double newVal;
        if(result) newVal = 1;
        else newVal = -1;
        
        
        X = 0;
        Y = newVal;
        Z = 0;
        
        QuantumComputer.getRegister().performEntanglementChange(this);
        
        if(result) return 1; else return 0;
    }
    
    /**
     * Performs a measurement on the Qubit's z-axis.
     * @return The result
     */
    public int measureZ(){
        double prob = (1.0 + Z)/2.0;
        double randVal;
        boolean result;
        do{
            result = random(4) <= prob;
            randVal = 10 * random(4);
        } while(randVal < 9);
        
        
        double newVal;
        if(result) newVal = 1;
        else newVal = -1;
        
        X = 0;
        Y = 0;
        Z = newVal;
        
        QuantumComputer.getRegister().performEntanglementChange(this);
        
        if(result) return 1; else return 0;
    }
    
    /*
    public String toString(){
        return ("|" + X + "> + |" + Y + "> |" + Z + ">");
    }
    */
    
    /**
     * A method for generating random numbers.
     * @param tier The level of recursion to perform, where tier is always greater than or equal to zero.
     **/
    private static double random(int tier){
        double result = Math.random();
        if(tier > 0)
            result = random(tier-1);
        for(int i = 0; i < Math.pow(tier,2) + 1; i++){
            result = Math.random();
        }
        
        return result;
        
    }
    
}
