package politicalchess;

/*

This is the Square class which is responsible for the sqaures of the board.

Created by: Mohamed Soliman --- ID: 17966266

*/

public class Square 
{
    //x position of the soldier
    private int x; 
    //y position of the soldier
    private int y; 
    //the soldier at the square
    private Soldier soldier; 
    
    //constructor to intialize the X and Y of a particular soldier
    public Square(int x, int y, Soldier soldier) 
    {
        this.setX(x); 
        this.setY(y); 
        this.soldier = soldier;
    }
    
    //get and sets
    public Soldier getSoldier() 
    { 
        return this.soldier; 
    } 
  
    public void setSoldier(Soldier soldier) 
    { 
        this.soldier = soldier; 
    }
    
    public int getX() 
    { 
        return this.x; 
    } 
  
    public void setX(int x) 
    { 
        this.x = x; 
    } 
  
    public int getY() 
    { 
        return this.y; 
    } 
  
    public void setY(int y) 
    { 
        this.y = y; 
    } 
  
    
}
