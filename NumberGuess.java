import java.util.Scanner;

public class NumberGuessFunctional {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int wins = 0, totalTries = 0;

        while (true) {
            int min = promptInt(sc, "Enter minimum number: ");
            int max = promptInt(sc, "Enter maximum number: ");
            int secret = getRandom(min, max);
            int tries = playRound(sc, secret);
            wins++;
            totalTries += tries;

            System.out.println("Tries: " + tries);
            System.out.println("Total wins: " + wins);
            System.out.printf("Win rate: %.2f%%\n", (double) wins / totalTries * 100);

            if (!promptYes(sc, "Play again? (y/n): ")) break;
        }

        System.out.println("Thanks for playing!");
        sc.close();
    }

    static int promptInt(Scanner sc, String msg) {
        System.out.print(msg);
        return sc.nextInt();
    }

    static boolean promptYes(Scanner sc, String msg) {
        System.out.print(msg);
        return sc.next().equalsIgnoreCase("y");
    }

    static int getRandom(int min, int max) {
        return (int) (Math.random() * (max - min + 1)) + min;
    }

    static int playRound(Scanner sc, int secret) {
        int guess, tries = 0;
        do {
            guess = promptInt(sc, "Guess the number: ");
            tries++;
            if (guess < secret) System.out.println("Too low!");
            else if (guess > secret) System.out.println("Too high!");
            else System.out.println("Correct!");
        } while (guess != secret);
        return tries;
    }
}

