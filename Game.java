import java.util.Scanner;
/**
 * This class simulates one game-playing process between two players. Keeps 
 * track of the current number of stones and informs the user the result of 
 * the game.
 * @author Christine Kneer
 * @version 05/24/2018
 */
public abstract class Game{
    protected NimPlayer p1,p2,currentplayer;
    protected int playerTurn;
    protected int currentremove;
    protected int currentstone;
    protected Scanner s;
    
    /**
     * Constructor:creates a new game.
     */ 
    public Game(NimPlayer p1, NimPlayer p2, int currentstone, Scanner s){
        this.p1 = p1;
        this.p2 = p2;
        this.s = s;
        this.currentstone = currentstone;
        playerTurn = 1;//player1 is defaulted to play first
        currentplayer = p1;
       }
    
    /**
     * Abstract class the represents the gameplaying process.
     */
    abstract void play();   
}
