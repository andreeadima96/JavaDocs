package ro.teamnet.zerotohero.oop.graphicshape;
import java.lang.Math;
/**
 * Created by Andreea.Dima on 7/4/2017.
 */
public class Circle extends Shape {
    private int xPos, yPos, radius;

    public Circle(){
        this.xPos = 4;
        this.yPos = 4;
        this.radius = 4;
    }

    public Circle(int xPos){
        this.xPos = xPos;
        this.yPos = 4;
        this.radius = 4;
    }

    public Circle(int xPos, int yPos){
        this.xPos = xPos;
        this.yPos = yPos;
        this.radius = 4;
    }

    public Circle(int xPos, int yPos, int radius){
        this.xPos = xPos;
        this.yPos = yPos;
        this.radius = radius;
    }

    @Override
    public double area() {
        return Math.PI * Math.pow(this.radius,2);
    }

    @Override
    public String toString() {
        return "center = (" + this.xPos + "," + this.yPos + ") and radius = " + this.radius;
    }

    void fillColour(){System.out.println(super.color);}
    void fillColour(int color){
        super.setColor(color);
        System.out.println("The circle color is now " + super.color);
    }
    void fillColour(float color){
        super.setSaturation(color);
        System.out.println("The circle saturation is now " + super.saturation);
    }
}
