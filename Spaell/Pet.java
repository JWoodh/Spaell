package Spaell;

public class Pet {
    private String[] races = {"cat", "dog", "turtle", "pangolin", "furry", "goldfish"};
    private int[] damages = {2, 5, 6, 9, -6, 0};
    private int[] indexs = {0,1,2,3,4,5};
    private String race;
    private int damage;
    private int index;

    Pet(int i){
        if(i==-1){
            i = (int)(Math.random()*races.length-1);
        }
        race = races[i];
        damage = damages[i];
        index = indexs[i];
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

    public int getIndex() {
        return index;
    }
}
