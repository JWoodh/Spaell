<?php
$active_page = 'modal';
include_once 'header.php';
?>



<style>

    /* The Modal (background) */
.modal {
  display: none; /* Hidden by default */
  position: fixed; /* Stay in place */
  z-index: 1; /* Sit on top */
  left: 0;
  top: 0;
  width: 100%; /* Full width */
  height: 100%; /* Full height */
  overflow: auto; /* Enable scroll if needed */
  background-color: rgb(0,0,0); /* Fallback color */
  background-color: rgba(0,0,0,0.4); /* Black w/ opacity */
}

/* Modal Content/Box */
.modal-content {
  background-color: #fefefe;
  margin: 15% auto; /* 15% from the top and centered */
  padding: 20px;
  border: 1px solid #888;
  width: 80%; /* Could be more or less, depending on screen size */
}

/* The Close Button */
.close {
  color: #aaa;
  float: right;
  font-size: 28px;
  font-weight: bold;
}

.close:hover,
.close:focus {
  color: black;
  text-decoration: none;
  cursor: pointer;
}
</style>

<button id="myBtn">Open Modal</button>

<div id="myModal" class="modal">

  <div class="modal-content">
    <span class="close">&times;</span>
    <p>Some text in the Modal..</p>
    <p>Hei jeg er en modals</p>
  </div>

</div>
<!--
    <div id="myModal" class="modal">
        <div class="modal-content">
            
            <p>Vennligst logg inn:</p>
            <form method="post">
                <label for="brukernavn">Brukernavn:</label>
                <input type="text" name="brukernavn" /><br />
                <label for="passord">Passord:</label>
                <input type="password" name="passord" /><br />
                
                <input type="submit" value="Logg inn" name="submit" />
            </form>
            <p>Eller klikk <a href="registration.php">her</a> for å registrere ny bruker
        </div>
    </div>
    
    
-->

<script type="text/javascript">

    // Get the modal
var modal = document.getElementById("myModal");

// Get the button that opens the modal
var btn = document.getElementById("myBtn");

// Get the <span> element that closes the modal
var span = document.getElementsByClassName("close")[0];

// When the user clicks on the button, open the modal
btn.onclick = function() {
  modal.style.display = "block";
}

// When the user clicks on <span> (x), close the modal
span.onclick = function() {
  modal.style.display = "none";
}

// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
  if (event.target == modal) {
    modal.style.display = "none";
  }
}
</script>