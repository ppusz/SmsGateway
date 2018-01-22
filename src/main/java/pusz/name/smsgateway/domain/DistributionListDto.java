package pusz.name.smsgateway.domain;

import java.util.List;

public class DistributionListDto {

    private Long id;
    private String description;
    private List<ContactDto> contactDtoList;

    public DistributionListDto() {
    }

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
