package ro.teamnet.zerotohero.oop;
import ro.teamnet.zerotohero.canvas.Canvas;
import ro.teamnet.zerotohero.oop.exceptions.ExamplesExceptions;
import ro.teamnet.zerotohero.oop.exceptions.ExceptionPropagation;
import ro.teamnet.zerotohero.oop.exceptions.MyException;
import ro.teamnet.zerotohero.oop.exceptions.NestedExceptions;
import ro.teamnet.zerotohero.oop.graphicshape.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Andreea.Dima on 7/4/2017.
 */
public class RunApp {
    public static void main(String[] args) {
        Circles circle = new Circles();

        System.out.println("The default circle area is " + circle.getAreaPub());

       circle.getAreaDef();

        Canvas canvas = new Canvas();
        canvas.paint();

        Shape shape = new Circle(10);
        System.out.println(shape.area());

        ShapeBehaviour square = new Square(10);
        System.out.println(square.area());

        Object p1 = new Point(10, 20);
        Object p2 = new Point(50, 100);
        Object p3 = new Point(10, 20);

        System.out.println("p1 equals p2 is " + p1.equals(p2));
        System.out.println("p1 equals p3 is " + p1.equals(p3));

        try{
            ArrayList a = new ArrayList(4);
            System.out.println(a.get(6));
        }catch(Exception e){
            System.out.println("This is my exception " + e.getMessage());
        }

        ExceptionPropagation e = new ExceptionPropagation();
        e.c();

        NestedExceptions n = new NestedExceptions();
        n.n();

        ExamplesExceptions ee = new ExamplesExceptions();
        ee.trycatchResources();

    }
}
