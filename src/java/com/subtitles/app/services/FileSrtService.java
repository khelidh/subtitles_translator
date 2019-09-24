/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.subtitles.app.services;

import com.subtitles.app.beans.FileSrt;
import com.subtitles.app.beans.TranslatedSubtitles;
import com.subtitles.app.dao.DaoFactory;
import com.subtitles.app.dao.FileSrtDaoImpl;
import com.subtitles.app.dao.TranslatedSubtitlesDaoImpl;
import com.subtitles.app.dao.interfaces.TranslatedSubtitlesDao;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author MAMA
 */
public class FileSrtService {
    DaoFactory daoFactory = DaoFactory.getInstance();
    FileSrtDaoImpl fileSrtDao = (FileSrtDaoImpl) daoFactory.getFileDao();
    TranslatedSubtitlesDaoImpl translatedSubtitlesDao = (TranslatedSubtitlesDaoImpl) daoFactory.getSubtitlesDao();
    TranslatedSubtitlesService translatedSubtitlesService = new TranslatedSubtitlesService();
    
    
    public Integer addFileSrt (FileSrt fileSrt) {
        return fileSrtDao.ajouter(fileSrt);
    }
    
    public List<FileSrt> getAllFiles() {
        return fileSrtDao.listerAllFiles();
    }
    
    public String[] getFileTranslationAllData(Integer subsID){
        return fileSrtDao.getFileAndTranslation(subsID);
    }

    /**
     * SetAttribute dans la request : les fichiers de sous-titres disponibles et les traductions en cours
     * @param request 
     */
    public void setFilesAvailableAndSubtitlesInProgress(HttpServletRequest request) {
        request.setAttribute("listeFiles", fileSrtDao.listerAllFiles());

        HashMap<FileSrt, TranslatedSubtitles> map = (HashMap<FileSrt, TranslatedSubtitles>) translatedSubtitlesDao.listerAllWithFileName();
        request.setAttribute("listeSubs", map);
    }
    
    
}
