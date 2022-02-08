public class Computer_Bot {
    /*
        Some variables that are used extensively during methods like filled_enemy and empty 
        I am also making grid[3][3] which will store the incoming grid in its self
        ans is array of size 2 which returns the answer 

        Algorithm used in Computer_Bot
        1. Firstly Bot check the places where bot can marker to win the game , 
        2. Then Bot check the places where bot has to put a market otherwise in next turn , player can put the marker to win the game
        3. Then I put marker on the first item in the list given below where there is no marker( 1- Indexed )
            2,2 -> 3,1 -> 1,3 -> 1,1 ->3,3
            if no of the above matches, put the marker anywhere.
            We are following the above patterns, in the order of decreasing weightage in the grid
            2,2 -> can be a part of 4 legal matches in the grid
            3,1 or 1,3 or 1,1 or 3,3 can be part of 3 legal matches in the grid
            rest can be part of 2 legal matches in the grid.
        This strategy makes the probability of bot winning higher with eaily implementable algorithm.
    */
    private char grid[][]=new char [3][3];
    private int ans[]=new int[2];
    private int filled;
    private int empty;

    /*
        Construtor to be calle when bot is initialized, making grid and ans to default value.
    */
    public Computer_Bot()
    {

        ans[0]=ans[1]=-1;
        for( int i=0;i<3;i++)
        {
            for( int j=0;j<3;j++)
                grid[i][j]=' ';
        }
    }

    /*
        solve uses the above mentioned algorithm to solve the problem.
        row_solver,column_solver, diagonal_solver takes a char as input check the frequency of that char in the respective domain function
        is applicable to,
        Like 1st if statement check if there are two or more O in a legal combination and then put bot market there if there exist one
        in the same fashion, second check for opponent marker and put bot marker to break the combination
        third one put the bot market in an optimal fashion.
    */

    public int[] solve( char input_grid[][])
    {
        grid=input_grid;
        ans[0]=ans[1]=-1;
        if( row_solver('O') || column_solver('O') || diagonal_solver( 'O'))
        {
            return this.ans;
        }
        else if( row_solver('X') || column_solver('X') || diagonal_solver('X'))
        {
            return this.ans;
        }
        else
        {
            fill_optimal();
        }
        return this.ans;
    }

    // To set filled and empty to default values. 
    private void default_val()
    {
        this.filled=0;
        this.empty=-1;
    }

    // checks if bot marker can be put or not in a row to have maximum chance of winning the game
    private boolean row_solver( char check)
    {
        for( int i=0;i<3;i++){
            default_val();
            for( int j=0;j<3;j++)
            {
                if( this.grid[i][j]==check)
                    this.filled++;
                else if( this.grid[i][j]==' ')
                    this.empty=j;
            }
            if( this.filled==2 && this.empty!=-1)
            {
                this.ans[0]=i;
                this.ans[1]=empty;
                return true;
            }
        }
        return false;
    }

    // checks if bot marker can be put or not in a column to have maximum chance of winning the game
    private boolean column_solver(  char check)
    {
        for( int j=0;j<3;j++){
            default_val();
            for( int i=0;i<3;i++)
            {
                if( this.grid[i][j]==check)
                    this.filled++;
                else if( this.grid[i][j]==' ')
                    this.empty=i;
            }
            if( this.filled==2 && this.empty!=-1)
            {
                this.ans[0]=empty;
                this.ans[1]=j;
                return true;
            }
        }
        return false;
    }

    // checks if bot marker can be put or not in a diagonal to have maximum chance of winning the game
    private boolean diagonal_solver(  char check)
    {
        default_val();
        for( int i=0;i<3;i++)
        {
            if( this.grid[i][i]==check)
            {
                this.filled++;
            }
            else if( this.grid[i][i]==' ')
                this.empty=i;
        }
        if( this.filled==2 && this.empty!=-1)
        {
            this.ans[0]=this.ans[1]=this.empty;
            return true;
        }

        default_val();

        for( int i=0;i<3;i++)
        {
            if( this.grid[i][2-i]==check)
                this.filled++;
            else if( this.grid[i][2-i]==' ')
                this.empty=i;
        }

        if( this.filled==2 && this.empty!=-1)
        {
            this.ans[0]=this.empty;
            this.ans[1]=2-this.empty;
            return true;
        }
        return false;
    }

    /*
        This function fills the grid when there is winning combination possible in this or next move.
        We follows the latter part of algorithm described in the starting of the file
    */
    private void fill_optimal( ){

        if( this.grid[1][1]==' '){
            this.ans[0]=this.ans[1]=1;
        }
        else if( this.grid[2][0]==' '){
            this.ans[0]=2;
            this.ans[1]=0;
        }
        else if( this.grid[0][2]==' '){
            this.ans[0]=0;
            this.ans[1]=2;
        }
        else if( this.grid[0][0]==' '){
            this.ans[0]=this.ans[1]=0;
        }
        else if( this.grid[2][2]==' '){
            this.ans[0]=this.ans[1]=2;
        }
        else
        {
            for( int i=0;i<3;i++)
            {
                for( int j=0;j<3;j++)
                {
                    if( this.grid[i][j]==' ')
                    {
                        this.ans[0]=i;
                        this.ans[1]=j;
                        return;
                    }
                }
            }
        }
        return;
    }
}
