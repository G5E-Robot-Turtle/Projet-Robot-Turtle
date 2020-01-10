package ui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Window extends JFrame {
    private int line = 8;
    private int column = 8;
    private int cellSize = 80;    //chaque cellule fait 80 de largeur et de hauteur

    public Window(int nbPlayers) {
        this.setTitle("Robot Turtle");
        this.setSize(640, 640);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    //bouton fermeture permet de quitter le programme
        this.setLocationRelativeTo(null);      //centrer la fenêtre au début

        //Le conteneur principal
        JPanel gridContent = new JPanel();
        gridContent.setPreferredSize(new Dimension(640, 640));   //ne change rien ?
        gridContent.setBackground(Color.WHITE);
        //On définit le layout manager
        gridContent.setLayout(new GridBagLayout());

        JPanel[][] emptyCells = new JPanel[line][column];
        fillCellsArray(line, column, emptyCells);

        GridBagConstraints gbc = new GridBagConstraints();   //pour ordonner la position des JPanel dans le conteneur principal
        initContent(gridContent, emptyCells, gbc, line, column);  //ajoute les emptyCells au conteneur principal

        if (nbPlayers == 2) {
            Cell turtle1 = new Cell("turtle1");  //nom de l'image à charger en paramètre du constructeur
            putCellIntoPosition(gridContent, emptyCells, turtle1, gbc, 1, 0);  //cette fonction remplace les 4 lignes en commentaire ci-dessous
//            gridContent.remove(emptyCells[0][1]);
//            gbc.gridx = 1;  //On positionne la case de départ du composant
//            gbc.gridy = 0;
//            gridContent.add(turtle1, gbc);

            Cell turtle2 = new Cell("turtle2");
            putCellIntoPosition(gridContent, emptyCells, turtle2, gbc, 5, 0);

            Cell jewel = new Cell("jewelGreen");
            putCellIntoPosition(gridContent, emptyCells, jewel, gbc, 3, 7);

            for (int line = 0; line < 8; line++) {    //mettre les murs
                Cell stoneWall = new Cell("WALL");
                putCellIntoPosition(gridContent, emptyCells, stoneWall, gbc, 7, line);
            }

        } else if (nbPlayers == 3) {
            Cell turtle1 = new Cell("turtle1");  //nom de l'image à charger en paramètre du constructeur
            putCellIntoPosition(gridContent, emptyCells, turtle1, gbc, 0, 0);  //cette fonction remplace les 4 lignes en commentaire ci-dessous
//            gridContent.remove(emptyCells[0][1]);
//            gbc.gridx = 0;  //On positionne la case de départ du composant
//            gbc.gridy = 0;
//            gridContent.add(turtle1, gbc);

            Cell turtle2 = new Cell("turtle2");
            putCellIntoPosition(gridContent, emptyCells, turtle2, gbc, 3, 0);

            Cell turtle3 = new Cell("turtle3");
            putCellIntoPosition(gridContent, emptyCells, turtle3, gbc, 6, 0);

            Cell jewel = new Cell("jewelRed");
            putCellIntoPosition(gridContent, emptyCells, jewel, gbc, 0, 7);

            Cell jewel2 = new Cell("jewelGreen");
            putCellIntoPosition(gridContent, emptyCells, jewel2, gbc, 3, 7);

            Cell jewel3 = new Cell("jewelBlue");
            putCellIntoPosition(gridContent, emptyCells, jewel3, gbc, 6, 7);

            for (int line = 0; line < 8; line++) {     //mettre les murs
                Cell stoneWall = new Cell("WALL");
                putCellIntoPosition(gridContent, emptyCells, stoneWall, gbc, 7, line);
            }

        } else if (nbPlayers == 4) {
            Cell turtle1 = new Cell("turtle1");  //nom de l'image à charger en paramètre du constructeur
            putCellIntoPosition(gridContent, emptyCells, turtle1, gbc, 0, 0);

            Cell turtle2 = new Cell("turtle2");
            putCellIntoPosition(gridContent, emptyCells, turtle2, gbc, 2, 0);

            Cell turtle3 = new Cell("turtle3");
            putCellIntoPosition(gridContent, emptyCells, turtle3, gbc, 5, 0);

            Cell turtle4 = new Cell("turtle4");
            putCellIntoPosition(gridContent, emptyCells, turtle4, gbc, 7, 0);

            Cell jewel = new Cell("jewelRed");
            putCellIntoPosition(gridContent, emptyCells, jewel, gbc, 1, 7);

            Cell jewel3 = new Cell("jewelBlue");
            putCellIntoPosition(gridContent, emptyCells, jewel3, gbc, 6, 7);
        }


        //On ajoute le conteneur au JFrame
        this.setContentPane(gridContent);
        this.setVisible(true);

    }

    public void fillCellsArray(int line, int column, JPanel[][] emptyCells) {    //mettre des JPanel blanc en fond de grille
        for (int i = 0; i < line; i++) {
            for (int j = 0; j < column; j++) {
                JPanel cellEmpty = new JPanel();
                cellEmpty.setBackground(Color.WHITE);
                cellEmpty.setPreferredSize(new Dimension(cellSize, cellSize));
                cellEmpty.setBorder(BorderFactory.createLineBorder(Color.black));   //encadrer en noir la cellule
                emptyCells[i][j] = cellEmpty;               //cellEmpty est une cellule blanche encadré en noir
            }
        }
    }

    public void initContent(JPanel content, JPanel[][] cell, GridBagConstraints gbc, int line, int column) {
        //La taille en hauteur et en largeur   //par défaut vaut 1
//        gbc.gridheight = 1;
//        gbc.gridwidth = 1;
        for (int i = 0; i < line; i++) {
            for (int j = 0; j < column; j++) {
                gbc.gridx = j;  //On positionne la case de départ du composant
                gbc.gridy = i;
                content.add(cell[i][j], gbc);
            }
        }
    }

    public void putCellIntoPosition(JPanel gridContent, JPanel[][] emptyCells, Cell cell, GridBagConstraints gbc, int x, int y) {
        gridContent.remove(emptyCells[y][x]);
        gbc.gridx = x;  //On positionne la case de départ du composant
        gbc.gridy = y;
        gridContent.add(cell, gbc);
    }  // à utiliser et tester pour voir si ça marche, puis continuer de poser les joyaux

}
