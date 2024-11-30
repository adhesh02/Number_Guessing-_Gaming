import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class NumberGuessingGameAWT extends Frame implements ActionListener {
    private Label rangeLabel, inputLabel, feedbackLabel, attemptsLabel;
    private TextField lowerBoundField, upperBoundField, guessField;
    private Button startButton, guessButton, resetButton;
    private int targetNumber, attempts;
    private boolean gameActive;

    public NumberGuessingGameAWT() {
        setTitle("Number Guessing Game");
        setSize(400, 300);
        setLayout(new FlowLayout());

        // UI Components
        rangeLabel = new Label("Enter range (lower and upper bounds):");
        add(rangeLabel);

        lowerBoundField = new TextField(5);
        upperBoundField = new TextField(5);
        add(lowerBoundField);
        add(upperBoundField);

        startButton = new Button("Start Game");
        add(startButton);
        startButton.addActionListener(this);

        inputLabel = new Label("Enter your guess:");
        inputLabel.setVisible(false);
        add(inputLabel);

        guessField = new TextField(5);
        guessField.setVisible(false);
        add(guessField);

        guessButton = new Button("Guess");
        guessButton.setVisible(false);
        add(guessButton);
        guessButton.addActionListener(this);

        feedbackLabel = new Label("");
        feedbackLabel.setVisible(false);
        add(feedbackLabel);

        attemptsLabel = new Label("");
        attemptsLabel.setVisible(false);
        add(attemptsLabel);

        resetButton = new Button("Reset");
        resetButton.setVisible(false);
        add(resetButton);
        resetButton.addActionListener(this);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton) {
            try {
                int lowerBound = Integer.parseInt(lowerBoundField.getText());
                int upperBound = Integer.parseInt(upperBoundField.getText());

                if (lowerBound >= upperBound) {
                    feedbackLabel.setText("Invalid range! Lower bound must be less than upper bound.");
                    feedbackLabel.setVisible(true);
                    return;
                }

                Random random = new Random();
                targetNumber = random.nextInt(upperBound - lowerBound + 1) + lowerBound;
                attempts = 0;
                gameActive = true;

                feedbackLabel.setText("Game started! Guess the number.");
                feedbackLabel.setVisible(true);

                inputLabel.setVisible(true);
                guessField.setVisible(true);
                guessButton.setVisible(true);
                resetButton.setVisible(true);

                lowerBoundField.setEnabled(false);
                upperBoundField.setEnabled(false);
                startButton.setEnabled(false);

            } catch (NumberFormatException ex) {
                feedbackLabel.setText("Please enter valid numbers for the range.");
                feedbackLabel.setVisible(true);
            }
        } else if (e.getSource() == guessButton && gameActive) {
            try {
                int guess = Integer.parseInt(guessField.getText());
                attempts++;

                if (guess < targetNumber) {
                    feedbackLabel.setText("Too low!");
                } else if (guess > targetNumber) {
                    feedbackLabel.setText("Too high!");
                } else {
                    feedbackLabel.setText("Congratulations! You guessed it in " + attempts + " attempts.");
                    gameActive = false;
                }

                feedbackLabel.setVisible(true);
                attemptsLabel.setText("Attempts: " + attempts);
                attemptsLabel.setVisible(true);

            } catch (NumberFormatException ex) {
                feedbackLabel.setText("Please enter a valid number.");
                feedbackLabel.setVisible(true);
            }
        } else if (e.getSource() == resetButton) {
            lowerBoundField.setText("");
            upperBoundField.setText("");
            guessField.setText("");

            lowerBoundField.setEnabled(true);
            upperBoundField.setEnabled(true);
            startButton.setEnabled(true);

            inputLabel.setVisible(false);
            guessField.setVisible(false);
            guessButton.setVisible(false);
            feedbackLabel.setVisible(false);
            attemptsLabel.setVisible(false);
            resetButton.setVisible(false);

            gameActive = false;
        }
    }

    public static void main(String[] args) {
        new NumberGuessingGameAWT();
    }
}
