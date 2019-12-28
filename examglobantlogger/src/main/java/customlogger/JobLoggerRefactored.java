package customlogger;

import customlogger.internalLogger.InternalLogger;
import customlogger.internalLogger.InternalLoggerFactory;

import java.io.IOException;
import java.text.DateFormat;
import java.util.*;
import java.util.logging.Logger;

public class JobLoggerRefactored {
    private static boolean logToFile;
    private static boolean logToConsole;
    private static boolean logToDatabase;
    private static boolean logMessage;
    private static boolean logWarning;
    private static boolean logError;
    private boolean initialized;
    private static Map dbParams;
    private static Logger logger;
    private static JobLoggerBuilder builder;
    private List<InternalLogger> internalLoggersChoosen;

    public static JobLoggerBuilder builder() {
        return JobLoggerBuilder.getInstance();
    }
    private JobLoggerRefactored(List<InternalLogger> loggers) {
        this.internalLoggersChoosen =  loggers;
        this.logger = Logger.getLogger("MyLog");
    }

    public void logError(String messageText) throws IOException {
        this.logMessage(messageText, MessageTypeEnum.ERROR);
    }

    public void logWarning(String messageText) throws IOException {
        this.logMessage(messageText, MessageTypeEnum.WARNING);
    }

    public void logInfo(String messageText) throws IOException {
        this.logMessage(messageText, MessageTypeEnum.INFO);
    }

    private void logMessage(String messageText, MessageTypeEnum type) throws IOException {
        if(!internalLoggersChoosen.isEmpty()) {
            for (InternalLogger item: internalLoggersChoosen) {
                item.logMessage(getFormatedMessage(messageText, type), logger);
            }
        } else {

        }
    }

    private String getFormatedMessage(String messageText, MessageTypeEnum type) {
        return type.getFormat() + " " + DateFormat.getDateInstance(DateFormat.LONG).format(new Date()) + " MESSAGE: " + messageText;
    }

    private enum MessageTypeEnum {
        INFO("message: "),
        WARNING("warrning: "),
        ERROR("error: ");

        private String format;

        private MessageTypeEnum(String format) {
            this.format = format;
        }
        public String getFormat() {
            return format;
        }
    }

    public static class JobLoggerBuilder {
        private static InternalLogger logForFile;
        private static InternalLogger logForConsole;
        private static InternalLogger logForDatabase;
        private JobLoggerBuilderFinal builderFinal;
        private List<InternalLogger> internalLoggers;

        public static JobLoggerBuilder getInstance() {
            return new JobLoggerRefactored.JobLoggerBuilder();
        }

        private JobLoggerBuilder() {
            internalLoggers = new ArrayList<InternalLogger>();
            this.builderFinal = new JobLoggerBuilderFinal(this);
        }

        public JobLoggerBuilderFinal onLog4File() {
            builderFinal.getInternalLoggers().add(getLog4File());
            return this.builderFinal;
        }

        public JobLoggerBuilderFinal onLog4Console() {
            builderFinal.getInternalLoggers().add(getLog4Console());
            return this.builderFinal;
        }

        public JobLoggerBuilderFinal onLog4Db() {
            builderFinal.getInternalLoggers().add(getLog4Db());
            return this.builderFinal;
        }

        private InternalLogger getLog4File() {
            if (logForFile == null) {
                logForFile =  InternalLoggerFactory.getInstance().create(InternalLoggerFactory.LoggerType.loggerFile);
            }
            return logForFile;
        }

        private InternalLogger getLog4Console() {
            if (logForConsole == null) {
                logForConsole =  InternalLoggerFactory.getInstance().create(InternalLoggerFactory.LoggerType.loggerConsole);
            }
            return logForConsole;
        }

        private InternalLogger getLog4Db() {
            if (logForDatabase == null) {
                logForDatabase =  InternalLoggerFactory.getInstance().create(InternalLoggerFactory.LoggerType.loggerBd);
            }
            return logForDatabase;
        }
    }

    public static class JobLoggerBuilderFinal{
        private JobLoggerBuilder builder;

        private JobLoggerBuilderFinal(JobLoggerBuilder builder) {
            this.builder = builder;
        }

        public JobLoggerRefactored build() {
            return new JobLoggerRefactored(builder.internalLoggers);
        }

        public JobLoggerBuilderFinal onLog4File() {
            builder.onLog4File();
            return this;
        }

        public JobLoggerBuilderFinal onLog4Console() {
            builder.onLog4Console();
            return this;
        }

        public JobLoggerBuilderFinal onLog4Db() {
            builder.onLog4Db();
            return this;
        }

        public List<InternalLogger> getInternalLoggers() {
            return builder.internalLoggers;
        }
    }
}


