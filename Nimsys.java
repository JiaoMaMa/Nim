import java.util.Scanner;
import java.io.*;
/**
 * This program keeps a collection of player. This collection allow new players
 * to be added and existing players to be removed,edited or rest. User can initiate 
 * a gameplay between any two existing players. User can also display player
 * information and see the rankings of win rate.
 * @author Christine Kneer
 * @version 05/24/2018
 */
public class Nimsys {
    private static final int DEFAULT_VALUE = 100;  
    private static NimPlayer[] players = new NimPlayer[DEFAULT_VALUE];
    private static int number_of_players = 0;
    /**
     * Takes three parameter, String username, family_name and given_name,
     * creates a new NimHumanPlayer object and adds it into the collection of players
     * if not already exists.
     * @param STring username, family_name, given_name
     */
    public static void addplayer(String username, String family_name, String given_name) {
        NimPlayer player = new NimHumanPlayer(username,family_name, given_name);
        if (username_index(username) >=0) {
            System.out.println("The player already exists.");
            }
        else {
            players[number_of_players] = player;
            number_of_players ++;
            }
    }
               
    /**
     * Takes three parameter, String username, family_name and given_name,
     * creates a new NimAIPlayer object and adds it into the collection of players
     * if not already exists.
     * @param STring username, family_name, given_name
     */    
    public static void addaiplayer(String username, String family_name, String given_name) {
        NimPlayer player = new NimAIPlayer(username,family_name, given_name);
        if (username_index(username) >=0) {
            System.out.println("The player already exists.");
            }
        else {
            players[number_of_players] = player;
            number_of_players ++;
            } 
    }

    /**
     * Removes all players in the collection.
     */
    public static void removeplayer() {
        players = new NimPlayer[DEFAULT_VALUE];
        number_of_players = 0;
        System.out.println();
        System.out.print("$");}

    /**
     * Removes the player with the given username (if exists) in the collection.
     * @param String username
     */
    public static void removeplayer(String username) {
        if(username_index(username) == -1)
        {System.out.println("The player does not exist.");
        System.out.println();
        System.out.print("$");
        }
        else {
            players[username_index(username)] = players[number_of_players - 1];
            number_of_players = number_of_players -1;
            System.out.println();
            System.out.print("$");
        }
    }
    
    /**
     * Takes three parameter String username, new_family_name and new_given_name,
     * and edits an exisiting player's information accordingly.
     * @param String username, new_family_name, new_given_name
     */
    public static void editplayer(String username, String new_family_name, String new_given_name) {
        if(username_index(username) == -1){
            System.out.println("The player does not exist.");
            System.out.println();
            System.out.print("$");
        }else {
            players[username_index(username)].change_name(new_family_name, new_given_name);
            System.out.println();
            System.out.print("$");
        }
    }
    
    /**
     * Resets the player's play history if the player exists.
     * @param String username
     */
    public static void resetstats(String username) {
        if(username_index(username) == -1){
            System.out.println("The player does not exist.");
            System.out.println();
            System.out.print("$");
        }else {
            players[username_index(username)].reset();
            System.out.println();
            System.out.print("$");}
    }
    
    /**
     * Resets all players' play history.
     */
    public static void resetstats() {
        int i;
        for (i = 0; i < number_of_players; i++)
            players[i].reset();
        System.out.println();
        System.out.print("$");
    }

    /**
     * Displays all player's information in a nicely formatted String.
     */
    public static void displayplayer() {
        int i;
        sortForname(players);
        for(i = number_of_players-1; i >= 0; i--) 
            System.out.println(players[i].toString());
        System.out.println();
        System.out.print("$");
    }
    
    /**
     * Takes a parameter, String username, and displays the player's information
     * if exists.
     */
    public static void displayplayer(String username) {
        if(username_index(username) == -1)
        {System.out.println("The player does not exist.");
        System.out.println();
        System.out.print("$");
        }
        else {
            System.out.println(players[username_index(username)].toString());
            System.out.println();
            System.out.print("$");
        }
    }


    /**
     * Displays the rankings of all players' win rate in ascending or descending order.
     * @param String order
     */
    public static void rankings(String order){
        //asc
        if (order.equals("asc")){
            sort(players,"asc");
            int i;
            for(i=0; i<number_of_players;i++) {
                System.out.println(printranking(players[i]));
                }
            System.out.println();
        }        
        //decs
        else {
            sort(players,"desc");
            int i;            
            for(i=number_of_players-1;i>=0;i--) {
                System.out.println(printranking(players[i]));
            }
        }
        System.out.println();
        System.out.print("$");
    }
    
