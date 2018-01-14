package pusz.name.smsgateway.domain;


public class SmsRequestDto {

    private Long id;
    private String phoneNumber;
    private String message;
    private String status;
    private boolean statusFinal;

    public SmsRequestDto() {
    }

    public SmsRequestDto(String phoneNumber, String message) {
        this.phoneNumber = phoneNumber;
        this.message = message;
    }

    public SmsRequestDto(Long id, String phoneNumber, String message, String status, boolean statusFinal) {
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.message = message;
        this.status = status;
        this.statusFinal = statusFinal;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getMessage() {
        return message;
    }

    public Long getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public boolean isStatusFinal() {
        return statusFinal;
    }

    @Override
    public String toString() {
        return "SendSmsRequest{" +
                "phoneNumber='" + phoneNumber + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
