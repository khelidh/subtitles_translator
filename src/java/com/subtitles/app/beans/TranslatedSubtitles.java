/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.subtitles.app.beans;

import java.util.ArrayList;
import java.util.Arrays;

/*
 * Traduction d'un fichier.srt (liée via une clé étrangère de l'id du fichier en question) :
        - id
        - id du fichier.srt
        - description de la traduction
        - traduction des sous-titres 
 */
public class TranslatedSubtitles {
    
    private static final String DELIMITER_JOIN = "$$$";
    Integer id;
    Integer fileId;
    String subtitlesDescription;
    ArrayList<String> tranlateSubtitles;

    public TranslatedSubtitles(Integer id, String subtitlesDescription) {
        this.id = id;
        this.subtitlesDescription = subtitlesDescription;
    }
    
    public TranslatedSubtitles(Integer id, String subtitlesDescription, String tranlateSubtitles) {
        this.id = id;
        this.subtitlesDescription = subtitlesDescription;
        this.setTranlateSubtitlesDB(tranlateSubtitles);
    }

    public TranslatedSubtitles(Integer id, Integer fileId, String subtitlesDescription, ArrayList<String> tranlateSubtitles) {
        this.id = id;
        this.fileId = fileId;
        this.subtitlesDescription = subtitlesDescription;
        this.tranlateSubtitles = tranlateSubtitles;
    }

    public TranslatedSubtitles(Integer fileId, String subtitlesDescription, ArrayList<String> tranlateSubtitles) {
        this.fileId = fileId;
        this.subtitlesDescription = subtitlesDescription;
        this.tranlateSubtitles = tranlateSubtitles;
    }

    public TranslatedSubtitles(Integer fileId) {
        this.fileId = fileId;
        this.subtitlesDescription = null;
        this.tranlateSubtitles = null;
    }
    
    public static String compressedArrayList(ArrayList array) {
        return String.join(DELIMITER_JOIN, array);
    }
    
    public String getTranlateSubtitlesDB(){
        return FileSrt.compressedArrayList(this.getTranlateSubtitles());
    }
    private void setTranlateSubtitlesDB(String tranlateSubtitlesString){
        this.setTranlateSubtitles(FileSrt.decompressedString(tranlateSubtitlesString));
    }

    private static ArrayList<String> decompressedString(String array) {
        return (ArrayList) Arrays.asList(array.split(DELIMITER_JOIN));
    }
    
    

    
    /////////// Get & Set
    
    public Integer getFileId() {
        return fileId;
    }

    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }

    public String getSubtitlesDescription() {
        return subtitlesDescription;
    }

    public void setSubtitlesDescription(String subtitlesDescription) {
        this.subtitlesDescription = subtitlesDescription;
    }

    public ArrayList<String> getTranlateSubtitles() {
        return tranlateSubtitles;
    }

    public void setTranlateSubtitles(ArrayList<String> tranlateSubtitles) {
        this.tranlateSubtitles = tranlateSubtitles;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    
    
    

    

    
    
    
    
    
}
