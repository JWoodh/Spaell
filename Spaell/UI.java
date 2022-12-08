package Spaell;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

// import del2.Spaell.Game.ChoiceHandler;
// import Game.ChoiceHandler;

import java.awt.Color;
import java.awt.Font;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.GridBagLayout;


public class UI {
    
    JFrame window;
    JPanel titleNamePanel, startButtonPanel, mainTextPanel, choiceButtonPanel, playerPanel, infoPanel, enemyPanel, enemyStatsPanel, playerStatsPanel, actionPanel, pussyPanel, namePanel, nameButtonPanel;
    JLabel titleNameLabel, generalHealthLabel, generalHealthNumberLabel, enemyHealthLabel, enemyHealthNumberLabel, enemyDamageLabel, enemyDamageNumberLabel,fightHealthLabel, fightHealthNumberLabel, damageLabel, damageNumberLabel, weaponLabel, weaponNameLabel;
    JButton startButton, nameButton, choice1, choice2, choice3, attackButton, itemButton, runButton;
    JTextArea nameArea, mainTextArea, fighTextArea;
    Container con;
    Font titleFont = new Font("Times New Roman", Font.PLAIN,90);
    Font normalFont = new Font("Times New Roman", Font.PLAIN, 26);
    Font nameFont = new Font("Times New Roman", Font.PLAIN, 40);
    Color Fore = new Color(255,228,0);
    Color Back = new Color(12,36,22);
    Color Disabled = new Color(24,41,31);
    Border border = BorderFactory.createLineBorder(Fore);


