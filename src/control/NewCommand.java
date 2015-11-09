package control;

import ui.NewDialog;

/**
 * Created by Gabriel on 09/11/2015.
 */
public class NewCommand implements Command {

    NewDialog dialog;

    public NewCommand(NewDialog dialog) {
        this.dialog = dialog;
    }

    @Override
    public void execute() {
        dialog.get();
    }
}
