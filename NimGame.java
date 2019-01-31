import java.util.Scanner;
import java.util.InputMismatchException;
/**
 * Simulates the simple version of the Game.
 * @author Christine Kneer
 * @version 05/24/2018
 */
public class NimGame extends Game{
    private int upperbound;
    /**
     * Constructor:creates a new game and plays the game.
     */ 
    public NimGame(NimPlayer p1, NimPlayer p2, int currentstone, int upperbound, Scanner s){
        super(p1, p2,currentstone,s);
        this.upperbound = upperbound;
        play();
    }
    
    /**
     * Starts the gameplaying process bewteen two Nimplayer player1 and player2.
     */
    public void play(){
        System.out.println();
        System.out.println("Initial stone count: " + currentstone);
        System.out.println("Maximum stone removal: " + upperbound);
        System.out.println("Player 1: " + p1.getGiven_name() + " " + p1.getFamily_name());
        System.out.println("Player 2: " + p2.getGiven_name() + " " + p2.getFamily_name());
        System.out.print("\n" + currentstone + " stones left:" );
        for(int n = 1; n<=currentstone; n++)
            System.out.print(" *");
        System.out.println();
        while(currentstone > 0) {
            try{
                System.out.println(currentplayer.getGiven_name() +"'s turn - remove how many?");
                //throws exception if invalid input
                currentremove = currentplayer.removeStone(currentstone,upperbound,s);
                if (playerTurn == 1){
                currentplayer = p2;
                playerTurn = -1;
                }//sets the player for next round
                else {
                currentplayer = p1;
                playerTurn = 1;//sets the player for next round
                }            
                currentstone -= currentremove ;//remove stone
                if(currentstone != 0) {
                    System.out.print("\n" + currentstone + " stones left:");
                    for(int n = 1; n<=currentstone; n++)
                        System.out.print(" *");
                    System.out.println();}
            }catch (InputMismatchException e){
                System.out.println("\nInvalid move. You must remove between 1 and "
                                        + Math.min(currentstone,upperbound) +" stones.");
                System.out.print("\n" + currentstone + " stones left:");
                for(int n = 1; n<=currentstone; n++)
                    System.out.print(" *");
                System.out.println();
                s.nextLine();//resets the scanner
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
            System.out.println(p1.getGiven_name() +" " +p1.getFamily_name() + " wins!");
            System.out.println();
            System.out.print("$");
        }
        else {
            p2.add_win_number();
            System.out.println(p2.getGiven_name() +" " + p2.getFamily_name() +  " wins!");
            System.out.println();
            System.out.print("$");
        }

    }
    
}


