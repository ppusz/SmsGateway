package pusz.name.smsgateway.controller.exception;

import org.apache.log4j.Logger;

public class SmsNotAccepted extends Exception {

    private static final Logger logger = Logger.getLogger(SmsNotAccepted.class);

    public SmsNotAccepted(String s) {
        super(s);
        logger.warn("Throwing SmsNotAccepted exception: " + s);
    }
}