    /**
     * A helper method that is used to print the winrate in rankings method.
     * @param NimPlayer p
     */
    private static String printranking(NimPlayer p){
        int winrate = (int)Math.round(p.win_rate() * 100.0);
        String space;
        if (p.win_rate() == 1.0){
            space = " ";
        } else if (p.win_rate() < 0.1){
            space = "   ";
        }else{
            space = "  ";
        }
        String result = winrate + "%" + space + "| 0" +p.getNumber_of_game()
              +" games | " + p.getGiven_name() +" "+ p.getFamily_name();
        return result;
    }
    
    /**
     * Exits the program.
     */
    public static void exit() {
        System.out.println();
        System.exit(0);
    }


    /**
     * Helper method that takes a parameter, String username, and returns the index
     * of the player in the collection if exists, -1 if not exists.
     * @param String username
     * @return int -1 if player not exists, index of the player in the collection if exists
     */
    public static int username_index(String username) {
        int i;
        for(i = 0; i < number_of_players;i++)
            if (players[i].getUsername().equals(username)) {
                return i;}
        return -1;
    }

    /**
     * Helper method that sorts the player collection with a given number of players
     * by each player's win rate in increasing order.
     */
    public static void sort(NimPlayer[] a,String order) {        
        int min;
        boolean sortname;
        for (int index = 0; index < number_of_players - 1; index++){
            min = index;
            for (int scan=index+1;scan < number_of_players; scan++){
                String username1 = a[scan].getUsername().toLowerCase();
                String username2 = a[min].getUsername().toLowerCase();
                if (order.equals("asc")){
                    sortname = username1.compareTo(username2) < 0;
                }else{
                    sortname = username1.compareTo(username2) > 0;
                }
                if ((a[scan].win_rate() <  a[min].win_rate())||
                ((a[scan].win_rate() ==  a[min].win_rate())&&sortname)){                 
                   min = scan;
                }
            }
            interchange(index,min,a);
        }
    }

    /**
     * Sort all players in the alphabethical order of their username.
     * @param NimPlayer[] a
     */
    public static void sortForname(NimPlayer[] a) {
        int index, indexOfNextSmallestName;
        for (index = 0; index < number_of_players - 1; index++)
        {
            indexOfNextSmallestName =
                    indexOfSmallestName(index, a, number_of_players);
            interchange(index,indexOfNextSmallestName, a);
        }
    }
      
    /**
     * Helper method that is used to keep track of the index of the smallest
     * name in the partially sorted array.
     * @param int startIndex, NimPlayer[] a, int number_of_players
     */
    private static int indexOfSmallestName(int
            startIndex, NimPlayer[] a, int number_of_players)
    {
        String name = a[startIndex].getUsername();
        int indexOfMin = startIndex;
        int index;
        for (index = startIndex + 1;
                index < number_of_players; index++)
            if (a[index].getUsername().compareTo(name)> 0)
            {
                name = a[index].getUsername();
                indexOfMin = index;
            }
        return indexOfMin;
    }
    
    /**
     * Helper method that swaps the two players at the given indices in a given 
     * players collection.
     * @param int index1,index2
     */
    private static void interchange(int i, int j,
            NimPlayer[] a)
    {
        NimPlayer temp;
        temp = a[i];
        a[i] = a[j];
        a[j] = temp; //original value of a[i]
    }
    
    /**
     * Saves the user statistics.
     */
    public static void save(){
        try{
            File file = new File("players.dat.txt");
            PrintWriter writer = new PrintWriter(file);
            for(int i=0;i<number_of_players;i++){
                String status;
                if (players[i].getClass() == NimHumanPlayer.class){
                    status = "human";//if a human player
                }else{
                    status = "ai";//if an ai player
                }
                writer.println(players[i].toString() + "," + status);
            }
            writer.close();
        } catch (IOException e){}
    }
    
    /**
     * Reads the user statistics.
     */
    public static void read(){
        try{
            File file = new File("players.dat.txt");
            BufferedReader s = new BufferedReader(new FileReader(file));
            int i = 0;
            String temp = s.readLine();
            while (temp!=null){
                String[] array = temp.split(",");
                if (array[5].equals("human")){//if a human player
                    addplayer(array[0],array[2],array[1]);
                }else{//if an ai player
                    addaiplayer(array[0],array[2],array[1]);
                }
                int w = Integer.parseInt(array[3].split(" ")[0]);
                int n = Integer.parseInt(array[4].split(" ")[0]);
                players[i].set_game_number(w);
                players[i].setNumber_of_won(n);
                i++;
                temp = s.readLine();
            }
            s.close();
        } catch (IOException e){}
    }
    

