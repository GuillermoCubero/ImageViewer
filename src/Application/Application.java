package APPLICATION;

import control.Command;
import control.NextImageCommand;
import control.PrevImageCommand;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashMap;

public class Application extends JFrame {

    private HashMap<String, Command> commands = new HashMap<>();
    private ImagePanel panel;
    private static final String PATH = "";

    public Application() throws IOException {
        this.setTitle("ImageViewer");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(500, 500));
        deployUI();
    }

    public static void main(String[] args) throws IOException {
        new Application().setVisible(true);
    }

    private JPanel toolbar() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel.add(prevButton());
        panel.add(nextButton());
        return panel;
    }

    private void createCommands() {
        commands.put("prev", prevCommand());
        commands.put("next", nextCommand());
    }

    private Command nextCommand() {
        return new NextImageCommand(this.panel);
    }

    private Command prevCommand() {
        return new PrevImageCommand(this.panel);
    }

    private void deployUI() throws IOException {
        this.getContentPane().add(toolbar(), BorderLayout.SOUTH);
        this.getContentPane().add(imagePanel(imageIn(PATH)));
        createCommands();
    }

    private JButton prevButton() {
        JButton prev = new JButton("<");
        prev.addActionListener(doCommand("prev"));
        return prev;
    }

    private ActionListener doCommand(String action) {
        return e -> commands.get(action).execute();
    }

    private JButton nextButton() {
        JButton next = new JButton(">");
        next.addActionListener(doCommand("next"));
        return next;
    }

    private model.Image imageIn(String path) {
        return new FileImageReader(path).read();
    }

    private ImagePanel imagePanel(model.Image image) throws IOException {
        panel = new ImagePanel();
        panel.show(image);
        return panel;
    }
}
