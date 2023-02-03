package Spaell;

public class VisibilityManager {
    
    UI ui;

    public VisibilityManager(UI userInterface){
        ui = userInterface; //Henter ut ui-en
    }
    //Viser tittelskjermen, gjør de tittelrelaterte panelene synlige og skjuler alle andre
    public void showTitleScreen(){
        ui.titleNamePanel.setVisible(true);
        ui.startButtonPanel.setVisible(true);

        ui.backPanel.setVisible(false);
        ui.userPanel.setVisible(false);
        ui.nameButtonPanel.setVisible(false);

        ui.generalTextPanel.setVisible(false);
        ui.choiceButtonPanel.setVisible(false);
        ui.playerPanel.setVisible(false);

        ui.infoPanel.setVisible(false);
        ui.enemyPanel.setVisible(false);
        ui.enemyStatsPanel.setVisible(false);
        ui.playerStatsPanel.setVisible(false);
        ui.actionPanel.setVisible(false);
        ui.pussyPanel.setVisible(false);
    }

    //Viser navnskjermen, gjør de navnrelaterte panelene synlige og skjuler alle andre
    public void nameLayout(){
        ui.titleNamePanel.setVisible(false);
        ui.startButtonPanel.setVisible(false);

        ui.backPanel.setVisible(true);
        ui.userPanel.setVisible(true);
        ui.nameButtonPanel.setVisible(true);
    }

    //Viser den generelle skjermen, gjør de relaterte panelene synlige og skjuler alle andre
    public void generalLayout(){
        ui.backPanel.setVisible(false);
        ui.userPanel.setVisible(false);
        ui.nameButtonPanel.setVisible(false);

        ui.generalTextPanel.setVisible(true);
        ui.choiceButtonPanel.setVisible(true);
        ui.playerPanel.setVisible(true);

        ui.infoPanel.setVisible(false);
        ui.enemyPanel.setVisible(false);
        ui.enemyStatsPanel.setVisible(false);
        ui.playerStatsPanel.setVisible(false);
        ui.actionPanel.setVisible(false);
        ui.pussyPanel.setVisible(false);
    }

    //Viser kampskjermen, gjør de kamprelaterte panelene synlige og skjuler alle andre
    public void fightLayout(){
        ui.titleNamePanel.setVisible(false);
        ui.startButtonPanel.setVisible(false);

        ui.generalTextPanel.setVisible(false);
        ui.choiceButtonPanel.setVisible(false);
        ui.playerPanel.setVisible(false);

        ui.infoPanel.setVisible(true);
        ui.enemyPanel.setVisible(true);
        ui.enemyStatsPanel.setVisible(true);
        ui.playerStatsPanel.setVisible(true);
        ui.actionPanel.setVisible(true);
        ui.pussyPanel.setVisible(true);
    }


}
