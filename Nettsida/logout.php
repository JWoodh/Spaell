<?php
$active_page = 'logout';
include_once 'header.php';
if(!isset($_SESSION['username'])){
    header('location: index.php');
}
?>

<p>Vil du logge ut?</p>
<form method="post">
    <input type="submit" value="Ja" name="submit" />
</form>
<?php
    if(isset($_POST['submit'])){
        //Gjøre om POST-data til variabler
        
            //Gyldig logout
            //unset($_SESSION["password"]);
            //unset($_SESSION["username"]);
            session_destroy();
            
            header('location: index.php');
    }
?>
