package io.github.ellismatthew4.empireeconomy.utils;

import java.util.logging.Logger;

public class LoggerService {
    private static LoggerService instance = null;
    private final Logger logger;

    private LoggerService(Logger logger) {
        this.logger = logger;
    }

    public static LoggerService getInstance() {
        return LoggerService.getInstance(null);
    }

    public static LoggerService getInstance(Logger logger) {
        if (instance == null) {
            instance = new LoggerService(logger);
        }
        return instance;
    }

    public void info(String val) {
        this.logger.info(val);
    }

    public void warning(String val) {

    }
}
