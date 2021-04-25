package syslin ;

public class ResGauss {

    //Définition des attributs de la classe
    int rang; //nombre de ligne
    int signature; //nombre de colonne
    
    //Constructeur
    public ResGauss(int rang, int signature){
        this.rang = rang;
        this.signature = signature;
    }
    
    public String toString() {
        return ("(Rang="+rang+", Signature="+signature+")");
    }
}
