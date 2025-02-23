import java.util.*;

public class GrammarSolver {
   private Map<String, String[]> map;

   /*Constructs a Grammar Solver map using an Arraylist of rules
   *Goes through the List, adding the left-hand side of the "::=" into the keys
   *and the right hand side into an array, spliting the rules by the "|",
   *with each index containing a rule.
   *@param Accepts a List of string as rules that will be used in the formation of the map
   *@throws an IllegalArgumentException if the List of rules is empty
   *@throws an IllegalArgumentException if the map keys already contain the non-terminal case provided
   */
   public GrammarSolver (List<String> rules) {
   
   if (rules == null || rules.size() < 1) throw new IllegalArgumentException();
   
   map = new TreeMap<>();  
      
      
      for (int i = 0; i < rules.size(); i++) {
         
         String line = rules.get(i); 
         String[] parts = line.split("::="); // split the line based on ::=
         String[] rightRules = parts[1].split ("\\|"); // split the right rules based on |
         
         if (map.containsKey(parts[0].trim())) {
         
         throw new IllegalArgumentException(); 
         
         } else {
           map.put (parts[0].trim(), rightRules); // add (non-terminal, rightRules)
           //non-terminal is a key set
          } 
      }
   }


   /* Goes through the keys in the map to see if it contains the desired Symbol
   * if it does then return true, otherwise, return false.
   *@param passes in a desired String to look for in the map key set
   @throws an IllegalArgumentException if the String passed in is empty
   *@returns true if the desired Symbol is found, false otherwise.
   */
   public boolean contains (String Symbol) {
   
   if (Symbol.length()==0 || Symbol == null) throw new IllegalArgumentException();
   
   if (map.containsKey(Symbol)) {// nonterminal should be in the key set of the map
   //if found return true
   return true;
   
   } else { //if not found return false;
      return false;
     }
   
   
   }
   
   /* Returns the all the left-hand side of each "::=" that are the keys in the map
   *@returns the key set of the map
   */
   public Set<String> getSymbols() {
   
   //set we want to return is the left-hand side, key set of the map
   
   Set<String> Symbols = new HashSet<>(map.keySet());
   
   return Symbols;
   
   }
   
   
   
   /* Recursively returns Strings to generate a grammatically accurate sentence/statement. 
   * Uses the given non-terminal Symbol to find that specific key in the map, then 
   * randomly generates Strings from the Symbol's parts array. Adds one random String from the array
   * to the parts array, then adds onto it by recursively calling onto the generate method within a for each loop 
   * that goes through the array.   
   * If the string passed is the terminal case (not found in the keys of the map) then return it.
   *@param String to look for in the keys, if it is not there then it is the terminal case so return it
   *@return a random string from the values and call on the method again to do so again
   *@throw IllegalArgumentException if the String passed in is empty
   */
   public String generate (String Symbol) {
   
   //string is empty
   if (Symbol.length()==0 || Symbol == null) throw new IllegalArgumentException();
   
   //base case: string is terminal, so return the string
   if (!map.containsKey(Symbol)) return Symbol;
    
   //getting the amount of rules in the non-terminal case by length of array
   int ruleAmount = map.get(Symbol).length;
   
   Random rand = new Random();
   String result = "";
   
   String randomRule = map.get(Symbol)[rand.nextInt(ruleAmount)];
   
   
  
    //split based on spaces
   String[] parts = randomRule.split ("\\s+");
   
    for (String part : parts) {
        result += generate(part) + (" ");
    } 
   
   return result.trim();
   }
}