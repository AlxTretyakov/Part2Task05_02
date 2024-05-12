package inno.tretyakov.repository;

import inno.tretyakov.entity.AccountPool;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AccountPoolRepository extends JpaRepository<AccountPool, Integer> {
    @Query(value = "select * from account_pool where branch_code = :branchCode and currency_code= :currencyCode  and mdm_code = :mdmCode and priority_code= :priorityCode and registry_type_code = :registryTypeCode limit 1", nativeQuery = true)
    List<AccountPool> getPool(@Param("branchCode") String branchCode, @Param("currencyCode") String currencyCode, @Param("mdmCode") String mdmCode, @Param("priorityCode") String priorityCode, @Param("registryTypeCode") String registryTypeCode);
}