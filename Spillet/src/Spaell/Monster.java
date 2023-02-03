package Spaell;

public class Monster {
    private String[] races = {"berater", "boggler", "swatter", "stomper","apostle" ,"basilisk"}; //Navnene til monsterne man møter
    private int[] damages = {3,6, 25, 22, 28, 25};// Skaden monstrene gjør
    private int[] healths = {30,45,25,60,96, 160};// Hvor mye liv monstrene har
    private String[] sprites = {"berater.png", "boggler.png", null, "stomper.png", "apostle.png", "basilisk.png"};
    private String race;
    private int damage;
    private int health;
    private String sprite;
    
    Monster(int i) {
        if (i==-1){
            i = (int)(Math.random()*races.length); //Velger et tilfeldig av monstrene 
        }
        race = races[i];
        damage = damages[i];
        health = healths[i];
        sprite = sprites[i];
    }

    //Lager setters og getters for variablene

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

    public String getSprite() {
        return sprite;
    }

    public String[] getRaces() {
        return races;
    }
}