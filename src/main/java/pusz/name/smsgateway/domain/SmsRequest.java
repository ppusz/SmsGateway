package pusz.name.smsgateway.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import pusz.name.smsgateway.domain.converter.*;

@Entity(name = "sms_requests")
public class SmsRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @NotNull
    @Column(name = "phone")
    private String phoneNumber;
    @NotNull
    @Column(name = "message")
    private String message;
    @NotNull
    @Column(name = "created")
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime dateOfCreation;
    @Column(name = "status")
    private String status;
    @Column(name = "status_final")
    private boolean statusFinal;
    @Column(name = "sender_id")
    private String senderId;
    @Column(name = "sent_date")
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime sentDate;

    public SmsRequest() {
    }

    public SmsRequest(String phoneNumber, String message, LocalDateTime dateOfCreation) {
        this.phoneNumber = phoneNumber;
        this.message = message;
        this.dateOfCreation = dateOfCreation;
    }

    public SmsRequest(String phoneNumber, String message, LocalDateTime dateOfCreation, String status, boolean statusFinal, String senderId, LocalDateTime sentDate) {
        this.phoneNumber = phoneNumber;
        this.message = message;
        this.dateOfCreation = dateOfCreation;
        this.status = status;
        this.statusFinal = statusFinal;
        this.senderId = senderId;
        this.sentDate = sentDate;
    }

    public Long getId() {
        return id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getDateOfCreation() {
        return dateOfCreation;
    }

    public String getStatus() {
        return status;
    }

    public boolean isStatusFinal() {
        return statusFinal;
    }

    public LocalDateTime getSentDate() {
        return sentDate;
    }

    public void setSentDate(LocalDateTime sentDate) {
        this.sentDate = sentDate;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setStatusFinal(boolean statusFinal) {
        this.statusFinal = statusFinal;
    }
}
