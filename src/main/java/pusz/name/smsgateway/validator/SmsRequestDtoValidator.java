package pusz.name.smsgateway.validator;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pusz.name.smsgateway.domain.SmsRequestDto;
import pusz.name.smsgateway.service.SmsSender;

@Component
public class SmsRequestDtoValidator {

    private static final Logger logger = Logger.getLogger(SmsRequestDtoValidator.class);

    @Autowired
    SmsSender sender;


    public void validate(SmsRequestDto smsRequestDto) throws SmsNotAccepted {
        logger.debug("Validating SmsRequestDto");
        if (!validatePhoneNumber(smsRequestDto.getPhoneNumber())) {
            throw new SmsNotAccepted("Invalid phone number");
        }
        if (!validateMessage(smsRequestDto.getPhoneNumber())) {
            throw new SmsNotAccepted("Invalid message");
        }
    }

    private boolean validatePhoneNumber(String phoneNumber) {
        return sender.checkNumber(phoneNumber);
    }

    private boolean validateMessage(String message) {
        return true;
    }


}
