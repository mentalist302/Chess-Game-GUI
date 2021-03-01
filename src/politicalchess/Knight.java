package politicalchess;

/*

This is the Knight class which extends the soldier class.

Created by: Mohamed Soliman --- ID: 17966266

*/

public class Knight extends Soldier
{
    //sets the color and the shape of the Knight
    public Knight(boolean white) 
    { 
        super(white,"N", "n"); 
    } 
    
    @Override
    public boolean canMove(ChessBoard board, Square start, Square end) 
    { 
        // we can't move the piece to a spot that has 
        // a piece of the same colour 
        if (end.getSoldier() != null && end.getSoldier().isWhite() == this.isWhite()) 
        { 
            return false; 
        } 
  
        int x = Math.abs(start.getX() - end.getX()); 
        int y = Math.abs(start.getY() - end.getY()); 
        
        return x * y == 2; 
    } 

} 
    

