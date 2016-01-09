package application;

import model.Image;
import ui.ImageDislay;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ImagePanel extends JPanel implements ImageDislay {

    private Image image;

    @Override
    public Image image() {
        return image;
    }

    @Override
    public void show(Image image) {
        this.image = image;
        this.repaint();
    }

    @Override
    protected void paintComponent(Graphics g){
        g.drawImage(bitmap(), 0, 0, this);
    }

    private BufferedImage bitmap() {
        return (BufferedImage) image.bitmap();
    }
}
