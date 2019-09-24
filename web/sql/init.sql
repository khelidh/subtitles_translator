-- Initialisation de la database ocrjee avec 2 tables :
--     - fichier_srt : permet de mettre en base toutes les informations du fichier.srt d'origine
--     - files_srt_translation : met en base la traduction en cours
CREATE DATABASE IF NOT EXISTS ocrjee DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
use ocrjee;

DROP TABLE IF EXISTS subtitles;
DROP TABLE IF EXISTS fichiers_srt;

CREATE TABLE  files_srt (
            id INT( 11 ) NOT NULL AUTO_INCREMENT, 
            file_name VARCHAR( 200 ) NOT NULL UNIQUE,
            file_description VARCHAR( 200 ) NOT NULL, 
            number_line MEDIUMTEXT NOT NULL,
            date_start MEDIUMTEXT NOT NULL,
            date_end MEDIUMTEXT NOT NULL,
            original_subtitles MEDIUMTEXT NOT NULL,
            PRIMARY KEY ( id )) 
            ENGINE = INNODB  ;
CREATE TABLE  files_srt_translation (
            id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
            id_file_srt INT NOT NULL,
            translation_description VARCHAR( 200 ) DEFAULT NULL,
            translated_subtitles MEDIUMTEXT DEFAULT NULL,
            
            CONSTRAINT fk_fichier_srt
            FOREIGN KEY (id_file_srt)
            REFERENCES fichiers_srt(id)
            ) ENGINE = INNODB ;