    public void createUI(Game.ChoiceHandler cHandler){

        window = new JFrame();
        window.setSize(800,600);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setBackground(Back);
        window.setLayout(null);
        con = window.getContentPane();

        // Title Layout

        titleNamePanel = new JPanel();
        titleNamePanel.setBounds(100,100,600,500);
        titleNamePanel.setBackground(Back);
        titleNameLabel = new JLabel("Spæll");
        titleNameLabel.setForeground(Fore);
        titleNameLabel.setFont(titleFont);
        titleNamePanel.add(titleNameLabel);

        startButtonPanel = new JPanel(); 
        startButtonPanel.setBounds(300,400,200,100);
        startButtonPanel.setBackground(Back);
        startButton = new JButton("START");
        startButton.setBackground(Back);
        startButton.setForeground(Fore);
        startButton.setFont(normalFont);
        startButton.setFocusPainted(false);
        startButton.addActionListener(cHandler);
        startButton.setActionCommand("start");
        startButtonPanel.add(startButton);

        con.add(titleNamePanel);
        con.add(startButtonPanel);

        // Name Layout

        namePanel = new JPanel();
        namePanel.setBounds(200,100,400,200);
        namePanel.setBackground(Back);
        nameArea = new JTextArea("Enter name here");
        nameArea.setForeground(Fore);
        nameArea.setBackground(Back);
        nameArea.setFont(nameFont);
        nameArea.setEditable(true);
        nameArea.setBorder(BorderFactory.createCompoundBorder(new EtchedBorder(), new EmptyBorder(10, 10, 10, 10)));
        namePanel.add(nameArea);

        nameButtonPanel = new JPanel();
        nameButtonPanel.setBounds(300,400,200,100);
        nameButtonPanel.setBackground(Back);
        nameButton = new JButton("DONE");
        nameButton.setBackground(Back);
        nameButton.setForeground(Fore);
        nameButton.setFont(normalFont);
        nameButton.setFocusPainted(false);
        nameButton.addActionListener(cHandler);
        nameButton.setActionCommand("name");
        nameButtonPanel.add(nameButton);

        con.add(namePanel);
        con.add(nameButtonPanel);


        // General Layout

        mainTextPanel = new JPanel();
        mainTextPanel.setBounds(100,100,600,250);
        mainTextPanel.setBackground(Back);
        con.add(mainTextPanel);

        mainTextArea = new JTextArea("MIM miMI m imimim imm immimimimimi miimmi mimim imimimi mimimimimi mimimi mimi mimimi mimimi mimimimi mimimimi mimimimimi mimi mimi mimimi mimimi mimi imim miim mim\n dnhrtsneddethegsda  th et etr he w et \nerg ert berert4eb e5 nveb5bn ryj ehvc5y\n 4vg3gt btbsd ni ");
        mainTextArea.setBounds(100,100,600,250);
        mainTextArea.setBackground(Back);
        mainTextArea.setForeground(Fore);
        mainTextArea.setFont(normalFont);
        mainTextArea.setLineWrap(true);
        mainTextArea.setWrapStyleWord(true);
        mainTextArea.setEditable(true);
        mainTextArea.setBorder(BorderFactory.createCompoundBorder(new EtchedBorder(), new EmptyBorder(10, 10, 10, 10)));
        mainTextPanel.add(mainTextArea);

        choiceButtonPanel = new JPanel();
        choiceButtonPanel.setBounds(250,350,300,150);
        choiceButtonPanel.setBackground(Back);
        choiceButtonPanel.setLayout(new GridLayout(3,1));
        con.add(choiceButtonPanel);

        choice1 = new JButton("Choice 1");
        choice1.setBackground(Back);
        choice1.setForeground(Fore);
        choice1.setFont(normalFont);
        choice1.setFocusPainted(false);
        choice1.addActionListener(cHandler);
        choice1.setActionCommand("c1");
        choiceButtonPanel.add(choice1);

        choice2 = new JButton(" Choice 2");
        choice2.setBackground(Back);
        choice2.setForeground(Fore);
        choice2.setFont(normalFont);
        choice2.setFocusPainted(false);
        choice2.addActionListener(cHandler);
        choice2.setActionCommand("c2");
        choiceButtonPanel.add(choice2);

        choice3 = new JButton("CHoice 3");
        choice3.setBackground(Back);
        choice3.setForeground(Fore);
        choice3.setFont(normalFont);
        choice3.setFocusPainted(false);
        choice3.addActionListener(cHandler);
        choice3.setActionCommand("c3");
        choiceButtonPanel.add(choice3);

        playerPanel = new JPanel();
        playerPanel.setBounds(130,15,300,50);
        playerPanel.setBackground(Back);
        playerPanel.setLayout(new GridLayout(1,2));
        con.add(playerPanel);

        generalHealthLabel = new JLabel("HP: ");
        generalHealthLabel.setFont(normalFont);
        generalHealthLabel.setForeground(Fore);
        playerPanel.add(generalHealthLabel);
        
        generalHealthNumberLabel = new JLabel(""+100);
        generalHealthNumberLabel.setFont(normalFont);
        generalHealthNumberLabel.setForeground(Fore);
        playerPanel.add(generalHealthNumberLabel);

        // Fight Layout

        infoPanel = new JPanel();
        infoPanel.setBounds(0,0,400,375);
        infoPanel.setBackground(Back);
        infoPanel.setLayout(new GridBagLayout());
        infoPanel.setBorder(BorderFactory.createCompoundBorder(new EtchedBorder(), new EmptyBorder(10, 10, 10, 10)));
        con.add(infoPanel);

        fighTextArea = new JTextArea("mjau");
        fighTextArea.setBounds(0,150,300,225);
        fighTextArea.setBackground(Back);
        fighTextArea.setForeground(Fore);
        fighTextArea.setFont(normalFont);
        fighTextArea.setLineWrap(true);
        fighTextArea.setWrapStyleWord(true);
        fighTextArea.setLayout(null);
        infoPanel.add(fighTextArea);

        enemyPanel = new JPanel();
        enemyPanel.setBounds(400,100,400,200);
        enemyPanel.setBackground(Back);
        enemyPanel.setBorder(BorderFactory.createCompoundBorder(new EtchedBorder(), new EmptyBorder(10, 10, 10, 10)));
        con.add(enemyPanel);

        enemyStatsPanel = new JPanel();
        enemyStatsPanel.setBounds(400,0,400,100);
        enemyStatsPanel.setBackground(Back);
        enemyStatsPanel.setLayout(new GridLayout(1,4));
        enemyStatsPanel.setBorder(BorderFactory.createCompoundBorder(new EtchedBorder(), new EmptyBorder(10, 10, 10, 10)));
        con.add(enemyStatsPanel);

        enemyHealthLabel = new JLabel("HP: ");
        enemyHealthLabel.setFont(normalFont);
        enemyHealthLabel.setForeground(Fore);
        enemyStatsPanel.add(enemyHealthLabel);

        enemyHealthNumberLabel = new JLabel("somethin aint right" + 80);
        enemyHealthNumberLabel.setFont(normalFont);
        enemyHealthNumberLabel.setForeground(Fore);
        enemyStatsPanel.add(enemyHealthNumberLabel);

        enemyDamageLabel = new JLabel("Dmg: ");
        enemyDamageLabel.setFont(normalFont);
        enemyDamageLabel.setForeground(Fore);
        enemyStatsPanel.add(enemyDamageLabel);

        enemyDamageNumberLabel = new JLabel("not right" + 4);
        enemyDamageNumberLabel.setFont(normalFont);
        enemyDamageNumberLabel.setForeground(Fore);
        enemyStatsPanel.add(enemyDamageNumberLabel);

        playerStatsPanel = new JPanel();
        playerStatsPanel.setBounds(0,375,400,175);
        playerStatsPanel.setBackground(Back);
        playerStatsPanel.setLayout(new GridLayout(0,2));
        playerStatsPanel.setBorder(BorderFactory.createCompoundBorder(new EtchedBorder(), new EmptyBorder(10, 10, 10, 10)));
        con.add(playerStatsPanel);

        fightHealthLabel = new JLabel("HP: ");
        fightHealthLabel.setFont(normalFont);
        fightHealthLabel.setForeground(Fore);
        playerStatsPanel.add(fightHealthLabel);

        fightHealthNumberLabel = new JLabel("nope"+ 100);
        fightHealthNumberLabel.setFont(normalFont);
        fightHealthNumberLabel.setForeground(Fore);
        playerStatsPanel.add(fightHealthNumberLabel);

        damageLabel = new JLabel("Dmg: ");
        damageLabel.setFont(normalFont);
        damageLabel.setForeground(Fore);
        playerStatsPanel.add(damageLabel);

        damageNumberLabel = new JLabel("mjau" + 2);
        damageNumberLabel.setFont(normalFont);
        damageNumberLabel.setForeground(Fore);
        playerStatsPanel.add(damageNumberLabel);

        weaponLabel = new JLabel("Weapon: ");
        weaponLabel.setFont(normalFont);
        weaponLabel.setForeground(Fore);
        playerStatsPanel.add(weaponLabel);

        weaponNameLabel = new JLabel("error");
        weaponNameLabel.setFont(normalFont);
        weaponNameLabel.setForeground(Fore);
        playerStatsPanel.add(weaponNameLabel);

        actionPanel = new JPanel();
        actionPanel.setBounds(400,300,400,150);
        actionPanel.setBackground(Back);
        actionPanel.setLayout(null);
        con.add(actionPanel);

        attackButton = new JButton("Attack");
        attackButton.setBounds(65,75,270,75);
        attackButton.setBackground(Back);
        attackButton.setForeground(Fore);
        attackButton.setFont(normalFont);
        attackButton.setFocusPainted(false);
        attackButton.addActionListener(cHandler);
        attackButton.setActionCommand("attack");
        actionPanel.add(attackButton);

        pussyPanel = new JPanel();
        pussyPanel.setBounds(400,450,400,150);
        pussyPanel.setBackground(Back);
        pussyPanel.setLayout(null);
        con.add(pussyPanel);

        itemButton = new JButton("Item");
        itemButton.setBounds(75,0,125,50);
        itemButton.setBackground(Back);
        itemButton.setForeground(Fore);
        itemButton.setFont(normalFont);
        itemButton.setFocusPainted(false);
        itemButton.addActionListener(cHandler);
        itemButton.setActionCommand("item");
        pussyPanel.add(itemButton);

        runButton = new JButton("Run");
        runButton.setBounds(200,0,125,50);
        runButton.setBackground(Back);
        runButton.setForeground(Fore);
        runButton.setFont(normalFont);
        runButton.setFocusPainted(false);
        runButton.addActionListener(cHandler);
        runButton.setActionCommand("run");
        pussyPanel.add(runButton);



        window.setVisible(true);
    }
}
