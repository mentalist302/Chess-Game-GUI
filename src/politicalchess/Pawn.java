package politicalchess;

/*

This is the Pawn class which extends the soldier class.

Created by: Mohamed Soliman --- ID: 17966266

*/

public class Pawn extends Soldier
{
    private int startNum = 0;
    
    //sets the color and the shape of the Pawn
    public Pawn(boolean white) 
    {
        super(white,"P", "p"); 
        
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
  
        int x = start.getX() - end.getX(); 
        int y = start.getY() - end.getY(); 
        
        //check if the pawn will move twice only in the first movement
        if(this.isWhite() && (x == -2 && y==0) && (startNum == 0) && end.getSoldier() == null)
        {
            startNum = startNum + 1;
            return true;
        }
        
        if(!this.isWhite() && (x == 2 && y==0) && (startNum == 0) && end.getSoldier() == null)
        {
            startNum = startNum + 1;
            return true;
        }
        
        //movements of the pawn
        if(end.getSoldier() !=null && end.getSoldier().isWhite() != this.isWhite() && this.isWhite() && x == -1 && (y == 1 || y == -1))
        {
            startNum = startNum + 1;
            return true;
        }
        
        if(end.getSoldier() !=null && end.getSoldier().isWhite() != this.isWhite() && !this.isWhite() && x == 1 && (y == 1 || y == -1))
        {
            startNum = startNum + 1;
            return true;
        }
        
        if((this.isWhite() && (x != -1 || y !=0)) || end.getSoldier() != null)
        {
            return false;
        }
        
        if((!this.isWhite() && (x != 1 || y !=0)) || end.getSoldier() != null)
        {
            return false;
        } 
        
        startNum = startNum + 1;
        return true; 
    } 
    
}
