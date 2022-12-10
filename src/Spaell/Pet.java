package Spaell;

public class Pet {
    private String[] races = {"cat", "dog", "turtle", "pangolin", "furry", "goldfish"}; //Liste med alle pets
    private int[] damages = {2, 5, 6, 9, -6, 0};//Pets sin skade de legger til på totalsummen
    private int[] indexs = {0,1,2,3,4,5};// Indexen for når jeg henter fra databasen
    private String race;
    private int damage;
    private int index;

    Pet(int i){
        if(i==-1){
            i = (int)(Math.random()*races.length-1); //Tilfeldig uthenting av pet
        }
        race = races[i];
        damage = damages[i];
        index = indexs[i];
    }

    //Setters og getters

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
