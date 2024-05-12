package inno.tretyakov.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;


@Data
@Entity
@Table(name = "tpp_product")
@AllArgsConstructor
@NoArgsConstructor
public class TppProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToMany
    @JoinColumn(name = "product_id")
    private List<Agreement> agreementSet;
    private Long product_code_id;
    private Long client_id;
    @Size(max = 50)
    private String type;
    @Size(max = 50)
    private String number;
    private Long priority;
    private Timestamp date_of_conclusion;
    private Timestamp start_date_time;
    private Timestamp end_date_time;
    private Long days;
    private Double penalty_rate;
    private Double nso;
    private Double threshold_amount;
    @Size(max = 50)
    private String requisite_type;
    @Size(max = 50)
    private String interest_rate_type;
    private Double tax_rate;
    @Size(max = 100)
    private String reasone_close;
    @Size(max = 50)
    private String state;
}
