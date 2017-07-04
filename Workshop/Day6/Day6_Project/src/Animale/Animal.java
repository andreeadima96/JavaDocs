package Animale;

/**
 * Created by Andreea.Dima on 7/4/2017.
 */
abstract public class Animal {

    public Animal(){
        System.out.println("Animale.Animal nou");
    }

    public abstract void mananca(Object o);
    public abstract void seJoaca();
    public abstract void faceBaie();
    public void doarme(){
        System.out.println("Animalul doarme");
    }

}
