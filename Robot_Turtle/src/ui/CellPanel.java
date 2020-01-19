package ui;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class CellPanel extends JPanel {
    private String name = "";

    public CellPanel(String cellName) {
        this.name = cellName;
    }

    public void paintComponent(Graphics g) {

        if (name.equals("jewelGreen")) {
            int random = (int) (Math.random() * 3);   //mettre une couleur aléatoire pour les joyaux après chaque tour
            if (random == 0) {
                name = "jewelBlue";
            } else if (random == 1) {
                name = "jewelRed";
            } else if (random == 2) {
                name = "jewelGreen";
            }
        }

        try {
            File file = new File("images/" + name + ".png");
            Image img = ImageIO.read(file);                 //charger l'image
            //Pour une image de fond
            g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
        } catch (IOException e) {
            e.printStackTrace();
        }
        setPreferredSize(new Dimension(80, 80));
        setBackground(Color.WHITE);
    }
}
