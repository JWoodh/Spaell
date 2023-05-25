package Spaell;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Connect {

    UI ui;

    //Henter ut ui-en
    public Connect(UI UserInterface){
        ui = UserInterface;
    }

    //Funksjon for å hente ut brukerne og dataen som er i databasen, og sette det på spilleren. 
    public void get() throws Exception{
        try{
            Connection con = getConnection(); //Får kobling til databasen
            PreparedStatement statement = con.prepareStatement("SELECT * FROM users WHERE username = '" + ui.nameArea.getText() + "'"); //Henter ut all infoen relatert til brukernavnet

            ResultSet result = statement.executeQuery();// Kjører statement og legger resultatet i result
            
            //Result har en tom linje først så for å komme til neste linje må jeg ha det inni en while løkke
            //selv om det bare er ett resultat
            while(result.next()){
                // Henter infoen om brukern fra databasen
                int hp = result.getInt("health");
                int weapon = result.getInt("weaponIndex");
                int pet = result.getInt("petIndex");
                int room = result.getInt("room");
                
                // Setter infoen på den nye spillern
                Room.player.setHealth(hp); 
                Room.player.setWeapon(new Weapon(weapon));
                Room.player.setPet(new Pet(pet));
                Room.roomcounter = room; 
            }
            
        } catch (Exception e){System.out.println(e);}
    }

    // Sjekker om det er en bruker som matcher navnet brukern skrev inn
    public boolean scan() throws Exception{
        Connection con = getConnection(); // Får kobling til databasen
        createTable(); //Lager tabellen om den ikke allerede finnes
        PreparedStatement statement = con.prepareStatement("SELECT username FROM users");//Henter ut alt
        ResultSet result = statement.executeQuery(); //Kjører statement

        //Kjører gjennom alle linjene i tabellen og sjekker om brukernavnet matcher det brukern skrev
        while(result.next()){ 
            if(result.getString("username").equals(ui.nameArea.getText())){
                return true;
            }
        }
        return false;   
    }

    //Sjekker om brukeren som skal logges inn med finnes og om passordene matcher
    public boolean scrutinise() throws Exception{
        Connection con = getConnection(); // Får kobling til databasen
        createTable(); //Lager tabellen om den ikke allerede finnes
        PreparedStatement statement = con.prepareStatement("SELECT username,password FROM users WHERE username = '"+ui.nameArea.getText()+"'");//Henter ut alt
        ResultSet result = statement.executeQuery(); //Kjører statement

        while(result.next()){
            if(result.getString("password").equals(ui.passwordArea.getText())){
                return true;
            }
        }
        return false;
    }

    // Funksjon for å sette inn info i databasen
    public void insert() throws Exception{
        Connection con = getConnection(); //Får kobling til databasen 
        PreparedStatement statement = con.prepareStatement("SELECT * FROM users"); //Henter tabellen
        ResultSet result = statement.executeQuery(); // Kjører sql koden

        // Definerer alle variablene jeg skal sette inn i databasen
        String username = Room.player.getName();
        String password = Room.password;
        int health = Room.player.getHealth();
        String weapon = Room.player.getWeapon().getType();
        String pet = Room.player.getPet().getRace();
        int room = Room.roomcounter;
        int weaponIndex = Room.player.getWeapon().getIndex();
        int petIndex = Room.player.getPet().getIndex();
        
        boolean check = true; //Sjekker om det ble et treff i det hele tatt
        while(result.next()){ //Kjører gjennom resultatene og sjekker om det allerede er en bruker som heter det
            if(result.getString("username").equals(username)){

                //Oppdaterer brukern med ny info
                PreparedStatement change = con.prepareStatement("UPDATE users SET password = '"+ password +"', health = '" + health + "', weapon = '" + weapon + "', pet = '" + pet + "', room = '" + room + "', weaponIndex = '" + weaponIndex + "', petIndex = '" + petIndex + "' WHERE username = '" + username + "'");
                change.executeUpdate();
                check = false;
            } 
        } 
        if(check){
            try{
                //Lagere brukern med info
                PreparedStatement inserted = con.prepareStatement("INSERT INTO users (username, password, health, weapon, pet, room, weaponIndex, petIndex) VALUES ('"+username+"','"+password+"','"+health+"','"+weapon+"', '"+pet+"', '"+room+"', '" + weaponIndex + "', '" + petIndex + "')");
                inserted.executeUpdate();
            } catch(Exception e){System.out.println(e);}
        }

    }

    // Sjekker om det er en tabell og lager om det ikke er
    public void createTable() throws Exception{
        try{
            Connection con = getConnection(); //Får kobling til databasen
            PreparedStatement create = con.prepareStatement("CREATE TABLE IF NOT EXISTS users(id int NOT NULL AUTO_INCREMENT, username varchar(16), password varchar(24), health int(3), weapon varchar(30), pet varchar(30), room int(4), weaponIndex int(4), petIndex int(4), admin int(1), PRIMARY KEY(id))"); //Lager databasen om den ikke allerede finnes
            create.executeUpdate();

        }catch(Exception e){System.out.println(e);}
    }

    // Oppretter kobling til databasen
    public static Connection getConnection() throws Exception{
        try{
            String driver = "com.mysql.cj.jdbc.Driver"; //Driveren
            // String url = "jdbc:mysql://10.2.2.2:3306/java"; //lenken til databsen
            String url = "jdbc:mysql://10.10.43.43:3306/java"; //lenken til databsen
            String username = "java";//Brukernavn for tilgang
            String password = "010500";//Passord for tilgang
            Class.forName(driver);

            Connection conn = DriverManager.getConnection(url, username, password); //Setter koblingen
            System.out.println("Connected");
            return conn; 
        } catch(Exception e){
            System.out.println(e); 
        }

        return null;
    }
}
