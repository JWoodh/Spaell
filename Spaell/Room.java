package Spaell;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class Room {

    Game game;
    UI ui; 
    VisibilityManager vm;
    static Player player;
    private Monster monster;
    static int roomcounter;
    private Weapon weapon;
    private Pet pet;

    int silverRing;

    public Room(Game g, UI userInterface, VisibilityManager vManager){
        
        game = g;
        ui = userInterface;
        vm = vManager;
    }

    public void selectPosition(String nextPosition){
        switch(nextPosition){
            case "newRoom": newRoom(); break;
            case "weaponPickup": weaponPickup(); break;
            case "weaponDisregard": weaponDisregard(); break;
            case "petPickup": petPickup(); break;
            case "petAbandon": petAbandon(); break; 
            case "fightSetup": fightSetup(); break;
            case "playerAttack": playerAttack(); break;
            case "monsterAttack": monsterAttack(); break;
            case "escape": escape(); break;

            case "escape1": escapeResult(1); break;
            case "escape2": escapeResult(2); break;
            case "escape3": escapeResult(3); break;

            case "save": try {
                    Connect.insert();
                } catch (Exception e) {
                    e.printStackTrace();
                } saveComplete();
                break;
        }
    }


    public void start(){
        player = new Player(ui.nameArea.getText());
        weapon = new Weapon(8);
        pet = new Pet(6);
        player.setWeapon(weapon);
        player.setPet(pet);
        ui.mainTextArea.setText("You are standing at the entrance of a dungeon.\nYou have a branch in your hand and your loyal pet goldfish is with you\n\nProceed?");
        ui.choice1.setText("Enter");
        ui.choice2.setText("");
        ui.choice3.setText("");

        System.out.println(player.getWeapon().getType());

        game.nextPosition1 = "newRoom";
    }

    public void newRoom(){
        roomcounter++;
        int random = (int)(Math.floor(Math.random()*5));
        switch(random){
            case 0: newWeapon(); break;
            case 1: newPet(); break;
            case 2,3,4: enemy(); break;
        }
    }


    public void newWeapon(){
        weapon = new Weapon(-1);

        ui.generalHealthLabel.setText("Wpn's Dmg: ");
        ui.generalHealthNumberLabel.setText(String.valueOf(weapon.getDamage()));

        ui.mainTextArea.setText("There is a weapon through a skeleton's chest\nIt's a " + weapon.getType() + ".\n\nNew: " + weapon.getType()
        + "                Current: "+ player.getWeapon().getType() + "\nDmg: " + weapon.getDamage() + "                       Dmg: " + player.getWeapon().getDamage());
        ui.choice1.setText("Pick up");
        ui.choice2.setText("Disregard");
        ui.choice3.setText("");

        game.nextPosition1 = "weaponPickup";
        game.nextPosition2 = "weaponDisregard";

    }


    public void weaponPickup(){
        ui.mainTextArea.setText("You picked up the " + weapon.getType() + " and disregarded the " + player.getWeapon().getType());
        player.setWeapon(weapon);
        ui.choice1.setText("Next Room");
        ui.choice2.setText("");
        ui.choice3.setText("");

        game.nextPosition1 = "newRoom";
        game.nextPosition2 = "";
        game.nextPosition3 = "";
    }
    
    public void weaponDisregard(){
        ui.mainTextArea.setText("You disregarded the " + weapon.getType());
        ui.choice1.setText("Next Room");
        ui.choice2.setText("");
        ui.choice3.setText("");

        game.nextPosition1 = "newRoom";
        game.nextPosition2 = "";
        game.nextPosition3 = "";
    }

    public void newPet(){
        pet = new Pet(-1);

        ui.generalHealthLabel.setText("Pet's Dmg: ");
        ui.generalHealthNumberLabel.setText(String.valueOf(pet.getDamage()));

        ui.mainTextArea.setText("There is a pet wandering around this room\nIt's a "+ pet.getRace() + " and it will help you in battle, dealing an extra " + pet.getDamage() + " damage to enemies\n\nBring it with you?");

        ui.choice1.setText("Bring Along");
        ui.choice2.setText("Abandon");
        ui.choice3.setText("");

        game.nextPosition1 = "petPickup";
        game.nextPosition2 = "petAbandon";
        game.nextPosition3 = "";

    }

    public void petPickup(){
        ui.mainTextArea.setText("The " + pet.getRace() + " is now your loyal friend and will help you out :D\nYour old pet" + player.getPet().getRace() + " has been abandoned and will probably become very sad and depressed and eaten up grotuesqely\n\n Move on to the next room?");
        player.setPet(pet);

        ui.choice1.setText("Next Room");
        ui.choice2.setText("");
        ui.choice3.setText("");

        game.nextPosition1 = "newRoom";
        game.nextPosition2 = "";
        game.nextPosition3 = "";
    }

    public void petAbandon(){
        ui.mainTextArea.setText("The "+ pet.getRace() + " has been abandoned and left to die.\n\nMoving on.");

        ui.choice1.setText("Next Room");
        ui.choice2.setText("");
        ui.choice3.setText("");

        game.nextPosition1 = "newRoom";
        game.nextPosition2 = "";
        game.nextPosition3 = "";
    }

    public void enemy(){
        ui.generalHealthLabel.setText("Hp: ");
        ui.generalHealthNumberLabel.setText(String.valueOf(player.getHealth()));
        monster = new Monster(-1);
        ui.mainTextArea.setText("You stand face to face with a " + monster.getRace());
        ui.choice1.setText("Fight");
        ui.choice2.setText("Attempt escape");

        game.nextPosition1 = "fightSetup";
        game.nextPosition2 = "escape";
        game.nextPosition3 = "";
    }

    public void fightSetup(){
        vm.fightLayout();
        ui.enemyHealthNumberLabel.setText(String.valueOf(monster.getHealth()));
        ui.enemyDamageNumberLabel.setText(""+ monster.getDamage());
        ui.fightHealthNumberLabel.setText(String.valueOf(player.getHealth()));
        ui.damageNumberLabel.setText(String.valueOf(player.getWeapon().getDamage() + player.getPet().getDamage()));
        ui.weaponNameLabel.setText(player.getWeapon().getType());
        ui.fighTextArea.setText("What will you do?");
    }

    public void playerAttack(){
        int enemyHealth = monster.getHealth();
        int playerDamage = (int)(((Math.random()/5)+1)*player.getWeapon().getDamage() + player.getPet().getDamage());

        enemyHealth -= playerDamage;
        ui.fighTextArea.setText("You attack the " + monster.getRace() +" and deal " + playerDamage + " damage");
        monster.setHealth(Math.max(0,enemyHealth));
        ui.enemyHealthNumberLabel.setText(String.valueOf(monster.getHealth()));
        Math.max(0,enemyHealth);
        ui.attackButton.setBackground(ui.Disabled);
        ui.attackButton.setEnabled(false);
            Timer timer = new Timer(2500, (ActionListener) new ActionListener() {
            
                public void actionPerformed(ActionEvent e) {

                    ui.attackButton.setEnabled(true);
                    ui.attackButton.setBackground(ui.Back);

                    if(monster.getHealth()>0){
                        monsterAttack();
                    } else{
                        victory();
                    }

                }
            }); timer.setRepeats(false);
            timer.start();


    }


    public void monsterAttack(){

                int random = (int)(Math.random() * 7);
        int enemyDamage = (int)((Math.random()/5+1)*monster.getDamage());
        if(random == 4){  
            enemyDamage *= 1.5;
        }
        int playerHealth = player.getHealth();
        playerHealth -= enemyDamage;
        player.setHealth(Math.max(0,playerHealth));
        ui.fightHealthNumberLabel.setText(String.valueOf(player.getHealth()));
        if(enemyDamage > monster.getDamage()*1.2){
            ui.fighTextArea.setText(monster.getRace() + " hits a crit and deals " + enemyDamage + " damage\n\nWhat will you do?");
        } else{
            ui.fighTextArea.setText(monster.getRace() + " attacks and deals " + enemyDamage + " damage\n\nWhat will you do?");
        }
        Timer timer = new Timer(1500, (ActionListener) new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {

                if(player.getHealth() == 0){
                    lose();
                }

            }
        }); timer.setRepeats(false);
        timer.start();
    }

    public void victory(){
        vm.generalLayout();
        ui.mainTextArea.setText("Congratulations! You slaughtered the innocent " + monster.getRace() + " who had done nothing wrong :)!\nYou gained nothing from it. \n\nWhat will you do next?");

        ui.choice1.setText("Proceed");
        ui.choice2.setText("Save");
        ui.choice3.setText("");

        game.nextPosition1 = "newRoom";
        game.nextPosition2 = "save";
        game.nextPosition3 = "";

    }

    public void lose(){
        vm.generalLayout();
        ui.mainTextArea.setText("You were brutally slaughtered\n\nYou died.");
        ui.choiceButtonPanel.setVisible(false);
    }

    public void escape(){
        ui.generalHealthNumberLabel.setText(String.valueOf(player.getHealth()));
        vm.generalLayout();

        ui.mainTextArea.setText("Pick a number");
        ui.choice1.setText("1");
        ui.choice2.setText("2");
        ui.choice3.setText("3");

        game.nextPosition1 = "escape1";
        game.nextPosition2 = "escape2";
        game.nextPosition3 = "escape3";        

    }

    public void escapeResult(int result){
        int random = (int)Math.floor(Math.random()*3+1);

        if(random != result){
            // escapeSuccess();
            ui.mainTextArea.setText("Wow, you guessed correctly. Who would have thought? \n\nNext room?");

            ui.choice1.setText("Next room");
            ui.choice2.setText("");
            ui.choice3.setText("");

            game.nextPosition1 = "newRoom";
            game.nextPosition2 = "";
            game.nextPosition3 = "";
        } else{
            // escapeFail();

            ui.mainTextArea.setText("You did not manage to escape\n\n The " + monster.getRace() + " attacks you");

            ui.choiceButtonPanel.setVisible(false);

            Timer timer = new Timer(2500, (ActionListener) new ActionListener() {
            
                public void actionPerformed(ActionEvent e) {

                    ui.choiceButtonPanel.setVisible(true);
    
                    fightSetup();
                    monsterAttack();
    
                }
            }); timer.setRepeats(false);
            timer.start();
        }
    }

    public void saveComplete(){
        ui.mainTextArea.setText("Game is saved!");

        ui.generalHealthNumberLabel.setText(String.valueOf(player.getHealth()));
        
        ui.choice1.setText("Proceed");
        ui.choice2.setText("");
        ui.choice3.setText("");

        game.nextPosition1 = "newRoom";
        game.nextPosition2 = "";
        game.nextPosition3 = "";
    }

}


