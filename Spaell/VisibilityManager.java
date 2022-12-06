package Spaell;

public class VisibilityManager {
    
    UI ui;

    public VisibilityManager(UI userInterface){
        ui = userInterface;
    }
    public void showTitleScreen(){
        ui.titleNamePanel.setVisible(true);
        ui.startButtonPanel.setVisible(true);

        ui.namePanel.setVisible(false);
        ui.nameButtonPanel.setVisible(false);

        ui.mainTextPanel.setVisible(false);
        ui.choiceButtonPanel.setVisible(false);
        ui.playerPanel.setVisible(false);

        ui.infoPanel.setVisible(false);
        ui.enemyPanel.setVisible(false);
        ui.enemyStatsPanel.setVisible(false);
        ui.playerStatsPanel.setVisible(false);
        ui.actionPanel.setVisible(false);
        ui.pussyPanel.setVisible(false);
    }
    public void nameLayout(){
        ui.titleNamePanel.setVisible(false);
        ui.startButtonPanel.setVisible(false);

        ui.namePanel.setVisible(true);
        ui.nameButtonPanel.setVisible(true);
    }
    public void generalLayout(){
        ui.namePanel.setVisible(false);
        ui.nameButtonPanel.setVisible(false);

        ui.mainTextPanel.setVisible(true);
        ui.choiceButtonPanel.setVisible(true);
        ui.playerPanel.setVisible(true);

        ui.infoPanel.setVisible(false);
        ui.enemyPanel.setVisible(false);
        ui.enemyStatsPanel.setVisible(false);
        ui.playerStatsPanel.setVisible(false);
        ui.actionPanel.setVisible(false);
        ui.pussyPanel.setVisible(false);
    }
    public void fightLayout(){
        ui.titleNamePanel.setVisible(false);
        ui.startButtonPanel.setVisible(false);

        ui.mainTextPanel.setVisible(false);
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
