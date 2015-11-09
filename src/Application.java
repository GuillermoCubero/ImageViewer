import control.*;
import ui.NewDialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Gabriel on 09/11/2015.
 */
public class Application extends JFrame {

    private Map<String, Command> commands = new HashMap<>();

    public static void main(String[] args) {
        new Application();
    }

    public Application() {
        this.createCommands();
        this.deployUI();
    }

    private NewDialog newDialog(){
        return () -> {
            JOptionPane.showConfirmDialog(Application.this, "Estás seguro?");
            return "";
        };
    }

    private void createCommands() {
        commands.put("New", new NewCommand(newDialog()));
        commands.put("Open", new OpenCommand());
        commands.put("Cut", new CutCommand());
        commands.put("Copy", new CopyCommand());
        commands.put("Paste", new PasteCommand());
    }

    private void deployUI() {
        this.setJMenuBar(createMenuBar());
        this.setResizable(false);
        this.setMinimumSize(new Dimension(500,500));
        this.setSize(new Dimension(500,500));
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(fileMenu());
        menuBar.add(editMenu());
        return menuBar;
    }

    private JMenu fileMenu() {
        JMenu menu = new JMenu("File");
        menu.setMnemonic('f');
        menu.add(newFile());
        menu.add(openFile());
        menu.add(editFile());
        return menu;
    }

    private JMenuItem editFile() {
        JMenuItem menuItem = new JMenuItem("Edit");
        menuItem.setMnemonic('e');
        menuItem.addActionListener(execute("Edit"));
        return menuItem;
    }

    private JMenuItem newFile() {
        JMenuItem menuItem = new JMenuItem("New...");
        menuItem.setMnemonic('n');
        menuItem.addActionListener(execute("New"));
        menuItem.addActionListener((e) -> System.out.println("New file invoked"));
        return menuItem;
    }

    private JMenuItem openFile() {
        JMenuItem menuItem = new JMenuItem("Open...");
        menuItem.setMnemonic('o');
        menuItem.addActionListener(execute("Open"));
        return menuItem;
    }

    private JMenu editMenu() {
        JMenu menu = new JMenu("Edit");
        menu.setMnemonic('e');
        menu.add(copy());
        menu.add(cut());
        menu.add(paste());
        return menu;
    }

    private JMenuItem paste() {
        JMenuItem menuItem = new JMenuItem("Paste");
        menuItem.setMnemonic('p');
        menuItem.addActionListener(execute("Paste"));
        return menuItem;
    }

    private JMenuItem cut() {
        JMenuItem menuItem = new JMenuItem("Cut");
        menuItem.setMnemonic('u');
        menuItem.addActionListener(execute("Cut"));
        return menuItem;
    }

    private JMenuItem copy() {
        JMenuItem menuItem = new JMenuItem("Copy");
        menuItem.setMnemonic('c');
        menuItem.addActionListener(execute("Copy"));
        return menuItem;
    }

    private ActionListener execute(final String command) {
        return e -> commands.get(command).execute();
    }
}
