/** 
 * Play the game of Concnetration
 * 
 * The runner creates a game board and shows the board. It gets a 2-tile selection from the user,
 * checks if the cards at the 2 tile locations match, and then re-displays the board.
 */
import java.util.Scanner;

/**
 * This version has the optional input validation improvements described at the end of Activity 4.1.2
 */
public class ConcentrationRunner
{
    // create the game 
    private static Concentration game = new Concentration();

    public static void main(String[] args)
    {
        // input object for the BlueJ console
        Scanner in = new Scanner(System.in);

        // instructions
        System.out.println("Welcome to the game of Concentration.");

        System.out.println("Select the tile locations you want to see,");
        System.out.println("or enter any non-integer character to quit.");
        System.out.println("(You will need to know 2-dim arrays to play!)");
        System.out.println("\nPress Enter to continue...");
        in.nextLine();

        // play until all tiles are matched
        while(!game.allTilesMatch()) {

            displayBoard();

            // get player's first selection, if not an integer, quit
            int i1 = -1;
            int j1 = -1;
            boolean validTileLoc = false;
            while(!validTileLoc) {

                System.out.print("First choice (i j): ");
                if (in.hasNextInt()) i1 = in.nextInt();
                else quitGame();
                if (in.hasNextInt()) j1 = in.nextInt();
                else quitGame();
                in.reset(); // ignore any extra input

                validTileLoc = game.validSelection(i1, j1);
                if (!validTileLoc) System.out.println("Invalid input, please try again");

            }

            // display first tile
            game.showFaceUp(i1, j1);
            displayBoard();

            // get player's second selection, if not an integer, quit
            int i2 = -1;
            int j2 = -1;
            validTileLoc = false;
            while(!validTileLoc) {

                System.out.print("Second choice (i j): ");
                if (in.hasNextInt()) i2 = in.nextInt();
                else quitGame();
                if (in.hasNextInt()) j2 = in.nextInt();
                else quitGame();
                in.reset(); // ignore any extra input

                validTileLoc = game.validSelection(i2, j2);
                if (!validTileLoc) System.out.println("Invalid input, please try again");
                else if ((i1 == i2) && (j1 == j2)){
                    System.out.println("You mush choose a different second tile");
                    validTileLoc = false;
                }

            }
            // display second tile on board
            game.showFaceUp(i2, j2);
            displayBoard(); 

            // determine if a match was found
            String matched = game.checkForMatch(i1, j1, i2, j2);
            System.out.println(matched);

            // wait 2 seconds to start the next turn
            wait(2); 
        }
        displayBoard();
        System.out.println("Game Over!");

    }

    /**
     * Clear the console and show the game board
     * Tiles can either indicate the card is  face up or face down
     */
    public static void displayBoard() {

        System.out.print('\u000C'); 
        System.out.println(game);
    }

    /**
     * Wait n second before clearing the console
     */
    private static void wait(int n) {
        try  {
            Thread.sleep(n * 1000);
        }
        catch (InterruptedException e) {
            System.out.println(e);
        }
    }

    /** 
     * User quits game
     */
    private static void quitGame() {
        System.out.println("Quit game!");
        System.exit(0);
    }

}
