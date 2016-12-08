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
public class EntiteJava {

    private String nom;
    private LinkedList<String> listeAttributs;
    private LinkedList<String> typeAttributs;

    /**
     * Constructeur par defaut
     * Initialize tous les attributs à null
     */
    public EntiteJava() {
        this.nom = null;
        this.listeAttributs = new LinkedList<>();
        this.typeAttributs = new LinkedList<>();
    }

    /**
     * Constructeur avec les trois attributs
     * @param nom
     * @param listeAttributs
     * @param typeAttributs
     */
    public EntiteJava(String nom, LinkedList<String> listeAttributs,
            LinkedList<String> typeAttributs) {
        this.nom = nom;
        this.listeAttributs = listeAttributs;
        this.typeAttributs = typeAttributs;
    }

    /**
     * Getter Nom
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

}
