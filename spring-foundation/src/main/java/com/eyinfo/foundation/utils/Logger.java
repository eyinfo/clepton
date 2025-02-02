package com.eyinfo.foundation.utils;

public class Logger {
    private static final java.util.logging.Logger LOGGER = java.util.logging.Logger.getLogger(Logger.class.getName());

    private static Logger logger;
    private static boolean _debug = false;

    public static Logger getInstance() {
        if (logger == null) {
            synchronized (Logger.class) {
                if (logger == null) {
                    logger = new Logger();
                }
            }
        }
        return logger;
    }

    public Logger() {
        _debug = SystemUtils.isDebug();
    }

    public void info(String msg) {
        LOGGER.info(msg);
        if (_debug) {
            System.out.println(String.format("eyinfo-info：%s", msg));
        }
    }

    public void warning(String msg) {
        LOGGER.warning(msg);
        if (_debug) {
            System.out.println(String.format("eyinfo-warning：%s", msg));
        }
    }

    public void error(String msg) {
        LOGGER.severe(msg);
        if (_debug) {
            System.out.println(String.format("eyinfo-error：%s", msg));
        }
    }

    public void fine(String msg) {
        LOGGER.fine(msg);
        if (_debug) {
            System.out.println(String.format("eyinfo-fine：%s", msg));
        }
    }
}
