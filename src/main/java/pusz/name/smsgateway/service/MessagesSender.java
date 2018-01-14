package pusz.name.smsgateway.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import pusz.name.smsgateway.domain.SmsRequest;

@Service
public class MessagesSender {

    private static final Logger logger = Logger.getLogger(MessagesSender.class);

    private static final int SCHEDULED_FIXED_DELAY = 15000;

    @Autowired
    private SmsSender sender;
    @Autowired
    private DbService dbService;

    @Scheduled(fixedDelay = SCHEDULED_FIXED_DELAY)
    private void sendSmsMessages() {
        List<SmsRequest> smsRequestsNotSent = dbService.getSmsRequestsNotSent();
        for(SmsRequest smsRequest : smsRequestsNotSent) {
            String smsSenderId = sender.send(smsRequest.getPhoneNumber(), smsRequest.getMessage());
            if(smsSenderId != null) {
                smsRequest.setSenderId(smsSenderId);
                smsRequest.setSentDate(LocalDateTime.now());
                dbService.saveSmsRequest(smsRequest);
                logger.debug("Sms sent: " + smsSenderId);
            }
        }
    }

    @Scheduled(fixedDelay = SCHEDULED_FIXED_DELAY)
    private void checkStatuses() {
        List<SmsRequest> smsRequestsNotFinal = dbService.getSmsRequestsNotFinal();
        for (SmsRequest smsRequest : smsRequestsNotFinal) {
            StatusDto statusDto = sender.getStatus(smsRequest.getSenderId());
            smsRequest.setStatus(statusDto.getStatus());
            smsRequest.setStatusFinal(statusDto.isFinal());
            dbService.saveSmsRequest(smsRequest);
            logger.debug("Status update: " + statusDto);
        }
    }
}
