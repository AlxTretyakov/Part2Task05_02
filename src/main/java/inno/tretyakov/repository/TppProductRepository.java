package inno.tretyakov.repository;

import inno.tretyakov.entity.TppProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TppProductRepository extends JpaRepository<TppProduct, Integer> {
    List<TppProduct> findFirstByNumber(String number);
}