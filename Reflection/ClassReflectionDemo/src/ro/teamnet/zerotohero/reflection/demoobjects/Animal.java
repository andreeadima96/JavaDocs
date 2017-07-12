package ro.teamnet.zerotohero.reflection.demoobjects;

/**
 * Created by Andreea.Dima on 7/12/2017.
 */
public class Animal {
    String name;

    public Animal(){
        System.out.println("Animal without name");
    }

    public Animal(String s){
        this.name = s;
    }

    public void eat(){
        System.out.println("Animal is eating");
    }

    public void play(){}
    class Bird{}

    class Cat{}

}
