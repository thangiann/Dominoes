//AM 5672 Athanasios Giannopoulos
import java.util.Scanner;

public class DominoesGame {
    
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        System.out.println("Welcome to dominoes!");
        System.out.print("Give your player name:");
        
        String name = in.nextLine();

        int playerPoints = 0;
        int computerPoints = 0;

        String answer = "y";

        while (answer.equals("y")) {
            DominoesRound dm = new DominoesRound(name);
            dm.playRound();

            System.out.println("Do you want to play another round?(y/n)");
            answer = in.nextLine();

            playerPoints += dm.getPlayer().getPoints();
            computerPoints += dm.getComputer().getPoints();
        }

        System.out.println();
        if (playerPoints < computerPoints) {
            System.out.println("Computer won with " + computerPoints + " points");
        }
        else if (playerPoints > computerPoints) {
            System.out.println("Player won with " + playerPoints + " points");
        }
        else {System.out.println("Draw! Both players have " + playerPoints + "points");}

        System.out.println("Thank you for playing!");
    }
}
