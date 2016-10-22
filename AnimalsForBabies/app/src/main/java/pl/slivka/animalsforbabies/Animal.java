package pl.slivka.animalsforbabies;

import android.graphics.drawable.Drawable;

/**
 * Created by Tomek on 2016-05-19.
 */
public class Animal {
    int id;
    String name;
    Drawable picture;

    public Animal(int id, String name, Drawable picture) {
        this.id = id;
        this.name = name;
        this.picture = picture;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Drawable getPicture() {
        return picture;
    }

    public void setPicture(Drawable picture) {
        this.picture = picture;
    }
}
