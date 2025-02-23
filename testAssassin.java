import java.io.*;
import java.util.*;

public class testAssassin {
 
 public static void main (String[] args) {
 
  onlyOneNameTest();
  killRingDoesNotContain();
  graveyardDoesNotContain();
  testKillMethodException1();
  testKillMethodException2();
  testWinner();
 }
 
   /*Tests for the Exception of an empty list of names 
   * by creating an Assassin manager with an empty list
   * and it should error
   */
    public static void onlyOneNameTest (){
    List<String> nameList = new ArrayList<>();
    //empty list
    
      try{
      AssassinManager manager = new AssassinManager(nameList);
      System.out.println("Test 1 was failed: Took in an empty list!");
      } catch (IllegalArgumentException e) {
         System.out.println("Test 1 was passed: Empty list did not make a kill ring!");
         }
   }
    
    
    /* Tests the killRingContains method by giving it a name that 
    * is not in the kill ring and it should return false.
    */
    public static void killRingDoesNotContain () {
    
    List<String> killringNames = new ArrayList<>();
    killringNames.add("name1");
    killringNames.add("name2");
    killringNames.add("name3");
    
    AssassinManager manager = new AssassinManager(killringNames);
    
    if (!manager.killRingContains("FalseName")){
    
    System.out.println("Test 2 was passed: killringContains method is accurate!");
    //tests to see if it finds a name that is not in the killring
    } else {
      System.out.println("Test 2 was failed: killringContains method is inaccurate!"); 
      }
    }
    
    
    /* Tests the graveyardContains method by giving it a name that 
    * is not in the graveyard and it should return false.
    */
    public static void graveyardDoesNotContain () {
    List<String> graveyardNames = new ArrayList<>();
    graveyardNames.add("name1");
    graveyardNames.add("name2");
    graveyardNames.add("name3");
    
    AssassinManager manager = new AssassinManager(graveyardNames);
    if (!manager.graveyardContains("FalseName")){
    //tests to see if it finds a name that is not in the graveyard
    System.out.println("Test 3 was passed: graveyardContains method is accurate!");
    } else {
      System.out.println("Test 3 was failed: graveyardContains method is inaccurate!"); 
      }
    }
    

   /* Tests the first of the two exceptions in the kill method by seeing
   * whether or not the kill method will run with only one person in the killring
   * if it does, it is running incorrectly, if it doesn't then it is working properly.
   */
   public static void testKillMethodException1() {
   List<String> nameList = new ArrayList<>();
   nameList.add("name1");
   
   
   AssassinManager manager = new AssassinManager(nameList);
   
   
      try {
      manager.kill("name1");  
   
      System.out.println("Test 4 was failed: Last player killed!");
      } catch (IllegalStateException e) {
         System.out.println("Test 4 was passed: Last player was not killed!");
        } 
   
   }


   /* Tests the second kill method exception by seeing if it properly sends
   * out an exception when given a name that is not in the killring. If it runs
   * with the given name, it is not working properly, if it doesn't then 
   * it is working properly
   */
   public static void testKillMethodException2(){
   List<String> nameList = new ArrayList<>();
   nameList.add("name1");
   nameList.add("name2");
   
   AssassinManager manager = new AssassinManager(nameList);
      try {
      manager.kill("unknownName");
      System.out.println("Test 5 was failed: Kill method is trying to kill an unknown person!");
      } catch (IllegalArgumentException e){
         System.out.println("Test 5 was passed: Threw an exception when given an unknown name!");
        } 
   }


   /* Tests to see that the last person in the killring is the winner and that the killring
   * is updating properly. If it is not updating properly, the last person is not the winner,
   * something went wrong, and the winner is incorrect.
   */
   public static void testWinner(){
   List<String> nameList = new ArrayList<>();
   nameList.add("name1");
   nameList.add("name2");
   nameList.add("name3");
   
   AssassinManager manager = new AssassinManager(nameList);
   
   manager.kill("name2");
   manager.kill("name3");
      if (manager.winner().equals("name1")){
      System.out.println("Test 6 was passed: Names are updated accurately, and the last player is the winner!");
      } else{
        System.out.println("Test 6 was failed: The last player was not considered the winner!");
        }
   
   }

}