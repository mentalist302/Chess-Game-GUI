package politicalchess;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.Scanner;

/*

This is the Main class which is responsible to run the game.

Created by: Mohamed Soliman --- ID: 17966266

*/

public class PoliticalChess
{
    public static void main(String[] args)
    {
        String nameOne = "";
        String nameTwo = "";
        Player p1;
        Player p2;
        int startX;
        int startY;
        int endX;
        int endY;
        boolean errorOne = true;
        boolean errorTwo = true;
        boolean errorThree = true;
        GamePlay game = new GamePlay();
        BufferedReader br = null;
        PrintWriter pw = null;
        String s;
        boolean finish = false;
        int number = 0;
        String entryOne;
        String entryTwo;
        Scanner scan = new Scanner(System.in);
        Scanner scanTwo = new Scanner(System.in);
        
        System.out.println("            Welcome To my amazing chess!                             ");
        System.out.println("");
        System.out.println("");
        
        p1 = new Player(true, nameOne);
        p2 = new Player(false, nameTwo);
        
        
        while(finish == false)
        {
            System.out.println("-------Please choose a number-------");
            System.out.println("");
            System.out.println("1-START THE GAME");
            System.out.println("--------------------------------------------------------");
            System.out.println("2-INSTRUCTIONS");
            System.out.println("--------------------------------------------------------");
            System.out.println("3-CLOSE THE GAME");
            System.out.println("--------------------------------------------------------");
            
            while(errorThree == true)
            {
                try
                {
                    number = scan.nextInt();
                    errorThree = false;
                }
                catch(InputMismatchException e)
                {
                    System.out.println("Please enter a number not a letter!");
                    scan.nextLine();
                }
            }   
            
            if(number == 1)
            {
                try{

                    pw = new PrintWriter("input.txt");

                    System.out.println("Please enter player 1 name!");
                    nameOne = scanTwo.nextLine();
                    System.out.println("Please enter player 2 name!");
                    nameTwo = scanTwo.nextLine();
                    
                    p1.name = nameOne;
                    p2.name = nameTwo;
                    

                    game.initialize(p1, p2);

                    pw.println("Player 1 name is " + nameOne);
                    pw.println("Player 2 name is " + nameTwo);

                    }catch(FileNotFoundException e)
                    {

                    }
                    finally
                    {
                        try{
                                if(br != null)
                                {
                                    br.close();
                                }

                            }
                        catch(IOException ex)
                        {

                        }
                    }  
                
                    while(!game.isEnd())
                    {
                        do {
                            try {

                                    System.out.println("Please choose the x direction of the soldier (1-8)");
                                    startX = (scan.nextInt()-1);
                                    System.out.println("Please choose the y direction of the soldier (A-H)");
                                    entryOne = scanTwo.nextLine();
                                    System.out.println("Where do you want to move the soldier to in the x direction? (1-8)");
                                    endX = (scan.nextInt()-1);
                                    System.out.println("Where do you want to move the soldier to in the y direction? (A-H)");
                                    entryTwo = scanTwo.nextLine();
                                    
                                    startY = game.letterToNumber(entryOne) -1;
                                    endY = game.letterToNumber(entryTwo) -1;


                                game.makeMove(p1, startX, startY, endX, endY);
                                errorOne = false;
                                } 
                                catch(InputMismatchException ex)
                                {
                                    System.out.println("Please enter a valid entry!");
                                    scan.nextLine();
                                }
                                        
                                catch (Exception e) 
                                {
                                    System.out.println("Please enter a valid entry!");
                                }



                            } while (errorOne && !game.isEnd());

                            do {
                                 try {
                                    if(!game.isEnd())
                                    {
                                        System.out.println("Please choose the x direction of the soldier (1-8)");
                                        startX = (scan.nextInt()-1);
                                        System.out.println("Please choose the y direction of the soldier (A-H)");
                                        entryOne = scanTwo.nextLine();
                                        System.out.println("Where do you want to move the soldier to in the x direction? (1-8)");
                                        endX = (scan.nextInt()-1);
                                        System.out.println("Where do you want to move the soldier to in the y direction? (A-H)");
                                        entryTwo = scanTwo.nextLine();
                                    
                                        startY = game.letterToNumber(entryOne) -1;
                                        endY = game.letterToNumber(entryTwo) -1;

                                        game.makeMove(p1, startX, startY, endX, endY);
                                    }

                                    errorTwo = false;
                                    } 
                                    catch(InputMismatchException ex)
                                    {
                                        System.out.println("Please enter a valid entry!");
                                        scan.nextLine();
                                    }
                                    catch (Exception e) 
                                    {
                                        System.out.println("Please enter a valid entry!");
                                        scan.nextLine();
                                    }
                            } while (errorTwo && !game.isEnd());


                        errorOne = true;
                        errorTwo = true;

                    }
            }
              
            else if(number == 2)
            {
              try
              {
                br = new BufferedReader(new FileReader("move.txt"));

                while ((s = br.readLine())!=null)
                {
                    System.out.println(s);
                } 

                }catch(FileNotFoundException e)
                {

                }
                catch(IOException ex)
                {

                }
                finally
                {
                    if(pw != null)
                    {
                        pw.close();
                    }

                }  
              System.out.println("");
              System.out.println("");
            }

            else if(number == 3)
            {
                finish = true;
                System.out.println("Thank you for playing!!");
            }

            else
            {
                System.out.println("Please enter a number (1-3)");
                System.out.println("");
            }
            
            errorThree = true;
        }       
    }
}