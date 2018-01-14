package pusz.name.smsgateway.domain;

import java.util.ArrayList;
import java.util.List;

public class DistributionListDto {

    private final Long id;
    private final String description;
    private List<ContactDto> contactDtoList;

    public DistributionListDto(final Long id, final String description, final List<ContactDto> contactDtoList) {
        this.id = id;
        this.description = description;
        this.contactDtoList = contactDtoList;
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public List<ContactDto> getContactDtoList() {
        return contactDtoList;
    }
}
