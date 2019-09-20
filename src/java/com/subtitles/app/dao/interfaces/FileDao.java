/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.subtitles.app.dao.interfaces;

import com.subtitles.app.beans.FileSrt;
import java.util.List;

/**
 *
 * @author MAMA
 */
public interface FileDao {
    Integer ajouter( FileSrt fileSrtDB );
    
    FileSrt findById(Integer fileId);
    List<FileSrt> listerAllFiles();
    String[] getFileAndTranslation(Integer subsID);
}
