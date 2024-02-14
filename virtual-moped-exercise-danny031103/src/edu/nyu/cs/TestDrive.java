package edu.nyu.cs;

import java.util.Scanner;

public class TestDrive {

  /**
   * The main function is automatically called first in a Java program.
   * It should instantiate a Moped and contain the main logic of the program, following the instructions.
   * 
   * @param args An array of any command-line arguments.
   */
  public static void main(String[] args) throws Exception {
    Scanner scn = new Scanner(System.in);
    Moped m = new Moped();

    // solution
    System.out.println("Thanks for jumping on the moped.  We're currently parked outside Dr. Rossinsky DDS's office at 10th St. and 5th Ave, facing South.  May I say your teeth look very clean.");
    System.out.println("What would you like to do?  At any time, say \"help\" for assistance.");

    boolean keepGoing = true;
    String response = "";
    while (!response.equals("park")) {
      response = scn.nextLine();
        switch (response) {
            case "go left":
                m.goLeft();
                m.printLocation();
                break;
            case "go right":
                m.goRight();
                m.printLocation();
                break;
            case "straight on":
                m.goStraight();
                m.printLocation();
                break;
            case "back up":
                m.goBackwards();
                m.printLocation();
                break;
            case "fill it up":
                m.fillGas();
                break;
            case "how we doin'?":
                m.printGasLevel();
                break;
            case "go to Xi'an Famous Foods":
                m.goToXianFamousFoods();
                m.printLocation();
                break;
            case "help":
                m.getHelp();
                break;
            case "park":
                m.park();
                break;
            default:
                System.out.println("I'm sorry, I don't understand that command. Type 'help' for a list of commands");
        }      
    }
    scn.close();
  }

}
