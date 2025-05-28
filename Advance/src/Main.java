import javax.swing.*;

public class Main {
    public static void main(String[] args) {

    new GUI();

    }
}

class Events{

    private double probability;
    public Events(double probability){
        this.probability = probability;
    }
}

class Enemy extends Events{

    public Enemy(double probability) {
        super(probability);
    }
}

class Treasure extends Events{

    public Treasure(double probability) {
        super(probability);
    }
}

class Fountain extends Events{

    public Fountain(double probability) {
        super(probability);
    }
}

class Pitfall extends Events{

    public Pitfall(double probability) {
        super(probability);
    }
}

class Wizard extends Events{

    public Wizard(double probability) {
        super(probability);
    }
}

class Items{

}

class Combat{

}

class Player {

}