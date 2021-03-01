package politicalchess;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/*

This is the Gui class which is responsible for GUI version of the game.

Created by: Mohamed Soliman --- ID: 17966266

*/

public class Gui extends JPanel implements ActionListener
{
    Pictures pics = new Pictures();
    JButton[][] button = new JButton[8][8]; 
    JButton restartButton;
    JButton exitButton;
    JButton infoButton;
    JButton movesButton;
    GamePlay game = new GamePlay();   
    String nameOne = "Player One";
    String nameTwo = "Player Two";
    boolean ok = false;
    boolean change = false;
    boolean yes = false;
    private Player playerOne; 
    private Player playerTwo;
    int xOne;
    int xTwo;
    int yOne;
    int yTwo;
    int count = 0;
    JLabel one;
    JPanel chessLayout;
    JPanel buttons;
    DataBase data;
    
    //The constructor which intialaize all the buttons and the layout of the game
    public Gui()
    {
        super();
        setLayout(new BorderLayout());
        restartButton = new JButton("Restart The Game");
        exitButton = new JButton("Exit");
        infoButton = new JButton("Info");
        movesButton = new JButton("View Moves");
        chessLayout = new JPanel(new GridLayout(8,8));
        buttons = new JPanel(new GridLayout(0,4));
        String message = "Please enter Player 1 name (WHITE SIDE)";
        nameOne = JOptionPane.showInputDialog(this, message);
        if(nameOne == null)
        {
            System.exit(0);
        }
        try 
        {
            data = new DataBase();
            data.connectChessDB();
        } 
        catch (SQLException ex)
        {
            
        }
        
        String messageTwo = "Please enter Player 2 name (BLACK SIDE)";
        nameTwo = JOptionPane.showInputDialog(this, messageTwo);
        if(nameTwo == null)
        {
            System.exit(0);
        }
        
        playerOne = new Player(true, nameOne);
        playerTwo = new Player(false, nameTwo);
        
        
        
        game.initialize(playerOne, playerTwo);
        setup();
        
        for(int i = 0; i < 8; i++)
        {
            for(int j = 0; j < 8; j++)
            {
                chessLayout.add(button[i][j]);
            }
        }
        
        one = new JLabel("Player Turn: " + nameOne);
        one.setFont(new Font("Serif", Font.PLAIN, 20));
        buttons.add(infoButton);
        buttons.add(movesButton);
        buttons.add(restartButton);
        buttons.add(exitButton);
        add(one, BorderLayout.NORTH);
        add(chessLayout, BorderLayout.SOUTH);
        add(buttons, BorderLayout.CENTER);
        
    }
    
    
    //this method intialize the grid of the chess
    public void setup()
    {
        for(int i = 0; i < 8; i++)
        {
            for(int j = 0; j < 8; j++)
            {
                button[i][j] = new JButton();
                button[i][j].addActionListener(this);
            }
        }
        
        restartButton.addActionListener(this);
        exitButton.addActionListener(this);
        infoButton.addActionListener(this);
        movesButton.addActionListener(this);
        
        update();
    }
    
    //this method updates the grid everytime a player made a move
    public void update()
    {
        for(int i = 0; i < 8; i++)
        {
            for(int j = 0; j < 8; j++)
            {
                
                if(game.board.getSquare(i, j).getSoldier() == null)
                {
                    button[i][j].setIcon(null);
                }
                else if(game.board.getSquare(i, j).getSoldier() instanceof Pawn && game.board.getSquare(i, j).getSoldier().isWhite())
                {
                    button[i][j].setIcon(pics.pawnWhite);
                }
                else if(game.board.getSquare(i, j).getSoldier() instanceof Pawn && !game.board.getSquare(i, j).getSoldier().isWhite())
                {
                    button[i][j].setIcon(pics.pawnBlack);
                }
                else if(game.board.getSquare(i, j).getSoldier() instanceof Rook && game.board.getSquare(i, j).getSoldier().isWhite())
                {
                    button[i][j].setIcon(pics.rookWhite);
                }
                else if(game.board.getSquare(i, j).getSoldier() instanceof Rook && !game.board.getSquare(i, j).getSoldier().isWhite())
                {
                    button[i][j].setIcon(pics.rookBlack);
                }
                else if(game.board.getSquare(i, j).getSoldier() instanceof Knight && game.board.getSquare(i, j).getSoldier().isWhite())
                {
                    button[i][j].setIcon(pics.knightWhite);
                }
                else if(game.board.getSquare(i, j).getSoldier() instanceof Knight && !game.board.getSquare(i, j).getSoldier().isWhite())
                {
                    button[i][j].setIcon(pics.knightBlack);
                }
                else if(game.board.getSquare(i, j).getSoldier() instanceof Bishop && game.board.getSquare(i, j).getSoldier().isWhite())
                {
                    button[i][j].setIcon(pics.bishopWhite);
                }
                else if(game.board.getSquare(i, j).getSoldier() instanceof Bishop && !game.board.getSquare(i, j).getSoldier().isWhite())
                {
                    button[i][j].setIcon(pics.bishopBlack);
                }
                else if(game.board.getSquare(i, j).getSoldier() instanceof Queen && game.board.getSquare(i, j).getSoldier().isWhite())
                {
                    button[i][j].setIcon(pics.queenWhite);
                }
                else if(game.board.getSquare(i, j).getSoldier() instanceof Queen && !game.board.getSquare(i, j).getSoldier().isWhite())
                {
                    button[i][j].setIcon(pics.queenBlack);
                }
                else if(game.board.getSquare(i, j).getSoldier() instanceof King && game.board.getSquare(i, j).getSoldier().isWhite())
                {
                    button[i][j].setIcon(pics.kingWhite);
                }
                else if(game.board.getSquare(i, j).getSoldier() instanceof King && !game.board.getSquare(i, j).getSoldier().isWhite())
                {
                    button[i][j].setIcon(pics.kingBlack);
                }
                
                if(ok == false)
                {
                    button[i][j].setBackground(Color.blue);
                    ok = true;
                }
                else
                {
                    button[i][j].setBackground(Color.white);
                    ok = false;
                }
            }
            if(ok == false)
            {
                ok = true;
            }
            else
            {
                ok = false;
            }
        }
    }

