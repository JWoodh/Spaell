<?php
$active_page = 'login';
include_once 'header.php';
?>

<p>Vennligst logg inn:</p>
<form method="post">
    
    <label for="brukernavn">Brukernavn:</label>
    <input type="text" name="brukernavn" /><br />
    <label for="passord">Passord:</label>
    <input type="password" name="passord" /><br />
    
    <input type="submit" value="Logg inn" name="submit" />
</form>
<p>Eller klikk <a href="registration.php">her</a> for å registrere ny bruker

<?php
    if(isset($_POST['submit'])){
        //Gjøre om POST-data til variabler
        $brukernavn = $_POST['brukernavn'];
        $passord = $_POST['passord'];
        
        //Koble til databasen
        $dbc = mysqli_connect('localhost', 'root', '', 'phplogin')
            or die('Error connecting to MySQL server.');
        
        //Gjøre klar SQL-strengen
        $query = "SELECT username, password from users where username='$brukernavn' and password='$passord'";
        
        //Utføre spørringen
        $result = mysqli_query($dbc, $query)
            or die('Error querying database.');
        
        //Koble fra databasen
        mysqli_close($dbc);

        $row = $result->fetch_assoc();
        $username = $row['username'];
        $password = $row['password'];
        


        //Sjekke om spørringen gir resultater
        if($result->num_rows > 0){
            //Gyldig login
            session_start();
            $_SESSION['username'] = $username;
            $_SESSION['password'] = $password;
            
            header('location: editprofile.php');
        }else{
            //Ugyldig login
            header('location: failure.html');
        }
    }
?>
