import java.util.Scanner;

public class TicTacToe {
    public static void main(String[] args) 
    {
        // Starting the TicTacToe and printing starting lines
        System.out.print("Sudoku grid is 1-indexed on Row axis from Top to botton and on Column axis from left right.\n");
        System.out.print("Which Kind of Mode you want to play:\n1. Player vs Player\n2. Player vs Bot\nType: ");
        // initializing the scanner functionality
        Scanner scan = new Scanner(System.in);
        int type = scan.nextInt();
        
        // Type 1 means Player vs Player mode is activated
        if (type == 1) 
        {
            System.out.print("Player vs Player Mode is Selected\n\n");
            Grid game = new Grid();// Initializing the grid
            // This while loop runs until an result has been confirmed by the grid class.
            while (game.result() == 0) 
            {
                System.out.print(String.format("Enter the position of your move (x,y) for Player-%d :", game.player_turn));
                // scanning input position of next move.
                int x = scan.nextInt();
                int y = scan.nextInt();
                // We play the game and if we are not able to make a valid, play we are prompted again for same player.
                if (game.play(x, y) == -1) {
                    System.out.print("Please make a valid move, Please choose another one.\n");
                }
            }
            game.declare();// declaring the result of the game
        } 
        else 
        {
            System.out.print(" Player vs Bot Mode is Selected\n\n");// Type 2 means Player vs Bot is selected
            Grid game = new Grid();// initialzing the grid
            Computer_Bot bot = new Computer_Bot(); // initializing the bot
            // playing the game until we get a valid result
            while (game.result() == 0) 
            {
                System.out.print(String.format("Enter the position of your move (x,y) for Player-%d :", game.player_turn));
                if (game.player_turn == 1) 
                {
                    // scanning the player input
                    int x = scan.nextInt();
                    int y = scan.nextInt();
                    // user playing the game
                    if (game.play(x, y) == -1) 
                    {
                        System.out.print("Please make a valid move, Please choose another one.\n");
                    }
                } else {
                    // Bot playing the game
                    int put[] = bot.solve(game.matrix()); // calculating the bot move
                    System.out.print(String.format(" Bot has choosen %d %d\n", put[0] + 1, put[1] + 1));
                    game.play(put[0] + 1, put[1] + 1); // bot playing the game
                }
            }
            game.declare();// declaring the result of the game
        }
        scan.close();// closing the scanner
    }
}
