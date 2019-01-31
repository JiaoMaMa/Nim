import java.util.*;
/**
 * Describes an AI player that extends the NimPlayer class and implements the
 * Testable interface.
 * 
 * @author Christine Kneer
 * @version 05/24/2018
 */
public class NimAIPlayer extends NimPlayer implements Testable{
    /**
     * Constructor
     */
    public NimAIPlayer(){
        super();
    }
    
    /**
     * Constructor:takes three parameters (username, family_name, given_name) 
     * and creates a new player.
     * @param String username,family_name,given_name
     */ 
    public NimAIPlayer(String username,String family_name,String given_name){
        super(username,family_name,given_name);
    }
    
    /**
     * Calculates and returns the number of stones to remove by the computer at the given
     * round using the maximum-winning strategy.
     * @param int currentstone, int M, Scanner s
     * @return int number_to_remove;
     */     
    public int removeStone(int currentstone, int M, Scanner s){
        int result = (currentstone-1)% (M+1);
        //if not in the case of a guaranteed win
        if (result == 0){
            Random random = new Random();
            int num = Math.min(currentstone,M);
            this.number_to_remove = random.nextInt(num)+1;
        //if in the case of a guaranteed win
        }else{
            this.number_to_remove = result;
        }
        return number_to_remove;
    }

    /**
     * Calculates and returns the remove stone command to remove by the computer at the given
     * round using the maximum-winning strategy for the AdvancedNimGame class.
     * @param boolean[] available, String lastMove
     * @return String remove stone command
     */ 
    public String advancedMove(boolean[] available, String lastMove) {
    // the implementation of the victory
    // guaranteed strategy designed by you
    String move = "";
    if (lastMove.equals("")){
        if (available.length%2 == 1){
            move = (available.length/2 + 1) + " 1";
        }else{
            move = (available.length/2) + " 2";
        }        
    }else{
        int num = Integer.parseInt(lastMove.split(" ")[0]);
        int stone = Integer.parseInt(lastMove.split(" ")[1]);
        if (stone == 1) move = (available.length - num + 1) + " 1";
        if (stone == 2) move = (available.length - num) + " 2";
    }  
    return move;
    }
    
}
