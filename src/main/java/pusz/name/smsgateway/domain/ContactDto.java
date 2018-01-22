package pusz.name.smsgateway.domain;

public class ContactDto {

    private Long id;
    private String fullName;
    private String phoneNumber;

    public ContactDto() {
    }

    public ContactDto(final Long id, final String fullName, final String phoneNumber) {
        this.id = id;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
    }

    public Long getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
