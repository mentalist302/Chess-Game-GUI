package politicalchess;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/*

This is the GamePlay class which is responsible for the whole gameplay of the game.

Created by: Mohamed Soliman --- ID: 17966266

 */
public class GamePlay {

    private Player playerOne;
    private Player playerTwo;
    public static ChessBoard board;
    public Player currentTurn;
    public boolean end;
    PrintWriter pw = null;
    FileWriter fstream;
    BufferedWriter out;
    DataBase data;

    //constructor 
    public GamePlay() {
        this.board = new ChessBoard();
        try {
            data = new DataBase();
            data.connectChessDB();
            data.delete();
        } catch (SQLException ex) {

        }
    }

    //get
    public boolean isEnd() {
        return this.end;
    }

    //this method will change the user input from a letter to a number so it can access the square array
    public int letterToNumber(String string) {
        if (string.equalsIgnoreCase("a")) {
            return 1;
        } else if (string.equalsIgnoreCase("b")) {
            return 2;
        } else if (string.equalsIgnoreCase("c")) {
            return 3;
        } else if (string.equalsIgnoreCase("d")) {
            return 4;
        } else if (string.equalsIgnoreCase("e")) {
            return 5;
        } else if (string.equalsIgnoreCase("f")) {
            return 6;
        } else if (string.equalsIgnoreCase("g")) {
            return 7;
        } else if (string.equalsIgnoreCase("h")) {
            return 8;
        }

        return 9;
    }

    //intialize the game
    public void initialize(Player p1, Player p2) {
        playerOne = p1;
        playerTwo = p2;
        end = false;

        board.reset();
        System.out.println(board.toString());
        System.out.println("");
        System.out.println(p1.name + " is on top");
        System.out.println("");
        System.out.println(p2.name + " is down");
        System.out.println("");

        if (p1.isWhiteSide()) {
            this.currentTurn = p1;
            System.out.println("It is " + p1.name + "'s turn");
        } else {
            this.currentTurn = p2;
            System.out.println("It is " + p2.name + "'s turn");
        }

    }

    //that will be called when a user wants to make a move
    public boolean makeMove(Player player, int startX, int startY, int endX, int endY) {

        Square startBox = board.getSquare(startX, startY);
        Square endBox = board.getSquare(endX, endY);
        Move move = new Move(player, startBox, endBox);
        return Generate(move, player);
    }

    //this method will generate the move and check if the move can happen or no.
    public boolean Generate(Move move, Player player) {
        Soldier soldier = move.getStart().getSoldier();

        //is the square that the user chose empty?
        if (soldier == null) {
            System.out.println("EMPTY SQUARE");
            playSound("one.wav");
            return false;
        }

        //is the movement of the user for the required soldier right??
        if (!soldier.canMove(board, move.getStart(), move.getEnd())) {
            System.out.println("Illeagl Move, Go learn Chess Please");
            playSound("one.wav");
            return false;
        }

        //The user shouldn't be able to access other color soldiers
        if (soldier.isWhite() != currentTurn.isWhiteSide()) {
            System.out.println("THAT IS NOT YOUR SOLDIER OMG =_=");
            playSound("one.wav");
            return false;
        }

        Soldier killedSoldier = move.getEnd().getSoldier();

        //was there a kill in that move?
        if (killedSoldier != null) {
            killedSoldier.setKilled(true);
            move.setSoldierKilled(killedSoldier);

            if (this.currentTurn == playerOne) {
                System.out.println("The " + killedSoldier.getShape() + " of " + playerTwo.name + " has been killed!!");

            } else {
                System.out.println("The " + killedSoldier.getShape() + " of " + playerOne.name + " has been killed!!");
            }
            playSound("dead.wav");
        }

        try {
            fstream = new FileWriter("input.txt", true);
            out = new BufferedWriter(fstream);

            out.write("Move of player " + currentTurn.name + " = " + move.toString());

            out.newLine();

        } catch (FileNotFoundException e) {

        } catch (IOException e) {

        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {

            }
        }

        
        //add the move to the moves table
        String x = "Move of player " + currentTurn.name + " = " + move.toString();
        
        try {
            data.add("VALUES ('" + x + "')");
        } catch (SQLException ex) {

        }

        //make the actual move
        move.getEnd().setSoldier(move.getStart().getSoldier());
        move.getStart().setSoldier(null);

        //is the killed soldier the king? if yes, end the game!
        if (killedSoldier != null && killedSoldier instanceof King) {
            if (player.isWhiteSide()) {
                System.out.println(playerOne.name + " won!!!!!");
                System.out.println("Hard Luck " + playerTwo.name);
            } else {
                System.out.println(playerTwo.name + " won!!!!!");
                System.out.println("Hard Luck " + playerOne.name);
            }
            playSound("end.wav");
            this.end = true;
        }

        //change the turn of the game
        if (this.currentTurn == playerOne && !end) {
            this.currentTurn = playerTwo;
            System.out.println("It is " + playerTwo.name + "'s turn");

        } else if (this.currentTurn == playerTwo && !end) {
            this.currentTurn = playerOne;
            System.out.println("It is " + playerOne.name + "'s turn");
        } else {
            System.out.println("HOPE YOU HAD FUN YAYAYA!");
            System.out.println("That is how the board looks like now!");
            try 
            {
                data.delete();
            }
            catch(SQLException ex) 
            {
                
            }
        }

        System.out.println(toString());
        playSound("move.wav");

        return true;
    }

    //the to string will update the board after each turn
    public String toString() {
        String s = "";
        s += "   A B C D E F G H\n";
        s += "  +---------------+\n";
        for (int y = 0; y < 8; y++) {
            s += (y + 1) + " |";
            for (int x = 0; x < 8; x++) {

                if (board.getSquare(y, x).getSoldier() != null && board.getSquare(y, x).getSoldier().getShape() != null && board.getSquare(y, x).getSoldier().isWhite()) {
                    s += board.getSquare(y, x).getSoldier().getShape();
                } else if (board.getSquare(y, x).getSoldier() != null && board.getSquare(y, x).getSoldier().getShapeTwo() != null && !board.getSquare(y, x).getSoldier().isWhite()) {
                    s += board.getSquare(y, x).getSoldier().getShapeTwo();
                } else {
                    s += " ";
                }

                if (x < 7) {
                    s += " ";
                }
            }

            s += "| " + (y + 1);
            s += "\n";
        }
        s += "  +---------------+\n";
        s += "   A B C D E F G H\n";

        return s;
    }

    public static synchronized void playSound(final String url) {
        new Thread(new Runnable() {
            public void run() {
                try {
                    File file = new File(url);
                    Clip clip = AudioSystem.getClip();
                    AudioInputStream inputStream = AudioSystem.getAudioInputStream(file);
                    clip.open(inputStream);
                    clip.start();
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            }
        }).start();
    }

}
