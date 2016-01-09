package model;

public interface Image {
    <E> E bitmap();

    Image prev();

    Image next();
}