import java.util.ArrayList;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

class Question {
    private String question;
    private String[] options;
    private int correctOption;

    public Question(String question, String[] options, int correctOption) {
        this.question = question;
        this.options = options;
        this.correctOption = correctOption;
    }

    public String getQuestion() {
        return question;
    }

    public String[] getOptions() {
        return options;
    }

    public int getCorrectOption() {
        return correctOption;
    }
}

public class QuizApplication {
    private static ArrayList<Question> questions = new ArrayList<>();
    private static int currentQuestionIndex = 0;
    private static int score = 0;

    public static void main(String[] args) {
        initializeQuestions();
        startQuiz();
    }

    private static void initializeQuestions() {
        // Add your quiz questions with options and correct answers here
        // Example:
        questions.add(new Question("What is the capital of France?",
                new String[]{"A. Paris", "B. London", "C. Rome", "D. Madrid"},
                0));
        // Add more questions similarly
    }

    private static void startQuiz() {
        Scanner scanner = new Scanner(System.in);
        showNextQuestion(scanner);
    }

    private static void showNextQuestion(Scanner scanner) {
        if (currentQuestionIndex < questions.size()) {
            Question currentQuestion = questions.get(currentQuestionIndex);
            System.out.println("Question: " + currentQuestion.getQuestion());
            for (String option : currentQuestion.getOptions()) {
                System.out.println(option);
            }

            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    System.out.println("Time's up!");
                    checkAnswer("TIMEOUT", currentQuestion.getCorrectOption());
                    showNextQuestion(scanner);
                }
            }, 15000); // 15 seconds per question

            System.out.print("Enter your choice (A/B/C/D): ");
            String userChoice = scanner.next().toUpperCase();
            timer.cancel(); // Cancel the timer
            checkAnswer(userChoice, currentQuestion.getCorrectOption());
        } else {
            showResult();
        }
    }

    private static void checkAnswer(String userChoice, int correctOption) {
        if (userChoice.equals("A") || userChoice.equals("B") || userChoice.equals("C") || userChoice.equals("D")) {
            if (userChoice.equals("TIMEOUT")) {
                System.out.println("Time's up! You didn't answer.");
            } else if (userChoice.charAt(0) - 'A' == correctOption) {
                System.out.println("Correct!");
                score++;
            } else {
                System.out.println("Incorrect!");
            }
        } else {
            System.out.println("Invalid choice!");
        }
        currentQuestionIndex++;
        showNextQuestion(new Scanner(System.in)); // Move to the next question
    }

    private static void showResult() {
        System.out.println("Quiz Finished!");
        System.out.println("Your Score: " + score + "/" + questions.size());
        // Optionally, you can display a summary of correct/incorrect answers here
    }
}

