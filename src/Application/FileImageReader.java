package application;

import model.Image;
import view.persistance.ImageReader;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;

public class FileImageReader implements ImageReader {

    private final static String[] imageExtensions = {"jpg", "png", "gif"};
    private File[] files;

    public FileImageReader(String path) {
        this(new File(path));
    }

    public FileImageReader(File folder) {
        this.files = folder.listFiles(imageFiles());
    }

    @Override
    public Image read() {
        return image(0);
    }

    private FileFilter imageFiles() {
        return pathname -> {
            for (String extension : imageExtensions) {
                if (pathname.getName().toLowerCase().endsWith(extension)) {
                    return true;
                }
            }
            return false;
        };
    }

    private Image image(final int index) {
        return new Image() {

            @Override
            @SuppressWarnings("unchecked")
            public <E> E bitmap() {
                try {
                    return (E) ImageIO.read(files[index]);
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }
            }

            @Override
            public Image prev() {
                return image(index > 0 ? index - 1 : files.length - 1);
            }

            @Override
            public Image next() {
                return image(index < files.length - 1 ? index + 1 : 0);
            }
        };
    }
}
