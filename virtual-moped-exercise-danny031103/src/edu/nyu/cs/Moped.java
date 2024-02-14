package edu.nyu.cs;

import java.util.Arrays;

/**
 * A virtual moped, roaming the streets of New York.
 * The signatures of a few methods are given and must be completed and used as indicated.
 * Create as many additional properties or methods as you want, as long as the given methods behave as indicated in the instructions.
 * Follow good object-oriented design, especially the principles of abstraction (i.e. the black box metaphor) and encapsulation (i.e. methods and properties belonging to specific objects), as we have learned them.
 * The rest is up to you.
 */
public class Moped {
    private int nothingCount = 0;
    private int gas = 20; 
    private String cardinaldirection = "south";
    private int streetnum = 10;
    private int avenum = 5;

    /**
     * Sets the orientation of the moped to a particular cardinal direction.
     * @param orientation A string representing which cardinal direction at which to set the orientation of the moped.  E.g. "north", "south", "east", or "west".
     */
    public void setOrientation(String orientation) {
        cardinaldirection=orientation;
    }

    /**
     * Returns the current orientation of the moped, as a lowercase String.
     * E.g. "north", "south", "east", or "west".
     * @return The current orientation of the moped, as a lowercase String.
     */
    public String getOrientation() {
        String orientation=cardinaldirection;
        String orientationlower=orientation.toLowerCase();
        return orientationlower;     
    }

    /**
     * Prints the current location, by default exactly following the format:
     *      Now at 12th St. and 5th Ave, facing South.
     *
     * If the current location is associated with location-based advertising, this method should print exactly following format:
     *      Now at 12th St. and 4th Ave, facing West.  Did you know The Strand has 18 Miles of new, used and rare books, and has been in business since 1927?
     * 
     * Note that the suffixes for the numbers must be correct: i.e. the "st" in "1st", "nd" in "2nd", "rd" in "3rd", "th" in "4th", etc, must be correct.
     */
    public void printLocation() {
        int currentstreet=streetnum;
        int currentave=avenum;
        String direction=cardinaldirection;
        String printingstreet="";
        String printingave="";

            //street formatting
            if (currentstreet==1){
                printingstreet="1st";
            }
            else if (currentstreet==2){
                printingstreet="2nd";
            }
            else if (currentstreet==3){
                printingstreet="3rd";
            }
            else{
                printingstreet=(currentstreet+"th");
            }
            //avenue formatting
            //String printingave="";
            if (currentave==1){
                printingave="1st";
            }
            else if (currentave==2){
                printingave="2nd";
            }
            else if (currentave==3){
                printingave="3rd";
            }
            else{
                printingave=(currentstreet+"th");
            }

            if (streetnum==12 && avenum==5){
                System.out.println("Now at 12th St. and 5th Ave, facing "+direction+". Did you know The Strand has 18 Miles of new, used and rare books, and has been in business since 1927?");
            }
            else if (streetnum==79 && avenum==8){
                System.out.println("Now at 79th St. and 8th Ave, facing "+direction+". See extinct and endangered species at the American Musem of Natural History!");
            }
            else if (streetnum==74 && avenum==1){
                System.out.println("Now at 74th St. and 1st Ave, facing "+ direction +". Memorial Sloan Kettering has ranked in the top two 'Best Hospitals for Cancer' every year since U.S. News and World Report began rating hospitals in 1990.");
            }
            else if (streetnum==56 && avenum==3){
                System.out.println("Now at 56th St. and 3rd Ave, facing "+ direction +". Get 10% off your first online order NOW at Tina's Cuban Cuisine!");
            }
            else if (streetnum==15 && avenum==8){
                System.out.println("Now at 15th St. and 8th Ave, facing "+ direction +". We have reached Xi'an Famous Foods.  Enjoy your noodles.");
            }
            else if (streetnum==12 && avenum==4){
                System.out.println("Now at 12th St. and 4th Ave, facing "+ direction +". The only survivor of Book row and known to have the occasional treasure in their used books is The Strand bookstore!");
            }
            else{
                System.out.println("Now at "+printingstreet+" St. and "+printingave+" Ave, facing "+direction+".");
            }
    }

    /**
     * Handles the command, `go left`.
     * Moves the moped one block to the left, and causes the moped to face the appropriate new cardinal direction.
     * Consumes gas with each block moved, and doesn't move or turn unless there is sufficient gas, as according to the instructions.
     * If attempting to drive off the map, the moped will turn but not move a block.  Turns-only consume no gas.
     * This method must not print anything.
     */
    public void goLeft() {
        if (gas!=0){
            if (cardinaldirection.equals("north")){
                cardinaldirection.equals("west");
                avenum+=1;
                gas-=1;
            }
            else if (cardinaldirection.equals("west")){
                cardinaldirection.equals("south");
                streetnum-=1;
                gas-=1;
            }
            else if (cardinaldirection.equals("south")){
                cardinaldirection.equals("east");
                avenum-=1;
                gas-=1;
            }
            else if (cardinaldirection.equals("east")){
                cardinaldirection.equals("north");
                streetnum+=1;
                gas-=1;
            }
        }
        else{
            System.out.println("Out of gas!");
        }
    }

