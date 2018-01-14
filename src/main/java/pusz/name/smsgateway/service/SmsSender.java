package pusz.name.smsgateway.service;

public interface SmsSender {

    String send(String phoneNumber, String message);

    StatusDto getStatus(String id);

    boolean checkNumber(String phoneNumber);
}
