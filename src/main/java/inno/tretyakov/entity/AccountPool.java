package inno.tretyakov.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@Table(name = "account_pool")
@AllArgsConstructor
@NoArgsConstructor
public class AccountPool {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToMany
    @JoinColumn(name = "account_pool_id")
    private List<Account> accountSet;
    @Size(max = 50)
    private String branch_code;
    @Size(max = 30)
    private String currency_code;
    @Size(max = 50)
    private String mdm_code;
    @Size(max = 30)
    private String priority_code;
    @Size(max = 50)
    private String registry_type_code;

}
