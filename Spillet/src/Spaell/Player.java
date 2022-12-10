package Spaell;

public class Player { 
    private String name; 
    private int health = 100;

    private Weapon weapon;
    private Pet pet;

    public Player(String name) { //Konstruktøren som setter på navnet når playeren lages
        this.name = name;
    }
    

    //Setters og getters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setPet(Pet pet){
        this.pet = pet;
    }

    public Pet getPet() {
        return pet;
    }
    
}