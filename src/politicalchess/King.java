package politicalchess;

import static java.lang.Math.sqrt;

/*

This is the King class which extends the soldier class.

Created by: Mohamed Soliman --- ID: 17966266

*/

public class King extends Soldier
{
    //constructor to set the color and the shape of the king
    public King(boolean white) 
    { 
        super(white,"K", "k"); 
    }
    
    @Override
    public boolean canMove(ChessBoard board, Square start, Square end) 
    { 
        // we can't move the piece to a Spot that  
        // has a piece of the same color 
        if (end.getSoldier() != null && end.getSoldier().isWhite() == this.isWhite()) 
        { 
            return false; 
        } 
  
        int x = Math.abs(start.getX() - end.getX()); 
        int y = Math.abs(start.getY() - end.getY()); 
       
        if (sqrt(x^2 + y^2) < 1.4) 
        {
            return true; 
        } 
  
        return false;
    } 
    
}
