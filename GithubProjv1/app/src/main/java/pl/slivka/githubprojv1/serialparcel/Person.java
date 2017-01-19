package pl.slivka.githubprojv1.serialparcel;

/**
 * Created by Olej on 2017-01-19.
 */
import java.io.Serializable;
public class Person implements Serializable {
    private static final long serialVersionUID = -7060210544600464481L;
    private String name;
    private int age;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

}