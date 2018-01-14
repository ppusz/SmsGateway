package pusz.name.smsgateway.domain;

public class ContactDto {

    private final Long id;
    private final String fullName;
    private final String phoneNumber;

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
