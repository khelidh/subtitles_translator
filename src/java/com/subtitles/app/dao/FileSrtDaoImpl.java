/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.subtitles.app.dao;

import com.subtitles.app.beans.FileSrt;
import com.subtitles.app.dao.interfaces.FileDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MAMA
 */
public class FileSrtDaoImpl implements FileDao{
    
    private static final String REQUETE_INSERT_FILE_SRT = "INSERT INTO fichiers_srt(file_name, file_description, number_line, date_start, date_end, subtitle"
            + ") VALUES(?, ?, ?, ?, ?, ?);";
    
    private static final String REQUETE_FIND_BY_ID = "SELECT file_name, file_description FROM fichiers_srt WHERE id = ?";
    private static final String REQUETE_GET_ALL_FILES_SRT = "SELECT id, file_name, file_description FROM fichiers_srt";
    
    private static final String REQUETE_GET_FILE_AND_TRANSLATION = 
              "SELECT f.file_name, f.file_description, f.number_line, f.date_start, f.date_end, f.subtitle, s.description_translation, s.subtitle_translated "
            + "FROM fichiers_srt f "
            + "JOIN subtitles s ON s.id_fichier_srt_fk = f.id "
            + "WHERE s.id = ?";

    private DaoFactory daoFactory;
    
    FileSrtDaoImpl(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public Integer ajouter(FileSrt fileSrt) {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        
        
        try {
            connexion = daoFactory.getConnection();
            preparedStatement = connexion.prepareStatement(REQUETE_INSERT_FILE_SRT,Statement.RETURN_GENERATED_KEYS);
            
            preparedStatement.setString(1, fileSrt.getFileName());
            preparedStatement.setString(2, fileSrt.getFileDescription());
            preparedStatement.setString(3, fileSrt.getNumbersDB());
            preparedStatement.setString(4, fileSrt.getDatesStartDB());
            preparedStatement.setString(5, fileSrt.getDatesEndDB());
            preparedStatement.setString(6, fileSrt.getSubsDB());

            preparedStatement.executeUpdate();
            
            // Récupéraation de l'id du record inséré. On retourne -1 si l'INSERT n'a pas abouti
            ResultSet tableKeys = preparedStatement.getGeneratedKeys();
            tableKeys.next();
            return tableKeys.getInt(1);
            
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public List<FileSrt> listerAllFiles() {
       return listerFiles(REQUETE_GET_ALL_FILES_SRT);
    }
    
    public List<FileSrt> listerFiles(String requete){
        
        List<FileSrt> filesSrt = new ArrayList<>();
        Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;

        try {
            connexion = daoFactory.getConnection();
            statement = connexion.createStatement();
            resultat = statement.executeQuery(requete);

            while (resultat.next()) {
                Integer fileId = resultat.getInt("id");
                String fileName = resultat.getString("file_name");
                String fileDescription = resultat.getString("file_description");
                
                FileSrt fileSrt = new FileSrt(fileId, fileName, fileDescription);
                filesSrt.add(fileSrt);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return filesSrt;
    }

    @Override
    public FileSrt findById(Integer fileId) {
        Connection connexion = null;
        ResultSet resultat = null;
        FileSrt fileSrt = null;
        try {
            connexion = daoFactory.getConnection();
            
            PreparedStatement preparedStatement = connexion.prepareStatement(REQUETE_FIND_BY_ID);
            preparedStatement.setInt(1, fileId);
            
            resultat = preparedStatement.executeQuery();
            
            String fileName = resultat.getString("file_name");
            String fileDescription = resultat.getString("file_description");

            fileSrt = new FileSrt(fileId ,fileName, fileDescription);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return fileSrt;
    }

    @Override
    public String[] getFileAndTranslation(Integer subsID) {
        Connection connexion = null;
        ResultSet resultat = null;
        
        String[] attributesArray = new String[8];
        
        try {
            connexion = daoFactory.getConnection();
            PreparedStatement preparedStatement = connexion.prepareStatement(REQUETE_GET_FILE_AND_TRANSLATION);
            preparedStatement.setInt(1, subsID);
            
            resultat = preparedStatement.executeQuery();
            
            if(resultat.next()){

            //file_name, file_description, number_line, date_start, date_end, subtitle, description_translation, subtitle_translated
              for (int i = 0; i < attributesArray.length; i++) {
                    attributesArray[i] = resultat.getString(i+1);
                }
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return attributesArray;
    }

  

    
    
    
}
