/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.hrApplication;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 *
 * @author Akil Mahimwala
 */
@Configuration
public class ApplicationConfiguration {

    private @Value("${filestore}")
    String filestore;
    @Value("${spring.datasource.url}")
    private String jdbcUrl;
    @Value("${spring.datasource.username}")
    private String jdbcUsername;
    @Value("${spring.datasource.password}")
    private String jdbcPassword;

    @Bean("jasyptStringEncryptor")
    public StringEncryptor stringEncryptor() throws IOException {
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        Properties props = new Properties();
        props.load(new FileInputStream(this.filestore + "jasypt.properties"));
        config.setPassword(props.getProperty("jasypt.password"));
        config.setAlgorithm("PBEWithMD5AndDES");
        config.setKeyObtentionIterations("1000");
        config.setPoolSize("1");
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
        config.setIvGeneratorClassName("org.jasypt.salt.NoOpIVGenerator");
        config.setStringOutputType("base64");
        encryptor.setConfig(config);
        return encryptor;
    }

    @Bean("dataSource")
    @Primary
    public DataSource dataSource() throws IOException {
        return DataSourceBuilder.create()
                .url(jdbcUrl)
                .username(jdbcUsername)
                .password(stringEncryptor().decrypt(jdbcPassword.substring(4, jdbcPassword.length() - 1)))
                .build();
    }

}
