package Spaell;

public class Weapon {
    private String[] types = {"toothpick","branch", "stick", "fork","dagger", "rapier", "sword", "scythe","glaive", "greatsword"}; // Alle våpentypene
    private int[] damages = {1,3,5,8,9,14,18,30,46, 86}; // Alle våpnenes skade
    private int[] indexs = {0,1,2,3,4,5,6,7,8,9}; // Alle våpnenes index for uthenting fra database
    private String type;
    private int damage;
    private int index;
    

    public Weapon(int i) { // Lager konstruktøren som setter typen, skaden og indexen når våpnene lages
        if(i ==-1){
            i = (int)(Math.random()*types.length); //Tilfeldig våpen
        }
        type = types[i];    
        damage = damages[i];
        index = indexs[i];
    }

    //Setters og getters

    public String getType() { 
        return type;
    }

    public int getDamage() { 
        return damage;
    }

    public int getIndex() {
        return index;
    }

}
