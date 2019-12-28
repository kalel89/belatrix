import customlogger.JobLoggerRefactored;
import junit.framework.TestCase;

import java.io.IOException;

public class JobLoggerFactoredTest extends TestCase {
    protected void setUp() throws Exception {
        super.setUp();
    }

    protected void tearDown() throws Exception {
        /*
        JobLoggerRefactored logger1 = JobLoggerRefactored.builder().onLog4File().build();
        JobLoggerRefactored logger2 = JobLoggerRefactored.builder().onLog4Console().build();
        JobLoggerRefactored logger3 = JobLoggerRefactored.builder().onLog4Db().build();
        JobLoggerRefactored logger4 = JobLoggerRefactored.builder().onLog4Db().onLog4Console().onLog4File().build();
        logger1.logError("rsto es un error");
        logger1.logInfo("rsto es un error");
        logger1.logWarning("rsto es un error");

        logger2.logError("loger2 es un error");
        logger2.logInfo("loger2 es un error");
        logger2.logWarning("loger2 es un error");

        logger3.logError("loger3 es un error");
        logger3.logInfo("loger3 es un error");
        logger3.logWarning("loger3 es un error");

        logger4.logError("loger4 es un error");
        logger4.logInfo("loger4 es un error");
        logger4.logWarning("loger4 es un error");

         */
    }
/*
    public final void testLogOnlyConsole() throws IOException {
        JobLoggerRefactored logger = JobLoggerRefactored.builder().onLog4Console().build();
        logger.logInfo(null);
        logger.logWarning("loger es un logWarning");
        logger.logError("loger es un logError");
    }

    public final void testLogOnlyFile() throws IOException {
        JobLoggerRefactored logger = JobLoggerRefactored.builder().onLog4File().build();
        logger.logInfo("loger es un info");
        logger.logWarning("loger es un logWarning");
        logger.logError("loger es un logError");
    }

    public final void testLogOnlyBD() throws IOException {
        JobLoggerRefactored logger = JobLoggerRefactored.builder().onLog4Db().build();
        logger.logInfo("loger es un info");
        logger.logWarning("loger es un logWarning");
        logger.logError("loger es un logError");
    }
*/
    public final void testLogOnlFileAndConsole() throws IOException {

        JobLoggerRefactored logger2 = JobLoggerRefactored.builder().onLog4Console().build();
        logger2.logWarning("lINFO loger 2");

        JobLoggerRefactored logger = JobLoggerRefactored.builder().onLog4File().build();
        logger.logInfo("lINFO loger 1");
    }

}