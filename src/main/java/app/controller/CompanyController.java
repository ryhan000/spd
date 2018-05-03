package app.controller;

import java.sql.Date;

import javax.annotation.security.RolesAllowed;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import app.entity.Company;
import app.entity.CompanyAccount;
import app.entity.CompanyAddress;
import app.entity.CompanyDirector;
import app.repository.CompanyAccountRepository;
import app.repository.CompanyAddressRepository;
import app.repository.CompanyDirectorRepository;
import app.repository.CompanyRepository;

@Controller
@Transactional
public class CompanyController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(CompanyController.class);

	@Autowired(required = true)
	private CompanyRepository companyRepository;

	@Autowired(required = true)
	private CompanyAddressRepository addressRepository;
	
	@Autowired(required = true)
	private CompanyDirectorRepository directorRepository;
	
	@Autowired(required = true)
	private CompanyAccountRepository accountRepository;

	@RequestMapping(value = REQUEST_MAPPING_COMPANIES, method = RequestMethod.GET)
	public String getAllCompanies(Model model) {
		logger.info("<== Entering to the getAllCompanies() method ... ==>");
		model.addAttribute("companies", companyRepository.findAll());
		logger.info("<== Out of 'getAllCompanies()' method ... ==>");
		return PAGE_COMPANIES;
	}

	@RequestMapping(value = REQUEST_MAPPING_COMPANY, method = RequestMethod.GET)
	public String getEditCompany(@RequestParam int id, Model model) {
		logger.info("<== Enter to 'getEditCompany()' method ... ==>");
		Company company = companyRepository.findOne(id);
		model.addAttribute("company", company);
		logger.info("<== Out of 'getEditCompany()' method ... ==>");
		return PAGE_COMPANY_EDIT;
	}

	@RequestMapping(value = REQUEST_MAPPING_COMPANY, params = PARAM_ADD, method = RequestMethod.POST)
	public String postAddCompany(@RequestParam String title, @RequestParam String edrpou, @RequestParam String inn,
			@RequestParam String vatCertificate) {
		logger.info("<== Enter to 'postAddCompany()' method ... ==>");
		logger.info("<== Saving new 'Company' ... ==>");
		Company company = new Company(title, edrpou, inn, vatCertificate);
		company = companyRepository.save(company);
		logger.info("<== Saving new 'Company' with ID=" + company.getId() + " was successful ==>");
		logger.info("<== Out of 'postAddCompany()' method ... ==>");
		return PAGE_REDIRECT + company.getUrl();
	}

	@RolesAllowed(ROLE_ADMIN)
	@RequestMapping(value = REQUEST_MAPPING_COMPANY, params = PARAM_EDIT, method = RequestMethod.POST)
	public String postEditCompany(@RequestParam Integer id, @RequestParam String title, @RequestParam String edrpou,
			@RequestParam String inn, @RequestParam String vatCertificate) {
		logger.info("<== Enter to 'postEditCompany()' method ... ==>");
		Company company = companyRepository.findOne(id);
		logger.info("<== Starting update 'Company' by ID=" + company.getId() + " ==>");
		company.setTitle(title);
		company.setEdrpou(edrpou);
		company.setInn(inn);
		company.setVatCertificate(vatCertificate);
		company = companyRepository.save(company);
		logger.info("<== Updating of 'Company' with ID=" + company.getId() + " was successful ==>");
		logger.info("<== Out of 'postEditCompany()' method ... ==>");
		return PAGE_REDIRECT_TO_COMPANIES;
	}

	@RequestMapping(value = REQUEST_MAPPING_COMPANY, params = PARAM_DELETE, method = RequestMethod.POST)
	public String postDeleteCompany(@RequestParam int id) {
		logger.info("<== Enter to 'postDeleteCompany()' method ... ==>");
		logger.info("<== Starting delete 'Company' by ID=" + id + " ==>");
		companyRepository.delete(id);
		logger.info("<== 'Company' with ID=" + id + " was deleted from DB ==>");
		logger.info("<== Out of 'postDeleteCompany()' method ... ==>");
		return PAGE_REDIRECT_TO_COMPANIES;
	}
	
	@RequestMapping(value = REQUEST_MAPPING_COMPANY_ACCOUNT, params = PARAM_ADD, method = RequestMethod.POST)
	public String postAddCompanyAccount(@RequestParam int companyId, @RequestParam String presentation,
			@RequestParam Date dateStart) {
		logger.info("<== Enter to 'postAddCompanyAccount()' method ... ==>");
		Company company = companyRepository.findOne(companyId);
		logger.info("<== Adding new 'CompanyAccount' for 'Company='" + company.getTitle() + "' ==>");
		CompanyAccount companyAccount = new CompanyAccount(company, presentation, dateStart);
		companyAccount = accountRepository.save(companyAccount);
		logger.info("<== Saving new 'CompanyAccount' with ID=" + companyAccount.getId() + " for 'Company='" + company.getTitle() +  " was successeful ==>");
		logger.info("<== Out of 'postAddCompanyAccount()' method ... ==>");
		return PAGE_REDIRECT + company.getUrl();
	}
	
	@RequestMapping(value = REQUEST_MAPPING_COMPANY_ACCOUNT, params = PARAM_EDIT, method = RequestMethod.POST)
	public String postEditCompanyAccount(@RequestParam int id, @RequestParam String presentation,
			@RequestParam Date dateStart) {
		logger.info("<== Enter to 'postEditCompanyAccount()' method ... ==>");
		CompanyAccount companyAccount = accountRepository.findOne(id);
		logger.info("<== Starting update 'CompanyAccount' by ID=" + companyAccount.getId() + " ==>");
		Company company = companyRepository.findOne(companyAccount.getCompany().getId());
		companyAccount.setCompany(company);
		companyAccount.setPresentation(presentation);
		companyAccount.setDateStart(dateStart);
		companyAccount = accountRepository.save(companyAccount);
		logger.info("<== Updating of 'CompanyAccount' with ID=" + companyAccount.getId() + " was successful ==>");
		logger.info("<== Out of 'postEditCompanyAccount()' method ... ==>");
		return PAGE_REDIRECT + company.getUrl();
	}
	
	@RequestMapping(value = REQUEST_MAPPING_COMPANY_ACCOUNT, params = PARAM_DELETE, method = RequestMethod.POST)
	public String postDeleteCompanyAccount(@RequestParam int id) {
		logger.info("<== Enter to 'postDeleteCompanyAccount()' method ... ==>");
		CompanyAccount companyAccount = accountRepository.findOne(id);
		Company company = companyRepository.findOne(companyAccount.getCompany().getId());
		logger.info("<== Starting delete 'CompanyAccount' with ID=" + companyAccount.getId() + " ==>");
		accountRepository.delete(companyAccount);
		logger.info("<== Deleting of 'CompanyAccount' with ID=" + companyAccount.getId() + " was successful ==>");
		logger.info("<== Out of 'postDeleteCompanyAccount()' method ... ==>");
		return PAGE_REDIRECT + company.getUrl();
	}

	@RequestMapping(value = REQUEST_MAPPING_COMPANY_ADDRESS, params = PARAM_ADD, method = RequestMethod.POST)
	public String postAddCompanyAddress(@RequestParam int companyId, @RequestParam String presentation,
			@RequestParam Date dateStart) {
		logger.info("<== Enter to 'postAddCompanyAddress()' method ... ==>");
		Company company = companyRepository.findOne(companyId);
		logger.info("<== Adding new 'CompanyAddress' for 'Company='" + company.getTitle() + "' ==>");
		CompanyAddress companyAddress = new CompanyAddress(company, presentation, dateStart);
		companyAddress = addressRepository.save(companyAddress);
		logger.info("<== Saving new 'CompanyAddress' with ID=" + companyAddress.getId() + " for 'Company='" + company.getTitle() +  " was successeful ==>");
		logger.info("<== Out of 'postAddCompanyAddress()' method ... ==>");
		return PAGE_REDIRECT + company.getUrl();
	}

	@RequestMapping(value = REQUEST_MAPPING_COMPANY_ADDRESS, params = PARAM_EDIT, method = RequestMethod.POST)
	public String postEditCompanyAddress(@RequestParam int id, @RequestParam String presentation,
			@RequestParam Date dateStart) {
		logger.info("<== Enter to 'postEditCompanyAddress()' method ... ==>");
		CompanyAddress companyAddress = addressRepository.findOne(id);
		logger.info("<== Starting update 'CompanyAddress' by ID=" + companyAddress.getId() + " ==>");
		Company company = companyRepository.findOne(companyAddress.getCompany().getId());
		companyAddress.setCompany(company);
		companyAddress.setPresentation(presentation);
		companyAddress.setDateStart(dateStart);
		companyAddress = addressRepository.save(companyAddress);
		logger.info("<== Updating of 'CompanyAddress' with ID=" + companyAddress.getId() + " was successful ==>");
		logger.info("<== Out of 'postEditCompanyAddress()' method ... ==>");
		return PAGE_REDIRECT + company.getUrl();
	}

	@RequestMapping(value = REQUEST_MAPPING_COMPANY_ADDRESS, params = PARAM_DELETE, method = RequestMethod.POST)
	public String postDeleteCompanyAddress(@RequestParam int id) {
		logger.info("<== Enter to 'postDeleteCompanyAddress()' method ... ==>");
		CompanyAddress companyAddress = addressRepository.findOne(id);
		Company company = companyRepository.findOne(companyAddress.getCompany().getId());
		logger.info("<== Starting delete 'CompanyAddress' with ID=" + companyAddress.getId() + " ==>");
		addressRepository.delete(companyAddress);
		logger.info("<== Deleting of 'CompanyAddress' with ID=" + companyAddress.getId() + " was successful ==>");
		logger.info("<== Out of 'postDeleteCompanyAddress()' method ... ==>");
		return PAGE_REDIRECT + company.getUrl();
	}
	
	@RequestMapping(value = REQUEST_MAPPING_COMPANY_DIRECTOR, params = PARAM_ADD, method = RequestMethod.POST)
	public String postAddCompanyDirector(@RequestParam int companyId, @RequestParam String post, @RequestParam String fullName, 
			@RequestParam String shortName, @RequestParam Date employmentDate) {
		logger.info("<== Enter to 'postAddCompanyDirector()' method ... ==>");
		Company company = companyRepository.findOne(companyId);
		logger.info("<== Adding new 'CompanyDirector' for 'Company='" + company.getTitle() + "' ==>");
		CompanyDirector companyDirector = new CompanyDirector(company, post, fullName, shortName, employmentDate);
		companyDirector = directorRepository.save(companyDirector);
		logger.info("<== Saving new 'CompanyDirector' with ID=" + companyDirector.getId() + " for 'Company='" + company.getTitle() +  " was successeful ==>");
		logger.info("<== Out of 'postAddCompanyDirector()' method ... ==>");
		return PAGE_REDIRECT + company.getUrl();
	}
	
	@RequestMapping(value = REQUEST_MAPPING_COMPANY_DIRECTOR, params=PARAM_EDIT, method = RequestMethod.POST)
	public String postEditCompanyDirector(@RequestParam Integer id, @RequestParam String post, @RequestParam String fullName, 
			@RequestParam String shortName, @RequestParam Date employmentDate, @RequestParam Date firedDate) {
		logger.info("<== Enter to 'postEditCompanyDirector()' method ... ==>");
		CompanyDirector director = directorRepository.findOne(id);
		Company company = companyRepository.findOne(director.getCompany().getId());
		logger.info("<== Starting update 'CompanyDirector' by ID=" + director.getId() + " ==>");
		director.setCompany(company);
		director.setPost(post);
		director.setFullName(fullName);
		director.setShortName(shortName);
		director.setEmploymentDate(employmentDate);
		director.setFiredDate(firedDate);
		director = directorRepository.save(director);
		logger.info("<== Updating of 'CompanyDirector' with ID=" + director.getId() + " was successful ==>");
		logger.info("<== Out of 'postEditCompanyDirector()' method ... ==>");
		return PAGE_REDIRECT + company.getUrl();
	}
	
	@RequestMapping(value = REQUEST_MAPPING_COMPANY_DIRECTOR, params = PARAM_DELETE, method = RequestMethod.POST)
	public String postDeleteCompanyDirector(@RequestParam int id) {
		logger.info("<== Enter to 'postDeleteCompanyDirector()' method ... ==>");
		logger.info("<== Starting delete 'CompanyDirector' by ID=" + id + " ==>");
		CompanyDirector director = directorRepository.findOne(id);
		directorRepository.delete(director);
		logger.info("<== 'CompanyDirector' with ID=" + id + " was deleted from DB ==>");
		logger.info("<== Out of 'postDeleteCompanyDirector()' method ... ==>");
		return PAGE_REDIRECT + director.getCompany().getUrl();
	}

}
