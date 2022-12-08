package Spaell;

public class Monster {
    private String[] races = {"berater", "boggler", "swatter", "stomper","apostle" ,"basilisk"};
    private int[] damages = {3,6, 25, 22, 28, 25};
    private int[] healths = {30,45,25,60,96,320};
    private String race;
    private int damage;
    private int health;
    
    Monster(int i) {
        if (i==-1){
            i = (int)(Math.random()*races.length);
        }
        race = races[i];
        damage = damages[i];
        health = healths[i];
    }

    public void setRace(String race) {
        this.race = race;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public String getRace() {
        return race;
    }

    public int getDamage() {
        return damage;
    }

    public int getHealth() {
        return health;
    }

    public String[] getRaces() {
        return races;
    }
}