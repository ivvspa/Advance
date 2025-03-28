import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class Main{
    public static void main(String[] args) {

        new GUI();


    }

    static void newMovie(Movie[] movies, JTextField title, JComboBox genre) {

        for (int i = 0; i < movies.length; i++) {
            if (movies[i] == null) {
                movies[i] = new Movie(title.getText(), genre.getSelectedItem().toString());
                System.out.println("Movie added!");
                Object selected = genre.getSelectedItem();
                System.out.println("Selected item: " + selected);
                return;
            }

        }
    }

    static void displayMovies (Movie[] movies, JTable table, Object[][] data) {

        String[] columnNames = {"#Number", "Title", "Genre"};

        for (int i = 0; i < movies.length; i++) {
            if (movies[i] != null) {
                data[i][0] = i + 1;
                data[i][1] = movies[i].getTitle();
                data[i][2] = movies[i].getGenre();
            }
        }
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        table.setModel(model);


    }



    static void sort(Movie[] movies, JComboBox sort) {

        if(sort.getSelectedItem()=="A-Z") {
            Arrays.sort(movies, new Comparator<Movie>() {
                @Override
                public int compare(Movie m1, Movie m2) {
                    if(m1!=null && m2!=null) {
                    return m1.getTitle().toUpperCase().compareTo(m2.getTitle().toUpperCase());
                    }
                    return 0;
                }
            });
        }
        else{
            Arrays.sort(movies, new Comparator<Movie>() {
                @Override
                public int compare(Movie m1, Movie m2) {
                    if(m1!=null && m2!=null) {
                        return m1.getGenre().toUpperCase().compareTo(m2.getGenre().toUpperCase());
                    }
                    return 0;
                }
            });
        }
    }

    static void search(Movie[] movies, JTextField choice, JTable table) {

        for (Movie movie : movies) {
            if (movie != null) {
                char[] searchChar = choice.getText().toUpperCase().toCharArray();

                int index = 0;
                for (int i = 0; i < movies.length; i++) {
                    if (movies[i] != null) {
                        char[] movieChar = (movies[i].getTitle().toUpperCase()).toCharArray();
                        if (Arrays.equals(movieChar, searchChar)) {
                            index = i;
                        }
                    }
                }
                table.setSelectionBackground(Color.CYAN);
                table.setRowSelectionInterval(index, index);
            }
        }
    }

    static int movieCount(Movie[] movies){
        int count = 0;
        for (Movie movie : movies) {
            if (movie != null) {
                count++;
            }
        }
        return count;
    }
}

class Movie{

    private String title;
    private String genre;

    public Movie(String title, String genre) {
        this.title = title;
        this.genre = genre;
    }

    void display (){
        System.out.println(title);
        System.out.println(genre);
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

}

