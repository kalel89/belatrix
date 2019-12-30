package customlogger.internalLogger;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import config.ReaderProperties;

public class InternalLoggerFile implements InternalLogger {
    private static Map dbParams;

    public InternalLoggerFile() {
        dbParams = new HashMap();
        dbParams.put("logFileFolder", ReaderProperties.getInstance().getDirectory());
    }

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
