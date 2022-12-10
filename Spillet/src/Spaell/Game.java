package Spaell;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Game {

    // Definerer klassene som brukes generelt og sender over til de som skal bruke
    Spaell.UI ui = new UI(); 
    ChoiceHandler chandler = new ChoiceHandler(); 
    Connect con = new Connect(ui); 
    VisibilityManager vm = new VisibilityManager(ui); 
    Room room = new Room(this, ui, vm, con); 

    //Definerer stringene jeg bruker til å sende til neste skjerm
    public String nextPosition1, nextPosition2, nextPosition3;
    public static void main(String[] args){
        new Game();
    }
    
    public Game(){
        //Lager UI-en og viser startskjermen
        ui.createUI(chandler);
        vm.showTitleScreen();
    }

    // Gir knappene effekt, forteller hvor de skal ta deg
    public class ChoiceHandler implements ActionListener{
        public void actionPerformed(ActionEvent event){
            String choice = event.getActionCommand();

            switch(choice){
                case "start" : vm.nameLayout();                                break;
                case "c1"    : room.selectPosition(nextPosition1);             break;
                case "c2"    : room.selectPosition(nextPosition2);             break;
                case "c3"    : room.selectPosition(nextPosition3);             break; 
                case "attack": room.playerAttack();                            break; 
                case "item"  : ui.fighTextArea.setText("You have no items"); break;
                case "run"   : room.escape();                                  break;
                case "name"  : vm.generalLayout(); room.start();               break;              
            }
        }
    }

}
