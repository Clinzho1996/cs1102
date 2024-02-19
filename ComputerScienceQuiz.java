import java.util.Scanner;

public class ComputerScienceQuiz {
    public static void main(String[] args) {
        // I create a Scanner object to read user input
        Scanner scanner = new Scanner(System.in);

        // I initialize variables to store correct answers and user score
        String[] correctAnswers = { "A", "A", "A", "A", "A" };
        int userScore = 0;

        // I display a welcome message to the user
        System.out.println("Welcome to the Computer Science Quiz!");

        // I loop through each question
        for (int i = 0; i < correctAnswers.length; i++) {
            // I display the current question number
            System.out.println("\nQuestion " + (i + 1) + ":");

            // I call the displayQuestion method to show the current question and its
            // options
            displayQuestion(i);

            // I prompt the user for their answer
            System.out.print("Your answer (A, B, C, or D): ");
            String userAnswer = scanner.next().toUpperCase(); // I convert input to uppercase for case-insensitivity

            // I check if the user's answer is correct and update the score accordingly
            if (userAnswer.equals(correctAnswers[i])) {
                System.out.println("Correct!"); // I provide feedback for a correct answer
                userScore++;
            } else {
                // I inform the user that their answer is incorrect and display the correct
                // answer
                System.out.println("Incorrect. The correct answer is " + correctAnswers[i]);
            }
        }

        // I calculate and display the final score as a percentage
        double percentageScore = (double) userScore / correctAnswers.length * 100;
        System.out.println("\nYour final score: " + userScore + " out of " + correctAnswers.length +
                " (" + String.format("%.2f", percentageScore) + "%)");

        // I close the Scanner to release resources
        scanner.close();
    }

    // I create a helper method to display questions along with their options
    private static void displayQuestion(int questionNumber) {
        // I define arrays containing questions and their respective options
        String[] questions = {
                "What does CPU stand for?",
                "Which programming language is known for its portability and compatibility?",
                "What is the binary representation of the decimal number 10?",
                "Who is often referred to as the 'Father of Computer Science'?",
                "In computer networking, what does 'IP' stand for?"
        };

        String[][] options = {
                { "A. Central Processing Unit", "B. Computer Processing Unit", "C. Central Processor Unit",
                        "D. Central Program Unit" },
                { "A. Java", "B. C++", "C. Python", "D. Assembly" },
                { "A. 1010", "B. 1111", "C. 1001", "D. 1100" },
                { "A. Alan Turing", "B. Bill Gates", "C. Steve Jobs", "D. Mark Zuckerberg" },
                { "A. Internet Protocol", "B. Internet Port", "C. Intranet Protocol", "D. Intranet Port" }
        };

        // I display the current question
        System.out.println(questions[questionNumber]);

        // I display options for the current question
        for (String option : options[questionNumber]) {
            System.out.println(option);
        }
    }
}
