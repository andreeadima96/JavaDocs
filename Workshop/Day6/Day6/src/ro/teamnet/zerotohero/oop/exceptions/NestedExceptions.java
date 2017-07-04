package ro.teamnet.zerotohero.oop.exceptions;

/**
 * Created by Andreea.Dima on 7/4/2017.
 */
public class NestedExceptions {
    public void n(){
        try{
            try{
                int array[] = {1,2,3};
                System.out.println(array[10]);
            }catch(ArithmeticException e){
                System.out.println("Try-catch block 2 " + e.getMessage());
            }
        }catch(Exception e){
            System.out.println("Try-catch block 1 " + e.getMessage());
        }
    }
}
