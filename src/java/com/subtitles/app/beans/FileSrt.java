/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.subtitles.app.beans;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import org.apache.commons.lang3.StringUtils;
import javax.servlet.ServletContext;

/*
 * Objet FileSrt : informations du fichier.srt uploadé par l'utilisateur :
 *      - id du fichier
 *      - nom (unique) du fichier
 *      - description du fichier
 *      - numérotation des sous-titres
 *      - intervalle de temps (début et fin en deux attributs) des sous-titres
 *      - sous-titres
 */
public class FileSrt {
    
    /******************************************************************************
    
    Constantes
    
    */

    public static final String DELIMITER = " --> ";
    public static final String DELIMITER_JOIN = "XXXXX";
    public static final String FILE_DIRECTORY = "/ressources_srt/";

    /********************************************************************************
    
    Attributs
    
    */
    
    private Integer id;
    private String fileName;
    private String fileDescription;
    private ArrayList<String> numbers;
    private ArrayList<String> datesStart;
    private ArrayList<String> datesEnd;
    private ArrayList<String> subs;
    
    /***********************************************************************************
    
    Constructeurs
    
    */

    public FileSrt(String fileName, String fileDescription) {
        this.id = -1;
        this.fileName = fileName;
        this.fileDescription = fileDescription;
    }

    public FileSrt(Integer id, String fileName, String fileDescription) {
        this.id = id;
        this.fileName = fileName;
        this.fileDescription = fileDescription;
    }

    public FileSrt(String fileName, String fileDescription, ArrayList<String> numbers, ArrayList<String> datesStart, ArrayList<String> datesEnd, ArrayList<String> subs) {
        this.fileName = fileName;
        this.fileDescription = fileDescription;
        this.numbers = numbers;
        this.datesStart = datesStart;
        this.datesEnd = datesEnd;
        this.subs = subs;
    }

    public FileSrt(String fileName, String fileDescription, String numbers, String datesStart, String datesEnd, String subs) {
        this.fileName = fileName;
        this.fileDescription = fileDescription;
        this.numbers = decompressedString(numbers);
        this.datesStart = decompressedString(datesStart);
        this.datesEnd = decompressedString(datesEnd);
        this.subs = decompressedString(subs);
    }
    
    /******************************************************************************
    
    Méthodes
    
    */

    /**
     * Permet de trier les données du fichier srt selon la structure suivante :
     *      - Ligne X : numérotation du sous-titre
     *      - Ligne X + 1 : timeline du sous-titre
     *      - Ligne(s) X + 2 (et suivantes) : sous-titre
     *      - Ligne suivante : chaine de caractère vide qui marque la fin d'un block sous-titre
     * 
     * Les données sont stockées dans des ArrayList<String> et ajoutées à l'objet FileSrt
     * @param servletContext 
     */
    public void fileHandler(ServletContext servletContext) {
        ArrayList<String> originalSubtitlesList = new ArrayList<>();
        ArrayList<String> numberLinesList = new ArrayList<>();
        ArrayList<String> datesStartList = new ArrayList<>();
        ArrayList<String> datesEndList = new ArrayList<>();

        String fileNameContext = servletContext.getRealPath(FILE_DIRECTORY + this.getFileName());
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(fileNameContext));
            String line;
            while ((line = br.readLine()) != null) {
                if (StringUtils.isNumeric(line)) {

                    //Numéro de ligne
                    numberLinesList.add(line);

                    line = br.readLine();

                    String[] tab = line.split(DELIMITER);
                    String dateStart = tab[0];
                    String dateEnd = tab[1];
                    datesStartList.add(dateStart);
                    datesEndList.add(dateEnd);

                    line = br.readLine();

                    String subtitle = "";
                    do {
                        subtitle += " " + line;
                        line = br.readLine();
                    } while (null != line && !line.equals(""));
                    originalSubtitlesList.add(subtitle);
                }
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.setNumbers(numberLinesList);
        this.setDatesStart(datesStartList);
        this.setDatesEnd(datesEndList);
        this.setSubs(originalSubtitlesList);
    }

    public static String compressedArrayList(ArrayList array) {
        return String.join(DELIMITER_JOIN, array);
    }

    public static ArrayList<String> decompressedString(String array) {
        return new ArrayList<>(Arrays.asList(array.split(DELIMITER_JOIN)));
    }

    public String getNumbersDB() {
        return compressedArrayList(this.getNumbers());
    }

    public String getDatesStartDB() {
        return compressedArrayList(this.getDatesStart());
    }

    public String getDatesEndDB() {
        return compressedArrayList(this.getDatesEnd());
    }

    public String getSubsDB() {
        return compressedArrayList(this.getSubs());
    }

    
    
    /**************************************************************************************
    
    Get & Set
       
    */
    
    public String getFileName() {
        return this.fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileDescription() {
        return fileDescription;
    }

    public void setFileDescription(String fileDescription) {
        this.fileDescription = fileDescription;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ArrayList<String> getNumbers() {
        return numbers;
    }

    public void setNumbers(ArrayList<String> numbers) {
        this.numbers = numbers;
    }

    public ArrayList<String> getDatesStart() {
        return datesStart;
    }

    public void setDatesStart(ArrayList<String> datesStart) {
        this.datesStart = datesStart;
    }

    public ArrayList<String> getDatesEnd() {
        return datesEnd;
    }

    public void setDatesEnd(ArrayList<String> datesEnd) {
        this.datesEnd = datesEnd;
    }

    public ArrayList<String> getSubs() {
        return subs;
    }

    public void setSubs(ArrayList<String> subs) {
        this.subs = subs;
    }

}
