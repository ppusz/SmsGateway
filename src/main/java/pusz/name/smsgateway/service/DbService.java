package pusz.name.smsgateway.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pusz.name.smsgateway.domain.Contact;
import pusz.name.smsgateway.domain.DistributionList;
import pusz.name.smsgateway.domain.SmsRequest;
import pusz.name.smsgateway.repository.ContactRepository;
import pusz.name.smsgateway.repository.DistributionListRepository;
import pusz.name.smsgateway.repository.SmsRequestRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DbService {

    private static final Logger logger = Logger.getLogger(MessagesSender.class);

    @Autowired
    private SmsRequestRepository smsRequestRepository;
    @Autowired
    private DistributionListRepository distributionListRepository;
    @Autowired
    private ContactRepository contactRepository;

    public SmsRequest saveSmsRequest(SmsRequest smsRequest) {
        logger.debug("Saving SmsRequest " + smsRequest.getId());
        return smsRequestRepository.save(smsRequest);
    }

    public Optional<SmsRequest> getSmsRequest(Long id) {
        return smsRequestRepository.findById(id);
    }

    public List<SmsRequest> getSmsRequestsNotSent() {
        logger.debug("Checking for not sent sms");
        return smsRequestRepository.findBySenderIdIsNull();
    }

    public List<SmsRequest> getSmsRequestsNotFinal() {
        logger.debug("Checking not final statuses");
        return smsRequestRepository.findBySenderIdIsNotNullAndStatusFinalIsFalse();
    }

    public List<DistributionList> getAllDistributionLists() {
        return distributionListRepository.findAll();
    }

    public Optional<DistributionList> getDistributionList(final Long id) {
        return distributionListRepository.findById(id);
    }

    public DistributionList saveDistributionList(final DistributionList distributionList) {
        return distributionListRepository.save(distributionList);
    }

    public void deleteDistributionList(final Long id) {
        distributionListRepository.deleteById(id);
    }

    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }

    public Optional<Contact> getContact(final Long id) {
        return contactRepository.findById(id);
    }

    public Contact saveContact(final Contact contact) {
        return contactRepository.save(contact);
    }

    public void deleteContact(final Long id) {
        contactRepository.deleteById(id);
    }
}
