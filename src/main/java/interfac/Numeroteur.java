/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfac;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author lemei
 */
public class Numeroteur<TO> {//TO pr type objet
    
    private TreeMap<Integer,TO> idVersObjet;
    private Map<TO,Integer> objetVersId;
    
    private int prochainID;
    
    public Numeroteur(){
        this(0);
    }
    
    private Numeroteur(int prochainID){
        this.prochainID=prochainID;
        this.idVersObjet=new TreeMap<>();
        this.objetVersId= new HashMap<>();
    }
    
    public int creeID(TO objet){
        if(this.objetVersId.containsKey(objet)){
            throw new Error("objet "+objet+" deja dans le numeroteur");
        }
        this.idVersObjet.put(this.prochainID, objet);
        this.objetVersId.put(objet, prochainID);
        this.prochainID++;
        return this.prochainID-1;
    }
    
    public boolean objetExiste(TO objet){
        return this.objetVersId.containsKey(objet);
    }
    
    public int getID(TO objet){
        if(this.objetExiste(objet)){
            return this.objetVersId.get(objet);
        }else{
            throw new Error("Objet "+objet+" inconnu dans numeroteur");
        }
    }
    public int getOucreeID(TO objet){
        if(this.objetExiste(objet)){
            return this.objetVersId.get(objet);
        }else{
            return this.creeID(objet);
        }
    }
}