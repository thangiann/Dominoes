import java.util.Scanner;

public class DominoesGame {
    
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        System.out.println("Welcome to dominoes!");
        System.out.print("Give your player name:");

        String name = in.nextLine();

            System.out.println();
        DominoesRound dm = new DominoesRound(name);

        String answer = "y";

        while (answer.equals("y")) {
            dm.playRound();

            System.out.println("Do you want to play another round?(y/n)");
            answer = in.nextLine();
        }

        System.out.println("Thank you for playing!");
    }
}
