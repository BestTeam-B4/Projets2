/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfac;

import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import syslin.Lire;

/**
 *
 * @author lemei
 */
public class Groupe extends Figure {
    
    private List<Figure> contient;
    

    public Groupe(){
        this.contient = new ArrayList<Figure>();
    }
    
    public void add(Figure f){
        if(f.getGroupe()!=this){
            if(f.getGroupe()!= null){
                throw new Error("figure deja dans un groupe");
            }
            this.contient.add(f);
            f.setGroupe(this);
        }
    }
    
    public void remove(Figure f){
        if(f.getGroupe()!=this){
            throw new Error("la figure n'est pas dans le groupe");
        }
        this.contient.remove(f);
        f.setGroupe(null);
    }
    
    public void removeAll(List<Figure>lf){
        for(Figure f:lf){
            this.remove(f);
        }
    }
    
    public static String indente(String toIndente, String prefix){
        return prefix + toIndente.replaceAll("\n", "\n"+ prefix);
    }
    
    @Override
    public String toString(){
        String res= "Groupe {\n";
        for (int i=0; i<this.contient.size();i++){
            res = res + indente(this.contient.get(i).toString()," ")+"\n";
        }
        return res+"}";
    }
    
    
    public Point choisiPoint(){
        List<Point> lp= new ArrayList<>();
        System.out.println("liste des points disponibles ; ");
        int nbr = 0;
        for (int i=0; i< this.contient.size();i++){
            Figure f = this.contient.get(i);
            if (f instanceof Point){
                nbr++;
                lp.add((Point) f);
                System.out.println(nbr+") "+f);
            }
        }
        if (nbr == 0){
            System.out.println("Aucun point disponible");
            return null;
        }else{
            int rep =-1;
            while (rep<0||rep>nbr){
                System.out.println("votre choix (0 pour annuler) : ");
                rep = Lire.i();
            }
            if (rep==0){
                return null;
            }else {
                return lp.get(rep-1);
            }
        }
    }
    
    public List<Figure> choisiFigure(){
        List<Figure> res = new ArrayList();
        int rep=-1;
        while (rep != 0){
            System.out.println("liste des figures disponibles : ");
            for (int i = 0; i <this.contient.size();i++){
                System.out.println((i+1)+") "+this.contient.get(i));
            }
            System.out.println("votre choix (0 pour finir) : ");
            rep=Lire.i();
            if(rep>0 && rep<=this.contient.size()){
                Figure f = this.contient.get(rep-1);
                if (res.contains(f)){
                    System.out.println("deja selectionnee");
                } else {
                    res.add(f);
                }
                System.out.println(res.size()+" figure(s) selectionnee(s)");
            }
        }
        return res;
    }
    
    public void sousGroupe(List<Figure> lf) {
        for(int i=0; i<lf.size();i++){
            Figure f =lf.get(i);
            if(f.getGroupe()!= this){
            throw new Error (f +" n'appartient pas au groupe "+this);
        }
        this.contient.remove(f);
        f.setGroupe(null);
    }
        Groupe sg = new Groupe();
        for(int i=0;i<lf.size();i++){
            sg.add(lf.get(i));
        }
        this.add(sg);
    }
       
