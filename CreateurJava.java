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

    public String[] scan(Scanner s, int n) {
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
        boolean passageRelation = false;
        String relationCourante = "";

        while ((line = d.readLine()) != null) {

            s = new Scanner(line);
            if (s.hasNext()) {

                suivant = s.next();

                switch (suivant) {
                    case "<entite":
                        separation = scan(s, 7);
                        ent = new EntiteJava();
                        ent.setNom(separation[0]);
                        this.entites.add(ent);
                        entites += 1;
                        break;
                    case "<relation":
                        separation = scan(s, 7);
                        passageRelation = true;
                        relationCourante = separation[0];
                        rel = new RelationJava();
                        rel.setNom(relationCourante);
                        this.relations.add(rel);
                        break;
                    case "<attribut":
                        separation = scan(s, 7);
                        if (passageRelation) {
                            for (RelationJava r : this.relations) {
                                if (r.getNom().equals(relationCourante)) {
                                    r.getListeAttributs().add(separation[1]);
                                    r.getTypeAttributs().add(separation[3]);
                                }
                            }
                        } else {
                            this.entites.get(entites - 1).getListeAttributs().add(separation[1]);
                            this.entites.get(entites - 1).getTypeAttributs().add(separation[3]);
                        }
                        break;
                    case "<link":
                        separation = scan(s, 5);
                        for (RelationJava rj : this.relations) {
                            if (rj.getNom().equals(separation[7])) {
                                rj.getListeCardinalites().add(separation[3]);
                                for (EntiteJava ej : this.entites) {
                                    if (ej.getNom().equals(separation[5])) {
                                        rj.getListeEntites().add(ej);
                                    }
                                }
                            }
                        }
                        break;
                    default:
                        break;
                }
            }
        }
    }

    public void creationFichiers() throws IOException {

        String nullval = "";

        for (EntiteJava ej : this.entites) {
            FileWriter fichier = new FileWriter(ej.getNom() + ".java");

            BufferedWriter out = new BufferedWriter(fichier);
            out.write("package ; \n \n");
            out.write("import java.util.*; \n \n");

            out.write("public Class " + ej.getNom() + " { \n \n");
            int att = 0;
            for (String ss : ej.getListeAttributs()) {
                switch (ej.getTypeAttributs().get(att)) {
                    case ("Auto_increment"):
                        // TODO https://stackoverflow.com/questions/24305830/java-auto-increment-id
                        ej.getTypeAttributs().set(att, "int");
                        nullval = "0";
                        break;
                    case ("Varchar"):
                        ej.getTypeAttributs().set(att, "String");
                        nullval = "null";
                        break;
                    case ("Char"):
                        ej.getTypeAttributs().set(att, "char");
                        nullval = "''";
                        break;
                    case ("Bool"):
                        ej.getTypeAttributs().set(att, "Boolean");
                        nullval = "null";
                        break;
                    case ("Date"):
                        ej.getTypeAttributs().set(att, "Date");
                        nullval = "null";
                        break;
                    case ("Int"):
                        ej.getTypeAttributs().set(att, "int");
                        nullval = "0";
                        break;
                    case ("Float"):
                        ej.getTypeAttributs().set(att, "float");
                        nullval = "0";
                        break;
                    case ("Money"):
                        ej.getTypeAttributs().set(att, "float");
                        nullval = "0";
                        break;
                    case ("BigInt"):
                        ej.getTypeAttributs().set(att, "int");
                        nullval = "0";
                        break;
                    case ("Blob"):
                        ej.getTypeAttributs().set(att, "Blob");
                        nullval = "null";
                        break;
                    case ("Datetime"):
                        ej.getTypeAttributs().set(att, "Date");
                        nullval = "null";
                        break;
                    case ("Decimal"):
                        ej.getTypeAttributs().set(att, "float");
                        nullval = "0";
                        break;
                    case ("Double"):
                        ej.getTypeAttributs().set(att, "double");
                        nullval = "0";
                        break;
                    case ("Double Precision"):
                        ej.getTypeAttributs().set(att, "double");
                        nullval = "0";
                        break;
                    case ("Longblob"):
                        ej.getTypeAttributs().set(att, "Blob");
                        nullval = "null";
                        break;
                    case ("Longtext"):
                        ej.getTypeAttributs().set(att, "String");
                        nullval = "null";
                        break;
                    case ("Mediunblob"):
                        ej.getTypeAttributs().set(att, "Blob");
                        nullval = "null";
                        break;
                    case ("Mediumint"):
                        ej.getTypeAttributs().set(att, "int");
                        nullval = "0";
                        break;
                    case ("Mediumtext"):
                        ej.getTypeAttributs().set(att, "String");
                        nullval = "null";
                        break;
                    case ("Numeric"):
                        ej.getTypeAttributs().set(att, "BigDecimal");
                        nullval = "null";
                        break;
                    case ("Real"):
                        ej.getTypeAttributs().set(att, "BigInteger");
                        nullval = "null";
                        break;
                    case ("Smallint"):
                        ej.getTypeAttributs().set(att, "int");
                        nullval = "0";
                        break;
                    case ("Text"):
                        ej.getTypeAttributs().set(att, "String");
                        nullval = "null";
                        break;
                    case ("Time"):
                        ej.getTypeAttributs().set(att, "LocalTime");
                        nullval = "null";
                        break;
                    case ("TimeStamp"):
                        ej.getTypeAttributs().set(att, "LocalDateTime");
                        nullval = "null";
                        break;
                    case ("TinyBlob"):
                        ej.getTypeAttributs().set(att, "Blob");
                        nullval = "null";
                        break;
                    case ("TinyINT"):
                        ej.getTypeAttributs().set(att, "int");
                        nullval = "0";
                        break;
                    case ("TinyText"):
                        ej.getTypeAttributs().set(att, "String");
                        nullval = "null";
                        break;
                    case ("Year"):
                        ej.getTypeAttributs().set(att, "int");
                        nullval = "0";
                        break;
                    case ("Integer"):
                        ej.getTypeAttributs().set(att, "Integer");
                        nullval = "null";
                        break;

                }
                out.write("    private " + ej.getTypeAttributs().get(att) + " " + ss + "; \n");
                att += 1;
            }
            out.write("\n");

            // Ecriture des constructeurs
            //constructeur par défault
            out.write("    public " + ej.getNom() + "(){ \n");
            for (String ss : ej.getListeAttributs()) {
                out.write("        this." + ss + "= " + nullval + ";\n");
            }
            out.write("    }\n \n");
            //constructeur avec tous les paramètres
            out.write("    public " + ej.getNom() + "(");
            att = 0;
            for (String ss : ej.getListeAttributs()) {
                out.write(ej.getTypeAttributs().get(att) + " " + ss);
                if (att < ej.getListeAttributs().size() - 1) {
                    out.write(",");
                }
                att += 1;
            }
            out.write("){ \n");
            for (String ss : ej.getListeAttributs()) {
                out.write("        this." + ss + " = " + ss + "; \n");
            }
            out.write("    } \n \n");
            //constructeur de recopie
            out.write("    public " + ej.getNom() + "(" + ej.getNom() + " recopie) { \n");
            for (String ss : ej.getListeAttributs()) {
                out.write("        this." + ss + " = recopie." + ss + "; \n");
            }
            out.write("    } \n \n");

            // Ecriture des getters et setters
            att = 0;
            for (String ss : ej.getListeAttributs()) {
                //ss_Maj est une version de ss avec la première lettre comme majuscule
                String ss_Maj = ss.replaceFirst(".", (ss.charAt(0) + "").toUpperCase());
                out.write("    public " + ej.getTypeAttributs().get(att) + " get" + ss_Maj + "(){ \n");
                out.write("        return " + ss + ";\n    }\n \n");
                out.write("    public void set" + ss_Maj + "(" + ej.getTypeAttributs().get(att) + " " + ss + ") { \n");
                out.write("        this." + ss + " = " + ss + "; \n    } \n \n");
                att += 1;
            }

            out.write("}");
            out.close();
        }

        for (RelationJava rj : this.relations) {

            FileWriter fichier = new FileWriter(rj.getNom() + ".java");

            BufferedWriter out = new BufferedWriter(fichier);
            out.write("package ; \n \n");
            out.write("import java.util.*; \n \n");

            int att = 0;
            for (String ss : rj.getListeAttributs()) {
                switch (rj.getTypeAttributs().get(att)) {
                    case ("Auto_increment"):
                        // TODO https://stackoverflow.com/questions/24305830/java-auto-increment-id
                        rj.getTypeAttributs().set(att, "int");
                        nullval = "0";
                        break;
                    case ("Varchar"):
                        rj.getTypeAttributs().set(att, "String");
                        nullval = "null";
                        break;
                    case ("Char"):
                        rj.getTypeAttributs().set(att, "char");
                        nullval = "''";
                        break;
                    case ("Bool"):
                        rj.getTypeAttributs().set(att, "Boolean");
                        nullval = "null";
                        break;
                    case ("Date"):
                        rj.getTypeAttributs().set(att, "Date");
                        nullval = "null";
                        break;
                    case ("Int"):
                        rj.getTypeAttributs().set(att, "int");
                        nullval = "0";
                        break;
                    case ("Float"):
                        rj.getTypeAttributs().set(att, "float");
                        nullval = "0";
                        break;
                    case ("Money"):
                        rj.getTypeAttributs().set(att, "float");
                        nullval = "0";
                        break;
                    case ("BigInt"):
                        rj.getTypeAttributs().set(att, "int");
                        nullval = "0";
                        break;
                    case ("Blob"):
                        rj.getTypeAttributs().set(att, "Blob");
                        nullval = "null";
                        break;
                    case ("Datetime"):
                        rj.getTypeAttributs().set(att, "Date");
                        nullval = "null";
                        break;
                    case ("Decimal"):
                        rj.getTypeAttributs().set(att, "float");
                        nullval = "0";
                        break;
                    case ("Double"):
                        rj.getTypeAttributs().set(att, "double");
                        nullval = "0";
                        break;
                    case ("Double Precision"):
                        rj.getTypeAttributs().set(att, "double");
                        nullval = "0";
                        break;
                    case ("Longblob"):
                        rj.getTypeAttributs().set(att, "Blob");
                        nullval = "null";
                        break;
                    case ("Longtext"):
                        rj.getTypeAttributs().set(att, "String");
                        nullval = "null";
                        break;
                    case ("Mediunblob"):
                        rj.getTypeAttributs().set(att, "Blob");
                        nullval = "null";
                        break;
                    case ("Mediumint"):
                        rj.getTypeAttributs().set(att, "int");
                        nullval = "0";
                        break;
                    case ("Mediumtext"):
                        rj.getTypeAttributs().set(att, "String");
                        nullval = "null";
                        break;
                    case ("Numeric"):
                        rj.getTypeAttributs().set(att, "BigDecimal");
                        nullval = "null";
                        break;
                    case ("Real"):
                        rj.getTypeAttributs().set(att, "BigInteger");
                        nullval = "null";
                        break;
                    case ("Smallint"):
                        rj.getTypeAttributs().set(att, "int");
                        nullval = "0";
                        break;
                    case ("Text"):
                        rj.getTypeAttributs().set(att, "String");
                        nullval = "null";
                        break;
                    case ("Time"):
                        rj.getTypeAttributs().set(att, "LocalTime");
                        nullval = "null";
                        break;
                    case ("TimeStamp"):
                        rj.getTypeAttributs().set(att, "LocalDateTime");
                        nullval = "null";
                        break;
                    case ("TinyBlob"):
                        rj.getTypeAttributs().set(att, "Blob");
                        nullval = "null";
                        break;
                    case ("TinyINT"):
                        rj.getTypeAttributs().set(att, "int");
                        nullval = "0";
                        break;
                    case ("TinyText"):
                        rj.getTypeAttributs().set(att, "String");
                        nullval = "null";
                        break;
                    case ("Year"):
                        rj.getTypeAttributs().set(att, "int");
                        nullval = "0";
                        break;
                    case ("Integer"):
                        rj.getTypeAttributs().set(att, "Integer");
                        nullval = "null";
                        break;

                }
                att += 1;
            }

            //Définition attributs
            out.write("public class " + rj.getNom() + " {\n\n");

            for (int j = 1; j <= rj.getListeEntites().size(); j++) {
                out.write("    private " + rj.getListeEntites().get(j - 1).getNom() + " entite" + j + ";\n");
            }

            for (int j = 1; j <= rj.getListeEntites().size(); j++) {
                out.write("    private String cardinalite" + j + " = \"" + rj.getListeCardinalites().get(j - 1) + "\";\n");
            }

            for (int j = 1; j <= rj.getListeAttributs().size(); j++) {
                out.write("    private " + rj.getTypeAttributs().get(j - 1) + " " + rj.getListeAttributs().get(j - 1) + "; \n");
            }

            out.write("\n\n");

            //Constructeurs
            //Constructeur par défaut
            out.write("    public " + rj.getNom() + "() {\n");
            for (int j = 1; j <= rj.getListeEntites().size(); j++) {
                out.write("        this.entite" + j + " = null;\n");
                out.write("        this.cardinalite" + j + " = \"\";\n");
            }
            for (int j = 1; j <= rj.getListeAttributs().size(); j++) {
                out.write("        this." + rj.getListeAttributs().get(j - 1) + " =" + nullval + ";\n");
            }
            out.write("    }\n\n");

            //Constructeur avec tous les paramètres
            out.write("    public " + rj.getNom() + "(");
            for (int j = 1; j <= rj.getListeAttributs().size(); j++) {
                out.write(rj.getTypeAttributs().get(j - 1) + " " + rj.getListeAttributs().get(j - 1) + ", ");
            }
            for (int j = 1; j <= rj.getListeEntites().size(); j++) {
                out.write("String cardinalite" + j + ", ");
            }
            for (int j = 1; j <= rj.getListeEntites().size() - 1; j++) {
                out.write(rj.getListeEntites().get(j - 1).getNom() + " entite" + j + ", ");
            }
            out.write(rj.getListeEntites().get(rj.getListeEntites().size() - 1).getNom() + " entite" + rj.getListeEntites().size() + ") {\n");

            for (int j = 1; j <= rj.getListeAttributs().size(); j++) {
                out.write("        this." + rj.getListeAttributs().get(j - 1) + " = " + rj.getListeAttributs().get(j - 1) + ";\n");
            }

            for (int j = 1; j <= rj.getListeEntites().size(); j++) {
                out.write("        this.entite" + j + " = entite" + j + ";\n");
            }

            for (int j = 1; j <= rj.getListeCardinalites().size(); j++) {
                out.write("        this.cardinalite" + j + " = cardinalite" + j + ";\n");
            }
            out.write("    }\n\n");

            //Constructeur de recopie
            out.write("    public " + rj.getNom() + "(" + rj.getNom() + " recopie){\n");
            for (int j = 1; j <= rj.getListeAttributs().size(); j++) {
                out.write("        this." + rj.getListeAttributs().get(j - 1) + " = recopie." + rj.getListeAttributs().get(j - 1) + ";\n");
            }
            for (int j = 1; j <= rj.getListeEntites().size(); j++) {
                out.write("        this.entite" + j + " = recopie.entite" + j + ";\n");
                out.write("        this.cardinalite" + j + " = recopie.cardinalite" + j + ";\n");
            }
            out.write("    }\n\n");

            //Getters and setters
            for (int j = 1; j <= rj.getListeCardinalites().size(); j++) {

                out.write("    public " + rj.getListeEntites().get(j - 1).getNom() + " getEntite" + j + "(){\n");
                out.write("        return entite" + j + ";\n    }\n\n");

                out.write("    public String getCardinalite" + j + "(){\n");
                out.write("        return cardinalite" + j + ";\n    }\n\n");

                out.write("    public void setEntite" + j + "(" + rj.getListeEntites().get(j - 1).getNom() + " entite" + j + "){\n");
                out.write("        this.entite" + j + " = entite" + j + ";\n    }\n\n");

                out.write("    public void setCardinalite" + j + "(String cardinalite" + j + "){\n");
                out.write("        this.cardinalite" + j + " = cardinalite" + j + ";\n    }\n\n");
            }

            for (int j = 1; j <= rj.getListeAttributs().size(); j++) {

                String Maj = rj.getListeAttributs().get(j - 1).replaceFirst(".", (rj.getListeAttributs().get(j - 1).charAt(0) + "").toUpperCase());

                out.write("    public " + rj.getListeAttributs().get(j - 1) + " get" + Maj + "(){\n");
                out.write("        return " + rj.getListeAttributs().get(j - 1) + ";\n    }\n\n");

                out.write("    public void set" + Maj + "(" + rj.getTypeAttributs().get(j - 1) + " " + rj.getListeAttributs().get(j - 1) + "){\n");
                out.write("        this." + rj.getListeAttributs().get(j - 1) + " = " + rj.getListeAttributs().get(j - 1) + ";\n    }\n\n");
            }

            out.write("}");
            out.close();

        }
    }
}
