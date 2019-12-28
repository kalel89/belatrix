package customlogger.internalLogger;

public class InternalLoggerFactory {
    private static InternalLoggerFactory instance;
    public enum LoggerType {
        loggerBd,
        loggerConsole,
        loggerFile
    };

    public static InternalLoggerFactory getInstance() {
        if (instance == null) {
            instance = new InternalLoggerFactory();
        }
        return instance;
    }

    private InternalLoggerFactory() {

    }

    public InternalLogger create(LoggerType type) {
        switch (type) {
            case loggerFile:
                return new InternalLoggerFile();
            case loggerConsole:
                return new InternalLoggerConsole();
            case loggerBd:
                return new InternalLoggerBd();
            default:
                return null;
        }
    }
}
