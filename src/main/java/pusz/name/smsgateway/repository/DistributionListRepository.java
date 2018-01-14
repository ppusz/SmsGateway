package pusz.name.smsgateway.repository;

import org.springframework.data.repository.CrudRepository;
import pusz.name.smsgateway.domain.DistributionList;

import java.util.List;
import java.util.Optional;

public interface DistributionListRepository extends CrudRepository<DistributionList, Long> {

    Optional<DistributionList> findById(Long id);

    @Override
    List<DistributionList> findAll();

    @Override
    DistributionList save(DistributionList distributionList);

    void deleteById(Long id);
}
