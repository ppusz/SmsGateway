package pusz.name.smsgateway.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pusz.name.smsgateway.controller.exception.SMSNotFoundException;
import pusz.name.smsgateway.domain.SmsRequestDto;
import pusz.name.smsgateway.mapper.SmsRequestMapper;
import pusz.name.smsgateway.service.DbService;
import pusz.name.smsgateway.validator.SmsNotAccepted;
import pusz.name.smsgateway.validator.SmsRequestDtoValidator;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/sms")
public class SmsController {

    private static final Logger logger = Logger.getLogger(SmsController.class);

    @Autowired
    private SmsRequestDtoValidator smsRequestDtoValidator;
    @Autowired
    private SmsRequestMapper requestMapper;
    @Autowired
    private DbService dbService;


    @PostMapping(value = "send", consumes = APPLICATION_JSON_VALUE)
    public void sendSms(@RequestBody SmsRequestDto smsRequestDto) throws SmsNotAccepted {
        logger.debug("Received SmsRequestDto " + smsRequestDto);
        smsRequestDtoValidator.validate(smsRequestDto);
        dbService.saveSmsRequest(requestMapper.mapToSmsRequest(smsRequestDto));
    }

    @GetMapping(value = "getSMS")
    public SmsRequestDto getSms(@RequestParam Long id) throws SMSNotFoundException {
        logger.debug("Returning sms with id: " + id);
        return requestMapper.mapToSmsRequestDto(dbService.getSmsRequest(id).orElseThrow(SMSNotFoundException::new));
    }

}
