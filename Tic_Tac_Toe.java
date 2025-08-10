//                              Tic-Tac-Toe Game

import java.util.Scanner;
class Tic_Tac_Toe
{
    static Scanner sc= new Scanner(System.in);
    public static void main(String[] args)
    {
        String[][] game= {
                {"a", "b", "c"},
                {"d", "e", "f"},
                {"g", "h", "i"}
        };
        displayBoard(game);
        int x=0;
        for(int i=1; i<=5; i++)
        {
            x= board(game, i);
            if(x==1)
                break;
        }
        if(x==0)
            System.out.println("Tie");
    }
    public static int board(String[][] game, int i)
    {
        System.out.println("Player O's turn:");
        System.out.print("Enter place: ");
        String alpha= sc.next();
        for(int j=0; j<3; j++)
        {
            for(int k=0; k<3; k++)
            {
                if(game[j][k].equalsIgnoreCase(alpha))
                    game[j][k]= "O";
            }
        }
        if(result(game))
        {
            displayBoard(game);
            System.out.println("O wins");
            return 1;
        }
        displayBoard(game);
        if(i==5)
            return 0;
        System.out.println("Player X's turn:");
        System.out.print("Enter place: ");
        alpha= sc.next();
        for(int j=0; j<3; j++)
        {
            for(int k=0; k<3; k++)
            {
                if(game[j][k].equalsIgnoreCase(alpha))
                    game[j][k]= "X";
            }
        }
        if(result(game))
        {
            displayBoard(game);
            System.out.println("X wins");
            return 1;
        }
        displayBoard(game);
        return 0;
    }
    public static void displayBoard(String[][] game)
    {
        System.out.println("---------");
        for(int i=0; i<3; i++)
        {
            for(int j=0; j<3; j++)
            {
                if(j==0)
                    System.out.print("| ");
                System.out.print(game[i][j]+" ");
                if(j==2)
                    System.out.print("|");
            }
            System.out.println();
        }
        System.out.println("---------");
    }
    public static boolean result(String[][] game)
    {
        for(int i=0; i<3; i++)
        {
            if ((game[i][0].equals("O") && game[i][1].equals("O") && game[i][2].equals("O")) ||
                    (game[0][i].equals("O") && game[1][i].equals("O") && game[2][i].equals("O")))
                return true;
            if ((game[i][0].equals("X") && game[i][1].equals("X") && game[i][2].equals("X")) ||
                    (game[0][i].equals("X") && game[1][i].equals("X") && game[2][i].equals("X")))
                return true;
        }

        if ((game[0][0].equals("O") && game[1][1].equals("O") && game[2][2].equals("O")) ||
                (game[0][2].equals("O") && game[1][1].equals("O") && game[2][0].equals("O")))
            return true;

        else if ((game[0][0].equals("X") && game[1][1].equals("X") && game[2][2].equals("X")) ||
                (game[0][2].equals("X") && game[1][1].equals("X") && game[2][0].equals("X")))
            return true;

        return false;
    }
}