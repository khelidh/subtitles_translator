/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.subtitles.app.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import com.subtitles.app.dao.interfaces.TranslatedSubtitlesDao;
import com.subtitles.app.dao.interfaces.FileSrtDao;

public class DaoFactory {
    
    private static final String jdbcDriver = "com.mysql.cj.jdbc.Driver";
    private static final String dbAddress = "jdbc:mysql://localhost:3306/";
    private static final String userPass = "?user=root&password=";
    private static final String dbName = "OCRjee";
    private static final String timeZone = "?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC";
    private static final String userName = "root";
    private static final String pass = "root";
    private static final String urlFinal = dbAddress + dbName + timeZone;
    
    
    
    private String url;
    private String username;
    private String password;

    DaoFactory(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public static DaoFactory getInstance() {
        try {
            Class.forName(jdbcDriver);
        } catch (ClassNotFoundException e) {

        }

        DaoFactory instance = new DaoFactory(
                urlFinal, userName, pass);
        return instance;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    // Récupération du FileDao
    public FileSrtDao getFileDao() {
        return new FileSrtDaoImpl(this);
    }
    // Récupération du SubtitlesDao
    public TranslatedSubtitlesDao getSubtitlesDao() {
        return new TranslatedSubtitlesDaoImpl(this);
    }
}