//     Room(){
//         int random = (int)(Math.random() * roomtypes.length);
//         if (roomtypes[random] == "enemy") {
//             monster = new Monster(-1);
//             System.out.println(player.getName() + " faces the monster " + monster.getRace());
//             System.out.println("Will you attack the monster? y/n");
//             String fight = input.nextLine();
//             if(fight.equals("y")){
//                 playerAttack();
            

//         } else{
//             Integer fightChance = (int)(Math.random() * 3 + 1);
//             System.out.println("Pick a number between 1-3: ");
//             String fightChoice = input.nextLine();
//             if(fightChoice.equals(fightChance.toString())){
//                 System.out.println(player.getName() + " managed to run away");
//                 new Room();
//             } else{
//                 System.out.println(player.getName() + " could not run away");
//                 monsterAttack();
//             }
//         }
//         }
//         if(roomtypes[random] == "weapon"){
//             Weapon weapon = new Weapon(-1);
//             System.out.println("There is a weapon on an altar, a " + weapon.getType() + " which deals " + weapon.getDamage());    
//             System.out.println("Pick it up? y/n");
//             String weaponChoice = input.nextLine();
//             if(weaponChoice.equals("y")){
//                 System.out.println(weapon.getType() + " equipped and " + player.getWeapon().getType() + " has been discarded");
//                 player.setWeapon(weapon);
//                 roomcounter += 1;
//                 new Room();
//             } else{
//                 System.out.println(player.getName() + " keeps the " + player.getWeapon().getType() + " and leaves the " + weapon.getType());
//                 roomcounter += 1;
//                 new Room();
//             }

