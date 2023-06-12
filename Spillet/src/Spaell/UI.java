package Spaell;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import java.awt.Color;
import java.awt.Font;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.GridBagLayout;

//Aller først vil jeg si at nesten all teksten blir oppdatert underveis i spillet og det som skrives her
//er placeholders så jeg kan vite om noe har gått galt. Ikke tenk for mye på hva som står :)

public class UI {

    // Definerer alle variablene
    JFrame window;
    JPanel titleNamePanel, startButtonPanel, generalTextPanel, choiceButtonPanel, playerPanel, infoPanel, enemyPanel, enemyStatsPanel, playerStatsPanel, actionPanel, pussyPanel, backPanel, userPanel, nameButtonPanel;
    JLabel titleNameLabel, generalHealthLabel, generalHealthNumberLabel, spriteLabel, enemyHealthLabel, enemyHealthNumberLabel, enemyDamageLabel, enemyDamageNumberLabel,fightHealthLabel, fightHealthNumberLabel, damageLabel, damageNumberLabel, weaponLabel, weaponNameLabel;
    JButton newButton, continueButton, backButton, nameButton, choice1, choice2, choice3, attackButton, itemButton, runButton;
    JTextArea nameArea, passwordArea, generalTextAreaa, fighTextArea;
    Container con;
    GridLayout userLayout, startLayout;

    //Alle fontene og fargene som brukes
    Font titleFont = new Font("Times New Roman", Font.PLAIN,90);
    Font normalFont = new Font("Times New Roman", Font.PLAIN, 26);
    Font nameFont = new Font("Times New Roman", Font.PLAIN, 40);
    Color Fore = new Color(255,228,0);
    Color Back = new Color(12,36,22);
    Color Disabled = new Color(24,41,31);

    // BufferedImage spriteImage;

