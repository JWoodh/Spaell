package Spaell;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Connect {

    UI ui;

    public Connect(UI UserInterface){
        ui = UserInterface;
    }

    public void get() throws Exception{
        try{
            Connection con = getConnection();
            PreparedStatement statement = con.prepareStatement("SELECT * FROM users WHERE username = '" + ui.nameArea.getText() + "'");

            ResultSet result = statement.executeQuery();
            
            while(result.next()){
                int hp = result.getInt("health");
                int weapon = result.getInt("weaponIndex");
                int pet = result.getInt("petIndex");
                int room = result.getInt("room");
                
                Room.player.setHealth(hp);
                Room.player.setWeapon(new Weapon(weapon));
                Room.player.setPet(new Pet(pet));
                Room.roomcounter = room; 
            }
            
        } catch (Exception e){System.out.println(e);}
    }

    public boolean scan() throws Exception{
        Connection con = getConnection();
        createTable();
        PreparedStatement statement = con.prepareStatement("SELECT * FROM users");
        ResultSet result = statement.executeQuery();
        while(result.next()){
            if(result.getString("username").equals(ui.nameArea.getText())){
                return true;
            }
        }
        return false;   
    }

    public void insert() throws Exception{
        Connection con = getConnection();
        PreparedStatement statement = con.prepareStatement("SELECT * FROM users");
        ResultSet result = statement.executeQuery();
        String username = Room.player.getName();
        int health = Room.player.getHealth();
        String weapon = Room.player.getWeapon().getType();
        String pet = Room.player.getPet().getRace();
        int room = Room.roomcounter;
        int weaponIndex = Room.player.getWeapon().getIndex();
        int petIndex = Room.player.getPet().getIndex();
        int check = 0;
        while(result.next()){
            if(result.getString("username").equals(username)){

                PreparedStatement change = con.prepareStatement("UPDATE users SET health = '" + health + "', weapon = '" + weapon + "', pet = '" + pet + "', room = '" + room + "', weaponIndex = '" + weaponIndex + "', petIndex = '" + petIndex + "' WHERE username = '" + username + "'");
                change.executeUpdate();
                check = 1;
            } 
        } 
        if(check == 0){
            try{
                PreparedStatement inserted = con.prepareStatement("INSERT INTO users (username, health, weapon, pet, room, weaponIndex, petIndex) VALUES ('"+username+"', '"+health+"','"+weapon+"', '"+pet+"', '"+room+"', '" + weaponIndex + "', '" + petIndex + "')");
                inserted.executeUpdate();
            } catch(Exception e){System.out.println(e);}
            finally{
                System.out.println("Insert completed");
            }
        }

    }

    public void createTable() throws Exception{
        try{
            Connection con = getConnection();
            PreparedStatement create = con.prepareStatement("CREATE TABLE IF NOT EXISTS users(id int NOT NULL AUTO_INCREMENT, username varchar(16), health int(3), weapon varchar(30), pet varchar(30), room int(4), weaponIndex int(4), petIndex int(4), PRIMARY KEY(id))");
            create.executeUpdate();

        }catch(Exception e){System.out.println(e);}
        finally{System.out.println("Function complete.");}
    }

    public static Connection getConnection() throws Exception{
        try{
            String driver = "com.mysql.cj.jdbc.Driver";




        
            String url = "jdbc:mysql://192.168.1.120:3306/java"; //IPEN ER ENDRET !!!!HUSK!!!!!!!!




            String username = "JWoodh";
            String password = "jwoodh";
            Class.forName(driver);

            Connection conn = DriverManager.getConnection(url, username, password);
            System.out.println("Connected");
            return conn;
        } catch(Exception e){
            System.out.println(e); 
        }

        return null;
    }
}
