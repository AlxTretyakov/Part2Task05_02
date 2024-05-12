package inno.tretyakov.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.sql.Timestamp;
import java.util.Set;

@Data
@Entity
@Table(name = "tpp_ref_product_register_type")
@AllArgsConstructor
@NoArgsConstructor
public class TppRefProductRegisterType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer internal_id;
    @Column(unique = true)
    @Size(max = 100)
    private String value;
    @Size(max = 100)
    private String register_type_name;
    @Column(insertable = false, updatable = false)
    @Size(max = 100)
    private String product_class_code;
    @ManyToOne
    @JoinColumn(name = "product_class_code", referencedColumnName = "value")
    private TppRefProductClass tppRefProductClass;
    private Timestamp register_type_start_date;
    private Timestamp register_type_end_date;
    @Column(insertable = false, updatable = false)
    private String account_type;
    @ManyToOne
    @JoinColumn(name = "account_type", referencedColumnName = "value")
    private TppRefAccountType tppRefAccountType;

}

