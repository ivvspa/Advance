import javax.swing.*;
import java.io.*;
import java.util.*;
import javax.swing.table.DefaultTableModel;


public class Main {
    public static void main(String[] args) { //main class

    new GUI();

    }

    public static Events eventPicker (ArrayList<Events> events){ //method which randomly selects an event


        double totalProbability = 0;
        for (Events event : events) {
            totalProbability += event.getProbability();
        }

        double randomValue = Math.random() * totalProbability;

        for (Events event : events) {
            randomValue -= event.getProbability();
            if (randomValue <= 0) {
                return event;
            }
        }
        return events.get(events.size() - 1);

    }

    public static Player load(String path) { //printing/saving
        Player p1 = new Player (0, 0);
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line1 = br.readLine();

            while(line1!=null) {
                int health = Integer.parseInt(line1);
                int level = Integer.parseInt(br.readLine());
                p1.setHealth(health);
                p1.setLevel(level);
                line1 = br.readLine();
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return p1;
    }


    public static void save(String path, Player p1, Inventory inv) { //reading/loading
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
            bw.write(p1.getHealth() + "\n");
            bw.write(p1.getLevel() + "\n");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static Items searchGimmickName(Inventory inv, String name){
        for (Items it : inv.getInv()){
            if (name.equals(it.getName())) {
                return it;
            }
        }
        System.out.println("dont got it");
        return inv.getInv().get(inv.getInv().size()-1);
    }

}

class Events{

    private double probability;
    public Events(double probability){
        this.probability = probability;

    }

    public double getProbability(){
        return probability;
    } //the weighted possibilities

}

class Enemy extends Events{

    private String name;
    private String attackName1;
    private String attackName2;
    private int damage1;
    private int damage2;
    private int health;


    public Enemy(double probability, int damage1, int damage2, String name, String attackName1, String attackName2, int health) { //attributes of enemies
        super(probability);
        this.damage1 = damage1;
        this.damage2 = damage2;
        this.name = name;
        this.attackName1 = attackName1;
        this.attackName2 = attackName2;
        this.health = health;
    }

    public String getName(){
        return name;
    }

    public int getHealth(){
        return health;
    }

    public int getDamage1(){
        return damage1;
    }

    public int getDamage2() {
        return damage2;
    }

    public void setHealth(int health, Player p1) {
        this.health = health + p1.getLevel();
    }

    public void setDamage1(int damage1, Player p1) {
        this.damage1 = damage1;
    }

    public void setDamage2(int damage2, Player p1) {
        this.damage2 = damage2;
    }

    public void attack1(Player p, JTextArea textArea) {
        textArea.append("\n" + name + " used " + attackName1 + "!");
        p.takeDamage(damage1 + p.getLevel()/2, textArea);
    }

    public void attack2(Player p, JTextArea textArea) {
        textArea.append("\n" + name + " used " + attackName2 + "!");
        p.takeDamage(damage2 + p.getLevel()/2, textArea);
    }

    public void takeDamage(int damage, JTextArea textArea) {
        health = health - damage;
        textArea.append("\n" + name + " has taken " + damage + " damage.");
        if (health <= 0) {
            textArea.append("\n" + name + " has fallen!");
        }
    }

    public void randomAttack(Player p, JTextArea textArea) { //chooses out of the two available attacks
        if (Math.random() < 0.5) {
            attack1(p, textArea);
        } else {
            attack2(p, textArea);
        }
    }

}

class Treasure extends Events{

    private Items item;

    public Treasure(double probability, Items item) { //where you get items from
        super(probability);
        this.item = item;
    }
    
    public Items getItem(){
        return item;
    }
    
}

class Fountain extends Events{

    public Fountain(double probability) {
        super(probability);
    } //healing

    public void fountain (Player p, JTextArea textArea) {
        p.healDamage(p.getHealth(), textArea);
    }

}

class Items{

    private String name;
    private String gimmick;
    private int effect;
    private String description;


    public Items (String name, String gimmick, int effect, String description) {  //attributes of items
        this.name = name;
        this.gimmick = gimmick;
        this.effect = effect;
        this.description = description;

    }

    public String toString() {
        return name;
    }

    public String getName() {
        return name;
    }

    public String getGimmick(){
        return gimmick;
    }

    public int getEffect(){
        return effect;
    }

    public String getDescription() {
        return description;
    }



    public void setEffect(int effect) {
        this.effect = effect;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGimmick(String gimmick) {
        this.gimmick = gimmick;
    }

    public void setEffect(int effect, Player p1) {
        this.effect = effect+p1.getLevel();
    }

    public void setDescription(String description) {
        this.description = description;
    }



    public void action (Player p, Enemy e, String gimmickSelection, JTextArea textArea){ //main idea that does not really end up working

        if (this.name.equals("Healing Potion")){
            p.healDamage(effect, textArea);

        }
        else if(this.name.equals("Bronze Spear") || this.name.equals("Silver Spear") || this.name.equals("Golden Spear") || this.name.equals("Legendary Spear") ){
             textArea.append("\nYou hurled the " + this.name);
             e.takeDamage(effect, textArea);

        }
        else if (gimmickSelection.equals("Power-up Potion")){
            this.effect += p.getLevel();
            textArea.append("\nYour body feels stronger.");
        }
    }


}

class Inventory {  //all the sorting/managing happens here
    private ArrayList<Items> inv;

    public Inventory(ArrayList<Items> inv) {
        this.inv = inv;
    }

    public ArrayList<Items> getInv() {
        return inv;
    }

    public int size() {
        return inv.size();
    }

    public void addItem(Items item, JTextArea textArea) {
        if (inv.size() <= 6){
            inv.add(item);
        }
        else {
            textArea.append("\nInventory is full! Try replacing the new found equipment with something old.");
        }
    }

    public void removeItem(Items item, JTextArea textArea, JTable table) {
        inv.remove(item);
        textArea.append("\nItem removed!");
    }

    public void updateItem(Items item, Inventory iv, Items selected) {
        for (int i = 0; i < iv.getInv().size(); i++) {
            if (iv.getInv().get(i) == selected)
                inv.set(i, item);

        }

    }

    public void readItem(Items i, JTextArea textArea) {
        //search method for the item that is in the box

        if (i != null) {
            textArea.append("\n" + i.getDescription());
        } else {
            textArea.append("\nYou don't have that many items... You want me to read nothing?");
        }
        }

    public void displayItems (ArrayList<Items> inventory, JTable table, Object[][] data) {  //displays all items in a table

        String[] columnNames = {"#Number", "Item Name"};

        for (int i = 0; i < inventory.size(); i++) {
            if (inventory.get(i) != null) {
                data[i][0] = i + 1;
                data[i][1] = inventory.get(i).getName();
            }
        }
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        table.setModel(model);

    }
    }


class Player {  //the user/the player with their attributes

    private int health;
    private int level;

    public Player(int health, int level) {
        this.health = health;
        this.level = level;

    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }


    public void takeDamage(int damage, JTextArea textArea) { //how health is adjusted
        health = health - damage;
        textArea.append("\nYou have taken " + damage + " damage. \nRemaining: " + health);
        if (health <= 0) {
            textArea.append("\nThis is the end. Restart the program to play again.");
            textArea.append("\nFinal level: " + level);

        }
    }
    public void healDamage(int heal, JTextArea textArea) {
        int maxHealth = level + 15;

        if(health+heal<=maxHealth) {
            this.health += heal;
            textArea.append("\nYou healed " + heal + " health!");
        }
        else if ((health+heal)>maxHealth){
            this.health = maxHealth;
            textArea.append("\nYou healed to max health!");
        }
        else{
            textArea.append("\nYou are at maximum health.");
        }
    }

}



