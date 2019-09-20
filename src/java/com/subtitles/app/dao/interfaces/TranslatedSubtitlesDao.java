/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.subtitles.app.dao.interfaces;

import com.subtitles.app.beans.FileSrt;
import com.subtitles.app.beans.TranslatedSubtitles;
import java.util.List;
import java.util.Map;

/**
 *
 * @author MAMA
 */
public interface TranslatedSubtitlesDao {
    Integer ajouter( TranslatedSubtitles subtitles );
    void enregistrer ( TranslatedSubtitles subtitles );
    void enregistrer ( Integer id, String translatedSubs, String descriptionSubs );
    
    List<TranslatedSubtitles> listerAll();
    Map<FileSrt, TranslatedSubtitles> listerAllWithFileName();
}
