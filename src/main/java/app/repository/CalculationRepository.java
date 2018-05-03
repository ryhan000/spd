package app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import app.entity.Calculation;

@Repository
public interface CalculationRepository extends JpaRepository<Calculation, Integer> {
	
	static final String PARAM_AGREEMENT_ID = "agreementId";
	static final String PARAM_SPECIFICATION_ID = "specificationId";
	static final String PARAM_CALCULATION_ID = "calculationId";
	static final String PARAM_ALIAS = "alias";

	public Integer findMaxCalculationNumberBySpecificationId(@Param(PARAM_SPECIFICATION_ID) Integer specificationId);
	
	public Double findSumOfCalculationsBySpecificationId(@Param(PARAM_SPECIFICATION_ID) Integer specificationId);
	
	public Double findClosingBalanceOfLastCalculationByAgreementId(@Param(PARAM_AGREEMENT_ID) Integer agreementId);
	
	public Double findActualEsvRateByCalculationId(@Param(PARAM_CALCULATION_ID) Integer calculationId);

	public Double findActualSimpleTaxRateByCalculationId(@Param(PARAM_CALCULATION_ID) Integer calculationId);

	public Double findActualBankComissionRateByCalculationId(@Param(PARAM_CALCULATION_ID) Integer calculationId);
	
	public Double findActualRateByAliasAndCalculationId(@Param(PARAM_CALCULATION_ID) int calculationId, @Param(PARAM_ALIAS) String alias);
	
}
