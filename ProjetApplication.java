/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbproject2;

import java.io.IOException;
import java.util.Scanner;
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
        
        Scanner reader = new Scanner(System.in);
        System.out.println("Écrivez le fichier XML que vous voulez lire\n"
                + "Le fichier doit être dans le même dossier que cette application");
        
        String n = reader.nextLine();
        
        CreateurJava cj = new CreateurJava(n);
        
        try {
            cj.lectureFichierXML();
            cj.creationFichiers();
            
        } catch (IOException ex) {
            Logger.getLogger(ProjetApplication.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
