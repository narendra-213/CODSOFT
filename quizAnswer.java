import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

class Question {
    private String questionText;
    private String[] options;
    private int correctAnswerIndex;

    public Question(String questionText, String[] options, int correctAnswerIndex) {
        this.questionText = questionText;
        this.options = options;
        this.correctAnswerIndex = correctAnswerIndex;
    }

    public String getQuestionText() {
        return questionText;
    }

    public String[] getOptions() {
        return options;
    }

    public int getCorrectAnswerIndex() {
        return correctAnswerIndex;
    }
}

class Quiz {
    private Question[] questions;
    private int score;
    private int currentQuestionIndex;
    private Scanner scanner;
    private Timer timer;
    private boolean timeUp;
    private static final int QUESTION_TIME_LIMIT = 10000; // 10 seconds

    public Quiz(Question[] questions) {
        this.questions = questions;
        this.score = 0;
        this.currentQuestionIndex = 0;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        for (currentQuestionIndex = 0; currentQuestionIndex < questions.length; currentQuestionIndex++) {
            displayQuestion(questions[currentQuestionIndex]);
            if (timeUp) {
                System.out.println("Time's up! Moving to the next question.");
                timeUp = false;
            } else {
                getUserAnswer();
            }
        }
        displayResult();
    }

    private void displayQuestion(Question question) {
        System.out.println("Question " + (currentQuestionIndex + 1) + ": " + question.getQuestionText());
        String[] options = question.getOptions();
        for (int i = 0; i < options.length; i++) {
            System.out.println((i + 1) + ". " + options[i]);
        }

        timeUp = false;
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                timeUp = true;
                System.out.println("\nTime's up! Moving to the next question.");
            }
        }, QUESTION_TIME_LIMIT); // 10 seconds for each question
    }

    private void getUserAnswer() {
        System.out.print("Your answer: ");
        int userAnswer = scanner.nextInt();
        timer.cancel();

        if (userAnswer - 1 == questions[currentQuestionIndex].getCorrectAnswerIndex()) {
            score++;
            System.out.println("Correct!");
        } else {
            System.out.println("Incorrect! The correct answer was: " + (questions[currentQuestionIndex].getCorrectAnswerIndex() + 1));
        }
    }

    private void displayResult() {
        System.out.println("\nQuiz over!");
        System.out.println("Your final score is: " + score + " out of " + questions.length);
        System.out.println("Summary of answers:");

        for (int i = 0; i < questions.length; i++) {
            System.out.println("Question " + (i + 1) + ": " + questions[i].getQuestionText());
            System.out.println("Correct answer: " + (questions[i].getCorrectAnswerIndex() + 1));
        }
    }
}

public class quizAnswer {
    public static void main(String[] args) {
        Question[] questions = {
            new Question("What is the capital of France?", new String[]{"Paris", "London", "Rome", "Berlin"}, 0),
            new Question("What is the largest planet in our solar system?", new String[]{"Earth", "Mars", "Jupiter", "Saturn"}, 2),
            new Question("What is the chemical symbol for water?", new String[]{"H2O", "CO2", "NaCl", "O2"}, 0),
            new Question("Who wrote 'Hamlet'?", new String[]{"Charles Dickens", "Leo Tolstoy", "Mark Twain", "William Shakespeare"}, 3),
            new Question("What is the square root of 64?", new String[]{"6", "7", "8", "9"}, 2)
        };

        Quiz quiz = new Quiz(questions);
        quiz.start();
    }
}
