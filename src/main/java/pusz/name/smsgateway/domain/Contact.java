package pusz.name.smsgateway.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "contacts")
public class Contact {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String fullName;

    @Column(name = "phone")
    private String phoneNumber;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "contacts")
    private List<DistributionList> distributionLists = new ArrayList<>();

    public Contact() {
    }

    public Contact(Long id, String fullName, String phoneNumber) {
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