    //this method checks if a certain action was preformed
    @Override
    public void actionPerformed(ActionEvent e)
    {
        Object source = e.getSource();
        
            for(int i = 0; i < 8; i++)
            {
                for(int j = 0; j < 8; j++)
                {

                    if(source == button[i][j] && button[i][j].getIcon() == null && count == 1)
                    {
                       button[i][j].setBackground(Color.red);
                       xTwo = i;
                       yTwo = j;
                       count++;
                    }

                    else if(source == button[i][j] && button[i][j].getIcon() != null && game.board.getSquare(i, j).getSoldier().isWhite() && count == 0)
                    {
                       button[i][j].setBackground(Color.red);
                       xOne = i;
                       yOne = j;
                       change = true;
                       count++;
                    }
                    else if(source == button[i][j] && button[i][j].getIcon() != null && game.board.getSquare(i, j).getSoldier().isWhite() && count == 1)
                    {
                       button[i][j].setBackground(Color.red);
                       xTwo = i;
                       yTwo = j;
                       count++;
                    }
                    else if(source == button[i][j] && button[i][j].getIcon() != null && !game.board.getSquare(i, j).getSoldier().isWhite() && count == 0)
                    {
                       button[i][j].setBackground(Color.red);
                       xOne = i;
                       yOne = j;
                       change = false;
                       count++;
                    }
                    else if(source == button[i][j] && button[i][j].getIcon() != null && !game.board.getSquare(i, j).getSoldier().isWhite() && count == 1)
                    {
                       button[i][j].setBackground(Color.red);
                       xTwo = i;
                       yTwo = j;
                       count++;
                    }
                }
            }

            if(change == true && count == 2 && !game.isEnd())
            {
               game.makeMove(playerOne, xOne, yOne, xTwo, yTwo);
               
               update();
               count = 0;
            }
            
            else if(change == false && count == 2 && !game.isEnd())
            {
                game.makeMove(playerTwo, xOne, yOne, xTwo, yTwo);
                update();
                count = 0;
            }
            
            if(game.isEnd() == true && yes == false)
            {
                String s = "Game Over! " + game.currentTurn.name + " WON!";
                JOptionPane.showMessageDialog(null, s);
                update();
                yes = true;
            }
            
            if(source == restartButton)
            {
                restart();
            }
            
            if(source == exitButton)
            {
                int dialogResult = JOptionPane.showConfirmDialog (null, "Would You Like to exit???","Warning",0);
                if(dialogResult == JOptionPane.YES_OPTION)
                {
                  System.exit(0);
                }
            }
            
            if(source == infoButton)
            {
                try 
                {
                    String m = data.print();
                    JOptionPane.showMessageDialog(null, m);
                    
                }
                catch (SQLException ex)
                {
                    
                }
            }
            
            if(source == movesButton)
            {
                try 
                {
                    String z = data.printMoves();
                    JOptionPane.showMessageDialog(null, z);
                } 
                catch (SQLException ex) 
                {
                    
                }
            }
            
            one.setText("Player Turn: " + game.currentTurn.name);
    }
    
    //this method restarts the game when the restart button is pressed
    public void restart()
    {
        int dialogResult = JOptionPane.showConfirmDialog (null, "Are you sure you want to restart??","Warning",0);
        if(dialogResult == JOptionPane.YES_OPTION)
        {
            String message = "Please enter Player 1 name (WHITE SIDE)";
            nameOne = JOptionPane.showInputDialog(this, message);
            if(nameOne == null)
            {
                return;
            }

            String messageTwo = "Please enter Player 2 name (BLACK SIDE)";
            nameTwo = JOptionPane.showInputDialog(this, messageTwo);
            if(nameTwo == null)
            {
                return;
            }
            game = new GamePlay();

            playerOne = new Player(true, nameOne);
            playerTwo = new Player(false, nameTwo);

            game.initialize(playerOne, playerTwo);
            update();
            yes = false;
        }
        
    }
    
    //the main method of the gui
    public static void main(String[] args)
    {
        Gui myPanel = new Gui();
        JFrame frame = new JFrame("Political Chess");	
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.getContentPane().add(myPanel);
        frame.pack();
        frame.setResizable(false);
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(new Point((d.width / 2) - (frame.getWidth() / 2), (d.height / 2) - (frame.getHeight() / 2)));
        frame.setVisible(true);
    }
}