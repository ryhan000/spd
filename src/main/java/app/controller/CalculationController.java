package app.controller;

import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import app.entity.Calculation;
import app.entity.Specification;
import app.repository.CalculationRepository;
import app.repository.SpecificationRepository;
import utils.BeanUtil;

@Controller
@Transactional
public class CalculationController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(CalculationController.class);

	@Autowired(required = true)
	private CalculationRepository calculationRepository;

	@Autowired(required = true)
	private SpecificationRepository specificationRepository;
	
	@RequestMapping(value = REQUEST_MAPPING_CALCULATION, method = RequestMethod.GET)
	public String getEditCalculation(@RequestParam int id, Model model) {
		logger.info("<== Enter to 'getEditCalculation()' method ... ==>");
		Calculation calculation = calculationRepository.findOne(id);
		model.addAttribute("calculation", calculation);
		Double actualEsvRate = calculationRepository.findActualEsvRateByCalculationId(id);
		logger.info("<== Value of 'actualEsvRate' is " + actualEsvRate + " ==>");
		model.addAttribute("esvRate", actualEsvRate);
		Double actualSimpleTaxRate = calculationRepository.findActualSimpleTaxRateByCalculationId(id);
		logger.info("<== Value of 'actualSimpleTaxRate' is " + actualSimpleTaxRate + " ==>");
		model.addAttribute("simpleTaxRate", actualSimpleTaxRate);
		logger.info("<== Trying to get actualRates ... ==>");
		Double actualBankComissionRate = calculationRepository.findActualRateByAliasAndCalculationId(id,
				"withdrawCashComission");
		logger.info("<== Value of 'actualBankComissionRate' is " + actualBankComissionRate + " ==>");
		model.addAttribute("bankComissionRate", actualBankComissionRate);
		logger.info("<== Out of 'getEditCalculation()' method ... ==>");
		return "calculation/edit";
	}

	@Transactional
	@RequestMapping(value = REQUEST_MAPPING_CALCULATION, params = PARAM_ADD, method = RequestMethod.POST)
	public String postAddCalculation(@RequestParam int specificationId, @RequestParam Integer partNumber,
			@RequestParam Date dateStart) {
		logger.info("<== Enter to 'postAddCalculation()' method ... ==>");
		Specification specification = specificationRepository.findOne(specificationId);
		logger.info("<== Saving new 'Calculation' for 'Specification='" + specification.getId() + "' ==>");
		Double openingBalance = BeanUtil.convertNullToDouble(calculationRepository.findClosingBalanceOfLastCalculationByAgreementId(specification.getAgreement().getId()));
		logger.info("<== The value of 'openingBalance' is " + openingBalance + "' ==>");
		Calculation calculation = new Calculation(specification, partNumber, dateStart, openingBalance);
		calculation = calculationRepository.save(calculation);
		Integer calculationId = calculation.getId();
		calculation.setSalaryRate(calculationRepository.findActualRateByAliasAndCalculationId(calculationId, "salaryRate"));
		logger.info("<== Salary Rate = " + calculation.getSalaryRate() + " ==>");
		calculation.setPremium(calculationRepository.findActualRateByAliasAndCalculationId(calculationId, "premium"));
		logger.info("<== Premium Rate = " + calculation.getPremium() + " ==>");
		calculation.setEsv(calculationRepository.findActualEsvRateByCalculationId(calculationId));
		logger.info("<== ESV Rate = " + calculation.getEsv() + " ==>");
		calculation.setRent(calculationRepository.findActualRateByAliasAndCalculationId(calculationId, "rent"));
		logger.info("<== Rent = " + calculation.getRent() + " ==>");
		calculation.setCardServiceFee(
				calculationRepository.findActualRateByAliasAndCalculationId(calculationId, "cardServiceFee"));
		logger.info("<== Card Service Fee = " + calculation.getCardServiceFee() + " ==>");
		calculation.setAccountServiceFee(
				calculationRepository.findActualRateByAliasAndCalculationId(calculationId, "accountServiceFee"));
		logger.info("<== Account Service Fee = " + calculation.getAccountServiceFee() + " ==>");
		calculation = calculationRepository.save(calculation);
		logger.info("<== Saving new 'Calculation' with ID=" + calculation.getId() + " for 'Specification="
				+ specification.getId() + "' was successful ==>");
		logger.info("<== Out of 'postAddCalculation()' method ... ==>");
		return "redirect:" + calculation.getUrl();
	}

	@RequestMapping(value = REQUEST_MAPPING_CALCULATION, params = PARAM_EDIT, method = RequestMethod.POST)
	public String postEditCalculation(@RequestParam Integer id, @RequestParam Integer partNumber,
			@RequestParam Date dateStart, @RequestParam String accountServiceFee, @RequestParam String cardServiceFee,
			@RequestParam String closingBalance, @RequestParam String esv, @RequestParam String moneyOnHand,
			@RequestParam String moneyTransfer, @RequestParam String openingBalance, @RequestParam String premium,
			@RequestParam String rent, @RequestParam String salaryRate, @RequestParam String surcharge,
			@RequestParam String turnover, @RequestParam String withdrawCash,
			@RequestParam String withdrawCashComission, @RequestParam String simpleTax) {
		logger.info("<== Enter to 'postEditCalculation()' method ... ==>");
		Calculation calculation = calculationRepository.findOne(id);
		Specification specification = calculation.getSpecification();
		logger.info("<== Starting update 'Calculation' by ID=" + calculation.getId() + " ==>");
		calculation.setSpecification(specification);
		calculation.setPartNumber(partNumber);
		calculation.setDateStart(dateStart);
		logger.info("<== 'accountServiceFee=" + accountServiceFee + "' ==>");
		calculation.setAccountServiceFee(BeanUtil.convertStringToDouble(accountServiceFee));
		logger.info("<== 'accountServiceFee=" + calculation.getAccountServiceFee() + "' ==>");
		calculation.setCardServiceFee(BeanUtil.convertStringToDouble(cardServiceFee));
		calculation.setClosingBalance(BeanUtil.convertStringToDouble(closingBalance));
		calculation.setEsv(BeanUtil.convertStringToDouble(esv));
		calculation.setMoneyOnHand(BeanUtil.convertStringToDouble(moneyOnHand));
		calculation.setMoneyTransfer(BeanUtil.convertStringToDouble(moneyTransfer));
		calculation.setOpeningBalance(BeanUtil.convertStringToDouble(openingBalance));
		calculation.setPremium(BeanUtil.convertStringToDouble(premium));
		calculation.setRent(BeanUtil.convertStringToDouble(rent));
		logger.info("<== 'SalaryRate=" + salaryRate + "' ==>");
		calculation.setSalaryRate(BeanUtil.convertStringToDouble(salaryRate));
		logger.info("<== 'SalaryRate=" + calculation.getSalaryRate() + "' ==>");
		calculation.setSurcharge(BeanUtil.convertStringToDouble(surcharge));
		calculation.setSimpleTax(BeanUtil.convertStringToDouble(simpleTax));
		calculation.setTurnover(BeanUtil.convertStringToDouble(turnover));
		calculation.setWithdrawCash(BeanUtil.convertStringToDouble(withdrawCash));
		calculation.setWithdrawCashComission(BeanUtil.convertStringToDouble(withdrawCashComission));
		calculationRepository.save(calculation);
		logger.info("<== Updating of 'postEditCalculation' with ID=" + calculation.getId() + " was successful ==>");
		logger.info("<== Out of 'postEditCalculation()' method ... ==>");
		return "redirect:" + specification.getUrl();
	}

	@RequestMapping(value = REQUEST_MAPPING_CALCULATION, params = PARAM_DELETE, method = RequestMethod.POST)
	public String postDeleteCalculation(@RequestParam int id) {
		logger.info("<== Enter to 'postDeleteCalculation()' method ... ==>");
		Calculation calculation = calculationRepository.findOne(id);
		Specification specification = calculation.getSpecification();
		logger.info("<== Starting delete 'Calculation' with ID=" + calculation.getId() + " ==>");
		calculationRepository.delete(calculation);
		logger.info("<== Deleting of 'Calculation' with ID=" + calculation.getId() + " was successful ==>");
		logger.info("<== Out of 'postDeleteCalculation()' method ... ==>");
		return "redirect:" + specification.getUrl();
	}

}
