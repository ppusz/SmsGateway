package pusz.name.smsgateway.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "distribution_lists")
public class DistributionList {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "description")
    private String description;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "join_contact_distribution_list",
            joinColumns = {@JoinColumn(name = "distribution_list_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "contact_id", referencedColumnName = "id")}
    )
    private List<Contact> contacts = new ArrayList<>();

    public DistributionList() {
    }

    public DistributionList(Long id, String description, List<Contact> contacts) {
        this.id = id;
        this.description = description;
        this.contacts = contacts;
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public List<Contact> getContacts() {
        return contacts;
    }
}
