package customlogger.internalLogger;

import customlogger.JobLoggerRefactored;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InternalLoggerFile implements InternalLogger {
    private static Map dbParams;

    public InternalLoggerFile() {
        dbParams = new HashMap();
        dbParams.put("logFileFolder", "C:\\Users\\Usuario\\Desktop\\imagesMines");
    }

    @Override
    public void logMessage(String message, Logger logger) throws IOException {
        final String path = dbParams.get("logFileFolder") + "/logFile.txt";
        File logFile = new File(path);
        if (!logFile.exists()) {
            logFile.createNewFile();
        }
        FileHandler fh = new FileHandler(path);
        logger.addHandler(fh);
        logger.log(Level.INFO, message);
        logger.removeHandler(fh);

    }
}