    //Funksjonen som lager all UI-en spillet bruker
    public void createUI(Game.ChoiceHandler cHandler){

        //Lager vinduet
        window = new JFrame("Spæll");
        window.setSize(800,600); // Setter størrelsen på vinduet
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Gjør så vinduet kan lukkes
        window.getContentPane().setBackground(Back); //Setter bakgrunnsfarge 
        window.setLayout(null); //Fjerner innebygde layout
        con = window.getContentPane();

        // Title Layout

        //Tittelen på spillet
        titleNamePanel = new JPanel();
        titleNamePanel.setBounds(100,100,600,200);
        titleNamePanel.setBackground(Back);
        titleNameLabel = new JLabel("Spæll");
        titleNameLabel.setForeground(Fore);
        titleNameLabel.setFont(titleFont);
        titleNamePanel.add(titleNameLabel);

        con.add(titleNamePanel);

        //Start-knapp panelet
        startLayout = new GridLayout(2,1);
        startLayout.setVgap(15);
        startButtonPanel = new JPanel(); 
        startButtonPanel.setBounds(300,375,200,125);
        startButtonPanel.setBackground(Back);
        startButtonPanel.setLayout(startLayout);

        //Startknappen
        newButton = new JButton("NEW GAME");
        // newButton.setBounds(5,0,200,50);
        newButton.setBackground(Back);
        newButton.setForeground(Fore);
        newButton.setFont(normalFont);
        newButton.setFocusPainted(false); //Fjerner unødvendige linjer som følger med knapper
        newButton.addActionListener(cHandler); // Gir knappen en funksjon
        newButton.setActionCommand("new"); //Setter kommmandoen knappen defineres av
        startButtonPanel.add(newButton); //Legger knappen på panelet

        continueButton = new JButton("CONTINUE");
        // continueButton.setBounds(0,75,200,50);
        continueButton.setBackground(Back);
        continueButton.setForeground(Fore);
        continueButton.setFont(normalFont);
        continueButton.setFocusPainted(false);
        continueButton.addActionListener(cHandler);
        continueButton.setActionCommand("continue");
        startButtonPanel.add(continueButton);

        con.add(startButtonPanel);

        // Name Layout

        // Panelet for tilbake knappen
        backPanel = new JPanel();
        backPanel.setBounds(25,25,200,50);
        backPanel.setBackground(Back);

        // Tilbakeknappen
        backButton = new JButton("BACK");
        backButton.setBackground(Back);
        backButton.setForeground(Fore);
        backButton.setFont(normalFont);
        backButton.setFocusPainted(false);
        backButton.setBorder(null);
        backButton.addActionListener(cHandler);
        backButton.setActionCommand("back");
        backPanel.add(backButton);

        //Panelet til område for å sette navn
        userLayout = new GridLayout(3,1);
        userLayout.setVgap(25);

        userPanel = new JPanel();
        userPanel.setBounds(200,115,400,300);
        userPanel.setLayout(userLayout);
        userPanel.setBackground(Back);

        //Området man setter navnet sitt
        nameArea = new JTextArea("Enter name here");
        nameArea.setBounds(0,0,400,100);
        nameArea.setForeground(Fore);
        nameArea.setBackground(Back);
        nameArea.setFont(nameFont);
        nameArea.setEditable(true);
        nameArea.setBorder(BorderFactory.createCompoundBorder(new EtchedBorder(), new EmptyBorder(10, 10, 10, 10))); //Lager en stilig linje rundt området
        userPanel.add(nameArea);

        passwordArea = new JTextArea("Enter password here");
        passwordArea.setBounds(0,200,400,100);
        passwordArea.setForeground(Fore);
        passwordArea.setBackground(Back);
        passwordArea.setFont(nameFont);
        passwordArea.setEditable(true);
        passwordArea.setBorder(BorderFactory.createCompoundBorder(new EtchedBorder(), new EmptyBorder(10, 10, 10, 10))); //Lager en stilig linje rundt området
        userPanel.add(passwordArea);

        //Panelet til knappen for å starte spillet
        nameButtonPanel = new JPanel();
        nameButtonPanel.setBounds(300,400,200,100);
        nameButtonPanel.setBackground(Back);

        //Knappen som starter spillet
        nameButton = new JButton("DONE");
        nameButton.setBackground(Back);
        nameButton.setForeground(Fore);
        nameButton.setFont(normalFont);
        nameButton.setFocusPainted(false);
        nameButton.addActionListener(cHandler);
        nameButton.setActionCommand("name");
        nameButtonPanel.add(nameButton);

        con.add(backPanel);
        con.add(nameButtonPanel);
        con.add(userPanel);

        // General Layout

        //Panelet for generell infotekst
        generalTextPanel = new JPanel();
        generalTextPanel.setBounds(100,100,600,250);
        generalTextPanel.setBackground(Back);
        con.add(generalTextPanel);

        //Generell infotekst
        generalTextAreaa = new JTextArea("MIM miMI m imimim imm immimimimimi miimmi mimim imimimi mimimimimi mimimi mimi mimimi mimimi mimimimi mimimimi mimimimimi mimi mimi mimimi mimimi mimi imim miim mim\n dnhrtsneddethegsda  th et etr he w et \nerg ert berert4eb e5 nveb5bn ryj ehvc5y\n 4vg3gt btbsd ni ");
        generalTextAreaa.setBounds(100,100,600,250);
        generalTextAreaa.setBackground(Back);
        generalTextAreaa.setForeground(Fore);
        generalTextAreaa.setFont(normalFont);
        generalTextAreaa.setLineWrap(true); //Tekst wrapper automatisk rundt når teksten går utenfor området sitt
        generalTextAreaa.setWrapStyleWord(true); //Fjerner whitespace ved linjeskifte
        generalTextAreaa.setEditable(false);
        generalTextAreaa.setBorder(BorderFactory.createCompoundBorder(new EtchedBorder(), new EmptyBorder(10, 10, 10, 10)));
        generalTextPanel.add(generalTextAreaa);

        //Panelet for hovedknappene
        choiceButtonPanel = new JPanel();
        choiceButtonPanel.setBounds(250,350,300,150);
        choiceButtonPanel.setBackground(Back);
        choiceButtonPanel.setLayout(new GridLayout(3,1)); //Legger opp hvordan knappene skal stå
        con.add(choiceButtonPanel);

        //Øverste valgknappen
        choice1 = new JButton("Choice 1");
        choice1.setBackground(Back);
        choice1.setForeground(Fore);
        choice1.setFont(normalFont);
        choice1.setFocusPainted(false);
        choice1.addActionListener(cHandler);
        choice1.setActionCommand("c1");
        choiceButtonPanel.add(choice1);

        //Midterste valgknappen
        choice2 = new JButton(" Choice 2");
        choice2.setBackground(Back);
        choice2.setForeground(Fore);
        choice2.setFont(normalFont);
        choice2.setFocusPainted(false);
        choice2.addActionListener(cHandler);
        choice2.setActionCommand("c2");
        choiceButtonPanel.add(choice2);

        //Nederste valgknappen
        choice3 = new JButton("CHoice 3");
        choice3.setBackground(Back);
        choice3.setForeground(Fore);
        choice3.setFont(normalFont);
        choice3.setFocusPainted(false);
        choice3.addActionListener(cHandler);
        choice3.setActionCommand("c3");
        choiceButtonPanel.add(choice3);

        //Panelet for generelle stats, vanligvis spillerens liv
        playerPanel = new JPanel();
        playerPanel.setBounds(130,15,300,50);
        playerPanel.setBackground(Back);
        playerPanel.setLayout(new GridLayout(1,2));
        con.add(playerPanel);

        //Teksten "hp:"
        generalHealthLabel = new JLabel("HP: ");
        generalHealthLabel.setFont(normalFont);
        generalHealthLabel.setForeground(Fore);
        playerPanel.add(generalHealthLabel);
        
        //Tallet som vises 
        generalHealthNumberLabel = new JLabel(""+100);
        generalHealthNumberLabel.setFont(normalFont);
        generalHealthNumberLabel.setForeground(Fore);
        playerPanel.add(generalHealthNumberLabel);

        // Fight Layout

        //Infopanelet for når man slåss
        infoPanel = new JPanel();
        infoPanel.setBounds(0,0,400,375);
        infoPanel.setBackground(Back);
        infoPanel.setLayout(new GridBagLayout());
        infoPanel.setBorder(BorderFactory.createCompoundBorder(new EtchedBorder(), new EmptyBorder(10, 10, 10, 10)));
        con.add(infoPanel);

        //Teksten for info
        fighTextArea = new JTextArea("mjau");
        fighTextArea.setBounds(0,150,300,225);
        fighTextArea.setBackground(Back);
        fighTextArea.setForeground(Fore);
        fighTextArea.setFont(normalFont);
        fighTextArea.setLineWrap(true);
        fighTextArea.setWrapStyleWord(true);
        fighTextArea.setLayout(null);
        infoPanel.add(fighTextArea);

        //Panelet for alt fiende-relatert
        enemyPanel = new JPanel();
        enemyPanel.setBounds(400,100,400,200);
        enemyPanel.setBackground(Back);
        enemyPanel.setBorder(BorderFactory.createCompoundBorder(new EtchedBorder(), new EmptyBorder(10, 10, 10, 10)));
        con.add(enemyPanel);

        // Sprite
        spriteLabel = new JLabel(new ImageIcon("img/placeholder.png"));
        enemyPanel.add(spriteLabel);

        //Fiendens stats, health og damage
        enemyStatsPanel = new JPanel();
        enemyStatsPanel.setBounds(400,0,400,100);
        enemyStatsPanel.setBackground(Back);
        enemyStatsPanel.setLayout(new GridLayout(1,4));
        enemyStatsPanel.setBorder(BorderFactory.createCompoundBorder(new EtchedBorder(), new EmptyBorder(10, 10, 10, 10)));
        con.add(enemyStatsPanel);

        //Teksten "hp:"
        enemyHealthLabel = new JLabel("HP: ");
        enemyHealthLabel.setFont(normalFont);
        enemyHealthLabel.setForeground(Fore);
        enemyStatsPanel.add(enemyHealthLabel);

        //Fiendens liv
        enemyHealthNumberLabel = new JLabel("somethin aint right" + 80);
        enemyHealthNumberLabel.setFont(normalFont);
        enemyHealthNumberLabel.setForeground(Fore);
        enemyStatsPanel.add(enemyHealthNumberLabel);

        //Teksten "Dmg: "
        enemyDamageLabel = new JLabel("Dmg: ");
        enemyDamageLabel.setFont(normalFont);
        enemyDamageLabel.setForeground(Fore);
        enemyStatsPanel.add(enemyDamageLabel);

        //Fiendens skade
        enemyDamageNumberLabel = new JLabel("not right" + 4);
        enemyDamageNumberLabel.setFont(normalFont);
        enemyDamageNumberLabel.setForeground(Fore);
        enemyStatsPanel.add(enemyDamageNumberLabel);

        //Alt av spillerens stats
        playerStatsPanel = new JPanel();
        playerStatsPanel.setBounds(0,375,400,175);
        playerStatsPanel.setBackground(Back);
        playerStatsPanel.setLayout(new GridLayout(0,2)); //0 rader betyr så mange som trengs
        playerStatsPanel.setBorder(BorderFactory.createCompoundBorder(new EtchedBorder(), new EmptyBorder(10, 10, 10, 10)));
        con.add(playerStatsPanel);

        //Teksten "hp:"
        fightHealthLabel = new JLabel("HP: ");
        fightHealthLabel.setFont(normalFont);
        fightHealthLabel.setForeground(Fore);
        playerStatsPanel.add(fightHealthLabel);

        //Spillerns hp
        fightHealthNumberLabel = new JLabel("nope"+ 100);
        fightHealthNumberLabel.setFont(normalFont);
        fightHealthNumberLabel.setForeground(Fore);
        playerStatsPanel.add(fightHealthNumberLabel);

        //Teksten "dmg: "
        damageLabel = new JLabel("Dmg: ");
        damageLabel.setFont(normalFont);
        damageLabel.setForeground(Fore);
        playerStatsPanel.add(damageLabel);

        //Spillerens totale skade
        damageNumberLabel = new JLabel("mjau" + 2);
        damageNumberLabel.setFont(normalFont);
        damageNumberLabel.setForeground(Fore);
        playerStatsPanel.add(damageNumberLabel);

        //Teksten "Weapon: "
        weaponLabel = new JLabel("Weapon: ");
        weaponLabel.setFont(normalFont);
        weaponLabel.setForeground(Fore);
        playerStatsPanel.add(weaponLabel);

        //Våpenet til spilleren
        weaponNameLabel = new JLabel("error");
        weaponNameLabel.setFont(normalFont);
        weaponNameLabel.setForeground(Fore);
        playerStatsPanel.add(weaponNameLabel);

        //Panelet for kamplayout-knappene
        actionPanel = new JPanel();
        actionPanel.setBounds(400,300,400,150);
        actionPanel.setBackground(Back);
        actionPanel.setLayout(null);
        con.add(actionPanel);

        //Angrepsknappen
        attackButton = new JButton("Attack");
        attackButton.setBounds(65,75,270,75);
        attackButton.setBackground(Back);
        attackButton.setForeground(Fore);
        attackButton.setFont(normalFont);
        attackButton.setFocusPainted(false);
        attackButton.addActionListener(cHandler);
        attackButton.setActionCommand("attack");
        actionPanel.add(attackButton);

        //Panelet for de andre greiene man kan gjøre under kamp
        //pussy som i feiging, ikke hva annet ellers man kan oppfatte det som
        pussyPanel = new JPanel();
        pussyPanel.setBounds(400,450,400,150);
        pussyPanel.setBackground(Back);
        pussyPanel.setLayout(null);
        con.add(pussyPanel);

        //Knappen for å bruke items
        itemButton = new JButton("Item");
        itemButton.setBounds(75,0,125,50);
        itemButton.setBackground(Back);
        itemButton.setForeground(Fore);
        itemButton.setFont(normalFont);
        itemButton.setFocusPainted(false);
        itemButton.addActionListener(cHandler);
        itemButton.setActionCommand("item");
        pussyPanel.add(itemButton);

        //Knappen for å prøve å stikke av
        runButton = new JButton("Run");
        runButton.setBounds(200,0,125,50);
        runButton.setBackground(Back);
        runButton.setForeground(Fore);
        runButton.setFont(normalFont);
        runButton.setFocusPainted(false);
        runButton.addActionListener(cHandler);
        runButton.setActionCommand("run");
        pussyPanel.add(runButton);


        //Gjør at man kan se vinduet
        window.setVisible(true);
    }
}
