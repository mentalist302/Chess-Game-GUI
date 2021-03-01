package politicalchess;

/*

This is the Move class which is responsible for the movements of the soldiers.

Created by: Mohamed Soliman --- ID: 17966266

*/

public class Move 
{
    //the player
    private Player player;
    //the starting square
    private Square start;
    //the ending square
    private Square end;
    //the soldier that moved
    private Soldier soldierMoved;
    //the soldier that was killed
    private Soldier soldierKilled;
    
    //constructor
    public Move(Player player, Square start, Square end) 
    { 
        this.player = player; 
        this.start = start; 
        this.end = end; 
        this.soldierMoved = start.getSoldier(); 
    }

    public Square getStart() 
    {
        return start;
    }

    public void setStart(Square start)
    {
        this.start = start;
    }

    public Square getEnd()
    {
        return end;
    }

    public void setEnd(Square end) 
    {
        this.end = end;
    }

    public Soldier getSoldierKilled()
    {
        return soldierKilled;
    }

    public void setSoldierKilled(Soldier soldierKilled)
    {
        this.soldierKilled = soldierKilled;
    }  
    
    //to string so we can output the movement later
    public String toString()
    {
        return "Moved from (" + (start.getX()+1) + ", " + (start.getY()+1) + ") ------ Moved to (" + (end.getX()+1) + ", " + (end.getY()+1) + ")"; 
    }
    
}