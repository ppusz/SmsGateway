package pusz.name.smsgateway.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pl.smsapi.BasicAuthClient;
import pl.smsapi.api.SmsFactory;
import pl.smsapi.api.action.hlr.HLRCheckNumber;
import pl.smsapi.api.action.sms.SMSGet;
import pl.smsapi.api.action.sms.SMSSend;
import pl.smsapi.api.response.CheckNumberResponse;
import pl.smsapi.api.response.MessageResponse;
import pl.smsapi.api.response.NumberResponse;
import pl.smsapi.api.response.StatusResponse;
import pl.smsapi.exception.ClientException;
import pl.smsapi.exception.SmsapiException;
import pl.smsapi.proxy.ProxyNative;

@Service
public class SmsSenderPlSmsApi implements SmsSender {

    private static final Logger logger = Logger.getLogger(SmsSenderPlSmsApi.class);

    @Value("${plsmsapi.username}")
    private String username;
    @Value("${plsmsapi.passwordhash}")
    private String passwordHash;

    @Override
    public String send(String phoneNumber, String message) {
        try {
            BasicAuthClient client = new BasicAuthClient(username, passwordHash);

            SmsFactory smsApi = new SmsFactory(client);
            SMSSend action = smsApi.actionSend()
                    .setSender("Info")
                    .setText(message)
                    .setTo(phoneNumber);

            StatusResponse result = action.execute();


            for (MessageResponse status : result.getList()) {
                logger.info(status.getId() + " " + status.getNumber() + " " + status.getStatus());
                return status.getId();
            }

        } catch (ClientException e) {
            logger.error(e);
        } catch (SmsapiException e) {
            logger.error(e);
        }

        return null;
    }

    @Override
    public StatusDto getStatus(String id) {
        try {
            BasicAuthClient client = new BasicAuthClient(username, passwordHash);

            SmsFactory smsApi = new SmsFactory(client);
            SMSGet action = smsApi.actionGet()
                    .id(id);

            StatusResponse result = action.execute();

            for (MessageResponse status : result.getList()) {
                logger.info(status.getStatus());
                return new StatusDto(status.getStatus(), status.isFinal());
            }

        } catch (ClientException e) {
            logger.error(e);
        } catch (SmsapiException e) {
            logger.error(e);
        }

        return null;
    }

    @Override
    public boolean checkNumber(String phoneNumber) {
        try {
            BasicAuthClient client = new BasicAuthClient(username, passwordHash);

            HLRCheckNumber checker = new HLRCheckNumber();
            checker.client(client);
            checker.proxy(new ProxyNative("https://api.smsapi.pl/"));
            checker.setNumber(phoneNumber);
            CheckNumberResponse result =  checker.execute();

            for (NumberResponse response : result.getList()) {
                logger.info("Checkinng number " + phoneNumber + ". Status " + response.getStatus());
                if (response.getStatus().equals("OK")) {
                    return true;
                }
            }
        } catch (ClientException e) {
            logger.error(e);
        } catch (SmsapiException e) {
            logger.error(e);
        }

        return false;
    }

}