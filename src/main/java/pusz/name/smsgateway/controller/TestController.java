package pusz.name.smsgateway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pusz.name.smsgateway.service.SmsSender;

@RestController
@RequestMapping("api/test")
public class TestController {

    @Autowired
    SmsSender sender;

    @GetMapping(value = "check")
    public void check(){
        sender.checkNumber("");
    }

}
