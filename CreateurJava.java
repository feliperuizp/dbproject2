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
                    case "<attribut":
                        separation = scan(s, 7);
                        this.entites.get(entites - 1).getListeAttributs().add(separation[1]);
                        this.entites.get(entites - 1).getTypeAttributs().add(separation[3]);
                        break;
                    case "<link":
                        separation = scan(s, 5);
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
                                }
                            }
                        }
                        nrel += 1;
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
            out.write("import java.util.LinkedList; \n \n");

            out.write("public Class " + ej.getNom() + " { \n \n");
            int att = 0;
            for (String ss : ej.getListeAttributs()) {
                switch (ej.getTypeAttributs().get(att)) {
                    case ("Auto_increment"):
                        // TODO https://stackoverflow.com/questions/24305830/java-auto-increment-id
                        ej.getTypeAttributs().set(att, "int");
                        nullval="0";
                        break;
                    case ("Varchar"):
                        ej.getTypeAttributs().set(att, "String");
                        nullval="null";
                        break;
                    case ("Char"):
                        ej.getTypeAttributs().set(att, "char");
                        nullval="''";
                        break;
                    case ("Bool"):
                        ej.getTypeAttributs().set(att, "Boolean");
                        nullval="null";
                        break;
                    case ("Date"):
                        ej.getTypeAttributs().set(att, "Date");
                        nullval="null";
                        break;
                    case ("Int"):
                        ej.getTypeAttributs().set(att, "int");
                        nullval="0";
                        break;
                    case ("Float"):
                        ej.getTypeAttributs().set(att, "float");
                        nullval="0";
                        break;
                    case ("Money"):
                        ej.getTypeAttributs().set(att, "float");
                        nullval="0";
                        break;
                    case ("BigInt"):
                        ej.getTypeAttributs().set(att, "int");
                        nullval="0";
                        break;
                    case ("Blob"):
                        ej.getTypeAttributs().set(att, "Blob");
                        nullval="null";
                        break;
                    case ("Datetime"):
                        ej.getTypeAttributs().set(att, "Date");
                        nullval="null";
                        break;
                    case ("Decimal"):
                        ej.getTypeAttributs().set(att, "float");
                        nullval="0";
                        break;
                    case ("Double"):
                        ej.getTypeAttributs().set(att, "double");
                        nullval="0";
                        break;
                    case ("Double Precision"):
                        ej.getTypeAttributs().set(att, "double");
                        nullval="0";
                        break;
                    case ("Longblob"):
                        ej.getTypeAttributs().set(att, "Blob");
                        nullval="null";
                        break;
                    case ("Longtext"):
                        ej.getTypeAttributs().set(att, "String");
                        nullval="null";
                        break;
                    case ("Mediunblob"):
                        ej.getTypeAttributs().set(att, "Blob");
                        nullval="null";
                        break;
                    case ("Mediumint"):
                        ej.getTypeAttributs().set(att, "int");
                        nullval="0";
                        break;
                    case ("Mediumtext"):
                        ej.getTypeAttributs().set(att, "String");
                        nullval="null";
                        break;
                    case ("Numeric"):
                        ej.getTypeAttributs().set(att, "BigDecimal");
                        nullval="null";
                        break;
                    case ("Real"):
                        ej.getTypeAttributs().set(att, "BigInteger");
                        nullval="null";
                        break;
                    case ("Smallint"):
                        ej.getTypeAttributs().set(att, "int");
                        nullval="0";
                        break;
                    case ("Text"):
                        ej.getTypeAttributs().set(att, "String");
                        nullval="null";
                        break;
                    case ("Time"):
                        ej.getTypeAttributs().set(att, "LocalTime");
                        nullval="null";
                        break;
                    case ("TimeStamp"):
                        ej.getTypeAttributs().set(att, "LocalDateTime");
                        nullval="null";
                        break;
                    case ("TinyBlob"):
                        ej.getTypeAttributs().set(att, "Blob");
                        nullval="null";
                        break;
                    case ("TinyINT"):
                        ej.getTypeAttributs().set(att, "int");
                        nullval="0";
                        break;
                    case ("TinyText"):
                        ej.getTypeAttributs().set(att, "String");
                        nullval="null";
                        break;
                    case ("Year"):
                        ej.getTypeAttributs().set(att, "int");
                        nullval="0";
                        break;
                    case ("Integer"):
                        ej.getTypeAttributs().set(att, "Integer");
                        nullval="null";
                        break;

                }
                out.write("private " + ej.getTypeAttributs().get(att) + " " + ss + "; \n");
                att += 1;
            }
            out.write("\n");

            // Ecriture des constructeurs
            //constructeur par défault
            out.write("public " + ej.getNom() + "(){ \n");
            for (String ss : ej.getListeAttributs()) {
                out.write("this." + ss + "= " + nullval+";\n");
            }
            out.write("}\n \n");
            //constructeur avec tous les paramètres
            out.write("public " + ej.getNom() + "(");
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
                out.write("this." + ss + " = " + ss + "; \n");
            }
            out.write("} \n \n");
            //constructeur de recopie
            out.write("public " + ej.getNom() + "(" + ej.getNom() + " recopie) { \n");
            for (String ss : ej.getListeAttributs()) {
                out.write("this." + ss + " = recopie." + ss + "; \n");
            }
            out.write("} \n \n");

            // Ecriture des getters et setters
            att = 0;
            for (String ss : ej.getListeAttributs()) {
                //ss_Maj est une version de ss avec la première lettre comme majuscule
                String ss_Maj = ss.replaceFirst(".", (ss.charAt(0) + "").toUpperCase());
                out.write("public " + ej.getTypeAttributs().get(att) + " get" + ss_Maj + "(){ \n");
                out.write("return " + ss + ";\n}\n \n");
                out.write("public void set" + ss_Maj + "(" + ej.getTypeAttributs().get(att) + " " + ss + ") { \n");
                out.write("this." + ss + " = " + ss + "; \n } \n \n");
                att += 1;
            }

            out.write("}");
            out.close();
        }

        for (RelationJava rj : this.relations) {

            FileWriter fichier = new FileWriter(rj.getNom() + ".java");

            BufferedWriter out = new BufferedWriter(fichier);
            out.write("import java.util.LinkedList; \n \n");
            
            //Définition attributs
            out.write("public class " + rj.getNom() + " {\n\n");
            out.write("private " + rj.getEntite1().getNom() + " entite1;\n");
            out.write("private " + rj.getEntite2().getNom() + " entite2;\n");
            out.write("private final String cardinalite1 = "+rj.getCard1()+";\n");
            out.write("private final String cardinalite2 = "+rj.getCard2()+";\n}\n\n");

            //Constructeurs
            //Constructeur par défaut
            out.write("public " + rj.getNom() + "() {\n");
            out.write(" this.entite1 = null;\n");
            out.write(" this.entite2 = null;\n");

            //Constructeur avec tous les paramètres
            out.write("public " + rj.getNom() + "(" + rj.getEntite1().getNom() + " entite1, " + rj.getEntite2().getNom() + " entite2) {\n");
            out.write(" this.entite1 = entite1;\n");
            out.write(" this.entite2 = entite2;\n");
            out.write(" this.cardinalite1 = cardinalite1;\n");
            out.write(" this.cardinalite2 = cardinalite2;\n\n");


            //Constructeur de recopiage
            out.write("public " + rj.getNom() + "(" + rj.getNom() + " relation){\n");
            out.write(" this.entite1 = relation.entite1;\n");
            out.write(" this.entite2 = relation.entite 2;\n");

            //Getters and setters
            out.write("public " + rj.getEntite1().getNom() + " getEntite1(){\n");
            out.write(" return entite1;\n}\n\n");

            out.write("public void setEntite1(" + rj.getEntite1().getNom() + " entite1){\n");
            out.write(" this.entite1 = entite1;\n}\n\n");

            out.write("public " + rj.getEntite2().getNom() + " getEntite2(){\n");
            out.write(" return entite2;\n}\n\n");

            out.write("public void setEntite2(" + rj.getEntite2().getNom() + " entite2){\n");
            out.write(" this.entite2 = entite2;\n}\n\n");

            out.write("public String getCardinalite1(){\n");
            out.write(" return cardinalite1;\n}\n\n");

            out.write("public void setCardinalite1(String cardinalite1){\n");
            out.write(" this.cardinalite1 = cardinalite1;\n}\n\n");

            out.write("public String getCardinalite2(){\n");
            out.write(" return cardinalite2;\n}\n\n");

            out.write("public void setCardinalite2(String cardinalite2){\n");
            out.write(" this.cardinalite2 = cardinalite2;\n}\n\n}");

            out.close();

        }
    }
}
