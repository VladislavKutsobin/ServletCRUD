package app.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "account")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
@Getter
@EqualsAndHashCode

public class Account extends BaseEntity {
    @Column(name = "name")
    private String accountName;
    @Column(name = "acc_status")
    @Enumerated(EnumType.STRING)
    private AccountStatus accountStatus;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "developers_accounts",
        joinColumns = @JoinColumn(name = "account_id"),
        inverseJoinColumns = @JoinColumn(name = "developer_id"))
    private Developer developer;
}
