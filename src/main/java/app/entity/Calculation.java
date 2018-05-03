package app.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "specification_calculation")
@NamedQueries({
	@NamedQuery(
		name = Calculation.FIND_MAX_CALCULATION_NUMBER_BY_SPECIFICATION_ID, 
		query = "select coalesce(max(c.partNumber),0) from Calculation c where c.specification.id = :specificationId"),
	@NamedQuery(
		name = Calculation.FIND_SUM_OF_CALCULATIONS_BY_SPECIFICATION_ID, 
		query = "select sum(c.turnover) from Specification s, Calculation c where s.id = c.specification.id and s.id = :specificationId"),
	@NamedQuery(
		name = Calculation.FIND_ACTUAL_ESV_RATE_BY_CALCULATION_ID, 
		query = "select e.value from ESVTax e where e.dateStart = (select max(e.dateStart) from ESVTax e where e.dateStart <= (select c.dateStart from Calculation c where c.id = :calculationId))"),
	@NamedQuery(
		name = Calculation.FIND_ACTUAL_SIMPLE_TAX_RATE_BY_CALCULATION_ID, 
		query = "select s.value from SimpleTax s where s.dateStart = (select max(s.dateStart) from SimpleTax s where s.dateStart <= (select c.dateStart from Calculation c where c.id = :calculationId))"),
	@NamedQuery(
		name = Calculation.FIND_ACTUAL_BANK_COMISSION_RATE_BY_CALCULATION_ID,
		query = "select p.value from Payment p, PaymentType pt where p.paymentType.id = pt.id and pt.isBankComission = true and p.spd.id = (select a.spd.id from Agreement a, Specification s, Calculation c where a.id = s.agreement.id and s.id = c.specification.id and c.id = :calculationId) and p.dateStart = (select max(c.dateStart) from Calculation c where c.dateStart <= (select c.dateStart from Calculation c where c.id = :calculationId))")
})
@NamedNativeQueries({
	@NamedNativeQuery(
		name = Calculation.FIND_CLOSING_BALANCE_OF_LAST_CALCULATION_BY_AGREEMENT_ID,
		query = "select COALESCE(sc.closing_balance, 0) from agreement a join specification s on s.agreement_id = a.id join specification_calculation sc on sc.specification_id = s.id where a.id = :agreementId order by sc.id desc limit 1"),
	@NamedNativeQuery(
		name = Calculation.FIND_ACTUAL_RATE_BY_ALIAS_AND_CALCULATION_ID,
		query = "select coalesce(p.value,0) from payment p, payment_type pt where p.payment_type_id = pt.id and pt.alias = :alias and p.spd_id = (SELECT a.spd_id FROM agreement a, specification s, specification_calculation c WHERE a.id = s.agreement_id AND s.id = c.specification_id and c.id = :calculationId) and p.date_start = (select max(p.date_start) from payment p, payment_type pt where p.payment_type_id = pt.id and pt.alias = :alias and p.spd_id = (SELECT a.spd_id FROM agreement a, specification s, specification_calculation c WHERE a.id = s.agreement_id AND s.id = c.specification_id and c.id = :calculationId) and p.date_start <= (select max(c.date_start) from specification_calculation c where c.id = :calculationId))")
})
public class Calculation extends UrlEntity implements Serializable {

