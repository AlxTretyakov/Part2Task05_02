package inno.tretyakov.repository;

import inno.tretyakov.entity.TppRefProductRegisterType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TppRefProductRegisterTypeRepository extends JpaRepository<TppRefProductRegisterType, String> {
    List<TppRefProductRegisterType> findFirstByValue(String value);
}