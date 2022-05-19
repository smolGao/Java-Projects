import javax.swing.*;

public class gameFrame extends JFrame {

    gameFrame() {
        // adds panel to this class
        this.add(new gamePanel());
        // sets the frame title
        this.setTitle("Snake");
        // terminates program when the user hits the close button
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // sets the size to be not changeable
        this.setResizable(false);
        // makes the window to fit components in the preferred size
        this.pack();
        // sets the frame to be visible
        this.setVisible(true);
        // centers the frame
        this.setLocationRelativeTo(null);
    }
}
