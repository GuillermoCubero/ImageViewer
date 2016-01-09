package Application;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Gabriel on 30/11/2015.
 */
public class Main extends JFrame{
    public static void main(String[] args) throws IOException {
        new Main().setVisible(true);
    }

    public Main() throws HeadlessException, IOException {
        this.setTitle("ImageViewer");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(500, 500));
        this.getContentPane().add(panel());
    }

    private JPanel panel() throws IOException {
        String filename = "C:\\Users\\gabri\\Pictures\\mbuntu-0.jpg";
        final BufferedImage image = ImageIO.read(new File(filename));
        return new JPanel(){

            @Override
            protected void paintComponent(Graphics g){
                g.drawImage(image, 0, 0, Main.this);
            }
        };
    }
}
