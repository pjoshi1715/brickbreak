package finalproject;

import java.awt.EventQueue;
import javax.swing.JFrame;

public class FinalProject extends JFrame {

    public FinalProject() {       
        initUI();
    }
    
    private void initUI() {
        add(new Board());
        setTitle("Block Breaker");

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);       
        setLocationRelativeTo(null);
        pack();
    }
    
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            var game = new FinalProject();
            game.setVisible(true);
        });
    }
}
