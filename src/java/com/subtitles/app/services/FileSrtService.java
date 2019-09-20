/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.subtitles.app.services;

import com.subtitles.app.beans.FileSrt;
import com.subtitles.app.dao.DaoFactory;
import com.subtitles.app.dao.FileSrtDaoImpl;
import java.util.List;

/**
 *
 * @author MAMA
 */
public class FileSrtService {
    DaoFactory daoFactory = DaoFactory.getInstance();
    FileSrtDaoImpl fileSrtDao = (FileSrtDaoImpl) daoFactory.getFileDao();
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
    
    
}
