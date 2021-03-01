package politicalchess;


/*

This is the Bishop class which extends the soldier class.

Created by: Mohamed Soliman --- ID: 17966266

*/

public class Bishop extends Soldier
{
    
    //sets the color and shape of the Bishop
    public Bishop(boolean white) 
    { 
        super(white, "B", "b"); 
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
        
        
        //because the bishop can only move in diagonal matter
        if(Math.abs(x) == Math.abs(y))
        {
            return true;
        }
        
        return false; 
    }        
    
}
