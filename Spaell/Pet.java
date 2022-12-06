package Spaell;

public class Pet {
    private String[] races = {"cat", "dog", "turtle", "pangolin", "furry", "fish"};
    private int[] damages = {2, 5, 6, 9, -6, 0};
    private String race;
    private int damage;

    Pet(int i){
        if(i==-1){
            i = (int)(Math.random()*races.length);
        }
        race = races[Math.max(0,i-1)];
        damage = damages[Math.max(0,i-1)];
    }

    public void setRace(String race) {
        this.race = race;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public String getRace() {
        return race;
    }

    public int getDamage() {
        return damage;
    }
}
