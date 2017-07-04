package ro.teamnet.zerotohero.oop.exceptions;

/**
 * Created by Andreea.Dima on 7/4/2017.
 */
public class ExceptionPropagation {

   public void a(){
        int m = 5/0;
    }

   public void b(){
        a();
    }

    public void c(){
        try{
            b();
        }catch (Exception e){
            System.out.println("Found exception "  + e.getMessage());
        }
    }
}
