/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.subtitles.app.dao;

import com.subtitles.app.beans.FileSrt;
import com.subtitles.app.beans.TranslatedSubtitles;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.subtitles.app.dao.interfaces.TranslatedSubtitlesDao;

/**
 *
 * @author MAMA
 */
public class TranslatedSubtitlesDaoImpl implements TranslatedSubtitlesDao{
    
    private static final String REQUETE_INSERT_SUBTITLES = "INSERT INTO subtitles(id_fichier_srt_fk) VALUES(?);";
    private static final String REQUETE_UPDATE_SUBTITLES = "UPDATE subtitles SET subtitle_translated = ?, description_translation = ? WHERE id = ?";
    private static final String REQUETE_SELECT_ALL = "SELECT * FROM subtitles";
    private static final String REQUETE_SELECT_ALL_WITH_FILE_NAME = 
            "SELECT s.id, s.description_translation, f.file_name, f.file_description "
            + "FROM subtitles s "
            + "JOIN fichiers_srt f "
            + "ON f.id = s.id_fichier_srt_fk "
            + "ORDER BY f.file_name ASC;";
    
    private DaoFactory daoFactory;

    TranslatedSubtitlesDaoImpl(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public Integer ajouter( TranslatedSubtitles subtitles ) {
        try {
            Connection connexion = daoFactory.getConnection();
            PreparedStatement preparedStatement = connexion.prepareStatement(REQUETE_INSERT_SUBTITLES, Statement.RETURN_GENERATED_KEYS);
            
            preparedStatement.setInt(1, subtitles.getFileId());
            preparedStatement.executeUpdate();
            
            // Récupéraation de l'id du record inséré. On retourne -1 si l'INSERT n'a pas abouti
            ResultSet tableKeys = preparedStatement.getGeneratedKeys();
            tableKeys.next();
            
            return tableKeys.getInt(1);
        } catch (SQLException e) {
            return -1;
        }
    }

    @Override
    public void enregistrer(TranslatedSubtitles subtitles) {
        try {
            Connection connexion = daoFactory.getConnection();
            PreparedStatement preparedStatement = connexion.prepareStatement(REQUETE_UPDATE_SUBTITLES);
            
            preparedStatement.setString(1, subtitles.getTranlateSubtitlesDB());
            preparedStatement.setString(2, subtitles.getSubtitlesDescription());
            preparedStatement.setInt(3, subtitles.getId());

            preparedStatement.executeUpdate();
            
        } catch (SQLException e) {
        }
    }
    @Override
    public void enregistrer(Integer id, String translatedSubs, String descriptionSubs) {
        
        try {
            Connection connexion = daoFactory.getConnection();
            PreparedStatement preparedStatement = connexion.prepareStatement(REQUETE_UPDATE_SUBTITLES);
            
            preparedStatement.setString(1, translatedSubs);
            preparedStatement.setString(2, descriptionSubs);
            preparedStatement.setInt(3, id);
            
            preparedStatement.executeUpdate();
            
        } catch (SQLException e) {
        }
    }

    @Override
    public List<TranslatedSubtitles> listerAll() {
        
        List<TranslatedSubtitles> listSubtitles = new ArrayList<>();
        Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;

        try {
            connexion = daoFactory.getConnection();
            statement = connexion.createStatement();
            resultat = statement.executeQuery(REQUETE_SELECT_ALL);

            while (resultat.next()) {
                Integer subsId = resultat.getInt("id");
                String subsDescription = resultat.getString("description_translation");
                
                TranslatedSubtitles subs = new TranslatedSubtitles(subsId, subsDescription, "");
                listSubtitles.add(subs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listSubtitles;
    }

    @Override
    public Map<FileSrt, TranslatedSubtitles> listerAllWithFileName() {
        HashMap<FileSrt, TranslatedSubtitles> map = new HashMap<>();
        
        Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;

        try {
            connexion = daoFactory.getConnection();
            statement = connexion.createStatement();
            resultat = statement.executeQuery(REQUETE_SELECT_ALL_WITH_FILE_NAME);

            while (resultat.next()) {
                Integer subsId = resultat.getInt("id");
                String subsDescription = resultat.getString("description_translation");
                String fileName = resultat.getString("file_name");
                String fileDescription = resultat.getString("file_description");
                
                FileSrt file = new FileSrt(fileName, fileDescription);
                TranslatedSubtitles subs = new TranslatedSubtitles(subsId, subsDescription);
                
                map.put(file, subs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return map;
    }
    
    
}
