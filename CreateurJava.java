/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbproject2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Scanner;

/**
 *
 * @author Ruizo
 */
public class CreateurJava {

    private final String adresseXML;
    private LinkedList<EntiteJava> entites;
    private LinkedList<RelationJava> relations;

    public CreateurJava(String adresseXML) {
        this.adresseXML = adresseXML;
        this.entites = new LinkedList<>();
        this.relations = new LinkedList<>();
    }

    public LinkedList<EntiteJava> getEntites() {
        return entites;
    }

    public void setEntites(LinkedList<EntiteJava> entites) {
        this.entites = entites;
    }

    public LinkedList<RelationJava> getRelations() {
        return relations;
    }

    public void setRelations(LinkedList<RelationJava> relations) {
        this.relations = relations;
    }
    
    

    public String[] scan(Scanner s,int n) {
        Scanner s2 = new Scanner(s.nextLine()).useDelimiter("");
        for (int i = 0; i < n; i++) {
            s2.next();
        }
        String nom = "";

        while (s2.hasNext()) {
            nom += s2.next();
        }

        String[] separation;
        
        return separation = nom.split("\"");
    }

    public void lectureFichierXML() throws FileNotFoundException, IOException {
        FileInputStream f = new FileInputStream(adresseXML);
        BufferedReader d = new BufferedReader(new InputStreamReader(f));
        String line;
        Scanner s;
        Scanner s2;
        String[] separation;
        String suivant;
        EntiteJava ent;
        RelationJava rel;
        int entites = 0;
        int nrel = 0;

        while ((line = d.readLine()) != null) {

            s = new Scanner(line);
            if (s.hasNext()) {

                suivant = s.next();

                switch (suivant) {
                    case "<entite":
                        separation = scan(s,7);
                        ent = new EntiteJava();
                        ent.setNom(separation[0]);
                        this.entites.add(ent);
                        entites += 1;
                        break;
                    case "<attribut":
                        separation = scan(s,7);
                        this.entites.get(entites - 1).getListeAttributs().add(separation[1]);
                        this.entites.get(entites - 1).getTypeAttributs().add(separation[3]);
                        int hola = 0;
                        for (String ss:separation){
                            System.out.println(ss+ " "+hola);
                            hola += 1;
                        }   break;
                    case "<link":
                        separation = scan(s,5);
                        if (nrel % 2 == 0) {
                            rel = new RelationJava();
                            rel.setNom(separation[7]);
                            rel.setCard1(separation[3]);
                            for (EntiteJava ej : this.entites) {
                                if (ej.getNom().equals(separation[5])) {
                                    rel.setEntite1(ej);
                                }
                            }
                            this.relations.add(rel);
                        } else {
                            this.relations.get((nrel - 1) / 2).setCard2(separation[3]);
                            for (EntiteJava ej : this.entites) {
                                if (ej.getNom().equals(separation[5])) {
                                    this.relations.get((nrel - 1) / 2).setEntite2(ej);
                                    System.out.println("holaaa agregamos a " + separation[5] + " a "+ this.relations.get((nrel-1)/2).getNom());
                                }
                            }
                        }   nrel += 1;
                        break;
                    default:
                        break;
                }
                
            }
        }
    }
    
    public void creationFichiers() throws IOException{
        
        
        //TODO Ecrire les fichiers java des entites
        for (EntiteJava ej: this.entites){
            FileWriter fichier = new FileWriter(ej.getNom()+".java");
            
            BufferedWriter out = new BufferedWriter(fichier);
                        
            out.write("public Class "+ ej.getNom() +" { \n \n");
            int att = 0;
            for (String ss: ej.getListeAttributs()){
                // TODO different cases for JAVA types
                out.write("private "+ej.getTypeAttributs().get(att)+" "+ss+"\n");
            }
            out.close();
        }
        
        //TODO Ecrire les fichiers java des relations
        for (RelationJava rj: this.relations){
            
        }
    }
}
