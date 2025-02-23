import java.util.*;

public class AssassinManager {

    private AssassinNode killring;
    private AssassinNode graveyard;

    
    /* Constructs an Assassin Manager object that adds all the names
    * from a given ArrayList into the killring keeping their order
    *@param passes in a List of names which will be added into the killring
    *@throws IllegalArgumentException if the list passed is empty
    */
    public AssassinManager (List<String> names) {
   
      if (names.size() < 1) throw new IllegalArgumentException("No players found!");
      
      killring = new AssassinNode(names.get(0));
      AssassinNode temp = killring;
      
      for (int i = 1; i < names.size(); i++){
     
         temp.next = new AssassinNode(names.get(i));
         temp = temp.next;
      }
    }



    /* Prints the names in the killring in order stating 
    * who is stalking who. If there is only one, that person
    * is stalking themselves.
    */
    public void printKillRing() {
    AssassinNode t = killring;
    
    String first = t.name;
    
      while (t.next != null) {
         System.out.println(t.name + " is stalking " + t.next.name);
         t = t.next;
      }
      
    System.out.println (t.name + " is stalking " + first);
    }
    
    
 
   /* Prints the names of the people killed and who they were killed by,
    * doing so in the order of latest to oldest kills. Only prints an output
    * if graveyard is not empty.     
    */
    public void printGraveyard() {
    
    String dead = "";
    String killer = "";
    AssassinNode each = graveyard;
    
       while (each!= null) {
    
       killer = each.killer;
       dead = each.name;
    
       System.out.println(dead + " was killed by " + killer);
       each = each.next;
    
       }
    }
    
    
    /* Goes through the killring and returns true if the desired name
    * is found and false if it is not.
    * @param desired name to detect in killring
    * @return true if desired name is found and false if it is not.
    */
    public boolean killRingContains (String name) {
    AssassinNode c = killring;
    int trueCounter1 = 0;
    
    
    while (c != null) {
       if (c.name.equalsIgnoreCase(name)) {
       trueCounter1++;
       }
       c = c.next;
    }
     
      if (trueCounter1 > 0){
        return true;
       } else {
    
          return false;
         }
    }
 
 
 
    /* Goes through the graveyard and returns true if the desired name
    * is found and false if it is not.
    * @param desired name to detect in graveyard
    * @return true if desired name is found and false if it is not.
    */ 
    public boolean graveyardContains (String name) {
    AssassinNode d = graveyard;
    int trueCounter2 = 0;
   
    while (d != null) {
    
     if (d.name.equalsIgnoreCase(name)) {
     trueCounter2 ++;
     }
     
     d = d.next;
    }
    
    if (trueCounter2 > 0){
    return true;
    } else {
       return false;
       }
    }

 
     /* Signifyies the end of the game by returning true if only one
     * person is in the killring
     * @return true if there is only one person remaining in the game
     * and false if there are more
     */
    public boolean gameOver() {
    
      if (killring.next == null){
      return true;
      } else {
        return false;
        }
    }

    /* Returns the name of the winner of the game
    * who is the last person in the killring
    * @returns the name of the person in the killring if there is only one
    * and null if there are more than one players remaining
    */  
    public String winner () {
    
      if (killring.next == null){
      return killring.name;
      } else {
      return null;
        } 
    }
   
   
   
    /* When this method is called on a player it moves them from the killring to the 
    * graveyard, without altering the order of the names in the killring
    * @throws IllegalStateException if there is only one person in the killring
    * signifying that the game is over
    * @throws IllegalArgumentException if the name that is passed in as a parameter
    * is not found in the killring
    * @param the name of the person who is killed and should be removed from
    * the killring and be put into the graveyard
    */
    public void kill (String name) {
    String s1 = name.substring(0,1).toUpperCase() + name.substring(1).toLowerCase();
    name = s1;
    //get user input in proper format
    if (killring.next == null) throw new IllegalStateException();
    //Exception if only one person is in the killring
    
    if (!killRingContains(name)) throw new IllegalArgumentException();
    //Exception if person is not found in killring
    
    AssassinNode temp1 = killring;
    String killer = "";
    
    //tests to see if the name if in the first node
    if(killring.name.equalsIgnoreCase(name)){
    killring = temp1.next;
    
      while(temp1.next!=null){
      temp1 = temp1.next;
      }
      
      killer = temp1.name;
      //if first person is killed, killer is the last person in the killring
    }
    
    else {
       while(!temp1.next.name.equalsIgnoreCase(name)){
        temp1 = temp1.next;
        }
      if(temp1.next!= null) {
       temp1.next = temp1.next.next;
       //skip it if there is a node following it
       } else {
          temp1.next = null;
         //otherwise, end it
         }
    killer = temp1.name;
   //killer is the person before the person killed
    }
   
   
     AssassinNode newdead = new AssassinNode(name);
     //adding dead name into graveyard
     newdead.killer = killer;
     //add newdead's killer
     newdead.next = graveyard;   
     graveyard = newdead;
    //order of the graveyard is from most recent kill to oldest kill
     }
} 