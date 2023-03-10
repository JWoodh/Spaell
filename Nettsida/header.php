<?php
session_start();

?>

<html>
    <head>

y        <link rel="stylesheet" href="style.css">
        <meta charset="utf-8">
        <title>PHP Innlogging</title>

    </head>
    <body>
        <div class="topnav">
            <a href="index.php" <?php if ($active_page == 'login'){ echo 'class="active"';};?>>Login</a>
            <a href="registration.php" <?php if ($active_page == 'registration'){ echo 'class="active"';};?>>Register </a>
            <a href="editprofile.php" <?php if ($active_page == 'editprofile'){ echo 'class="active"';};?>>Edit profile</a>
            <a href="modal.php" <?php if ($active_page == 'modal'){ echo 'class="active"';};?>>Modal Example</a>
            <div class="topnav-right">
                <a href="logout.php" <?php if ($active_page == 'logout'){ echo 'class="active"';};?>>Logout</a>
                <!--
                <a href="#search">Search</a>
                <a href="#about">About</a>
                -->
            </div>
        </div>
