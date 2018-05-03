package app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import app.entity.CompanyAddress;

public interface CompanyAddressRepository extends JpaRepository<CompanyAddress, Integer> {
	
	static final String PARAM_AGREEMENT_ID = "agreementId";
	static final String PARAM_SPECIFICATION_ID = "specificationId";
	static final String FIND_ACTUAL_COMPANY_ADDRESS_BY_AGREEMENT_ID = "select ca from CompanyAddress ca where ca.company.id = ("
			+ "select a.company.id from Agreement a where a.id = :agreementId) and ca.dateStart = ("
			+ "select max(ca.dateStart) from CompanyAddress ca where ca.company.id = ("
			+ "select a.company.id from Agreement a where a.id = :agreementId) and ca.dateStart <= ("
			+ "select a.dateStart from Agreement a where a.id = :agreementId))";
	static final String FIND_ACTUAL_COMPANY_ADDRESS_BY_SPECIFICATION_ID = "select ca from CompanyAddress ca where ca.company.id = ("
			+ "select a.company.id from Agreement a, Specification s where a.id = s.agreement.id and s.id = :specificationId) and ca.dateStart = ("
			+ "select max(ca.dateStart) from CompanyAddress ca where ca.company.id = ("
			+ "select a.company.id from Agreement a, Specification s where a.id = s.agreement.id and s.id = :specificationId) and ca.dateStart <= ("
			+ "select s.dateStart from Specification s where s.id = :specificationId))";
	
	@Transactional
	@Query(FIND_ACTUAL_COMPANY_ADDRESS_BY_AGREEMENT_ID)
	public CompanyAddress findActualCompanyAddressByAgreementId(@Param(PARAM_AGREEMENT_ID) Integer agreementId);
	
	@Transactional
	@Query(FIND_ACTUAL_COMPANY_ADDRESS_BY_SPECIFICATION_ID)
	public CompanyAddress findActualCompanyAddressBySpecificationId(@Param(PARAM_SPECIFICATION_ID) Integer specificationId);

}