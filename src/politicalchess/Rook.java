package politicalchess;

import java.util.ArrayList;

/*

This is the rook class which extends the soldier class.

Created by: Mohamed Soliman --- ID: 17966266

*/

public class Rook extends Soldier
{
    ArrayList<Soldier> array = new ArrayList();
    
    //sets the color and the shape of the Rook
    public Rook(boolean white) 
    { 
        super(white,"R", "r"); 
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
        
        
        if(y == 0 && (start.getX() != end.getX()))
        {
            return true;
            
        }
        else if(x == 0 && (start.getY() != end.getY()))
        {
            return true;
        }
        
        
        return false; 
    } 
    
}
