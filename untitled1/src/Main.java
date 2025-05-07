import java.util.*;

public class Main {

    public static void main(String[] args) {
        spellChecker checker = new spellChecker();

        System.out.print("Enter a word to spell check or q to quit: ");
        Scanner scan = new Scanner(System.in);
        String word = scan.nextLine();
        while (!word.equals("q")) {
            if (checker.linearSpellCheck(word))
                System.out.println(word + " is spelled correctly!");
            else
                System.out.println(word + " is misspelled!");
            System.out.print("Enter a word to spell check or q to quit: ");
            word = scan.nextLine();
        }
    }
}

class spellChecker
{

    // create and initialize an alphabetically ordered String Array of animals
    String[] animals = {
            "Albatross", "Bear", "Beetle", "Chameleon", "Cheetah",
            "Crab", "Crocodile", "Deer", "Dolphin", "Eagle",
            "Elephant", "Flamingo", "Fox", "Frog", "Giraffe",
            "Gorilla", "Hippo", "Hummingbird", "Kangaroo", "Koala",
            "Komodo Dragon", "Leopard", "Lion", "Lizard", "Lobster",
            "Millipede", "Monkey", "Octopus", "Owl", "Panda",
            "Parrot", "Penguin", "Peacock", "Pigeon", "Polar Bear",
            "Rhino", "Scorpion", "Shark", "Sloth", "Snake",
            "Spider", "Swan", "Tarantula", "Tiger", "Turtle",
            "Whale", "Wolf", "Zebra"
    };

    // pass the String Array into the ArrayList
    private ArrayList<String> dictionary = new ArrayList<String>(Arrays.asList(animals));

    public boolean linearSpellCheck(String word)
    {
        int count = 0;
        for(int i=0; i < dictionary.size(); i++)
        {
            count++;
            if (word.equalsIgnoreCase(dictionary.get(i))) {
                System.out.println("Number of words checked: " + count);
                return true;
            }
        }
        System.out.println("Number of words checked: " + count);
        return false;

    }
    public boolean binarySpellCheck(String word) {
        int count = 0;
        int left = 0;
        int right = dictionary.size() - 1;
        while (left <= right)
        {
            int middle = (left + right) / 2;
            if (word.compareToIgnoreCase(dictionary.get(middle)) < 0)
            {
                right = middle - 1;
                count++;
            }
            else if (word.compareToIgnoreCase(dictionary.get(middle)) > 0)
            {
                left = middle + 1;
                count++;
            }
            else {
                count++;
                System.out.println("Number of words checked: " + count);
                return true;

            }

        }
        count++;
        System.out.println("Number of words checked: " + count);
        return false;
    }



}