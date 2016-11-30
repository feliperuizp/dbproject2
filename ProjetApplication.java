/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbproject2;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ruizo
 */
public class ProjetApplication {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        CreateurJava cj = new CreateurJava("/Users/Ruizo/Desktop/ejemplo BD.xml");
        
        try {
            cj.lectureFichierXML();
            
            /* TEST
            for (EntiteJava ej: cj.getEntites()){
                System.out.println(ej.getNom());
            }
            
            System.out.println("asfnoasf");
            
            for (RelationJava rj: cj.getRelations()){
                System.out.println(rj.getNom());
                System.out.println(rj.getCard1());
                System.out.println(rj.getCard2());
                System.out.println(rj.getEntite1().getNom());
                System.out.println(rj.getEntite2().getNom());
            }
            
            */
            
        } catch (IOException ex) {
            Logger.getLogger(ProjetApplication.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
