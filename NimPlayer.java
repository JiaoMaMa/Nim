import java.util.Scanner;
/**
 * An abstract class that describes a player (either a human player or an AI 
 * player) and has the following information associated with it: username, 
 * family name, given name, number of games played, number of games won and 
 * number of stones intended to remove this round.
 * @author Christine Kneer
 * @version 05/24/2018
 */
public abstract class NimPlayer {
    protected String username;
    protected String family_name;
    protected String given_name;
    protected int number_of_game;
    protected int number_of_won;
    protected int number_to_remove;
    
    /**
     * Constructor:creates a new empty player with some default values.
     */     
    public NimPlayer(){
        this.username= "";
        this.given_name = "";
        this.family_name = "";
        //Both number of games played and number of games won are defaulted to be
        //0 when a new NimPlyaer object is constructed
        this.number_of_game = 0;
        this.number_of_won =0;
        this.number_to_remove=0;
    }
    
    /**
     * Constructor:takes three parameters (username, family_name, given_name) 
     * and creates a new player.
     * @param String username,family_name,given_name
     */    
    public NimPlayer(String username,String family_name,String given_name) {
        this();
        this.username= username;
        this.given_name = given_name;
        this.family_name = family_name;
    }

    /**
     * Getter: returns the username of the player.
     * @return String username
     */        
    public String getUsername() {
        return username;
    }
    
    /**
     * Getter: returns the family name of the player.
     * @return String family_name
     */    
    public String getFamily_name() {
        return family_name;
    }
    
    /**
     * Getter: returns the given name of the player.
     * @return given_name
     */    
    public String getGiven_name() {
        return given_name;
    }
    
    /**
     * Getter: returns the number of games played by the player.
     * @return int number_of_game
     */    
    public int getNumber_of_game() {   
    return number_of_game;
    }
    
    /**
     * Getter: returns the number of games won by the player.
     * @return int number_of_won
     */    
    public int getNumber_of_won() {
    return number_of_won;
    }
    
    /**
     * Setter: sets the number of games won by the player.
     * @param int n
     */
    public void setNumber_of_won(int n) {
    number_of_won = n;
    }
    
    /**
     * Setter:sets the number of games played by the player.
     * @param int n
     */
    public void set_game_number(int n) {
    number_of_game = n;
    }
    
    /**
     * Increments the number of games played by the player. Needs to be invoked
     * every time when a new game is played by the player.
     */    
    public void add_game_number() {
        number_of_game =number_of_game +1;
    }

    /**
     * Increments the number of games won by the player. Needs to be invoked
     * every time when a game is won by the player.
     */    
    public void add_win_number() {
        number_of_won = number_of_won +1;
    }
    
    /**
     * Computes and returns the win rate of the given player so far.
     * @return double win rate
     */    
    public double win_rate() {
        if (number_of_game == 0) return 0.0;
        return ((double)number_of_won/ number_of_game);
    }
    
    /**
     * Takes in two parameters, String new_family_name and new_given_name, and
     * changes the player's information accordingly.
     * @param String new_family_name, new_given_name
     */    
    public void change_name(String new_family_name, String new_given_name) {
        family_name = new_family_name;
        given_name = new_given_name;
    }
    
    /**
     * Resets the player's play history by setting the number of games played
     * and number of games won to 0.
     */    
    public void reset() {
        number_of_game = 0;
        number_of_won = 0;
    }
    
    /**
     * Returns a String representation of the player's information and play history.
     * @return String string representation of the player
     */    
    public String toString() {
        return(username + "," + given_name + "," + family_name + "," 
                + number_of_game + " games" +"," +number_of_won + " wins");
    }
    
    /**
     * An abstract method that is intended to return the number of stones to 
     * remove at the given round.
     * @return int number_To_Remove
     */
    abstract int removeStone(int currentstone, int M, Scanner s);
    
    /**
     * An uimplemented method that represents the advancedMove method for the NimHumanPlayer calss.
     * @param boolean[] available,Scanner s
     * @return String
     */
    public String advancedMove(boolean[] available,Scanner s){
        return "";
    }
    
    /**
     * An uimplemented method that represents the advancedMove method for the NimAIPlayer calss.
     * @param boolean[] available,String lastMove
     * @return String
     */    
    public String advancedMove(boolean[] available,String lastMove){
        return "";
    }
}
