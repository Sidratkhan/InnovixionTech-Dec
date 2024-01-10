import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

class Question {
    String text;
    int type; // 0: Fill in the blanks, 1: Multiple choice, 2: True/False
    List<String> options;
    String correctAnswer;

    public Question(String text, int type) {
        this.text = text;
        this.type = type;
        this.options = new ArrayList<>();
    }
}

public class QuizApplication {
    public static void main(String[] args) {
        List<Question> questions = initializeQuestions();

        Scanner scanner = new Scanner(System.in);
        int score = 0;

        for (Question question : questions) {
            System.out.println(question.text);

            switch (question.type) {
                case 0: // Fill in the blanks
                    System.out.print("Your answer: ");
                    String userAnswer = scanner.nextLine();
                    if (userAnswer.equalsIgnoreCase(question.correctAnswer)) {
                        System.out.println("Correct!");
                        score++;
                    } else {
                        System.out.println("Incorrect. Correct answer: " + question.correctAnswer);
                    }
                    break;

                case 1: // Multiple choice
                    displayOptions(question.options);
                    System.out.print("Your choice (enter the number): ");
                    int userChoice = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character
                    if (userChoice >= 1 && userChoice <= question.options.size()) {
                        String selectedOption = question.options.get(userChoice - 1);
                        if (selectedOption.equalsIgnoreCase(question.correctAnswer)) {
                            System.out.println("Correct!");
                            score++;
                        } else {
                            System.out.println("Incorrect. Correct answer: " + question.correctAnswer);
                        }
                    } else {
                        System.out.println("Invalid choice.");
                    }
                    break;

                case 2: // True/False
                    displayOptions(Arrays.asList("True", "False"));
                    System.out.print("Your choice (enter True or False): ");
                    String tfAnswer = scanner.nextLine();
                    if (tfAnswer.equalsIgnoreCase(question.correctAnswer)) {
                        System.out.println("Correct!");
                        score++;
                    } else {
                        System.out.println("Incorrect. Correct answer: " + question.correctAnswer);
                    }
                    break;
            }

            System.out.println();
        }

        System.out.println("Quiz completed. Your score: " + score + " out of " + questions.size());
    }

    private static void displayOptions(List<String> options) {
        for (int i = 0; i < options.size(); i++) {
            System.out.println((i + 1) + ". " + options.get(i));
        }
    }

    private static List<Question> initializeQuestions() {
        List<Question> questions = new ArrayList<>();

        // Fill in the blanks question
        Question fillInTheBlanks = new Question("Java is a ______-oriented programming language.", 0);
        fillInTheBlanks.correctAnswer = "object";
        questions.add(fillInTheBlanks);

        // Multiple choice question
        Question multipleChoice = new Question("Which of the following is a primitive data type in Java?", 1);
        multipleChoice.options.add("String");
        multipleChoice.options.add("int");
        multipleChoice.options.add("float");
        multipleChoice.options.add("boolean");
        multipleChoice.correctAnswer = "int";
        questions.add(multipleChoice);

        // True/False question
        Question trueFalse = new Question("Java is a compiled language. (True/False)", 2);
        trueFalse.correctAnswer = "True";
        questions.add(trueFalse);

        // Shuffle the order of questions
        Collections.shuffle(questions);

        return questions;
    }
}
