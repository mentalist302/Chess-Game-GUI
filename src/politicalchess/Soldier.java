package politicalchess;

/*

This is the Soldier class which will be extended by all the soldiers pieces.

Created by: Mohamed Soliman --- ID: 17966266

*/

public abstract class Soldier
{
    //is the soldier alive or not
    private boolean killed;
    //color of the soldier
    private boolean white;
    //Shape of the soldier if white
    private String shape;
    //Shape of the soldier if black
    private String shapeTwo;
    
    //constructor to intialize the color of the soldier
    public Soldier(boolean white, String shape, String shapeTwo) 
    { 
        this.setWhite(white);
        
        this.shape = shape;
        this.shapeTwo = shapeTwo;
        
        
    }
    
    //get and sets 
    public boolean isWhite() 
    { 
        return this.white; 
    } 
  
    public void setWhite(boolean white) 
    { 
        this.white = white; 
    } 
  
    public boolean isKilled() 
    { 
        return this.killed == true; 
    } 
  
    public void setKilled(boolean killed) 
    { 
        this.killed = killed; 
    } 

    public String getShape()
    {
        return shape;
        
    }

    public void setShape(String shape) 
    {
        this.shape = shape;
    }
    
    public String getShapeTwo()
    {
        return shapeTwo;
        
    }

    public void setShapeTwo(String shapeTwo) 
    {
        this.shapeTwo = shapeTwo;
    }    
    
    //This function will return true if the soldier can move and false if the soldier can't move and it will be overridden by the soldiers
    public abstract boolean canMove(ChessBoard board, Square start, Square end);
}
