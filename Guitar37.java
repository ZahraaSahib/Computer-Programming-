/** Guitar37 Class represents the acceptable strings on a musical instrument
* @author Zahraa Sahib
* @Version 5/16/24
*/
import java.util.*;

public class Guitar37 implements Guitar {
   private GuitarString[] GuitarstringArr;
   private int counter; 
   public static final String KEYBOARD = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
   public int sum = 0;

   /** Constructs a Guitar37, creates an array of GuitarStrings with
   * thirty-seven GuitarStrings for the thirty-seven keys
   * Each of the keys will have its own unique frequency   
   */
   public Guitar37() {
      // GuitarString [] 
      GuitarstringArr = new GuitarString[KEYBOARD.length()];
      
       for (int i = 0; i<37; i++) {
          double n = 440 * Math.pow(2, (i-24)/12.0);
          GuitarstringArr[i] = new GuitarString(n) ;
       }
   } 
     
     /** Plucks one of the keys based on the index calculated
     * by adding twenty-four onto the given pitch value, then applying the
     * pluck method to the GuitarString at that index in the GuitarStrings Array
     * @param pitch integer value of a desired pitch to be played
     * will be utilized by calling the pluck method on the correlated GuitarString
     */
     public void playNote(int pitch) {
      int index = pitch+24;
      GuitarstringArr[index].pluck();
     }
   
   
     /** Takes a character and, if the given character is one of the thirty-seven 
     * accepted keys, it return the index of the key in the KEYBOARD string.
     * If it is not one of the acceptable keys, it returns -1.
     * @param char string A desired character to be played, will error if it is not an acceptable key
     * @returns the index of the desired character, or -1 if it is not in the KEYBOARD string
     */
     public boolean hasString (char key) {
        if (KEYBOARD.indexOf(key)!=-1){
            return true;
        }else {
            return false;
         }
     }
   
   
     /** Applies the pluck method to play a desired key, will pluck a key if it is one of the acceptable
     * characters, otherwise it will throw an exception
     * @param char string provides desired string to be plucked
     * @throws IllegalArgumentException Throws an error if the key 
     * pressed is not one of the thirty-seven keys in the KEYBOARD string
     */
     public void pluck(char key) {
   
         if (KEYBOARD.indexOf(key)==-1) throw new IllegalArgumentException();
 
         GuitarstringArr[KEYBOARD.indexOf(key)].pluck();
         
     }  
   
   
   
      /** Goes through each index of the array of GuitarStrings and adds it to a variable to calculate their sum
      * @returns the total sum of all the values in the array of GuitarStrings
      */
      public double sample() {
   
         for (int i=0; i<37; i++) {
          double n = GuitarstringArr[i].sample();
          sum+=n;
         }
        return sum;
      }
   
   
        /** Calls tic method on desired key and keeps track or how many times this specific tic method is called with a variable
        */
        public void tic() {
        
         for(int i =0; i<37; i++) {
            GuitarstringArr[i].tic();
            counter++;
         }
        }
      
      
      
        /** Method return the counter that keeps track
        * of how many times the tick method has been called
        * @returns the counter that increments 
        * each time the tic method is called
        */ 
        public int time() { 
         return counter;
        }

}