import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class gamePanel extends JPanel implements ActionListener {
    // sets the screen size
    static final int SCREEN_WIDTH = 600;
    static final int SCREEN_HEIGHT = 600;
    // sets total # of units on the screen
    static final int UNIT_SIZE = 25;
    // calculates the total # of units
    static final int GAME_UNITS = (SCREEN_WIDTH * SCREEN_HEIGHT);
    static final int DELAY = 75;

    // the location of the body parts of the snake
    final int x[] = new int[GAME_UNITS];
    final int y[] = new int[GAME_UNITS];

    // snake units
    int bodyParts = 6;

    // counts the # of apples eaten
    int applesEaten;

    // the variables for the location of a random apple
    int appleX;
    int appleY;

    // the direction of the snake
    char direction = 'R'; // L R U D

    // game condition
    boolean running = false;

    // instance variables of timer and random
    Timer timer;
    Random random;

    // the constructor
    gamePanel() {
        // instantiates the random variable
        random = new Random();
        // sets the preferred size
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        // sets the background color
        this.setBackground(Color.black);
        // sets the focusability
        this.setFocusable(true);
        // registers keyListener
        this.addKeyListener(new MyKeyAdapter());
        // calls the method to initiate the game
        startGame();
    }

    public void startGame() {
        // creates new apple
        newApple();
        // updates game status to running
        running = true;
        // instantiates timer variable
        timer = new Timer(DELAY, this);
        // starts the timer
        timer.start();
    }

    public void paintComponent(Graphics g) {
        // paints the component
        super.paintComponent(g);
        // draws the window board
        draw(g);
    }

    public void draw(Graphics g) {
        if (running) {
            /*
            // draws the squares to make the screen more visualize
            for(int i = 0; i < SCREEN_HEIGHT/UNIT_SIZE; i++) {
                g.drawLine(i*UNIT_SIZE, 0, i*UNIT_SIZE, SCREEN_HEIGHT);
                g.drawLine(0, i*UNIT_SIZE, SCREEN_WIDTH, i*UNIT_SIZE);
            }
             */
            // sets the apple's color and draws it as an oval
            g.setColor(Color.red);
            g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);

            // paints the snake
            // draws the head
            g.setColor(Color.green);
            g.fillRect(x[0], y[0], UNIT_SIZE, UNIT_SIZE);
            // draw the body
            for (int i = 1; i < bodyParts; i++) {
                // g.setColor(new Color(45, 180, 0));
                g.setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
                g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
            }
            // prints the score
            g.setColor(Color.yellow);
            g.setFont(new Font("Ink Free", Font.BOLD, 40));
            FontMetrics metrics = getFontMetrics(g.getFont());
            g.drawString("Score: " + applesEaten, (SCREEN_WIDTH - metrics.stringWidth("Score: " + applesEaten))/2, g.getFont().getSize());
        }
        else { // running == false
            gameOver(g);
        }
    }


    // randomizes the location for another Apple
    public void newApple() {
        appleX = random.nextInt((int)(SCREEN_WIDTH/UNIT_SIZE)) * UNIT_SIZE;
        appleY = random.nextInt((int)(SCREEN_HEIGHT/UNIT_SIZE)) * UNIT_SIZE;
    }


    // updates the locations of the body parts of the snake
    public void move() {
        // shifts the body parts' locations,
        for (int i = bodyParts; i > 0; i--) {
            x[i] = x[i-1];
            y[i] = y[i-1];
        }

        // updates the head location (x[0]) according to the direction
        switch (direction) {
            case 'U':
                y[0] = y[0] - UNIT_SIZE;
                break;
            case 'D':
                y[0] = y[0] + UNIT_SIZE;
                break;
            case 'L':
                x[0] = x[0] - UNIT_SIZE;
                break;
            case 'R':
                x[0] = x[0] + UNIT_SIZE;
                break;
            default:
                break;
        }
    }


    // checks if the snake eats the apple
    public void checkApple() {
        if(x[0] == appleX && y[0] == appleY) {
            bodyParts++;
            applesEaten++;
            newApple();
        }
    }


    // checks if the snake died
    public void checkCollisions() {
        // checks if head collides with body
        for (int i = bodyParts; i > 0; i--) {
            if (x[0] == x[i] && y[0] == y[i]) {
                running = false;
            }
        }
        // check if head touches left border
        if (x[0] < 0) running = false;
        // check if head touches right border
        if (x[0] > SCREEN_WIDTH) running = false;
        // check if head touches top border
        if (y[0] < 0) running = false;
        // check if head touches bottom border
        if (y[0] > SCREEN_HEIGHT) running = false;

    }

    public void gameOver(Graphics g) {
        // prints score
        g.setColor(Color.red);
        g.setFont(new Font("Ink Free", Font.BOLD, 40));
        FontMetrics metrics1 = getFontMetrics(g.getFont());
        g.drawString("Score: " + applesEaten, (SCREEN_WIDTH - metrics1.stringWidth("Score: " + applesEaten))/2, g.getFont().getSize());

        // prints game over
        g.setColor(Color.red);
        g.setFont(new Font("Ink Free", Font.BOLD, 75));
        FontMetrics metrics2 = getFontMetrics(g.getFont());
        g.drawString("Game Over", (SCREEN_WIDTH - metrics2.stringWidth("Game Over"))/2, SCREEN_HEIGHT/2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // runs whenever a click on a registered component
        if (running) {
            // updates the location of the snake's body parts
            move();
            // checks if the snake eats the apple
            checkApple();
            // checks if the snake died
            checkCollisions();
            // updates the board
            repaint();
        }
    }

    public class MyKeyAdapter extends KeyAdapter {
        @Override
        // changes directions of the snake accordingly to the key pressed
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    if (direction != 'R') direction = 'L';
                    break;
                case KeyEvent.VK_RIGHT:
                    if (direction != 'L') direction = 'R';
                    break;
                case KeyEvent.VK_UP:
                    if (direction != 'D') direction = 'U';
                    break;
                case KeyEvent.VK_DOWN:
                    if (direction != 'U') direction = 'D';
                    break;
                default:
                    break;
            }
        }
    }
}
