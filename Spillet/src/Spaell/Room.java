package Spaell;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import javax.swing.Timer;

public class Room {

    //Definerer alle variabler sm brukes globalt

    Game game;
    UI ui; 
    VisibilityManager vm;
    Connect con;
    static Player player;
    static String password;
    private Monster monster;
    static int roomcounter;
    private Weapon weapon;
    private Pet pet;

    public Room(Game g, UI userInterface, VisibilityManager vManager, Connect Conn){
    // Setter klassene for når jeg skal kalle dem
    game = g;
    ui = userInterface;
    vm = vManager;
    con = Conn;
    }

    // Funksjonen som sjekker hvilken posisjon programmet skal til og kaller samsvarende funksjon
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

            case "save": try {con.insert();} catch (Exception e) {e.printStackTrace();} //Oppdaterer databasen
                        saveComplete(); break;
        }
    }

    public void whichName(int i){
        if(i==0){
            ui.nameButton.setActionCommand("register");
        } else{
            ui.nameButton.setActionCommand("login");
        }
    }

    public void register(){
        try {
            if(con.scan()){
                ImageIcon icon = new ImageIcon("img/registericon.jpg");
                JOptionPane.showMessageDialog(ui.window, "This username is already in use", "Nuh uh", 2, icon);
            } else{
                player = new Player(ui.nameArea.getText()); //Gir spillern navnet brukern skrev inn på forrige side
                password = ui.passwordArea.getText();
                Weapon weapon = new Weapon(1); //Setter våpen branch
                Pet pet = new Pet(5); //Setter kjæledyr gullfisk
                player.setWeapon(weapon);
                player.setPet(pet);
                startScreen();
            }
        } catch (Exception e) {e.printStackTrace();}
    }

    //Lager og setter alt av utstyr på Player
    public void login(){
        try {
            if(!con.scrutinise()){
                ImageIcon icon = new ImageIcon("img/loginicon.png");
                JOptionPane.showMessageDialog(ui.window, "The username or password is wrong", "Uh oh", 2, icon);
            } else{
                player = new Player(ui.nameArea.getText()); //Gir spillern navnet brukern skrev inn på forrige side
                password = ui.passwordArea.getText();
                con.get();
                
                //Sjekker om spillern allerede er død, om man prøver å hente ut en død spiller fra databasen
                if(player.getHealth() > 0){
                    startScreen();
                } else{
                    deadScreen();
                }
            }
        } catch (Exception e) {e.printStackTrace();}
    }

    //Introskjermen som gir deg startinfo
    public void startScreen(){
        vm.generalLayout();
        ui.generalHealthNumberLabel.setText(String.valueOf(player.getHealth()));

        ui.generalTextAreaa.setText("You are standing at the entrance of a dungeon.\nYou have a " + player.getWeapon().getType() + " in your hand \nYour loyal pet " + player.getPet().getRace() + " is with you\n\nProceed?");

        ui.choice1.setText("Enter");
        ui.choice2.setText("");
        ui.choice3.setText("");

        game.nextPosition1 = "newRoom";
    }

    //Om man prøver å starte spillet med et navn man allerede har dødd med
    //At et navn kun kan brukes i en playthrough er ikke uheldig, det er en feature
    public void deadScreen(){
        vm.generalLayout();
        ui.generalHealthNumberLabel.setText(String.valueOf(player.getHealth()));
        ui.generalTextAreaa.setText(player.getName() + " is already dead \n\nYou cannot come back to life at this time");
        ui.choiceButtonPanel.setVisible(false); //Fjerner muligheten til å trykke på noe som helst
    }

    //Lager nye rom
    public void newRoom(){
        roomcounter++; 
        int random = (int)(Math.floor(Math.random()*5)); //Tilfeldig tall fra og med 0-4
        switch(random){
            case 0: newWeapon(); break; //Nytt våpen:)
            case 1: newPet(); break; // Ny pet :)
            case 2,3,4: enemy(); break; //Motstander :(
        }
    }

    //Funksjonen for å finne et nytt, tilfeldig våpen
    public void newWeapon(){
        weapon = new Weapon(-1); //Lager et nytt tilfeldig våpen

        //Setter labelen øverst til å vise skaden våpenet gjør
        ui.generalHealthLabel.setText("Wpn's Dmg: ");
        ui.generalHealthNumberLabel.setText(String.valueOf(weapon.getDamage()));

        ui.generalTextAreaa.setText("There is a weapon through a skeleton's chest\nIt's a " + weapon.getType() + ".\n\nNew: " + weapon.getType()
        + "                Current: "+ player.getWeapon().getType() + "\nDmg: " + weapon.getDamage() + "                       Dmg: " + player.getWeapon().getDamage());
        ui.choice1.setText("Pick up");
        ui.choice2.setText("Disregard");
        ui.choice3.setText("");

        game.nextPosition1 = "weaponPickup";
        game.nextPosition2 = "weaponDisregard";

    }


    //Om våpenet ble plukka opp setter det spillerns våpen til det nye
    public void weaponPickup(){
        ui.generalTextAreaa.setText("You picked up the " + weapon.getType() + " and disregarded the " + player.getWeapon().getType());
        player.setWeapon(weapon);
        ui.choice1.setText("Next Room");
        ui.choice2.setText("");
        ui.choice3.setText("");

        game.nextPosition1 = "newRoom";
        game.nextPosition2 = "";
        game.nextPosition3 = "";
    }
    
    //Om spillern ikke plukker opp våpenet
    public void weaponDisregard(){
        ui.generalTextAreaa.setText("You disregarded the " + weapon.getType());
        ui.choice1.setText("Next Room");
        ui.choice2.setText("");
        ui.choice3.setText("");

        game.nextPosition1 = "newRoom";
        game.nextPosition2 = "";
        game.nextPosition3 = "";
    }

    //Finner en ny pet
    public void newPet(){
        pet = new Pet(-1); //Henter tilfeldig pet

        //Setter øverste labelen til dyrets ekstra skade
        ui.generalHealthLabel.setText("Pet's Dmg: ");
        ui.generalHealthNumberLabel.setText(String.valueOf(pet.getDamage()));

        ui.generalTextAreaa.setText("There is a pet wandering around this room\nIt's a "+ pet.getRace() + ". In battle, it will deal an extra " + pet.getDamage() + " damage to enemies\n\nBring it with you?");

        ui.choice1.setText("Abandon partner");
        ui.choice2.setText("Leave it");
        ui.choice3.setText("");

        game.nextPosition1 = "petPickup";
        game.nextPosition2 = "petAbandon";
        game.nextPosition3 = "";

    }
    //Om spillern plukker opp dyret, bytter ut gamle
    public void petPickup(){
        ui.generalTextAreaa.setText("The " + pet.getRace() + " is now your loyal friend and will help you :D\n\nYour old " + player.getPet().getRace() + " has been abandoned and will be very depressed and eaten\n\n Move on");
        player.setPet(pet);

        ui.choice1.setText("Next Room");
        ui.choice2.setText("");
        ui.choice3.setText("");

        game.nextPosition1 = "newRoom";
        game.nextPosition2 = "";
        game.nextPosition3 = "";
    }

    //Om spillern lar dyret være
    public void petAbandon(){
        ui.generalTextAreaa.setText("The "+ pet.getRace() + " has been abandoned and left to die.\n\nMoving on.");

        ui.choice1.setText("Next Room");
        ui.choice2.setText("");
        ui.choice3.setText("");

        game.nextPosition1 = "newRoom";
        game.nextPosition2 = "";
        game.nextPosition3 = "";
    }

    //Møte med enemies, gir valget mellom å møte den eller prøve å stikke
    public void enemy(){
        ui.generalHealthLabel.setText("Hp: ");
        ui.generalHealthNumberLabel.setText(String.valueOf(player.getHealth()));
        monster = new Monster(-1   ); //Lager tilfeldig monster
        ui.generalTextAreaa.setText("You stand face to face with a " + monster.getRace());
        ui.choice1.setText("Fight");
        ui.choice2.setText("Attempt escape");

        game.nextPosition1 = "fightSetup";
        game.nextPosition2 = "escape";
        game.nextPosition3 = "";
    }

    //Lager oppsettet for kamp, setter alle stat-labels og endrer layouten på skjermen
    public void fightSetup(){
        vm.fightLayout();
        ui.spriteLabel.setIcon(new ImageIcon("img/sprites175/" + monster.getRace() + ".png"));
        ui.enemyHealthNumberLabel.setText(String.valueOf(monster.getHealth()));
        ui.enemyDamageNumberLabel.setText(""+ monster.getDamage());
        ui.fightHealthNumberLabel.setText(String.valueOf(player.getHealth()));
        ui.damageNumberLabel.setText(String.valueOf(player.getWeapon().getDamage() + player.getPet().getDamage()));
        ui.weaponNameLabel.setText(player.getWeapon().getType());
        ui.fighTextArea.setText("What will you do?");
    }

    //Funksjonen for spillerens angrep
    public void playerAttack(){
        int enemyHealth = monster.getHealth();
        int playerDamage = (int)(((Math.random()/5)+1)*player.getWeapon().getDamage() + player.getPet().getDamage()); //Legger til en 20% variasjon på mengden skade spillern gjør

        enemyHealth -= playerDamage; //Fjerner livet som ble tatt
        ui.fighTextArea.setText("You attack the " + monster.getRace() +" and deal " + playerDamage + " damage");
        monster.setHealth(Math.max(0,enemyHealth));
        ui.enemyHealthNumberLabel.setText(String.valueOf(monster.getHealth()));
        ui.attackButton.setBackground(ui.Disabled);  //Endrer fargen og brukbarheten til angrepsknappen
        ui.attackButton.setEnabled(false); //Endrer fargen og brukbarheten til angrepsknappen

            //Legger inn et delay før neste funksjon kjører så man faktisk kan lese hva som skjer
            Timer timer = new Timer(1800, (ActionListener) new ActionListener() {
            
                //Det som skjer etter delayet
                public void actionPerformed(ActionEvent e) {

                    ui.attackButton.setEnabled(true);
                    ui.attackButton.setBackground(ui.Back);

                    //Om monsteret dør, vinner man, ellers angriper det tilbake
                    if(monster.getHealth()>0){
                        monsterAttack();
                    } else{
                        victory();
                    }

                }
            }); timer.setRepeats(false); //Delayet skal kun kjøre en gang per påkalling
            timer.start(); //Kjører timeren

    }

    //Funksjonen for monsterets angrep
    public void monsterAttack(){

                int random = (int)(Math.random() * 7);
        int enemyDamage = (int)((Math.random()/5+1)*monster.getDamage()); //20% variasjon i skade gjort
        if(random == 4){  
            enemyDamage *= 1.5; //Kritisk treff sjanse
        }
        int playerHealth = player.getHealth();
        playerHealth -= enemyDamage; //Gjør skaden
        player.setHealth(Math.max(0,playerHealth));
        ui.fightHealthNumberLabel.setText(String.valueOf(player.getHealth()));
        if(enemyDamage > monster.getDamage()*1.2){ //Sjekker om det ble et kritisk treff
            ui.fighTextArea.setText(monster.getRace() + " hits a crit and deals " + enemyDamage + " damage\n\nWhat will you do?");
        } else{
            ui.fighTextArea.setText(monster.getRace() + " attacks and deals " + enemyDamage + " damage\n\nWhat will you do?");
        }
        Timer timer = new Timer(1000, (ActionListener) new ActionListener() { //Delay for å rekke å lese før du utløper
            
            public void actionPerformed(ActionEvent e) {

                if(player.getHealth() == 0){
                    lose();
                }

            }
        }); timer.setRepeats(false); //Delayet kjører kun en gang
        timer.start(); //Starter timeren
    }

    //Funksjon som kalles om man vant en kamp
    public void victory(){
        vm.generalLayout(); // Setter layoutet på skjermen tilbake til vanlig, selv om kamplayouten er veldig stilig
        ui.generalTextAreaa.setText("Congratulations! You slaughtered the innocent " + monster.getRace() + " who did nothing wrong :)!\nYou gained nothing from it. \n\nWhat will you do next?");

        ui.choice1.setText("Proceed");
        ui.choice2.setText("Save");
        ui.choice3.setText("");

        game.nextPosition1 = "newRoom";
        game.nextPosition2 = "save";
        game.nextPosition3 = "";

    }

    public void lose(){
        vm.generalLayout(); // Setter layoutet på skjermen tilbake til vanlig, selv om kamplayouten er veldig stilig

        //Oppdaterer databasen med nyeste nytt om at karakteren er død, så de ikke kommer tilbake igjen
        try {con.insert();}
        catch (Exception e) {e.printStackTrace();}

        ui.generalHealthNumberLabel.setText(String.valueOf(player.getHealth()));

        ui.generalTextAreaa.setText("You were brutally slaughtered, " + player.getName() + "\n\nYou died.");
        ui.choiceButtonPanel.setVisible(false); //Fjerner knappene
    }

    //Forsøk å komme seg unna kamper
    public void escape(){
        ui.generalHealthNumberLabel.setText(String.valueOf(player.getHealth()));
        vm.generalLayout(); // Setter layoutet på skjermen tilbake til vanlig, selv om kamplayouten er veldig stilig

        ui.generalTextAreaa.setText("Pick a number");
        ui.choice1.setText("1");
        ui.choice2.setText("2");
        ui.choice3.setText("3");

        game.nextPosition1 = "escape1";
        game.nextPosition2 = "escape2";
        game.nextPosition3 = "escape3";        

    }

    //Sjekker om spilleren klarte å komme seg unna
    public void escapeResult(int result){
        int random = (int)Math.floor(Math.random()*3+1); //Lager tilfeldig tall, 1,2 eller 3

        if(random == result){ // Sjekker om spillerens valg er det samme som programmets
            escapeSuccess();

        } else{
            escapeFail();

            
        }
    }

    //Om spillern kom seg unna
    public void escapeSuccess(){
        ui.generalTextAreaa.setText("Wow, you guessed correctly. Who would have thought? \n\nNext room?");

        ui.choice1.setText("Next room");
        ui.choice2.setText("");
        ui.choice3.setText("");

        game.nextPosition1 = "newRoom";
        game.nextPosition2 = "";
        game.nextPosition3 = "";
    }

    //Om spillern ikke kom seg unna
    public void escapeFail(){
        ui.generalTextAreaa.setText("You did not manage to escape\n\n The " + monster.getRace() + " attacks you");

        ui.choiceButtonPanel.setVisible(false); //Fjerner muligheten til trykke på ting mens man venter

        Timer timer = new Timer(2500, (ActionListener) new ActionListener() { //Timer før man blir sendt tilbake i kamp
        
            public void actionPerformed(ActionEvent e) {

                ui.choiceButtonPanel.setVisible(true); //Setter knappene synlige igjen til senere

                fightSetup(); //Setter om til kamplayout
                monsterAttack(); // lar monsteret angripe deg for å ha feilet

            }
        }); timer.setRepeats(false); // Timeren skal ikke repetere
        timer.start(); //starter timeren
    }

    //Funksjon som viser skjerm for en suksessfull save
    public void saveComplete(){
        ui.generalTextAreaa.setText("Game is saved!");

        ui.generalHealthNumberLabel.setText(String.valueOf(player.getHealth()));
        
        ui.choice1.setText("Proceed");
        ui.choice2.setText("");
        ui.choice3.setText("");

        game.nextPosition1 = "newRoom";
        game.nextPosition2 = "";
        game.nextPosition3 = "";
    }

}