//         }
//         if(roomtypes[random] == "pet"){
//             Pet pet = new Pet(-1);
//             System.out.println("There is a pet in a cage in this room, a " + pet.getRace() + " which will add " + pet.getDamage() + " damage to your total");
//             if(player.getPet().getRace() == "jon"){
//                 System.out.println("Do you want to adopt it? y/n");
//                 String petChoice = input.nextLine();
//                 if(petChoice.equals("y")){
//                     player.setPet(pet);
//                     System.out.println("The " + pet.getRace() + " is your partner now");
//                     roomcounter += 1;
//                     new Room();
//                 } else{
//                     System.out.println("The " + pet.getRace() + " has been abandoned");
//                     roomcounter += 1;
//                     new Room();
//                 }
//             } else{
//                 System.out.println("You already have a " + player.getPet().getRace() + " which deals " + player.getPet().getRace() + " damage");
//                 System.out.println("Do you want to switch out your " + player.getPet().getRace() + " with the " + pet.getRace() + "? y/n");
//                 String petChoice = input.nextLine();
//                 if(petChoice.equals("y")){
//                     System.out.println("The " + pet.getRace() + " is your partner now, and " + player.getPet().getRace() + " has been abandoned");
//                     player.setPet(pet);
//                     roomcounter += 1;
//                     new Room();
//                 } else{
//                     System.out.println("The " + pet.getRace() + " has been abandoned, and you keep your " + player.getPet().getRace());
//                     roomcounter += 1;
//                     new Room();
//                 }
//             }
//         }
//     }

