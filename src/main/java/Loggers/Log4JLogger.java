package Loggers;

import org.apache.log4j.Logger;


public class Log4JLogger {

    //Initialize Log4j instance
    public static Logger logger = Logger.getLogger(Log4JLogger.class.getName());

        //We can use it when starting tests
        public static void startLog (String testClassName){
            logger.info(testClassName);
        }

        //We can use it when ending tests
        public static void endLog (String testClassName){
            logger.info(testClassName);
        }

        //Info Level Logs
        public static void info (String message){
            logger.info(message);
        }

        //Warn Level Logs
        public static void warn (String message){
            logger.warn(message);
        }

        //Error Level Logs
        public static void error (String message){
            logger.error(message);
        }

        //Fatal Level Logs
        public static void fatal (String message){
            logger.fatal(message);
        }

        //Debug Level Logs
        public static void debug (String message){
            logger.debug(message);
        }
}
