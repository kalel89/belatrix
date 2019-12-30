package customlogger.internalLogger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.logging.Logger;

import config.ReaderProperties;

public class InternalLoggerBd implements InternalLogger {

    public void logMessage(String message, Logger loger) throws Exception {
        String url = ReaderProperties.getInstance().getUrl();
        String user = ReaderProperties.getInstance().getUser();
        String passwd = ReaderProperties.getInstance().getPass();
        Class.forName(ReaderProperties.getInstance().getDriver());
    	Connection connection = DriverManager.getConnection(url, user, passwd);
    	PreparedStatement preparedStatement = connection.prepareStatement("insert into LOG_VALUE(message) VALUES (?)");
        preparedStatement.setString(1, message);
        preparedStatement.executeUpdate();
    	Statement stmt = connection.createStatement(); 
        connection.close();
    }
}
