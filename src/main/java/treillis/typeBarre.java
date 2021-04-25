package treillis;

public class typeBarre{

    //Définitions des attributs

    private int id ; //identificateur
    private double cout; //coût au mètre
    private double longmax; // une longueur maximale
    private double longmin; //  une longueur minimale
    private double resistsn; //  une résistance maximale à la tension
    private double resistcmpn; // une résistance maximale à la compression
   
    //Constructeur
    public typeBarre(int id, double cout,  double lgmax, double lgmin, double resistsn, double resistcmpn)
    {
        this.id= id;
        this.cout= cout;
        this.longmax= lgmax;
        this.longmin= lgmin;
        this.resistsn=resistsn;
        this.resistcmpn=resistcmpn;
    }
    
      //Méthode .get
      public int getid(){
        return this.id;
    }
    public double getcout(){
        return this.cout;
    }
    public double getlongmax(){
    return this.longmax;
    }

    public double getlongmin(){
        return this.longmin;
        }

    public double getresistsn() {
        return this.resistsn;
    }
    public double getresistcmpn() {
        return this.resistcmpn;
    }

    //Méthode set

    public void setid(int id){
        this.id=id;
    }

    public void setcout(double cout){
        this.longmax=cout;
    }

    public void setlongmax(double l){
        this.longmax=l;
    }

    public void setlongmin(double l){
        this.longmin=l;
    }

    public void setresistsn(double tension){
        this.resistsn=tension;
    }

    public void setresistcmpn(double compression){
        this.resistcmpn=compression;
    }

    //Méthode toString
    public String toString(){
        String res ="";
        res="identificateur = "+String.valueOf(this.getid());
        res=res+" cout = "+ String.valueOf(this.getcout());
        res= res+ " longueur maximale = "+ String.valueOf(this.getlongmax());
        res= res+ " longueur minimale = "+ String.valueOf(this.getlongmin());
        res= res+ " resistance maximale a la tension = "+ String.valueOf(this.getresistsn());
        res= res+ " resistance maximale a la compression = "+ String.valueOf(this.getresistcmpn());
        return res;
    }

}