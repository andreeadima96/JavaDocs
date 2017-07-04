package Exceptions;

/**
 * Created by Andreea.Dima on 7/4/2017.
 */
public class AnimalManancaAnimalException extends RuntimeException {
    public AnimalManancaAnimalException(String s){
        System.out.println("AnimalManancaAnimalException " + s);
    }
}