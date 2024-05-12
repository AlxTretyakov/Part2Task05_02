package inno.tretyakov.repository;

import inno.tretyakov.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Integer> {
    @Query(value = "select * from account where account_pool_id = :poolid and bussy = false order by id limit 1", nativeQuery = true)
    List<Account> findAccount(@Param("poolid") Integer poolid);
}
