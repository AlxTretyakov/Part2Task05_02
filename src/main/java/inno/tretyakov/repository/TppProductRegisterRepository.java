package inno.tretyakov.repository;

import inno.tretyakov.entity.TppProductRegister;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TppProductRegisterRepository extends JpaRepository<TppProductRegister, Integer> {
    @Query(value = "select * from tpp_product_register where product_id = :productid and type= :type", nativeQuery = true)
    List<TppProductRegister> findReg(@Param("productid") Long ProductId, @Param("type") String type);
}