    /**
     * Handles the command, `go right`.
     * Moves the moped one block to the right, and causes the moped to face the appropriate new cardinal direction.
     * Consumes gas with each block moved, and doesn't move or turn unless there is sufficient gas, as according to the instructions.
     * If attempting to drive off the map, the moped will turn but not move a block.  Turns-only consume no gas.
     * This method must not print anything.
     */
    public void goRight() {
        if (gas!=0){
            if (cardinaldirection.equals("north")){
                cardinaldirection.equals("east");
                avenum-=1;
                gas-=1;
            }
            else if (cardinaldirection.equals("east")){
                cardinaldirection.equals("south");
                streetnum-=1;
                gas-=1;
            }
            else if (cardinaldirection.equals("south")){
                cardinaldirection.equals("west");
                avenum+=1;
                gas-=1;
            }
            else if (cardinaldirection.equals("west")){
                cardinaldirection.equals("north");
                streetnum+=1;
                gas-=1;
            }
        }
        else{
            System.out.println("Out of gas!");
        }
    }
    

    /**
     * Handles the command,`straight on`.
     * Moves the moped one block straight ahead.
     * Consumes gas with each block moved, and doesn't move unless there is sufficient gas, as according to the instructions.
     * This method must not print anything.
     */
    public void goStraight() {
        int gaslevel=gas;
        String cardinal=cardinaldirection;
        if (gaslevel>0){
            if (cardinal.equals("west")){
                avenum+=1;
                gas-=1;
            }
            else if (cardinal.equals("east")){
                avenum-=1;
                gas-=1;
            }
            else if (cardinal.equals("south")){
                streetnum-=1;
                gas-=1;
            }
            else {
                streetnum+=1;
                gas-=1;
            }
        }
    }

    /**
     * Handles the command,`back up`.
     * Moves the moped one block backwards, but does not change the cardinal direction the moped is facing.
     * Consumes gas with each block moved, and doesn't move unless there is sufficient gas, as according to the instructions.
     * This method must not print anything.
     */
    public void goBackwards() {
        if (gas!=0){
            if (cardinaldirection.equals("north")){
                streetnum-=1;
                gas-=1;
            }
            if (cardinaldirection.equals("south")){
                streetnum+=1;
                gas-=1;
            }
            if (cardinaldirection.equals("east")){
                avenum+=1;
                gas-=1;
            }
            if (cardinaldirection.equals("west")){
                avenum-=1;
                gas-=1;
            }
        }
        else {
            System.out.println("Out of gas!");
        }
    }

    /**
     * Handles the command,`how we doin'?`.
     * This method must not print anything.
     * @return The current gas level, as an integer from 0 to 100.
     */
    public int getGasLevel() {
        int gaslevel=gas/20;
        return gaslevel; 
    }

    /**
     * Prints the current gas level, by default exactly following the format:
     *      The gas tank is currently 85% full.
     *
     * If the moped is out of gas, this method should print exactly following format:
     *      We have run out of gas.  Bye bye!
     */
    public void printGasLevel() {
        int gaslevel=gas;
        double gaslevelratio=(gaslevel/20)*100;
        if (gaslevel==0){
            System.out.println("We have run out of gas.  Bye bye!");
        }
        else{
            System.out.println("The gas tank is currently "+gaslevelratio+"% full.");
        }
    }

    /**
     * Handles the command, `fill it up`.
     * This method must not print anything.
     * Fills the gas level to the maximum.
     */
    public void fillGas() {
        gas=20;
    }

    /**
     * Handles the command, `park`.
     * This causes the program to quit.  
     * You can use System.exit(0); to cause a program to quit with status code 0, which indicates a normal graceful exit. 
     * (In case you were wondering, status code 1 represents quitting as a result of an error of some kind).
     */
    public void park() {
        System.exit(0);
    }

    /**
     * Handles the command, `go to Xi'an Famous Foods`
     * Causes the moped to self-drive, block-by-block, to 8th Ave. and 15th St.
     * Consumes gas with each block, and doesn't move unless there is sufficient gas, as according to the instructions.
     */
    public void goToXianFamousFoods() {
        int gaslevel=gas;
        while (streetnum!=15){
            if (streetnum<15){
                streetnum+=1;
                System.out.println("Now at "+streetnum+"th St. and "+avenum+"th Ave, facing north.");
                gas-=1;
                if (gaslevel==0){
                    System.out.println("Out of gas!");
                    break;
                }
            }
            else if (streetnum>15){
                streetnum-=1;
                System.out.println("Now at "+streetnum+"th St. and "+avenum+"th Ave, facing south.");
                gas-=1;
                if (gaslevel==0){
                    System.out.println("Out of gas!");
                    break;
                }
            }
        }
        while (avenum!=8){
            if (avenum<8){
                avenum+=1;
                System.out.println("Now at "+streetnum+"th St. and "+avenum+"th Ave, facing west.");
                gas-=1;
                if (gaslevel==0){
                    System.out.println("Out of gas!");
                    break;
                }
            }
            else if (avenum>8){
                avenum-=1;
                System.out.println("Now at "+streetnum+"th St. and "+avenum+"th Ave, facing east.");
                gas-=1;
                if (gaslevel==0){
                    System.out.println("Out of gas!");
                    break;
                }
            }
        }
    }

    /**
     * Generates a string, containing a list of all the user commands that the program understands.
     * @return String containing commands that the user can type to control the moped.
     */
    public String getHelp() {
        String helpcommands="go left, go right, straight on, back up, how we doin'?, fill it up, park, go to Xi'an Famous Foods, and help";
        return helpcommands;        
    }

    /**
     * Sets the current location of the moped.
     * @param location an int array containing the new location at which to place the moped, in the order {street, avenue}.
     */
    public void setLocation(int[] location) {
        streetnum=location[0];
        avenum=location[1];
    }

    /**
     * Gets the current location of the moped.
     * @return The current location of the moped, as an int array in the order {street, avenue}.
     */
    public int[] getLocation() {
        int[]location= new int[2];
        location[0]=streetnum;
        location[1]=avenum;
        return location;
    }
}
