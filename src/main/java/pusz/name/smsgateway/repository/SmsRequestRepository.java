package pusz.name.smsgateway.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pusz.name.smsgateway.domain.SmsRequest;

import java.util.List;
import java.util.Optional;

@Repository
public interface SmsRequestRepository extends CrudRepository<SmsRequest, Long> {

    Optional<SmsRequest> findById(Long id);

    @Override
    SmsRequest save(SmsRequest smsRequest);

    List<SmsRequest> findBySenderIdIsNull();

    List<SmsRequest> findBySenderIdIsNotNullAndStatusFinalIsFalse();
}
