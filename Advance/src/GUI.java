import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GUI extends JFrame {
    private JPanel panel1;
    private JButton save;
    private JButton advance;
    private JButton set;
    private JTextArea textArea;
    private JButton use;
    private JButton delete;
    private JButton read;
    private JTable table1;
    private JButton load;
    private JLabel stats;
    private JButton restart;
    private Events randomEvent;
    private Player p1 = new Player (14, 1);


    public GUI() {  //here is mostly set-ups and creating objects which I can use
        setTitle("Advance");
        setSize(1000, 600);
        setContentPane(panel1);
        setVisible(true);

        textArea.setText("Press Advance to start!");

        Enemy gwishin = new Enemy(0.25, 2,5, "Gwishin", "Revenge", "Petrified", 8);
        Enemy gumiho = new Enemy(0.15, 5, 10, "Gumiho", "Liver Extractor", "Charming Smile", 6);
        Enemy daltokki = new Enemy(0.15, 15, 18, "Daltokki", "Rice Cake Throw", "Moon Haunting", 2);
        Enemy yong = new Enemy (0.05, 10, 12, "Yong", "Tail Whip", "Water Hurricane", 3);
        Enemy saja = new Enemy (0.01, 20, 30,"Jeosung Saja", "Reaper Scythe", "Death Knocks on the Door", 10);

        Enemy[] collective = {gwishin, gumiho, daltokki, yong, saja};

        Items healingPotion = new Items ("Healing Potion", "heal", 10, "A simple potion that restores your health. \nStocking up on them seems like a good idea.");
        Items powerUp = new Items ("Power-up Potion", "add", 10, "A big glass of blue, sparkly lemonade. \nLooks like it might give you some strength.");
        Items bronzeSpear = new Items ("Bronze Spear", "damage", 5, "Big pointy stick with a fork on top. \nNot very useful, but sufficient for your survival.");
        Items silverSpear = new Items ("Silver Spear", "damage", 8, "A well-crafted weapon. \nIts speed is formidable in comparison to its damage.");
        Items goldenSpear = new Items ("Golden Spear", "damage", 14, "A weapon crafted by a professional. \nIts speed is unremarkable, but its damage is really good.");
        Items legendarySpear = new Items ("Legendary Spear", "damage", 20, "The fabled weapon. \nHeavy as it is, it deals extraordinary damage.");

        Treasure treasure1 = new Treasure(0.07, healingPotion);
        Treasure treasure2 = new Treasure(0.03, powerUp);
        Treasure treasure3 = new Treasure(0.02, bronzeSpear);
        Treasure treasure4 = new Treasure(0.04, silverSpear);
        Treasure treasure5 = new Treasure(0.03, goldenSpear);
        Treasure treasure6 = new Treasure(0.01, legendarySpear);

        Items treasureItem = new Items("", "", 0, ""); //for set button

        Fountain fountain = new Fountain(0.09);


        Inventory inv = new Inventory (new ArrayList<Items>());
        inv.getInv().add(bronzeSpear);

        Object[][] data = new Object[6][2];

        ArrayList<Events> events = new ArrayList<Events>();
        events.add(gwishin);
        events.add(gumiho);
        events.add(daltokki);
        events.add(yong);
        events.add(saja);
        events.add(treasure1);
        events.add(treasure2);
        events.add(treasure3);
        events.add(treasure4);
        events.add(treasure5);
        events.add(treasure6);
        events.add(fountain);

        inv.displayItems(inv.getInv(), table1, data);

        stats.setText("Level:" +p1.getLevel());



        advance.addActionListener(new ActionListener() {  //the advance button, where the progression is kept score off, events are randomized and pulled off
            public void actionPerformed(ActionEvent e) {

                stats.setText("Level:" +p1.getLevel());

                if (p1.getHealth()>0) {
                    p1.setLevel(p1.getLevel() + 1);


                    p1.setHealth(p1.getHealth());

                    healingPotion.setEffect(healingPotion.getEffect(), p1);
                    powerUp.setEffect(powerUp.getEffect(), p1);
                    bronzeSpear.setEffect(bronzeSpear.getEffect(), p1);
                    silverSpear.setEffect(silverSpear.getEffect(), p1);
                    goldenSpear.setEffect(goldenSpear.getEffect(), p1);
                    legendarySpear.setEffect(legendarySpear.getEffect(), p1);

                    for (Enemy enemy : collective) {
                        enemy.setHealth(enemy.getHealth(), p1);
                        enemy.setDamage1(enemy.getDamage1(), p1);
                        enemy.setDamage2(enemy.getDamage2(), p1);
                    }

                    randomEvent = Main.eventPicker(events);
                    if (randomEvent instanceof Enemy) {
                        textArea.setText("A wild " + ((Enemy) randomEvent).getName() + " appears!");
                        if (((Enemy) randomEvent).getHealth() > 0) {
                            advance.setEnabled(false);
                        }
                    } else if (randomEvent instanceof Fountain) {
                        textArea.setText("A small stone fountain appears before your very next step. \nA small spring of refreshing water is all you needed.");
                        ((Fountain) randomEvent).fountain(p1, textArea);
                    } else {
                        textArea.setText("A treasure chest full of gold stands before you. You find a: \n");
                        textArea.append(((Treasure) randomEvent).getItem().getName() + "\n");
                        if (inv.size() < 7) {

                            inv.addItem(((Treasure) randomEvent).getItem(), textArea);
                            inv.displayItems(inv.getInv(), table1, data);


                        } else {
                            treasureItem.setName(((Treasure) randomEvent).getItem().getName());
                            treasureItem.setGimmick(((Treasure) randomEvent).getItem().getGimmick());
                            treasureItem.setEffect(((Treasure) randomEvent).getItem().getEffect());
                            treasureItem.setDescription(((Treasure) randomEvent).getItem().getDescription());
                        }
                    }
                }
                if (p1.getHealth()<0) {
                    advance.setEnabled(true);
                    p1.setLevel(1);
                }

            }
        });

        use.addActionListener(new ActionListener() {  //the fighting mechanic
            public void actionPerformed(ActionEvent e) {

                if (randomEvent instanceof Enemy) {

                    int column = table1.getSelectedColumn();
                    int row = table1.getSelectedRow();
                    String gimmickName = (String) table1.getValueAt(row,column);

                    Items i = Main.searchGimmickName(inv, gimmickName);
                    if(!i.getName().isEmpty()) {
                        i.action(p1, ((Enemy) randomEvent), gimmickName, textArea);
                        if (((Enemy) randomEvent).getHealth() > 0) {
                            ((Enemy) randomEvent).randomAttack(p1, textArea);
                        } else {
                            advance.setEnabled(true);
                        }
                    }
                    else {
                        textArea.append("Select the item you want to use.");
                    }
                }

            }
        });

        delete.addActionListener(new ActionListener() { //delete function
            public void actionPerformed(ActionEvent e) {

                int column = table1.getSelectedColumn();
                int row = table1.getSelectedRow();
                String deleteT = (String) table1.getValueAt(row,column);
                Items i = Main.searchGimmickName(inv, deleteT);
                inv.removeItem(i, textArea, table1);
                inv.displayItems(inv.getInv(), table1, data);

            }
        });

        read.addActionListener(new ActionListener() {  //reading function
            public void actionPerformed(ActionEvent e) {

                int column = table1.getSelectedColumn();
                int row = table1.getSelectedRow();
                String deleteT = (String) table1.getValueAt(row,column);
                Items i = Main.searchGimmickName(inv, deleteT);
                inv.readItem(i, textArea);

            }
        });

        set.addActionListener(new ActionListener() {  //setting function
            public void actionPerformed(ActionEvent e) {

                int column = table1.getSelectedColumn();
                int row = table1.getSelectedRow();
                String deleteT = (String) table1.getValueAt(row,column);
                Items i = Main.searchGimmickName(inv, deleteT);
                inv.updateItem(treasureItem, inv, i);
                inv.displayItems(inv.getInv(), table1, data);

            }
        });

        save.addActionListener(new ActionListener() {   //saving function
            public void actionPerformed(ActionEvent e) {

                Main.save("save.txt", p1, inv);
                textArea.append("\nSaved");

            }
        });

        load.addActionListener(new ActionListener() {  //loading function
            public void actionPerformed(ActionEvent e) {

                p1 = Main.load("save.txt");
                textArea.setText("Loaded save");
                inv.displayItems(inv.getInv(), table1, data);
                advance.setEnabled(true);

            }
        });

        restart.addActionListener(new ActionListener() {  //loading function
            public void actionPerformed(ActionEvent e) {

                dispose();
                new GUI();

            }
        });


    }
    }

