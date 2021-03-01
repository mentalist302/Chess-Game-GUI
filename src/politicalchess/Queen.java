package politicalchess;

/*

This is the Queen class which extends the soldier class.

Created by: Mohamed Soliman --- ID: 17966266

*/

public class Queen extends Soldier
{
    //sets the color and the shape of the Queen
    public Queen(boolean white) 
    { 
        super(white,"Q", "q"); 
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
        
        if(y == 0&& (start.getX() != end.getX()))
        {
            return true;
        }
        if(x == 0 && (start.getY() != end.getY()))
        {
            return true;
        }
        if(Math.abs(x) == Math.abs(y))
        {
            return true;
        }
        
        return false;
    }
    
}
