import java.util.Scanner;
import java.util.InputMismatchException;
/**
 * Describes a human player that extends the NimPlayer class and implements the
 * Testable interface.
 * @author Christine Kneer
 * @version 05/24/2018
 */
public class NimHumanPlayer extends NimPlayer{

    /**
     * Constructor:takes three parameters (username, family_name, given_name) 
     * and creates a new player.
     * @param String username,family_name,given_name
     */     
    public NimHumanPlayer(String username,String family_name,String given_name){
        super(username,family_name,given_name);
    }
    
    /**
     * Sets and returns the number of stones to remove at the given rouond.
     * Throws InputMismatchException if:
     * 1.User does not input an integer number.
     * 2.User's input is a negative number.
     * 3.User input a number that is greater than the number of stones left.
     * 4.User input a number that is greater than the maximum limit.
     * @param int currentstone, int M,Scanner s
     */
    public int removeStone(int currentstone, int M,Scanner s) throws InputMismatchException{
       int currentremove = s.nextInt();
       if (currentremove > M || currentremove < 1 || currentstone - currentremove < 0){
           throw new InputMismatchException();
        }else{
           this.number_to_remove = currentremove;
       }
       return number_to_remove;
    }
    
    /**
     * Returns the command to move stones for AdavancedNimGame if and only if the command is valid.
     * Throws InputMismatchException if:
     * 1.User does not input an integer number.
     * 2.User's input is a negative number.
     * 3.User intends to take a stone that has already been taken.
     * 4.User intends to take a stone that does not exist in the setting of that given game.
     */
    public String advancedMove(boolean[] available,Scanner s) throws InputMismatchException
    {
       String input = s.nextLine();
       String[] array = input.split(" ");
       String move;
       try{
           int num = Integer.parseInt(array[0]);
           int numstone = Integer.parseInt(array[1]);
           int[] nums = {num,numstone};
           if ((Integer.parseInt(array[1]) > 2)||(Integer.parseInt(array[1]) < 0)||
              ((Integer.parseInt(array[0]) < 0))){
               throw new InputMismatchException();
           }else if (!isAvailable(available,nums)){
               throw new InputMismatchException();
           }else{
               move = input;
           }
        }catch (NumberFormatException e){
            throw new InputMismatchException();
        }catch (ArrayIndexOutOfBoundsException e){
            throw new InputMismatchException();
        }
       return move;
    }
    
    /**
     * A helper method that returns the validity of the user's input.
     * @param boolean[] available,int[] s
     * @return boolean the validity of the user's input
     */
    private boolean isAvailable(boolean[] available,int[] s) throws InputMismatchException{
        try{
            if ((s[1] == 2)&&(!available[s[0]])) return false;
            return (available[s[0]-1]);
        }catch (ArrayIndexOutOfBoundsException e){
            throw new InputMismatchException();
        }
    }
    
    
}
