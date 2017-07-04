package ro.teamnet.zerotohero.oop.graphicshape;

/**
 * Created by Andreea.Dima on 7/4/2017.
 */
public class Circles {
    public double getAreaPub(){
        Circle c = new Circle();
        return c.area();
    }

    public void getAreaDef(){
        Circle circle = new Circle();
        circle.fillColour();
        circle.fillColour(7);
        circle.fillColour(1);
    }
}
