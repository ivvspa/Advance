import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Main {
    public static void main(String[] args) {

        //frame
        JFrame frame = new JFrame("Rock, Paper, Scissors!");
        frame.setSize(740, 550);
        frame.setLayout(new GridLayout(3, 1));

        //panel for title
        JPanel results = new JPanel(new FlowLayout());
        JLabel label = new JLabel("Write your own or engage in the original rock, paper, scissors!\n Press Setup to initialize!");
        label.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 14));
        results.add(label);

        //display text area
        JTextArea display = new JTextArea(20, 45);
        display.setFont(new Font("Arial", Font.BOLD, 16));
        display.setForeground(Color.BLACK);
        results.add(display);

        //panel for labels
        JPanel textPanel = new JPanel(new FlowLayout());
        JLabel labelName = new JLabel("Names: ");
        labelName.setFont(new Font("Serif", Font.BOLD, 12));
        textPanel.add(labelName);

        //panel for textboxes
        JTextField rock = new JTextField("Rock", 12);
        JTextField paper = new JTextField("Paper", 12);
        JTextField scissors = new JTextField("Scissors", 12);
        textPanel.add(rock);
        textPanel.add(paper);
        textPanel.add(scissors);

        //panel for buttons

        JPanel buttons = new JPanel(new FlowLayout());
        JButton setup = new JButton("Setup");
        setup.setFont(new Font("Arial", Font.BOLD, 20));
        JButton rock1 = new JButton(" ");
        rock1.setFont(new Font("Arial", Font.BOLD, 20));
        JButton paper1 = new JButton(" ");
        paper1.setFont(new Font("Arial", Font.BOLD, 20));
        JButton scissors1 = new JButton(" ");
        scissors1.setFont(new Font("Arial", Font.BOLD, 20));

        //inserting buttons
        buttons.add(setup);
        buttons.add(rock1);
        buttons.add(paper1);
        buttons.add(scissors1);

        //colors
        setup.setBackground(Color.PINK);
        rock1.setBackground(Color.GRAY);
        paper1.setBackground(Color.WHITE);
        scissors1.setBackground(Color.RED);

        //adding all the components
        frame.add(results);
        frame.add(textPanel);
        frame.add(buttons);

        frame.setVisible(true);
        //end of GUI set-up

        //initialization
        Rock r1 = new Rock(rock.getText());
        Paper p1 = new Paper(paper.getText());
        Scissors s1 = new Scissors(scissors.getText());
        Computer c1 = new Computer();
        Counter game = new Counter();

        //actions

        //setup for the different names
        setup.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                rock1.setText(rock.getText());
                paper1.setText(paper.getText());
                scissors1.setText(scissors.getText());

            }
        });

        //rock button
        rock1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (c1.random(rock, paper, scissors).equals(rock.getText())) {
                    display.setText("You picked: " + rock.getText() + "\nComputer picked: " + c1.computerMove + "\nIt's a tie!\n" + game.getPWin() + " - " + game.getCWin());
                }

                else if (c1.computerMove.equals(scissors.getText())){
                    display.setText("You picked: " + rock.getText() + "\nComputer picked: " + c1.computerMove + "\nYou win!\n" + game.setPWin() + " - " + game.getCWin());
                }
                else {
                    display.setText("You picked: " + rock.getText() + "\nComputer picked: " + c1.computerMove + "\nYou lose!\n" + game.getPWin() + " - " + game.setCWin());
                }

            }
        });

        //paper button
        paper1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (c1.random(rock, paper, scissors).equals(paper.getText())) {
                    display.setText("You picked: " + paper.getText() + "\nComputer picked: " + c1.computerMove + "\nIt's a tie!\n" + game.getPWin() + " - " + game.getCWin());
                }

                else if (c1.computerMove.equals(rock.getText())){

                    display.setText("You picked: " + paper.getText() + "\nComputer picked: " + c1.computerMove + "\nYou win!\n" + game.setPWin() + " - " + game.getCWin());
                }
                else {
                    display.setText("You picked: " + paper.getText() + "\nComputer picked: " + c1.computerMove + "\nYou lose!\n" + game.getPWin() + " - " + game.setCWin());
                }
            }
        });

        //scissors button
        scissors1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (c1.random(rock, paper, scissors).equals(scissors.getText())) {
                    display.setText("You picked: " + scissors.getText() + "\nComputer picked: " + c1.computerMove + "\nIt's a tie!\n" + game.getPWin() + " - " + game.getCWin());
                }

                else if (c1.computerMove.equals(paper.getText())){
                    display.setText("You picked: " + scissors.getText() + "\nComputer picked: " + c1.computerMove + "\nYou win!\n" + game.setPWin() + " - " + game.getCWin());
                }
                else {
                    display.setText("You picked: " + scissors.getText() + "\nComputer picked: " + c1.computerMove + "\nYou lose!\n" + game.getPWin() + " - " + game.setCWin());
                }
            }
        });


    }
}

//classes for rock, paper, and scissors

class Rock{

    private String rockName;

    public Rock(String rockName){
        this.rockName = rockName;
    }

    String getRockName (){
        return rockName;
    }

}

class Paper {

    private String paperName;

    public Paper(String paperName) {

        this.paperName = paperName;
    }
    String getPaperName (){
        return paperName;
    }
}

class Scissors {

    private String scissorsName;

    public Scissors(String scissorsName) {

        this.scissorsName = scissorsName;
    }

    String getScissorsName (){
        return scissorsName;
    }
}


//configuring of Computer
class Computer {

    String computerMove;

    String random(JTextField textField1, JTextField textField2, JTextField textField3) {

        //action/choice of computer
        Random random = new Random();
        int num = random.nextInt(3);
        if (num == 1) {
            computerMove = textField1.getText();
        }
        else if (num == 2) {
            computerMove = textField2.getText();
        }
        else {
            computerMove = textField3.getText();
        }
        return computerMove;
    }
}

//counter for the wins
class Counter {
    private int cWin;
    private int pWin;

    int getCWin(){
        return cWin;
    }

    int setCWin(){
        return ++cWin;
    }

    int getPWin(){
        return pWin;
    }

    int setPWin(){
        return ++pWin;
    }
}