    public void menuTexte() {
        int rep=-1;
        while (rep != 0){
            System.out.println("1) afficher le groupe");
            System.out.println("2) ajouter un point");
            System.out.println("3) ajouter un segment avec deux nouveaux points");
            System.out.println("4) ajouter un segment sur deux points existants");
            System.out.println("5) creer un sous-groupe");
            System.out.println("0) quitter");
            System.out.println("votre choix :");
            rep = Lire.i();
            if(rep == 1){
                System.out.println(this);
            }else if(rep == 2){
                Point np = Point.demandePoint();
                this.add(np);
                
                
            }else if (rep == 3){
                Segment ns = Segment.demandeSegment();
                this.add(ns);
                
            }else if (rep == 4){
                System.out.println("choisissez le debut du segment");
                Point deb=this.choisiPoint();
                if (deb != null){
                System.out.println("choisissez la fin du segment");
                Point fin = this.choisiPoint();
            }else if (rep == 5){
                List<Figure> select = this.choisiFigure();
                this.sousGroupe(select);
            }
        }
    }
        
    public static Groupe groupeTest(){
        Point p1 = new Point();
        Point p2 = new Point(100,0);
        Point p3 = new Point(100,100);
        Point p4 = new Point(0,100);
        Point p5 = new Point(50,50);
        Segment s1 = new Segment(p1,p2);
        Segment s2 = new Segment(p2,p3);
        Segment s3 = new Segment(p3,p1);
        Segment s4 = new Segment(p1,p4);
        Groupe triangle = new Groupe();
        triangle.add(s1);
        triangle.add(s2);
        triangle.add(s3);
        Groupe res = new Groupe();
        res.add(p5);
        res.add(p5);
        res.add(triangle);
        return res;
    }
   
    public static void test1(){
       System.out.println("groupe teste : \n" +Groupe.groupeTest());
    }
    
     public static void testMenu(){
        Groupe g=groupeTest();
        g.menuTexte();
    }
    
     public static void exempleProblemeSauvegarde(){
        
            Point p11=new Point(1,1);
            Point p12=new Point(2,2);
            Point p13=new Point(2,2);
            Point p14=new Point(3,3);
            Segment s11=new Segment(p11,p12);
            Segment s12=new Segment(p13,p14);
            Groupe gr1=new Groupe();
            gr1.add(s11);
            gr1.add(s12);
            Point p21=new Point(1,1);
            Point p22=new Point(2,2);
            Point p23=new Point(3,3);
            Segment s21=new Segment(p21,p22);
            Segment s22=new Segment(p22,p23);
            Groupe gr2=new Groupe();
            gr2.add(s21);
            gr2.add(s22);
            gr2.add(gr1);
            System.out.println("Groupe 1 : "+gr1);
            System.out.println("Groupe 2 : "+gr2);
            try {
            gr1.sauvegarde(new File("groupe1.txt"));
            gr2.sauvegarde(new File("groupe2.txt"));
        } catch (IOException ex) {
            throw new Error("probleme : "+ex.getMessage());
        }
        }
    
    public static void main(String []args){
        //test1();
        //groupeTest().menuTexte();
        exempleProblemeSauvegarde();
    }
    
    @Override
    public double maxX(){
        if(this.contient.isEmpty()){
            return 0;
        }else{
            double max = this.contient.get(0).maxX();
            for(int i=1;i<this.contient.size();i++){
                double cur=this.contient.get(i).maxX();
                if(cur>max){
                    max=cur;
                }
            }
        
        return max;
    }
}
    
    public double maxY(){
        if(this.contient.isEmpty()){
            return 0;
        }else{
            double max = this.contient.get(0).maxY();
            for(int i=1;i<this.contient.size();i++){
                double cur=this.contient.get(i).maxY();
                if(cur>max){
                    max=cur;
                }
            }
        
        return max;
    }
}
    
    public double minX(){
        if(this.contient.isEmpty()){
            return 0;
        }else{
            double min = this.contient.get(0).minX();
            for(int i=1;i<this.contient.size();i++){
                double cur=this.contient.get(i).minX();
                if(cur<min){
                    min=cur;
                }
            }
        
        return min;
    }
}
    
    public double minY(){
        if(this.contient.isEmpty()){
            return 0;
        }else{
            double min = this.contient.get(0).minY();
            for(int i=1;i<this.contient.size();i++){
                double cur=this.contient.get(i).minY();
                if(cur<min){
                    min=cur;
                }
            }
        
        return min;
    }
}
    @Override
    public double distancePoint(Point p){
        if(this.contient.isEmpty()){
            return new Point(0,0).distancePoint(p);
        }else{
            double dist=this.contient.get(0).distancePoint(p);
            for(int i=1;i<this.contient.size();i++){
                double cur=this.contient.get(i).distancePoint(p);
                if(cur<dist){
                    dist=cur;
                }
            }
            return dist;
        }
    }
    
      @Override
    public void dessine(GraphicsContext context){
        for(Figure f : this.contient){
            f.dessine(context);
        }
    }
     @Override
    public void dessineSelection(GraphicsContext context){
        for(Figure f : this.contient){
            f.dessine(context);
        }
    }
    public void clear(){
        List<Figure> toRemove=new ArrayList<>(this.contient);
        this.removeAll(toRemove);
    }
    
    public int size(){
        return this.contient.size();
    }
    public Figure plusProche(Point p,double distMax){
        if(this.contient.isEmpty()){
            return null;
        } else{
            Figure fmin=this.contient.get(0);
            double min=fmin.distancePoint(p);
            for(int i=1;i<this.contient.size();i++){
                Figure fcur=this.contient.get(i);
                double cur=fcur.distancePoint(p);
                if(cur<min){
                    min=cur;
                    fmin=fcur;
                }
            }
            if(min<= distMax){
                return fmin;
            }else{
                return null;
            }
        }
    }

    @Override
    public void changeCouleur(Color value) {
        for(Figure f:this.contient){
            f.changeCouleur(value);
        }
    }
    
    @Override
    public void save(Writer w, Numeroteur<Figure> num) throws IOException {
        if(!num.objetExiste(this)){
            int id=num.creeID(this);
            for(Figure f:this.contient){
                f.save(w,num);
            }
            w.append("Groupe;"+id);
            for(Figure f:this.contient){
                w.append(";"+num.getID(f));
            }
         w.append("\n");
        }
    }
    
}
