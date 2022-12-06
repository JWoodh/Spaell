package Spaell;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Game {

    UI ui = new UI();
    ChoiceHandler chandler = new ChoiceHandler();
    VisibilityManager vm = new VisibilityManager(ui);
    Room room = new Room(this, ui, vm);


    public String nextPosition1, nextPosition2, nextPosition3;
    public static void main(String[] args){
        new Game();
    }
    
    public Game(){
        
        ui.createUI(chandler);
        vm.showTitleScreen();
    }

    public class ChoiceHandler implements ActionListener{
        public void actionPerformed(ActionEvent event){
            String choice = event.getActionCommand();

            switch(choice){
                case "start" : vm.nameLayout();                    break;
                case "c1"    : room.selectPosition(nextPosition1); break;
                case "c2"    : room.selectPosition(nextPosition2); break;
                case "c3"    : room.selectPosition(nextPosition3); break; 
                case "attack": room.playerAttack();                break; 
                case "run"   : room.escape();                      break;
                case "name"  : vm.generalLayout(); room.start();   break;              
            }
        }
    }

}
