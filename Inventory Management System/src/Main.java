import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Main {
    public static void main(String[] args) {

        JFrame frame = new JFrame("Inventory Manager");
        frame.setSize(750, 820);
        frame.setLayout(new GridLayout(3, 1));

        //panel for title
        JPanel results = new JPanel(new FlowLayout());
        JLabel label = new JLabel("Inventory Manager System");
        label.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 20));
        results.add(label);

        //display text area
        JTextArea display = new JTextArea(20, 45);
        display.setFont(new Font("Arial", Font.BOLD, 16));
        display.setForeground(Color.BLACK);
        results.add(display);

        //panel for labels
        JPanel textPanel = new JPanel(new FlowLayout());
        JLabel labelName = new JLabel("Name,");
        labelName.setFont(new Font("Serif", Font.BOLD, 12));
        textPanel.add(labelName);
        JLabel labelPrice = new JLabel("Price,");
        labelPrice.setFont(new Font("Serif", Font.BOLD, 12));
        textPanel.add(labelPrice);
        JLabel labelQuantity = new JLabel("Quantity:");
        labelQuantity.setFont(new Font("Serif", Font.BOLD, 12));
        textPanel.add(labelQuantity);

        //panel for textboxes
        JTextField name = new JTextField(12);
        JTextField price = new JTextField(12);
        JTextField quantity = new JTextField(12);
        textPanel.add(name);
        textPanel.add(price);
        textPanel.add(quantity);

        //panel for buttons
        JPanel buttons = new JPanel(new FlowLayout());
        JButton addProduct = new JButton("Add Product");
        addProduct.setFont(new Font("Arial", Font.BOLD, 20));
        JButton updateProduct = new JButton("Update Product");
        updateProduct.setFont(new Font("Arial", Font.BOLD, 20));
        JButton displayProducts = new JButton("Display Products");
        displayProducts.setFont(new Font("Arial", Font.BOLD, 20));
        buttons.add(addProduct);
        buttons.add(updateProduct);
        buttons.add(displayProducts);

        //colors
        addProduct.setBackground(Color.GRAY);
        updateProduct.setBackground(Color.GRAY);
        displayProducts.setBackground(Color.GRAY);

        //adding all the components
        frame.add(results);
        frame.add(textPanel);
        frame.add(buttons);

        frame.setVisible(true);

        Inventory i1 = new Inventory(new ArrayList<Product>()); //inventory

        //event for adding a product
        addProduct.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                    double prices = Double.parseDouble(price.getText());
                    int quantities = Integer.parseInt(quantity.getText());
                    Product p1 = new Product(name.getText(), prices, quantities);
                    if (prices>=0 && quantities>=0) {
                        i1.addProduct(p1, display);
                        display.setText("Product Added");
                    }
                    else{
                        display.setText("Prices and quantity cannot be negative");
                    }

            }
        });

        //event for changing a product
        updateProduct.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (i1.products.isEmpty()){
                    display.setText("No such Product added");
                }
                else {

                    double prices = Double.parseDouble(price.getText());
                    int quantities = Integer.parseInt(quantity.getText());
                    Product p1 = new Product(name.getText(), prices, quantities);

                    if (quantities >= 0 && prices >= 0) {
                        i1.updateProduct(p1, display);
                        display.setText("Product updated");
                    }
                    else {
                        display.setText("Please provide the correct variables");
                    }
                }
            }
        });

        //event for displaying all products
        displayProducts.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                    i1.displayAllProducts(display);
            }
        });

    }
}

class  Product extends Main {

    private String name;
    private double price;
    private int quantity;

    public Product (String name, double price, int quantity){
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    //getters and setters
    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public String setName(Product p) {
        return name = name;
    }

    public double setPrice(Product p) {
        return price = price;
    }

    public int setQuantity(Product p) {
        return quantity = quantity;
    }

    //display method for one product
    void productDetails(JTextArea textArea){
        textArea.append("Name: " + name + "\nPrice: " + price + "\nQuantity: " + quantity + "\n.................\n");
    }
}

class Inventory extends Main {

    ArrayList<Product> products;

    public Inventory(ArrayList<Product> product){

        this.products = new ArrayList<Product>();

    }

    public void addProduct(Product product, JTextArea textArea){
        if (products.size()<=2){
            System.out.println("Product Name: " + product.getName()); //for debugging, just in case
            products.add(product);
        }
        else {
            textArea.setText("Product list is full");
        }
    }

    public String updateProduct(Product product, JTextArea textArea){

        for (int n=0; n<=products.size(); n++){

            if (products.get(n).getName().equals(product.getName())){
                products.set(n, product);
                break;
            }
            else {
                textArea.setText("Product name does not match");
            }
        }
        return products.toString();
    }

    void displayAllProducts(JTextArea textArea){

        if (products.isEmpty()) {
            textArea.setText("Product List is empty");
        }
        else {
            textArea.setText("");
            for (Product product : products) {
                product.productDetails(textArea);
            }
        }
    }
}
