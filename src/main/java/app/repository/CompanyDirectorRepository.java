package app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import app.entity.CompanyDirector;

public interface CompanyDirectorRepository extends JpaRepository<CompanyDirector, Integer> {
	
	static final String PARAM_AGREEMENT_ID = "agreementId";
	static final String PARAM_SPECIFICATION_ID = "specificationId";
	static final String FIND_ACTUAL_COMPANY_DIRECTOR_BY_AGREEMENT_ID = "select cd from CompanyDirector cd where cd.company.id = ("
			+ "select a.company.id from Agreement a where a.id = :agreementId) and cd.employmentDate = ("
			+ "select max(cd.employmentDate) from CompanyDirector cd where cd.company.id = ("
			+ "select a.company.id from Agreement a where a.id = :agreementId) and cd.employmentDate <= ("
			+ "select a.dateStart from Agreement a where a.id = :agreementId))";
	static final String FIND_ACTUAL_COMPANY_DIRECTOR_BY_SPECIFICATION_ID = "select cd from CompanyDirector cd where cd.company.id = ("
			+ "select a.company.id from Agreement a, Specification s where a.id = s.agreement.id and s.id = :specificationId) and cd.employmentDate = ("
			+ "select max(cd.employmentDate) from CompanyDirector cd where cd.company.id = ("
			+ "select a.company.id from Agreement a, Specification s where a.id = s.agreement.id and s.id = :specificationId) and cd.employmentDate <= ("
			+ "select s.dateStart from Specification s where s.id = :specificationId))";
	
	@Transactional
	@Query(FIND_ACTUAL_COMPANY_DIRECTOR_BY_AGREEMENT_ID)
	public CompanyDirector findActualDirectorByAgreementId(@Param(PARAM_AGREEMENT_ID) Integer agreementId);

	
	@Transactional
	@Query(FIND_ACTUAL_COMPANY_DIRECTOR_BY_SPECIFICATION_ID)
	public CompanyDirector findActualDirectorBySpecificationId(@Param(PARAM_SPECIFICATION_ID) Integer specificationId);

	
}