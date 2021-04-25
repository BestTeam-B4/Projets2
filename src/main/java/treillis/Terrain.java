package treillis;
import java.util.*;

public class Terrain{

    // Attributs

    private double maxX;
    private double maxY;
    private double minX;
    private double minY;
    private ArrayList<TriangleTerrain> triangles;
    
    //Constructeur
    Terrain(double maxx, double maxy ,double minx, double miny, ArrayList<TriangleTerrain> t)
    {
        this.maxX= maxx;
        this.maxY= maxy;
        this.minX= minx;
        this.minY=miny;
        this.triangles= t;
    }

    //méthode get

    public double getmaxX(){
        return this.maxX;
    }
    public double getmaxY(){
        return this.maxY;
    }
    public double getminY(){
    return this.minY;
}
    public double getminX(){
    return this.minX;
    }

    public ArrayList<TriangleTerrain> gettriangles(){
    return this.triangles;

}   
    //méthode set
    public void setmaxX(int i){
        this.maxX=i;
    }
    public void setmaxY(int i){
        this.maxY= i;
    }
    
    public void setminX(int i){
        this.minX= i;
}

    public void setminY(int i){
        this.minY=i;
    }

    public void settriangle(ArrayList<TriangleTerrain> triangles){
        this.triangles= triangles;
    }

    public void addtriangle(TriangleTerrain triangle){
        this.triangles.add(triangle);
    }

    //méthode toString
    public static String formatDouble(double x) {
        return formatDouble(x);
    }

    public  String toString(){
    
    String res= "";
        
        res="maxX= "+ formatDouble(this.maxX)+" ; ";
        res= res+ "maxY ="+ formatDouble(this.maxY)+" ; ";
        res = res+ "minX= "+ formatDouble(this.minX)+" ; ";
        res= res+ "minY=" + formatDouble(this.minY)+" ; ";

    return res;
    }

}

