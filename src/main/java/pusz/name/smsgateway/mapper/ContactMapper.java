package pusz.name.smsgateway.mapper;

import org.springframework.stereotype.Component;
import pusz.name.smsgateway.domain.Contact;
import pusz.name.smsgateway.domain.ContactDto;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ContactMapper {

    public Contact mapToContact(final ContactDto contactDto) {
        return new Contact(
                contactDto.getId(),
                contactDto.getFullName(),
                contactDto.getPhoneNumber());
    }

    public ContactDto mapToContactDto(final Contact contact) {
        return new ContactDto(
                contact.getId(),
                contact.getFullName(),
                contact.getPhoneNumber());
    }

    public List<ContactDto> mapToContactDtoList(final List<Contact> contactList) {
        return contactList.stream()
                .map(s -> mapToContactDto(s))
                .collect(Collectors.toList());
    }

    public List<Contact> mapToContactList(final List<ContactDto> contactDtoList) {
        return contactDtoList.stream()
                .map(s -> mapToContact(s))
                .collect(Collectors.toList());
    }
}
