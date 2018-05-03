package app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import app.entity.Agreement;

@Repository
public interface AgreementRepository extends JpaRepository<Agreement, Integer> {

}
