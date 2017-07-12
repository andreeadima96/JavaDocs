package ro.teamnet.hello;

/**
 * Created by Andreea.Dima on 7/10/2017.
 */
public class HelloWorld {
    public void sayHello(){
        System.out.println("Hello World!");
    }

    public String returnHelloKey(){
        return "HelloKey";
    }

    public static void main(String[] args) {
        HelloWorld helloWorld = new HelloWorld();
        helloWorld.sayHello();
    }

}
