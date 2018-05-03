package app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import app.entity.AgreementFile;

@Repository
public interface AgreementFileRepository extends JpaRepository<AgreementFile, Integer> {

}
