package app.controller;

import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import app.entity.Agreement;
import app.entity.AgreementTarif;
import app.entity.Company;
import app.entity.SPD;
import app.repository.AgreementRepository;
import app.repository.AgreementTarifRepository;
import app.repository.CompanyRepository;
import app.repository.SPDRepository;
import app.repository.SpecificationRepository;

@Controller
@Transactional
public class AgreementController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(AgreementController.class);

	@Autowired(required = true)
	private AgreementRepository agreementRepository;
	
	@Autowired(required = true)
	private SpecificationRepository specificationRepository;

	@Autowired(required = true)
	private SPDRepository spdRepository;
	
	@Autowired(required = true)
	private CompanyRepository companyRepository;

	@Autowired(required = true)
	private AgreementTarifRepository tarifRepository;

	@RequestMapping(value = REQUEST_MAPPING_AGREEMENTS, method = RequestMethod.GET)
	public String getAddAgreement(Model model) {
		model.addAttribute("agreements", agreementRepository.findAll());
		return PAGE_AGREEMENTS;
	}
	
	@RequestMapping(value = REQUEST_MAPPING_AGREEMENTS, params = PARAM_DELETE, method = RequestMethod.POST)
	public String postDeleteAgreements(@RequestParam int id) {
		logger.info("<==((((((((( Enter to 'postDeleteAgreements()' method ... )))))))))==>");
		Agreement agreement = agreementRepository.findOne(id);
		logger.info("<==((((((((( Start delete 'Agreement' by ID=" + agreement.getId() + " )))))))))==>");
		agreementRepository.delete(agreement);
		logger.info("<==((((((((( 'Agreement' with ID=" + agreement.getId() + " was deleted from DB )))))))))==>");
		logger.info("<==((((((((( Out of 'postDeleteAgreements()' method ... )))))))))==>");
		return PAGE_REDIRECT_TO_AGREEMENTS;
	}

	@RequestMapping(value = REQUEST_MAPPING_AGREEMENT, params = PARAM_ADD, method = RequestMethod.GET)
	public String getAddAgreement(@RequestParam int spdId, Model model) {
		model.addAttribute("spd", spdRepository.findOne(spdId));
		return PAGE_AGREEMENT_ADD;
	}

	@RequestMapping(value = REQUEST_MAPPING_AGREEMENT, method = RequestMethod.GET)
	public String getAgreement(@RequestParam int id, Model model) {
		logger.info("<== Enter to 'getAgreement()' method ... ==>");
		Agreement agreement = agreementRepository.findOne(id);
		int specificationNumber = specificationRepository.findMaxSpecificationNumberByAgreementId(id);
		List<Company> companies = companyRepository.findAll();
		model.addAttribute("agreement", agreement);
		model.addAttribute("specificationNumber", specificationNumber + 1);
		model.addAttribute("companies", companies);
		logger.info("<== Out of 'getAgreement()' method ... ==>");
		return PAGE_AGREEMENT_EDIT;
	}

	@RequestMapping(value = REQUEST_MAPPING_AGREEMENT, params = PARAM_ADD, method = RequestMethod.POST)
	public String postAddAgreement(@RequestParam int spdId, @RequestParam String number, @RequestParam Date dateStart) {
		logger.info("<== Enter to 'postAddAgreement()' method ... ==>");
		SPD spd = spdRepository.findOne(spdId);
		logger.info("<== Saving new 'Agreement' for 'SPD='" + spd.getAlias() + "' ==>");
		Agreement agreement = new Agreement(spd, number, dateStart);
		agreement = agreementRepository.save(agreement);
		logger.info("<== Adding default zero tarif for new agreement with ID=" + agreement.getId() + " ==>");
		postAddAgreementTarif(agreement.getId(), 0d, 0d, 0d, dateStart);
		logger.info("<== Saving new 'Agreement' with ID=" + agreement.getId() + " for 'SPD=" + spd.getAlias() + "' was successful ==>");
		logger.info("<== Out of 'postAddAgreement()' method ... ==>");
		return PAGE_REDIRECT + agreement.getUrl();
	}

	@RequestMapping(value = REQUEST_MAPPING_AGREEMENT, params = PARAM_EDIT, method = RequestMethod.POST)
	public String postEditAgreement(@RequestParam int id, @RequestParam String number, @RequestParam Date dateStart, 
			@RequestParam("company_id") int companyId) {
		logger.info("<== Enter to 'postEditAgreement()' method ... ==>");
		Agreement agreement = agreementRepository.findOne(id);
		logger.info("<== Starting update 'Agreement' by ID=" + agreement.getId() + " ==>");
		SPD spd = spdRepository.findOne(agreement.getSpd().getId());
		Company company = companyRepository.findOne(companyId);
		agreement.setSpd(spd);
		agreement.setNumber(number);
		agreement.setDateStart(dateStart);
		agreement.setCompany(company);
		agreement = agreementRepository.save(agreement);
		logger.info("<== Updating of 'Agreement' with ID=" + agreement.getId() + " was successful ==>");
		logger.info("<== Out of 'postEditAgreement()' method ... ==>");
		return PAGE_REDIRECT + spd.getUrl();
	}

	@RequestMapping(value = REQUEST_MAPPING_AGREEMENT, params = PARAM_DELETE, method = RequestMethod.POST)
	public String postDeleteAgreement(@RequestParam int id) {
		logger.info("<== Enter to 'postDeleteAgreement()' method ... ==>");
		Agreement agreement = agreementRepository.findOne(id);
		logger.info("***Start delete 'Agreement' by ID=" + agreement.getId() + " ==>");
		SPD spd = spdRepository.findOne(agreement.getSpd().getId());	
		agreementRepository.delete(agreement);
		logger.info("<== 'Agreement' with ID=" + agreement.getId() + " was deleted from DB ==>");
		logger.info("<== Out of 'postDeleteAgreement()' method ... ==>");
		return PAGE_REDIRECT + spd.getUrl();
	}
	
	@RequestMapping(value = REQUEST_MAPPING_AGREEMENT_TARIF, params = PARAM_ADD, method = RequestMethod.POST)
	public String postAddAgreementTarif(@RequestParam Integer agreementId, @RequestParam Double configuring, @RequestParam Double programming, 
			@RequestParam Double architecting, @RequestParam Date dateStart) {
		logger.info("<== Enter to 'postAgreementTarifEdit()' method ... ==>");
		Agreement agreement = agreementRepository.findOne(agreementId);
		logger.info("<== Adding new 'AgreementTarif' for 'Agreement='" + agreement.getNumber() + "' ==>");
		AgreementTarif tarif = new AgreementTarif(agreement, configuring, programming, architecting, dateStart);
		tarif = tarifRepository.save(tarif);
		logger.info("<== Saving new 'AgreementTarif' with ID=" + tarif.getId() + " for 'Agreement='" + agreement.getNumber() +  " was successeful ==>");
		logger.info("<== Out of 'postAddAgreementTarif()' method ... ==>");
		return PAGE_REDIRECT + agreement.getUrl();
	}

	@RequestMapping(value = REQUEST_MAPPING_AGREEMENT_TARIF, params = PARAM_EDIT, method = RequestMethod.POST)
	public String postEditAgreementTarif(@RequestParam Integer id, @RequestParam Double configuring,
			@RequestParam Double programming, @RequestParam Double architecting, @RequestParam Date dateStart) {
		logger.info("<== Enter to 'postEditAgreementTarif()' method ... ==>");
		AgreementTarif tarif = tarifRepository.findOne(id);
		logger.info("<== Starting update 'AgreementTarif' by ID=" + tarif.getId() + " ==>");
		Agreement agreement = agreementRepository.findOne(tarif.getAgreement().getId());
		tarif.setAgreement(agreement);
		tarif.setConfiguring(configuring);
		tarif.setProgramming(programming);
		tarif.setArchitecting(architecting);
		tarif.setDateStart(dateStart);
		tarifRepository.save(tarif);
		logger.info("<== Updating of 'AgreementTarif' with ID=" + tarif.getId() + " was successful ==>");
		logger.info("<== Out of 'postEditAgreementTarif()' method ... ==>");
		return PAGE_REDIRECT + agreement.getUrl();
	}
	
	@RequestMapping(value = REQUEST_MAPPING_AGREEMENT_TARIF, params = PARAM_DELETE, method = RequestMethod.POST)
	public String postDeleteAgreementTarif(@RequestParam int id) {
		logger.info("<== Enter to 'postDeleteAgreementTarif()' method ... ==>");
		AgreementTarif tarif = tarifRepository.findOne(id);
		Agreement agreement = agreementRepository.findOne(tarif.getAgreement().getId());
		logger.info("<== Starting delete 'AgreementTarif' with ID=" + tarif.getId() + " ==>");
		tarifRepository.delete(tarif);
		logger.info("<== Deleting of 'AgreementTarif' with ID=" + tarif.getId() + " was successful ==>");
		logger.info("<== Out of 'postDeleteAgreementTarif()' method ... ==>");
		return PAGE_REDIRECT + agreement.getUrl(); 
	}

}
