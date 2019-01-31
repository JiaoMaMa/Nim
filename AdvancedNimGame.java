import java.util.Scanner;
import java.util.InputMismatchException;
/**
 * Simulates the advanced version of the game.
 * @author Christine Kneer
 * @version 05/24/2018
 */
public class AdvancedNimGame extends Game{
    private boolean[] available;
    private String lastMove;
    
    /**
     * Constructor:creates a new game and plays the game.
     */ 
    public AdvancedNimGame(NimPlayer p1, NimPlayer p2, int currentstone, Scanner s){
        super(p1,p2,currentstone,s);
        available = new boolean[currentstone];
        lastMove = "";
        for (int i = 0;i<currentstone;i++){
            available[i] = true;
        }
        play();
       }
    
    /**
     * Helper method that is used to print stones left.
     * @return String string representaion of the stones left
     */
    private String printstones(){
        String result = "";
        for (int i=0;i<available.length;i++){
            String temp;
            if (available[i]){
                temp ="*";
            }else{
                temp ="x";
            }
            result += " <" + (i + 1) +"," + temp + ">";
        }
        return result;
    }
    
    /**
     * Represents one move and updates the array of booleans accordingly.
     * @param String s
     */
    public void move(String s){
        String[] array = s.split(" ");
        int num = Integer.parseInt(array[0]);
        int numstone = Integer.parseInt(array[1]);
        available[num-1] = false;
        if (numstone == 2) available[num] = false;
    }
    
    /**
     * Starts the gameplaying process bewteen two Nimplayer player1 and player2.
     */
    public void play(){
        System.out.println();
        System.out.println("Initial stone count: " + currentstone);
        System.out.println("Stones display:" + printstones());
        System.out.println("Player 1: " + p1.getGiven_name() + " " + p1.getFamily_name());
        System.out.println("Player 2: " + p2.getGiven_name() + " " + p2.getFamily_name());
        System.out.print("\n" + currentstone + " stones left:" );
        System.out.println(printstones());
        while(currentstone > 0) {
            try{
                System.out.println(currentplayer.getGiven_name() +"'s turn - which to remove?");
                if (currentplayer.getClass() == NimHumanPlayer.class){
                    lastMove = currentplayer.advancedMove(available,s);
                    currentremove = Integer.parseInt(lastMove.split(" ")[1]);
                }else{
                    lastMove = currentplayer.advancedMove(available,lastMove);
                    currentremove = Integer.parseInt(lastMove.split(" ")[1]);
                }
                move(lastMove);//moves the stone
                currentstone -= currentremove ;//remove stone
                if (playerTurn == 1){
                currentplayer = p2;
                playerTurn = -1;
                }//sets the player for next round
                else {
                currentplayer = p1;
                playerTurn = 1;//sets the player for next round
                }            
                if(currentstone != 0) {
                    System.out.print("\n" + currentstone + " stones left:");
                    System.out.println(printstones());
                }
            }catch (InputMismatchException e){
                System.out.println();
                System.out.println("Invalid move.");
                System.out.print("\n" + currentstone + " stones left:");
                System.out.println(printstones());
            }
        }        
        System.out.println("\nGame Over");
        //after one game is played, increments both player's number of
        //games played      
        p1.add_game_number();
        p2.add_game_number();
        //increments the number of games won of whoever wins the game
        if(playerTurn== 1) {
            p1.add_win_number();
            System.out.println(p2.getGiven_name() +" " +p2.getFamily_name() + " wins!");
            System.out.println();
            System.out.print("$");
        }
        else {
            p2.add_win_number();
            System.out.println(p1.getGiven_name() +" " + p1.getFamily_name() +  " wins!");
            System.out.println();
            System.out.print("$");
        }

    }
    
}
