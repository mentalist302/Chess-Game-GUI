package politicalchess;

/*

This is the ChessBoard class which is responsible to intialize the board and the soldiers to their start places.

Created by: Mohamed Soliman --- ID: 17966266

*/

public class ChessBoard 
{
    //Square array
    Square[][] squares;
    
    //constructor to reset the Chess Board
    public ChessBoard() 
    { 
        squares = new Square[8][8];
    }
    
    //get the current square
    public Square getSquare(int x, int y) 
    {
        return squares[x][y]; 
    } 
    
    //reset the soldiers to their start squares
    public void reset() 
    { 
        // initialize white pieces 
        squares[0][0] = new Square(0, 0, new Rook(true)); 
        squares[0][1] = new Square(0, 1, new Knight(true)); 
        squares[0][2] = new Square(0, 2, new Bishop(true));
        squares[0][3] = new Square(0, 3, new Queen(true));
        squares[0][4] = new Square(0, 4, new King(true));
        squares[0][5] = new Square(0, 5, new Bishop(true));
        squares[0][6] = new Square(0, 6, new Knight(true));
        squares[0][7] = new Square(0, 7, new Rook(true));
        
        //Pawns
        squares[1][0] = new Square(1, 0, new Pawn(true)); 
        squares[1][1] = new Square(1, 1, new Pawn(true)); 
        squares[1][2] = new Square(1, 2, new Pawn(true)); 
        squares[1][3] = new Square(1, 3, new Pawn(true)); 
        squares[1][4] = new Square(1, 4, new Pawn(true)); 
        squares[1][5] = new Square(1, 5, new Pawn(true)); 
        squares[1][6] = new Square(1, 6, new Pawn(true)); 
        squares[1][7] = new Square(1, 7, new Pawn(true));  
  
        // initialize black pieces 
        squares[7][0] = new Square(7, 0, new Rook(false)); 
        squares[7][1] = new Square(7, 1, new Knight(false)); 
        squares[7][2] = new Square(7, 2, new Bishop(false)); 
        squares[7][3] = new Square(7, 3, new Queen(false)); 
        squares[7][4] = new Square(7, 4, new King(false)); 
        squares[7][5] = new Square(7, 5, new Bishop(false)); 
        squares[7][6] = new Square(7, 6, new Knight(false)); 
        squares[7][7] = new Square(7, 7, new Rook(false)); 
        
        //Pawns
        squares[6][0] = new Square(6, 0, new Pawn(false)); 
        squares[6][1] = new Square(6, 1, new Pawn(false)); 
        squares[6][2] = new Square(6, 2, new Pawn(false));
        squares[6][3] = new Square(6, 3, new Pawn(false));
        squares[6][4] = new Square(6, 4, new Pawn(false));
        squares[6][5] = new Square(6, 5, new Pawn(false));
        squares[6][6] = new Square(6, 6, new Pawn(false));
        squares[6][7] = new Square(6, 7, new Pawn(false));
  
        // initialize remaining boxes without any piece 
        for (int i = 2; i < 6; i++)
        { 
            for (int j = 0; j < 8; j++)
            { 
                squares[i][j] = new Square(i, j, null); 
            } 
        }
    
    } 
    
    //the to string method will return the first shape of the chessbaord
    public String toString()
    {
        String s = "";
	s += "   A B C D E F G H\n";
	s += "  +---------------+\n";
	for (int y=0; y<8; y++) 
        {
	    s += (y+1) + " |";
	    for (int x=0; x<8; x++) 
            {

                    if(squares[y][x].getSoldier() != null && squares[y][x].getSoldier().getShape() != null && squares[y][x].getSoldier().isWhite())
                    {
                        s += squares[y][x].getSoldier().getShape();
                    }
                    else if(squares[y][x].getSoldier() != null && squares[y][x].getSoldier().getShapeTwo() != null && !squares[y][x].getSoldier().isWhite())
                    {
                        s += squares[y][x].getSoldier().getShapeTwo();
                    }
                    else
                    {
                       s += " "; 
                    }

		if (x<7) s += " ";
            }
            
	    s += "| " + (y+1);
	    s += "\n";
	}
	s += "  +---------------+\n";
	s += "   A B C D E F G H\n";
        
        return s;
    }
    
}



                
