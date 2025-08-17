package utils;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;

public class LoggerUtil {

    private static final Logger log;

    static {
        Configurator.setRootLevel(Level.INFO);

        log = LogManager.getLogger("AutomationFramework");
    }

    public static Logger getLogger() {
        return log;
    }
}


