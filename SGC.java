import java.util.Scanner;

public class SGC {

    public static int getTotalMarks(Scanner sc, int subjects) {
        int total = 0;
        for (int i = 1; i <= subjects; i++) {
            System.out.print("Enter marks for subject " + i + ": ");
            total += sc.nextInt();
        }
        return total;
    }

    public static String getGrade(double avg) {
        if (avg >= 90) {
            return "A+";
        }
        else if (avg >= 80){
            return "A";
        }
        else if (avg >= 70){
             return "B";
        }
        else if (avg >= 60) {
            return "C";
        }
        else if (avg >= 50){
             return "D";
        }
        else if (avg >= 40){
             return "E";
        }
        else{

            return "Fail (F)";
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of subjects: ");
        int s = sc.nextInt();

        int total = getTotalMarks(sc, s);
        double avg = (double) total / s;
        String grade = getGrade(avg);

        System.out.println("Total Marks: " + total);
        System.out.printf("Average Percentage: %.2f%%\n", avg);
        System.out.println("Grade: " + grade);

        sc.close();
    }
}
