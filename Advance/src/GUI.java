import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame {
    private JPanel panel1;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JTextArea textArea1;
    private JTextField textField1;
    private JComboBox comboBox1;


    public GUI() {
        setTitle("Advance");
        setSize(700, 500);
        setContentPane(panel1);
        setVisible(true);
    }
}