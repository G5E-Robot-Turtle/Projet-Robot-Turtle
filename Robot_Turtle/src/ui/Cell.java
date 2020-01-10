package ui;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Cell extends JPanel {
    private String name = "";

    public Cell(String cellName) {
        this.name = cellName;
    }

    public void paintComponent(Graphics g) {
        try {
            File file = new File("images/" + name + ".png");
            String path = file.getPath();
            Image img = ImageIO.read(file);    //charger l'image
//            g.drawImage(img, 0, 0, this);
            //Pour une image de fond
            g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
        } catch (IOException e) {
            e.printStackTrace();
        }
        setPreferredSize(new Dimension(80, 80));
        setBackground(Color.WHITE);
    }

}