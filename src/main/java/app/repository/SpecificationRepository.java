package app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import app.entity.Specification;

public interface SpecificationRepository extends JpaRepository<Specification, Integer> {
	
	static final String PARAM_AGREEMENT_ID = "agreementId";
	static final String FIND_MAX_SPECIFICATION_NUMBER_BY_AGREEMENT_ID = "SELECT COALESCE(max(specification_number),0) FROM specification WHERE agreement_id = :agreementId";
	
	@Transactional
	@Query(value = FIND_MAX_SPECIFICATION_NUMBER_BY_AGREEMENT_ID, nativeQuery = true)
	public Integer findMaxSpecificationNumberByAgreementId(@Param(PARAM_AGREEMENT_ID) Integer agreementId);
}
