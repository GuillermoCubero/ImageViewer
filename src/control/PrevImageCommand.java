package control;

import model.Image;
import ui.ImageDislay;

public class PrevImageCommand implements Command {
    private final ImageDislay imageDislay;

    public PrevImageCommand(ImageDislay imageDislay) {
        this.imageDislay = imageDislay;
    }

    @Override
    public void execute() {
        imageDislay.show(prevImage());
    }

    private Image prevImage() {
        return currentImage().prev();
    }

    public Image currentImage() {
        return imageDislay.image();
    }
}
