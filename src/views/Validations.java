package views;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import javax.swing.JTextField;

public class Validations {
    
    public static void only_letters(JTextField field) {
        field.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                
                if (!Character.isLetter(c)) e.consume();
            }
        });
    }
    
    public static void only_numbers(JTextField field) {
        field.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                
                if (!Character.isDigit(c)) e.consume();
            }
        });
    }
    
    public static void only_alphanumeric(JTextField field) {
        field.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                
                if (!Character.isLetterOrDigit(c)) e.consume();
            }
        });
    }
    
    public static void length(JTextField field, int max_length) {
        field.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                int len = field.getText().length();
                
                if (len >= max_length) e.consume();
            }
        });
    }
    
    public static void exclude_characters(JTextField field,
        char ... characters)
    {
        Arrays.sort(characters);
        
        field.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                int index = Arrays.binarySearch(characters, e.getKeyChar());
                
                if (index >= 0) e.consume();
            }
        });
    }
    
}
