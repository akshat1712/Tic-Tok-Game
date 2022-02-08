public class Grid {
    /*
        Grid represent the TicTacToe matrix, we will X for ( Player 1) and O for ( Player 2)

        game_over denotes whether game is over or not. Different states of game_over is described below.
            game_over==0  => Result not decided
            game_over==1  => first player won
            game_over==2  => second player won
            game_over==3  => Match drawn
        
        player_turn ( public : to allow access by outside) denotes whose turn is this time, ex- player_turn==1 means , it is player 1 time
        
        rem_places: tells number of places in the matrix to be filled

    */
    private char grid[][] = new char[3][3];
    private int game_over;
    public int player_turn;
    private int rem_places;


    /*
        Checker checks if some player has won or not. This function uses three helper function
        1. row_checker
        2. Column_checker
        3. Diagonal_checker

        These function does what their name suggest.

        if any of these function return an non-zero value, we know that game has ended due to winning of some player .
        , so we update the attribute game_over as required.

        if game_over is non-zero , then no need to check ( this is an extreme case)
    */
    private void Checker() 
    {
        if (game_over != 0)
            return;

        if (row_checker() != 0) {
            this.game_over = row_checker();
        } else if (column_checker() != 0)
            this.game_over = column_checker();
        else if (diagonal_checker() != 0)
            this.game_over = diagonal_checker();
    }

// Check each row and tells if any player has won or not.
    private int row_checker() 
    {
        for (int i = 0; i < 3; i++) {
            int second = 0, first = 0;
            for (int j = 0; j < 3; j++) {
                if (this.grid[i][j] == 'X')
                    first++;
                else if (this.grid[i][j] == 'O')
                    second++;
            }
            if (first == 3)
                return 1;
            else if (second == 3)
                return 2;
        }
        return 0;
    }

// checks each column and tells if any player has won or not.
   private int column_checker() 
   {
        for (int j = 0; j < 3; j++) {
            int first = 0, second = 0;
            for (int i = 0; i < 3; i++) {
                if (this.grid[i][j] == 'X')
                    first++;
                else if (this.grid[i][j] == 'O')
                    second++;
            }
            if (first == 3)
                return 1;
            else if (second == 3)
                return 2;
        }
        return 0;
    }

// checks both digonal and tells if any player has won or not.
    private int diagonal_checker() 
    {
        int first = 0, second = 0;
        for (int i = 0; i < 3; i++) 
        {
            if (this.grid[i][i] == 'X')
                first++;
            else if (this.grid[i][i] == 'O')
                second++;
        }
        if (first == 3)
            return 1;
        else if (second == 3)
            return 2;
        first=0;second=0;

        for( int i=0;i<3;i++)
        {
            if( this.grid[i][2-i]=='X')
                first++;
            else if( this.grid[i][2-i]=='O')
                second++;
        }

        if( first==3)
            return 1;
        else if( second==3)
            return 2;

        return 0;
    }

    /*
        Construtor to be called when Grid is initialized. It performes following things.
            1. It makes every position in the grid to hold spaces i.e make them empty.
            2. game_over is set to 0
            3. rem_places to 9, as there are 9 places left in the matrix.
            4. Player_turn is set to 1, as first time player 1 will go.
    */
    public Grid() 
    {
        for (int i = 0; i < 3; i++) 
        {
            for (int j = 0; j < 3; j++)
                this.grid[i][j] = ' ';
        }
        this.game_over = 0;
        this.player_turn = 1;
        this.rem_places=9;
        print_grid();
    }


    /*
    I am taking 1-indexed as input and converting to 0-indexed and if we are allowed to put marker there, we put it otherwise we revert
    back. When it is successful, we change the player_turn for next play.

    Then we also print the grid and check the grid for winners, if no winner is there, then check if whole grid is filled or not. 
    */
    public int play(int x, int y) 
    {
        if( x<=0 || x>3 || y<=0 || y>3)
            return -1;
        if( grid[x-1][y-1]!=' ')
            return -1;
        if (player_turn == 1) {
            this.grid[x - 1][y - 1] = 'X';
            this.player_turn = 2;
            System.out.print("First Player Turn Done.\n\n");
        } else {
            this.grid[x - 1][y - 1] = 'O';
            this.player_turn = 1;
            System.out.print("Second Player Turn Done.\n\n");
        }
        Checker();
        print_grid();
        if( this.game_over!=0)
            return 0;
        this.rem_places-=1;
        if(this.rem_places==0)
            this.game_over=3;
        return 0;
    }
    

    // Tells what is state of the game i.e who the winner is or match is drawn
    public int result(){
        return this.game_over;
    }

    // This method prints the grid in nice-looking manner
    
    public void print_grid() 
    {
        for (int i = 0; i < 3; i++) 
        {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) 
            {
                System.out.print(String.format("%c | ", grid[i][j]));
            }
            System.out.print("\n");
        }
        System.out.print("\n");
    }

    // This method declares what is the result of the match after it has ended
    public void declare()
    {
        if( result()==1){
            System.out.print("The game is Won by First Player.\n");
        }
        else if( result()==2){
            System.out.print(" The game is Won by Second Player.\n");
        }
        else{
            System.out.print("The game is drawn.\n");
        }
    }

    // Return The TicTacToe Matrix
    public char[][] matrix(){
        return this.grid;
    }



}