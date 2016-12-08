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
    
    /**
     * Constructeur
     */
    public RelationJava() {
        this.nom = null;
        this.listeEntites = new LinkedList<>();
        this.listeCardinalites = new LinkedList<>();
        this.listeAttributs = new LinkedList<>();
        this.typeAttributs = new LinkedList<>();
    }

    /**
     * Getter nom
     * @return
     */
    public String getNom() {
        return nom;
    }

    /**
     * Setter nom
     * @param nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /** 
     * Getter typeAttributs
     * @return
     */
    public LinkedList<String> getTypeAttributs() {
        return typeAttributs;
    }

    /**
     * Setter typeAttributs
     * @param typeAttributs
     */
    public void setTypeAttributs(LinkedList<String> typeAttributs) {
        this.typeAttributs = typeAttributs;
    }
    
    /**
     * Getter listeAttributs
     * @return
     */
    public LinkedList<String> getListeAttributs() {
        return listeAttributs;
    }

    /**
     * Setter listeAttributs
     * @param listeAttributs
     */
    public void setListeAttributs(LinkedList<String> listeAttributs) {
        this.listeAttributs = listeAttributs;
    }
    
    /**
     * Getter listeEntites
     * @return
     */
    public LinkedList<EntiteJava> getListeEntites() {
        return listeEntites;
    }

    /**
     * Setter listeEntites
     * @param listeEntites
     */
    public void setListeEntites(LinkedList<EntiteJava> listeEntites) {
        this.listeEntites = listeEntites;
    }

    /**
     * Getter listeCardinalites
     * @return
     */
    public LinkedList<String> getListeCardinalites() {
        return listeCardinalites;
    }

    /**
     * Setter listeCardinalites
     * @param listeCardinalites
     */
    public void setListeCardinalites(LinkedList<String> listeCardinalites) {
        this.listeCardinalites = listeCardinalites;
    }

}
