package ro.teamnet.zerotohero.oop.exceptions;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Andreea.Dima on 7/4/2017.
 */
public class ExamplesExceptions {
    public String trycatchResources(){
        BufferedReader br = null;
        try {
             br = new BufferedReader(new FileReader("date.in"));
            return br.readLine();
        }catch(IOException e){
            System.out.println("IOException ");
        }
        finally{
            if(br != null)
                try {
                    br.close();
                }catch(IOException e){
                    System.out.println("IOException close()");
                }
        }
        return null;
    }
}
