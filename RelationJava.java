/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbproject2;
import java.util.LinkedList;
/**
 *
 * @author Ruizo
 */
public class RelationJava {

    private String nom;
    private LinkedList<EntiteJava> listeEntites;
    private LinkedList<String> listeCardinalites;
    private LinkedList<String> listeAttributs;
    private LinkedList<String> typeAttributs;
    
    public RelationJava() {
        this.nom = null;
        this.listeEntites = new LinkedList<>();
        this.listeCardinalites = new LinkedList<>();
        this.listeAttributs = new LinkedList<>();
        this.typeAttributs = new LinkedList<>();
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public LinkedList<String> getTypeAttributs() {
        return typeAttributs;
    }

    public void setTypeAttributs(LinkedList<String> typeAttributs) {
        this.typeAttributs = typeAttributs;
    }
    
    public LinkedList<String> getListeAttributs() {
        return listeAttributs;
    }

    public void setListeAttributs(LinkedList<String> listeAttributs) {
        this.listeAttributs = listeAttributs;
    }
    
    public LinkedList<EntiteJava> getListeEntites() {
        return listeEntites;
    }

    public void setListeEntites(LinkedList<EntiteJava> listeEntites) {
        this.listeEntites = listeEntites;
    }

    public LinkedList<String> getListeCardinalites() {
        return listeCardinalites;
    }

    public void setListeCardinalites(LinkedList<String> listeCardinalites) {
        this.listeCardinalites = listeCardinalites;
    }

}
