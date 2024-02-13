package core;

import java.util.ArrayList;

public class card {
    private double color;
    private ArrayList<Short> list;
    private int numShapes;
    private String shape;

    public  card(ArrayList<Short> list){
        //this.color = color;
        this.list = list;
        //this.numShapes = numShapes;
        //this.shape = shape;
    }

    public void setColor(double color) {
        this.color = color;
    }

    public void setNumShapes(int numShapes){
        this.numShapes = numShapes;
    }

    public void setShape(String shape){
        this.shape = shape;
    }

   /* public double calcCenter(){
        return 0;
    }*/
}
