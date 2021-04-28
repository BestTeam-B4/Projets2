/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfac;

import java.util.List;
import java.util.ArrayList;
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
    
    
    
    public static void main(String []args){
        test1();
    }
}
