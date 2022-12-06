package Spaell;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Connect {
    public static void main(String[] args) throws Exception{
        createTable();
        insert();
        get();
    }

    public static ArrayList<String> get() throws Exception{
        try{
            Connection con = getConnection();
            PreparedStatement statement = con.prepareStatement("SELECT * FROM users");

            ResultSet result = statement.executeQuery();

            ArrayList<String> array = new ArrayList<String>();
            while(result.next()){
                System.out.print(result.getString("weapon"));
                System.out.print("  ");
                System.out.println(result.getString("pet"));

                array.add(result.getString("pet"));
            }
            System.out.println("All records have been seelected");
            return array;
        } catch (Exception e){System.out.println(e);}
        return null;
    }

    public static void insert() throws Exception{
        Connection con = getConnection();
        PreparedStatement statement = con.prepareStatement("SELECT * FROM users");
        ResultSet result = statement.executeQuery();
        String var1 = Room.player.getName();
        String var2 = Room.player.getWeapon().getType();
        String var3 = Room.player.getPet().getRace();
        int var4 = Room.roomcounter;
        int check = 0;
        while(result.next()){
            if(result.getString("username").equals(var1)){

                PreparedStatement change = con.prepareStatement("UPDATE users SET weapon = '" + var2 + "', pet = '" + var3 + "', room = '" + var4 + "' WHERE username = '" + var1 + "'");
                change.executeUpdate();
                check = 1;
            } 
        } 
        
        if(check == 0){
            try{
                createTable();
                PreparedStatement inserted = con.prepareStatement("INSERT INTO users (username, weapon, pet, room) VALUES ('"+var1+"', '"+var2+"', '"+var3+"', '"+var4+"')");
                inserted.executeUpdate();
            } catch(Exception e){System.out.println(e);}
            finally{
                System.out.println("Insert completed");
            }
        }

    }

    public static void createTable() throws Exception{
        try{
            Connection con = getConnection();
            PreparedStatement create = con.prepareStatement("CREATE TABLE IF NOT EXISTS users(id int NOT NULL AUTO_INCREMENT, username varchar(16), weapon varchar(30), pet varchar(30), room int(4), PRIMARY KEY(id))");
            create.executeUpdate();

        }catch(Exception e){System.out.println(e);}
        finally{System.out.println("Function complete.");}
    }

    public static Connection getConnection() throws Exception{
        try{
            String driver = "com.mysql.cj.jdbc.Driver";
            String url = "jdbc:mysql://10.2.2.175:3306/java"; //Localhost er placholder for ip
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
