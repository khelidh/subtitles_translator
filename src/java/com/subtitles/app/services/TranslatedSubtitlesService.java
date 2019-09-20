/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.subtitles.app.services;

import com.subtitles.app.dao.DaoFactory;
import com.subtitles.app.dao.TranslatedSubtitlesDaoImpl;

/**
 *
 * @author MAMA
 */
public class TranslatedSubtitlesService {
    DaoFactory daoFactory = DaoFactory.getInstance();
    TranslatedSubtitlesDaoImpl translatedSubtitlesDao = (TranslatedSubtitlesDaoImpl) daoFactory.getSubtitlesDao();
    FileSrtService fileSrtService = new FileSrtService();
    
    
    
    
    
    
}
