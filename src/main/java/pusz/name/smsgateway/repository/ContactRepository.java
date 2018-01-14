package pusz.name.smsgateway.repository;

import org.springframework.data.repository.CrudRepository;
import pusz.name.smsgateway.domain.Contact;

import java.util.List;
import java.util.Optional;

public interface ContactRepository extends CrudRepository<Contact, Long> {

    Optional<Contact> findById(Long id);

    @Override
    List<Contact> findAll();

    @Override
    Contact save(Contact contact);

    void deleteById(Long id);
}
