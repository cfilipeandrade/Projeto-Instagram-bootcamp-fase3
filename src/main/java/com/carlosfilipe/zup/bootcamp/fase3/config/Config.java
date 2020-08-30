package com.carlosfilipe.zup.bootcamp.fase3.config;

import java.util.Properties;

import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    private Properties properties;
    private Properties config;
    private static Config instance;


    private Config() {}


	private String _getProperty(String key) {
		return properties.getProperty(key);
	}

	private String _getConfig(String key) {
		return config.getProperty(key);
	}

	public static String getProperty(String key) {
		if (instance == null)
			instance = new Config();
		return instance._getProperty(key);
	}

	public static String getConfig(String key) {
		if (instance == null)
			instance = new Config();
		return instance._getConfig(key);
	}
    
}