	public static final String FIND_MAX_CALCULATION_NUMBER_BY_SPECIFICATION_ID = "Calculation.findMaxCalculationNumberBySpecificationId";
	public static final String FIND_SUM_OF_CALCULATIONS_BY_SPECIFICATION_ID = "Calculation.findSumOfCalculationsBySpecificationId";
	public static final String FIND_ACTUAL_ESV_RATE_BY_CALCULATION_ID = "Calculation.findActualEsvRateByCalculationId";
	public static final String FIND_ACTUAL_SIMPLE_TAX_RATE_BY_CALCULATION_ID = "Calculation.findActualSimpleTaxRateByCalculationId";
	public static final String FIND_ACTUAL_BANK_COMISSION_RATE_BY_CALCULATION_ID = "Calculation.findActualBankComissionRateByCalculationId";
	public static final String FIND_CLOSING_BALANCE_OF_LAST_CALCULATION_BY_AGREEMENT_ID = "Calculation.findClosingBalanceOfLastCalculationByAgreementId";
	public static final String FIND_ACTUAL_RATE_BY_ALIAS_AND_CALCULATION_ID = "Calculation.findActualRateByAliasAndCalculationId";
	
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "specification_id")
	@JsonBackReference
	private Specification specification;

	@Temporal(TemporalType.DATE)
	@Column(name = "date_start")
	private Date dateStart;

	@Column(name = "part_number")
	private Integer partNumber;

	@Column(name = "opening_balance", nullable = false, columnDefinition = DECIMAL_10_2_DEFAULT_0_00)
	private Double openingBalance;

	@Column(name = "esv", columnDefinition = DECIMAL_10_2_DEFAULT_0_00)
	private Double esv;

	@Column(name = "salary_rate", columnDefinition = DECIMAL_10_2_DEFAULT_0_00)
	private Double salaryRate;

	@Column(name = "premium", columnDefinition = DECIMAL_10_2_DEFAULT_0_00)
	private Double premium;

	@Column(name = "surcharge", columnDefinition = DECIMAL_10_2_DEFAULT_0_00)
	private Double surcharge;

	@Column(name = "card_service_fee", columnDefinition = DECIMAL_10_2_DEFAULT_0_00)
	private Double cardServiceFee;

	@Column(name = "account_service_fee", columnDefinition = DECIMAL_10_2_DEFAULT_0_00)
	private Double accountServiceFee;

	@Column(name = "simple_tax", columnDefinition = DECIMAL_10_2_DEFAULT_0_00)
	private Double simpleTax;

	@Column(name = "withdraw_cash_comission", columnDefinition = DECIMAL_10_2_DEFAULT_0_00)
	private Double withdrawCashComission;

	@Column(name = "closing_balance", columnDefinition = DECIMAL_10_2_DEFAULT_0_00)
	private Double closingBalance;

	@Column(name = "rent", columnDefinition = DECIMAL_10_2_DEFAULT_0_00)
	private Double rent;

	@Column(name = "turnover", columnDefinition = DECIMAL_10_2_DEFAULT_0_00)
	private Double turnover;

	@Column(name = "money_on_hand", columnDefinition = DECIMAL_10_2_DEFAULT_0_00)
	private Double moneyOnHand;

	@Column(name = "money_transfer", columnDefinition = DECIMAL_10_2_DEFAULT_0_00)
	private Double moneyTransfer;

	@Column(name = "withdraw_cash", columnDefinition = DECIMAL_10_2_DEFAULT_0_00)
	private Double withdrawCash;

	public Calculation() {
	}

	public Calculation(Specification specification, Integer partNumber, Date dateStart, Double openingBalance) {
		this.specification = specification;
		this.dateStart = dateStart;
		this.partNumber = partNumber;
		this.openingBalance = openingBalance;
	}

	public Specification getSpecification() {
		return specification;
	}

	public void setSpecification(Specification specification) {
		this.specification = specification;
	}

	public Date getDateStart() {
		return dateStart;
	}

	public void setDateStart(Date dateStart) {
		this.dateStart = dateStart;
	}

	public Integer getPartNumber() {
		return partNumber;
	}

	public void setPartNumber(Integer partNumber) {
		this.partNumber = partNumber;
	}

	public Double getOpeningBalance() {
		return openingBalance;
	}

	public void setOpeningBalance(Double openingBalance) {
		this.openingBalance = openingBalance;
	}

	public Double getEsv() {
		return esv;
	}

	public void setEsv(Double esv) {
		this.esv = esv;
	}

	public Double getSalaryRate() {
		return salaryRate;
	}

	public void setSalaryRate(Double salaryRate) {
		this.salaryRate = salaryRate;
	}

	public Double getPremium() {
		return premium;
	}

	public void setPremium(Double premium) {
		this.premium = premium;
	}

	public Double getSurcharge() {
		return surcharge;
	}

	public void setSurcharge(Double surcharge) {
		this.surcharge = surcharge;
	}

	public Double getCardServiceFee() {
		return cardServiceFee;
	}

	public void setCardServiceFee(Double cardServiceFee) {
		this.cardServiceFee = cardServiceFee;
	}

	public Double getAccountServiceFee() {
		return accountServiceFee;
	}

	public void setAccountServiceFee(Double accountServiceFee) {
		this.accountServiceFee = accountServiceFee;
	}

	public Double getSimpleTax() {
		return simpleTax;
	}

	public void setSimpleTax(Double simpleTax) {
		this.simpleTax = simpleTax;
	}

	public Double getWithdrawCashComission() {
		return withdrawCashComission;
	}

	public void setWithdrawCashComission(Double withdrawCashComission) {
		this.withdrawCashComission = withdrawCashComission;
	}

	public Double getClosingBalance() {
		return closingBalance;
	}

	public void setClosingBalance(Double closingBalance) {
		this.closingBalance = closingBalance;
	}

	public Double getRent() {
		return rent;
	}

	public void setRent(Double rent) {
		this.rent = rent;
	}

	public Double getTurnover() {
		return turnover;
	}

	public void setTurnover(Double turnover) {
		this.turnover = turnover;
	}

	public Double getMoneyOnHand() {
		return moneyOnHand;
	}

	public void setMoneyOnHand(Double moneyOnHand) {
		this.moneyOnHand = moneyOnHand;
	}

	public Double getMoneyTransfer() {
		return moneyTransfer;
	}

	public void setMoneyTransfer(Double moneyTransfer) {
		this.moneyTransfer = moneyTransfer;
	}

	public Double getWithdrawCash() {
		return withdrawCash;
	}

	public void setWithdrawCash(Double withdrawCash) {
		this.withdrawCash = withdrawCash;
	}

}
