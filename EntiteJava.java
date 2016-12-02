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

    public EntiteJava() {
        this.nom = null;
        this.listeAttributs = new LinkedList<>();
        this.typeAttributs = new LinkedList<>();
    }

    public EntiteJava(String nom, LinkedList<String> listeAttributs,
            LinkedList<String> typeAttributs) {
        this.nom = nom;
        this.listeAttributs = listeAttributs;
        this.typeAttributs = typeAttributs;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public LinkedList<String> getListeAttributs() {
        return listeAttributs;
    }

    public void setListeAttributs(LinkedList<String> listeAttributs) {
        this.listeAttributs = listeAttributs;
    }

    public LinkedList<String> getTypeAttributs() {
        return typeAttributs;
    }

    public void setTypeAttributs(LinkedList<String> typeAttributs) {
        this.typeAttributs = typeAttributs;
    }

}
