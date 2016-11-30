/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbproject2;

/**
 *
 * @author Ruizo
 */
public class RelationJava {
    
    private String nom;
    private EntiteJava entite1;
    private EntiteJava entite2;
    private String card1;
    private String card2;

    public RelationJava() {
        this.nom = null;
        this.entite1 = null;
        this.card1 = "";
        this.card2 = "";
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public EntiteJava getEntite1() {
        return entite1;
    }

    public void setEntite1(EntiteJava entite1) {
        this.entite1 = entite1;
    }

    public EntiteJava getEntite2() {
        return entite2;
    }

    public void setEntite2(EntiteJava entite2) {
        this.entite2 = entite2;
    }

    public String getCard1() {
        return card1;
    }

    public void setCard1(String card1) {
        this.card1 = card1;
    }

    public String getCard2() {
        return card2;
    }

    public void setCard2(String card2) {
        this.card2 = card2;
    }
    
    
    
    
    
}
