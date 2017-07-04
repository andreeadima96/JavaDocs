package ro.teamnet.zerotohero.oop.graphicshape;

/**
 * Created by Andreea.Dima on 7/4/2017.
 */
public class Point {
    int xPos;
    int yPos;

    public Point(int x,int y){
        this.xPos = x;
        this.yPos = y;
    }

    @Override
    public boolean equals(Object other) {
        if(other == null)
            return false;
        if(other instanceof Point){
            Point anotherPoint = (Point) other;
            if((this.xPos == anotherPoint.xPos) && (this.yPos == anotherPoint.yPos))
                return  true;
        }

        return false;
    }
}
