package model;

public interface Image {
    Object bitmap();
    Image prev();
    Image next();
}