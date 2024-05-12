package inno.tretyakov.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.engine.internal.Cascade;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "tpp_ref_account_type")
@AllArgsConstructor
@NoArgsConstructor
public class TppRefAccountType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer internal_id;
    @Column(unique = true)
    @Size(max = 100)
    private String value;
}

