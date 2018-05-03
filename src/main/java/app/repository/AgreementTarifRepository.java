package app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import app.entity.AgreementTarif;

public interface AgreementTarifRepository extends JpaRepository<AgreementTarif, Integer>{
	
	static final String PARAM_SPECIFICATION_ID = "specificationId";
	
	public AgreementTarif findAgreementTarifBySpecificationId(@Param(PARAM_SPECIFICATION_ID) Integer specificationId);
	

}