package Spaell;

public class Weapon {
    private String[] types = {"toothpick","branch", "stick", "fork","dagger", "rapier", "sword", "scythe","glaive", "greatsword"}; // lager private variabler
    private int[] damages = {1,3,5,8,9,14,18,30,46, 86}; // lager private variabler
    private int[] indexs = {0,1,2,3,4,5,6,7,8,9};
    private String type;
    private int damage;
    private int index;
    

    public Weapon(int i) { // Lager konstruktøren som setter typen og skaden når Våpene lages
        if(i ==-1){
            i = (int)(Math.random()*types.length);
        }
        type = types[i];    
        damage = damages[i];
        index = indexs[i];
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() { //Setter funksjonen for å hente type
        return type;
    }

    public int getDamage() { // Setter funksjonen for å hente skade
        return damage;
    }

    public int getIndex() {
        return index;
    }

}
