<?php
$active_page = 'registration';
include_once 'header.php';
?>
<p>Opprett ny bruker:</p>
<form method="post">
    <label for="brukernavn">Brukernavn:</label>
    <input type="text" name="brukernavn" /><br />
    <label for="passord">Passord:</label>
    <input type="password" name="passord" /><br />

    <input type="submit" value="Logg inn" name="submit" />
</form>   
<?php
    if(isset($_POST['submit'])){
        //Gjøre om POST-data til variabler
        $brukernavn = $_POST['brukernavn'];
        $passord = $_POST['passord'];
        
        //Koble til databasen
        $dbc = mysqli_connect('localhost', 'root', '', 'phplogin')
            or die('Error connecting to MySQL server.');
        
        //Gjøre klar SQL-strengen
        $query = "INSERT INTO users VALUES ('$brukernavn','$passord')";
        
        //Utføre spørringen
        $result = mysqli_query($dbc, $query)
            or die('Error querying database.');
        
        //Koble fra databasen
        mysqli_close($dbc);

        //Sjekke om spørringen gir resultater
        if($result){
            //Gyldig login
            echo "Takk for at du lagde bruker! Trykk <a href='index.php'>her</a> for å logge inn";
        }else{
            //Ugyldig login
            echo "Noe gikk galt, prøv igjen!";
        }
    }
?>
