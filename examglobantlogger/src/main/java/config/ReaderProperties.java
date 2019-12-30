package config;

import java.io.InputStream;
import java.util.Properties;

public class ReaderProperties {
	private static ReaderProperties instance;
	private String url;
	private String user;
	private String pass;
	private String driver;
	private String directory;
	InputStream inputStream;
		 
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public String getDirectory() {
		return directory;
	}

	public void setDirectory(String directory) {
		this.directory = directory;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	private ReaderProperties() {
		try {
			Properties prop = new Properties();
			String propFileName = "config.properties";
 
			inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
 
			if (inputStream != null) {
				prop.load(inputStream);
			} 
			// get the property value and print it out
			this.url = prop.getProperty("url");
			this.user = prop.getProperty("user");
			this.pass = prop.getProperty("pass");
			this.driver = prop.getProperty("driver");
			this.directory = prop.getProperty("directory");		
			System.out.println(url + user + driver + directory);
			inputStream.close();
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		} finally {
		}
	}

	public static ReaderProperties getInstance() {
		if(instance == null) {
			instance = new ReaderProperties();
		}
		return instance;
	}
}
