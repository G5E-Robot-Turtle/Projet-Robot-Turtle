package ui;


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import cell.*;

public class Window extends JFrame {
    private int line = 8;
    private int column = 8;
    private int cellSize = 80;    //chaque cellule fait 80 de largeur et de hauteur
    private JPanel gridContent = new JPanel();        //Le conteneur principal
    private CellPanel[][] emptyCells = new CellPanel[line][column];
    private GridBagLayout gbl = new GridBagLayout();
    private GridBagConstraints gbc = new GridBagConstraints();   //pour ordonner la position des JPanel dans le conteneur principal

    private ArrayList<String> namesAccepted = new ArrayList<>() {
        {
            add("ICE");
            add("WALL");
            add("jewelBlue");
            add("jewelGreen");
            add("jewelRed");
            add("turtle1");
            add("turtle2");
            add("turtle3");
            add("turtle4");
            add("empty");
            add("background");   //pas encore utilisé
        }
    }; //pour charger les images


    public Window(int nbPlayers) {
        this.setTitle("Robot Turtle");
        this.setSize(800, 800);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    //bouton fermeture permet de quitter le programme
        this.setLocationRelativeTo(null);      //centrer la fenêtre au début

        gridContent.setBounds(50, 50, 680, 680);
//        gridContent.setPreferredSize(new Dimension(680, 680));   //ne change rien ?
        gridContent.setBackground(Color.WHITE);
        //On définit le layout manager
        gridContent.setLayout(gbl);
        fillCellsArray(line, column);

        initContent(line, column);  //ajoute les emptyCells au conteneur principal

        if (nbPlayers == 2) {
            CellPanel turtle1 = new CellPanel("turtle1");  //nom de l'image à charger en paramètre du constructeur
            putCellIntoPosition(turtle1, 1, 0);  //cette fonction remplace les 4 lignes en commentaire ci-dessous

            CellPanel turtle2 = new CellPanel("turtle2");
            putCellIntoPosition(turtle2, 5, 0);

            CellPanel jewel = new CellPanel("jewelGreen");
            putCellIntoPosition(jewel, 3, 7);

            for (int line = 0; line < 8; line++) {    //mettre les murs
                CellPanel stoneWall = new CellPanel("WALL");
                putCellIntoPosition(stoneWall, 7, line);
            }

        } else if (nbPlayers == 3) {
            CellPanel turtle1 = new CellPanel("turtle1");  //nom de l'image à charger en paramètre du constructeur
            putCellIntoPosition(turtle1, 0, 0);  //cette fonction remplace les 4 lignes en commentaire ci-dessous

            CellPanel turtle2 = new CellPanel("turtle2");
            putCellIntoPosition(turtle2, 3, 0);

            CellPanel turtle3 = new CellPanel("turtle3");
            putCellIntoPosition(turtle3, 6, 0);

            CellPanel jewel = new CellPanel("jewelRed");
            putCellIntoPosition(jewel, 0, 7);

            CellPanel jewel2 = new CellPanel("jewelGreen");
            putCellIntoPosition(jewel2, 3, 7);

            CellPanel jewel3 = new CellPanel("jewelBlue");
            putCellIntoPosition(jewel3, 6, 7);

            for (int line = 0; line < 8; line++) {     //mettre les murs
                CellPanel stoneWall = new CellPanel("WALL");
                putCellIntoPosition(stoneWall, 7, line);
            }

        } else if (nbPlayers == 4) {
            CellPanel turtle1 = new CellPanel("turtle1");  //nom de l'image à charger en paramètre du constructeur
            putCellIntoPosition(turtle1, 0, 0);

            CellPanel turtle2 = new CellPanel("turtle2");
            putCellIntoPosition(turtle2, 2, 0);

            CellPanel turtle3 = new CellPanel("turtle3");
            putCellIntoPosition(turtle3, 5, 0);

            CellPanel turtle4 = new CellPanel("turtle4");
            putCellIntoPosition(turtle4, 7, 0);

            CellPanel jewel = new CellPanel("jewelRed");
            putCellIntoPosition(jewel, 1, 7);

            CellPanel jewel3 = new CellPanel("jewelBlue");
            putCellIntoPosition(jewel3, 6, 7);
        }

        //On ajoute le conteneur au JFrame
        this.setContentPane(gridContent);
        this.setVisible(true);

    }

    public void fillCellsArray(int line, int column) {    //mettre des JPanel blanc en fond de grille
        for (int i = 0; i < line; i++) {
            for (int j = 0; j < column; j++) {
                CellPanel cellEmpty = new CellPanel("empty");
//                cellEmpty.setBackground(Color.WHITE);
                cellEmpty.setPreferredSize(new Dimension(cellSize, cellSize));
                cellEmpty.setBorder(BorderFactory.createLineBorder(Color.black));   //encadrer en noir la cellule
                emptyCells[i][j] = cellEmpty;               //cellEmpty est une cellule blanche encadré en noir
            }
        }
    }

    public void initContent(int line, int column) {
        //La taille en hauteur et en largeur   //par défaut vaut 1
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        for (int i = 0; i < line; i++) {
            for (int j = 0; j < column; j++) {
                gbc.gridx = j;  //On positionne la case de départ du composant
                gbc.gridy = i;
                gridContent.add(emptyCells[i][j], gbc);
            }
        }
    }

    public void putCellIntoPosition(CellPanel cell, int x, int y) {
        gridContent.remove(emptyCells[y][x]);
        gbc.gridx = x;  //On positionne la case de départ du composant
        gbc.gridy = y;
        gridContent.add(cell, gbc);
    }

    public void putCellIntoPositionWithStringName(String name, int x, int y) {
        CellPanel cell = new CellPanel(name);
        cell.setBorder(BorderFactory.createLineBorder(Color.black));   //encadrer en noir la cellule
        putCellIntoPosition(cell, x, y);
    }

    public void refresh(Cell[][] grid) {     // actualise la l'interface graphique, cette fonction besoin de la grille de la console
        gridContent.removeAll();
        String name = "";
        gridContent.setLayout(gbl);
        for (int i = 0; i < line; i++) {
            for (int j = 0; j < column; j++) {
                name = grid[i][j].getName();
                name = rectifyName(name);
                if (namesAccepted.contains(name)) {
                    putCellIntoPositionWithStringName(name, j, i);
                } else {
                    System.out.println("\n\nError, incorrect name\n");
                }
            }
        }
        this.setContentPane(gridContent);
        this.setVisible(true);
    }


    public String rectifyName(String name) {   //rectifier le nom de la grille pour adapter au nom des images .png
        if (name.equals("Player 1")) {
            name = "turtle1";
        } else if (name.equals("Player 2")) {
            name = "turtle2";
        } else if (name.equals("Player 3")) {
            name = "turtle3";
        } else if (name.equals("Player 4")) {
            name = "turtle4";
        } else if (name.equals("Jewel")) {
            name = "jewelGreen";   //CellPanel modifiera
        } else if (name.equals("EmptyZone")) {
            name = "empty";
        } else if (name.equals("Stone wall")) {
            name = "WALL";
        } else if (name.equals("Ice wall")) {
            name = "ICE";
        }
        return name;
    }
}
