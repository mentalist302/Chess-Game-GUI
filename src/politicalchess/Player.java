package politicalchess;

/*

This is the Player class which is responsible for the players.

Created by: Mohamed Soliman --- ID: 17966266

*/

public class Player
{
    //is the player white or black?
    private boolean whiteSide;
    //the name of the player 
    public String name; 
    
    //constructor to intialize the color of the player
    public Player(boolean whiteSide, String name) 
    { 
        this.whiteSide = whiteSide; 
        this.name = name;
    } 
    
    //get
    public boolean isWhiteSide() 
    { 
        return this.whiteSide == true; 
    }
    
    
    
    
}
