package customlogger.internalLogger;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InternalLoggerConsole implements InternalLogger {

    public void logMessage(String message, Logger logger) {
        ConsoleHandler ch = new ConsoleHandler();
        logger.addHandler(ch);
        logger.log(Level.INFO, message);
        logger.removeHandler(ch);
    }


}
