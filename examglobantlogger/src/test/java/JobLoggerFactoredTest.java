import customlogger.JobLoggerRefactored;
import junit.framework.TestCase;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class JobLoggerFactoredTest extends TestCase {
	
	private Connection getConection() throws Exception {
		String url = "jdbc:h2:~/tmp/h2dbs/log";
        String user = "sa";
        String passwd = "abc123";
        Class.forName("org.h2.Driver" );
    	return DriverManager.getConnection(url, user, passwd);
	}
	
    protected void setUp() throws Exception {
        super.setUp();
    	Connection connection = getConection();    
    	String sql =  "CREATE TABLE LOG_VALUE(message VARCHAR(255))";  
    	connection.createStatement().executeUpdate(sql);
        connection.close();             
    }

    protected void tearDown() throws Exception {
    	Connection connection = getConection();    
    	String sql =  "DROP TABLE LOG_VALUE";    
    	connection.createStatement().executeUpdate(sql);
        connection.close();        
    }

    public final void testLogOnlyBDActived() throws Exception {
        JobLoggerRefactored logger = JobLoggerRefactored.builder().onLog4Db(true).build();
        String[] lista = {"loger es un info", "loger es un logWarning", "loger es un logError"};
        logger.logInfo(lista[0]);
        logger.logWarning(lista[1]);
        logger.logError(lista[2]);
        
        Connection connection = getConection();
        PreparedStatement preparedStatement = connection.prepareStatement("Select message from LOG_VALUE");
        ResultSet rs = preparedStatement.executeQuery();
        
        int indice = 0;
        while (rs.next()) {
            assertTrue(rs.getString("message").contains(lista[indice]));
            indice++;
        }
    }


    public final void testLogOnlyBDInactived() throws Exception {
        JobLoggerRefactored logger = JobLoggerRefactored.builder().onLog4Db(false).build();
        String[] lista = {"loger es un info", "loger es un logWarning", "loger es un logError"};
        logger.logInfo(lista[0]);
        logger.logWarning(lista[1]);
        logger.logError(lista[2]);
        
        Connection connection = getConection();
        PreparedStatement preparedStatement = connection.prepareStatement("Select message from LOG_VALUE");
        ResultSet rs = preparedStatement.executeQuery();
        
        assertFalse(rs.next());
    }
    
    public final void testLogOnlFileAndConsoleActived() throws Exception {

        JobLoggerRefactored logger2 = JobLoggerRefactored.builder().onLog4Console(true).build();
        logger2.logWarning("lINFO loger 2");

        JobLoggerRefactored logger = JobLoggerRefactored.builder().onLog4File(false).build();
        logger.logInfo("lINFO loger 1");
        
    }
    
    public final void testLogOnlFileAndConsoleInactived() throws Exception {

        JobLoggerRefactored logger2 = JobLoggerRefactored.builder().onLog4Console(false).build();
        logger2.logWarning("lINFO loger 2");

        JobLoggerRefactored logger = JobLoggerRefactored.builder().onLog4File(false).build();
        logger.logInfo("lINFO loger 1");
        
    }

}