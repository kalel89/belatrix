package customlogger.internalLogger;

import java.io.IOException;
import java.util.logging.Logger;

public interface InternalLogger {
    void logMessage(String message, Logger logger) throws IOException, Exception;
}
