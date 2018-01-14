package pusz.name.smsgateway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pusz.name.smsgateway.controller.exception.ContactNotFoundException;
import pusz.name.smsgateway.domain.ContactDto;
import pusz.name.smsgateway.mapper.ContactMapper;
import pusz.name.smsgateway.service.DbService;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("api/contact")
public class ContactController {

    @Autowired
    DbService dbService;
    @Autowired
    ContactMapper mapper;

    @GetMapping(value = "getAll")
    public List<ContactDto> getContacts() {
        return mapper.mapToContactDtoList(dbService.getAllContacts());
    }

    @GetMapping(value = "getOne")
    public ContactDto getContact(@RequestParam Long id) throws ContactNotFoundException {
        return mapper.mapToContactDto(dbService.getContact(id).orElseThrow(ContactNotFoundException::new));
    }

    @DeleteMapping(value = "delete")
    public void deleteContact(@RequestParam Long id) {
        dbService.deleteContact(id);
    }

    @PutMapping(value = "update", consumes = APPLICATION_JSON_VALUE)
    public ContactDto updateContact(@RequestBody ContactDto contactDto) {
        return mapper.mapToContactDto(dbService.saveContact(mapper.mapToContact(contactDto)));
    }

    @PostMapping(value = "create", consumes = APPLICATION_JSON_VALUE)
    public ContactDto createContact(@RequestBody ContactDto contactDto) {
        return mapper.mapToContactDto(dbService.saveContact(mapper.mapToContact(contactDto)));
    }
}
