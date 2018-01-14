package pusz.name.smsgateway.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pusz.name.smsgateway.domain.DistributionList;
import pusz.name.smsgateway.domain.DistributionListDto;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DistributionListMapper {

    @Autowired
    ContactMapper contactMapper;

    public DistributionList mapToDistributionList(DistributionListDto distributionListDto) {
        return new DistributionList(
                distributionListDto.getId(),
                distributionListDto.getDescription(),
                contactMapper.mapToContactList(distributionListDto.getContactDtoList()));
    }

    public DistributionListDto mapToDistributionListDto(final DistributionList distributionList) {
        return new DistributionListDto(
                distributionList.getId(),
                distributionList.getDescription(),
                contactMapper.mapToContactDtoList(distributionList.getContacts()));
    }

    public List<DistributionListDto> mapToDistributionListDtoList(final List<DistributionList> distributionListList) {
        return distributionListList.stream()
                .map(s -> mapToDistributionListDto(s))
                .collect(Collectors.toList());
    }
}
