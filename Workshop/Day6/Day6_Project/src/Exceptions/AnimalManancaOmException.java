package Exceptions;

/**
 * Created by Andreea.Dima on 7/4/2017.
 */
public class AnimalManancaOmException extends RuntimeException {
    public AnimalManancaOmException(String s){
        System.out.println("AnimalManancaOmException " + s);
    }
}
