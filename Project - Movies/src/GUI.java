import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame {

    private JButton addMovie;
    private JButton sorting;
    private JComboBox<String> genre;
    private JTable table;
    private JTextField textField1;
    private JComboBox<String> sorts;
    private JPanel mainPanel;
    private JLabel label;
    private JButton find;

    public GUI() {
        setTitle("Movie Collector Manager");
        setSize(700, 500);
        setContentPane(mainPanel);
        setVisible(true);

        Movie[] movies = new Movie[30];
        String[] genres = {"Horror", "Comedy", "Romance", "Drama", "Documentary", "Thriller", "Animated", "Sci-Fi", "Crime"};
        String[] sort = {"A-Z", "By Genre"};

        Object[][] data = new Object[movies.length][3];

        addMovie.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                Main.newMovie(movies, textField1, genre);
                label.setText("Movie Count:" + Main.movieCount(movies));
                Main.displayMovies(movies, table, data);

            }
        });

        sorting.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                Main.sort(movies, sorts);
                Main.displayMovies(movies, table, data);

            }
        });

        find.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Main.search(movies, textField1, table);

            }
        });
    }
}