    enum cmd {addplayer,removeplayer,editplayer,resetstats,displayplayer,rankings,startgame,exit,
                        addaiplayer,startadvancedgame}


    /**
     * Prompts the user to give commands to the program and performs actions according
     * to the user's commands.
     */
    public static void main(String[] args)
    {
        Scanner keyboard = new Scanner(System.in);
        String answer;
        cmd operation = null;
        String arg;
        String[] array;

        System.out.println("Welcome to Nim");
        System.out.println();       
        read();
        System.out.print("$");
        
        while(true)
        {
            answer = keyboard.next();
            try{
            operation = cmd.valueOf(answer);
            switch(operation)
            {
                case exit:
                    save();
                    exit();
                case addplayer:
                    //array->{String username,String family_name, String given_name}
                    arg = keyboard.nextLine().trim();
                    array = arg.split(",");
                    addplayer(array[0],array[1],array[2]);
                    System.out.println();
                    System.out.print("$");
                    break;
                case addaiplayer:
                    //array->{String username,String family_name, String given_name}
                    arg = keyboard.nextLine().trim();
                    array = arg.split(",");
                    addaiplayer(array[0],array[1],array[2]);
                    System.out.println();
                    System.out.print("$");
                    break;
                case removeplayer:
                    //array-> {String username}
                    arg = keyboard.nextLine().trim();
                    if(arg.length() >=1) {
                        removeplayer(arg);}
                    else {
                        System.out.println("Are you sure you want to remove all players? (y/n)");
                        if(keyboard.nextLine().equals("y")) {
                            removeplayer();}}
                    break;
                case editplayer:
                    //array->{String username,String new_family_name, String new_given_name}
                    arg = keyboard.nextLine().trim();
                    array = arg.split(",");
                    Nimsys.editplayer(array[0],array[1],array[2]);
                    break;
                case resetstats:
                    arg = keyboard.nextLine().trim();
                    if(arg.length()>=1) {
                        resetstats(arg);}
                    else {
                        System.out.println("Are you sure you want to reset all player statistics? (y/n)");
                        if(keyboard.nextLine().equals("y"))
                        {
                            resetstats();
                        }   
                    }
                    break;
                case displayplayer:
                    arg = keyboard.nextLine().trim();
                    if(arg.length()>=1) {
                        displayplayer(arg);
                    }
                    else {
                        displayplayer();
                    }
                    break;
                case rankings:
                    arg = keyboard.nextLine().trim();
                    Nimsys.rankings(arg);
                    break;
                case startgame:
                    arg = keyboard.nextLine().trim();
                    array = arg.split(",");
                    if(username_index(array[2]) == -1 || username_index(array[3]) == -1) {
                        //check whether one of the player doesn't exist
                        System.out.println("One of the players does not exist.");
                        System.out.println();
                        System.out.print("$");
                    }else{
                        NimPlayer player1 =players[username_index(array[2])];
                        NimPlayer player2 =players[username_index(array[3])];
                        Game n = new NimGame(player1,player2,Integer.parseInt(array[0]),
                        Integer.parseInt(array[1]),keyboard);
                    }
                    break;
                case startadvancedgame:
                    arg = keyboard.nextLine().trim();
                    array = arg.split(",");
                    if(username_index(array[1]) == -1 || username_index(array[2]) == -1) {
                        //check whether one of the player doesn't exist
                        System.out.println("One of the players does not exist.");
                        System.out.println();
                        System.out.print("$");
                    }else{
                        NimPlayer player1 =players[username_index(array[1])];
                        NimPlayer player2 =players[username_index(array[2])];
                        Game n = new AdvancedNimGame(player1,player2,Integer.parseInt(array[0]),keyboard);
                    }
                    break;
            }
            //catch IllegalArgumentException first
            }catch (IllegalArgumentException e){
                System.out.println("'" + answer + "'" + " is not a valid command.");
                System.out.println();
                System.out.print("$");
                keyboard.nextLine();
            //then catch ArrayIndexOutOfBoundsException
            }catch (ArrayIndexOutOfBoundsException e){
                System.out.println("Incorrect number of arguments supplied to command.");
                System.out.println();
                System.out.print("$");      
            }
        }
    }
}


