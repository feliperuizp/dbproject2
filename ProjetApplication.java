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
     * Main du projet
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        CreateurJava cj = new CreateurJava("/Users/fabienrouillon/Desktop/MCDtest.xml");
        
        try {
            cj.lectureFichierXML();
            cj.creationFichiers();
            
        } catch (IOException ex) {
            Logger.getLogger(ProjetApplication.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
