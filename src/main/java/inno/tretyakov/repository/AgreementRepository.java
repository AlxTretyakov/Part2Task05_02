package inno.tretyakov.repository;

import inno.tretyakov.entity.Agreement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AgreementRepository extends JpaRepository<Agreement, Integer> {
    List<Agreement> findFirstByNumber(String number);
}