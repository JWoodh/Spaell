<h2>Når du er lei av å klikke, kan du lagre scoren din i databasen:</h2>
<form method="POST" name="saveScore">
    <label for="navn">Navn:</label>
    <input type="text" name="navn" id="nameField">
    <input type="hidden" name="score" value="0">
    <input type="submit" name="save" value="Lagre score">
</form>
<?php

$tjener = "localhost";
$brukernavn = "root";
$passord = "";
$database = "phplogin";


if (isset($_POST["save"])) {

    //Opprette kobling
    $kobling = new mysqli($tjener, $brukernavn, $passord, $database);

    //Sjekk om kobling virker
    if ($kobling->connect_error) {
        die("Noe gikk galt: " . $kobling->connect_error);
    }

    //Angi UTF-8 som tegnsett
    $kobling->set_charset("utf8");

    //Lagrer feltene i variable
    $navn = $_POST["navn"];
    $score = $_POST["score"];

    $sql = "INSERT INTO high_score (name, score) VALUES ('$navn','$score')";

    if ($kobling->query($sql)) {
        header("Refresh:0"); // Oppdaterer siden så de nye resultatene blir vist
    } else {
        echo "Noe gikk galt med spørringen $sql ($kobling->error).";
    }
}
?>