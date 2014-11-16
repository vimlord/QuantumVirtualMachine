/*
 * The Qubit class stores a Qubit, which can exist as a superposition of boolean states.
 */

package computer;


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
        //Stores the magnitude of the entire value. The way the three axes are stored is done to shorten computation.
        //The magnitude is only used to make sure that the magnitude equals 1.
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
        //The probability of being 1
        double prob = (1.0 + X)/2.0;
        
        //Generates a random value via recursion and Math.random()
        double randVal;
        boolean result;
        do{
            result = random(4) <= prob;
            randVal = 10 * random(4);
        } while(randVal < 9);
        
        //Sets new values
        double newVal;
        if(result) newVal = 1;
        else newVal = -1;
        
        X = newVal;
        Y = 0;
        Z = 0;
        
        //Enacts entanglement with all of the entangled particles
        QuantumComputer.getRegister().performEntanglementChange(this);
        
        //Returns the measurement
        if(result) return 1; else return 0;
    }
    
    /**
     * Performs a measurement on the Qubit's y-axis.
     * @return The result
     */
    public int measureY(){
        
        //Generates random result for whether or not result is 0 or 1.
        double prob = (1.0 + Y)/2.0;
        double randVal;
        boolean result;
        do{
            result = random(4) <= prob;
            randVal = 10 * random(4);
        } while(randVal < 9);
        
        
        //Sets mew values resulting from measurement
        double newVal;
        if(result) newVal = 1;
        else newVal = -1;
        
        
        X = 0;
        Y = newVal;
        Z = 0;
        
        //Performs entanglement changes
        QuantumComputer.getRegister().performEntanglementChange(this);
        
        //Returns result
        if(result) return 1; else return 0;
    }
    
    /**
     * Performs a measurement on the Qubit's z-axis.
     * @return The result
     */
    public int measureZ(){
        //Generates random result
        double prob = (1.0 + Z)/2.0;
        double randVal;
        boolean result;
        do{
            result = random(4) <= prob;
            randVal = 10 * random(4);
        } while(randVal < 9);
        
        //Finds new value due to measurement
        double newVal;
        if(result) newVal = 1;
        else newVal = -1;
        
        X = 0;
        Y = 0;
        Z = newVal;
        
        //Quantum Entanglement changes
        QuantumComputer.getRegister().performEntanglementChange(this);
        
        //Returns the measurent's value.
        if(result) return 1; else return 0;
    }
    
    /*
    public String toString(){
        return ("|" + X + "> + |" + Y + "> |" + Z + ">");
    }
    */
    
    /**
     * A method for generating random numbers that utilizes recursion.
     * @param tier The level of recursion to perform, where tier is always greater than or equal to zero.
     * @return A random double from 0.0 to 1.0
     **/
    public static double random(int tier){
        double result = Math.random();
        if(tier > 0)
            result = random(tier-1);
        for(int i = 0; i < Math.pow(tier,2) + 1; i++){
            result = Math.random();
        }
        
        return result;
        
    }
    
}