//     private void playerAttack(){
//         int enemyHealth = monster.getHealth();
//         int playerDamage = (int)(((Math.random()/5)+1)*player.getWeapon().getDamage() + player.getPet().getDamage());

//         enemyHealth -= playerDamage;
//         monster.setHealth(Math.max(0,enemyHealth));
//         System.out.println("The attack deals " + playerDamage + " damage");
//         System.out.println(monster.getRace() + " has " + Math.max(0,enemyHealth) + " health left");
//         if(enemyHealth > 0){
//             monsterAttack();
//         } else{
//             roomcounter += 1;
//             System.out.println("You murdered the " + monster.getRace());
//             System.out.println("Move on or save game? m/s" );
//             String move = input.nextLine();
//             if(move.equals("m")){
//                 new Room();
//             } else{
//                 System.out.println("Game saved!");
//                 new Room(); //OOOOOOOO
//             }
//         }
//     }

//     private void monsterAttack(){
//         int random = (int)(Math.random() * 8);
//         int enemyDamage = monster.getDamage();
//         if(random == 4){
//             enemyDamage = (int)(monster.getDamage()*1.5);
//         } else{
//             enemyDamage = monster.getDamage();
//         }
//         int playerHealth = player.getHealth();

//         playerHealth -= enemyDamage;
//         player.setHealth(Math.max(0,playerHealth));
//         if(enemyDamage > monster.getDamage()){
//             System.out.println(monster.getRace() + " hits a crit and deals " + enemyDamage + " damage");
//         } else{
//             System.out.println(monster.getRace() + " attacks and deals " + enemyDamage + " damage");
//         }
//         System.out.println(player.getName() + " has " + player.getHealth() + " health");
//         if(playerHealth > 0){
//             System.out.println("Want to attack, " + player.getName() + "? y/n");
//             String fight = input.nextLine();
//             if(fight.equals("y")){
//                 playerAttack();
//             } else{
//                 int escape = (int)(Math.random()*3);
//                 if(escape == 2){
//                     roomcounter += 1;
//                     System.out.println("You escaped!");
//                     new Room();
//                 } else{
//                     System.out.println("Could not escape");
//                     monsterAttack();
//                 }
//             }
//         } else{
//             System.out.println("You were slaughtered!");
//         }
//     }

// }