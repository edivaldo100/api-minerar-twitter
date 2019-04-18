package com.edivaldo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties
//@PropertySource("application-${spring.profiles.active}.properties")
@PropertySource("application.properties")
public class Config {

	@Value("${db_driver}")
	private String db_driver;
			
	@Value("${db_url}")
	private String db_url;
	
	@Value("${db_username}")
	private String db_username;
	
	@Value("${db_password}")
	private String db_password;
	
	@Value("${db_platform}")
	private String db_platform;

	public String getDb_driver() {
		return db_driver;
	}

	public void setDb_driver(String db_driver) {
		this.db_driver = db_driver;
	}

	public String getDb_url() {
		return db_url;
	}

	public void setDb_url(String db_url) {
		this.db_url = db_url;
	}

	public String getDb_username() {
		return db_username;
	}

	public void setDb_username(String db_username) {
		this.db_username = db_username;
	}

	public String getDb_password() {
		return db_password;
	}

	public void setDb_password(String db_password) {
		this.db_password = db_password;
	}

	public String getDb_platform() {
		return db_platform;
	}

	public void setDb_platform(String db_platform) {
		this.db_platform = db_platform;
	}

	@Override
	public String toString() {
		return "Config [db_driver=" + db_driver + ", db_url=" + db_url + ", db_username=" + db_username
				+ ", db_password=" + db_password + ", db_platform=" + db_platform + "]";
	}
	
	
}
