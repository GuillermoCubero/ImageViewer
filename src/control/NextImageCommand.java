package control;

import model.Image;
import ui.ImageDislay;

public class NextImageCommand implements Command {

    private final ImageDislay imageDislay;

    public NextImageCommand(ImageDislay imageDislay) {
        this.imageDislay = imageDislay;
    }

    @Override
    public void execute() {
        imageDislay.show(nextImage());
    }

    private Image nextImage() {
        return currentImage().next();
    }

    public Image currentImage(){
        return imageDislay.image();
    }
}
