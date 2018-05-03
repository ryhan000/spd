package app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import app.entity.CompanyAccount;

public interface CompanyAccountRepository extends JpaRepository<CompanyAccount, Integer> {
	
	static final String PARAM_AGREEMENT_ID = "agreementId";
	static final String PARAM_SPECIFICATION_ID = "specificationId";

	public CompanyAccount findActualCompanyAccountByAgreementId(@Param(PARAM_AGREEMENT_ID) Integer agreementId);

	public CompanyAccount findActualCompanyAccountBySpecificationId(@Param(PARAM_SPECIFICATION_ID) Integer specificationId);

}