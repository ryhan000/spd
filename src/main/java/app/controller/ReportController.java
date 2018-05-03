package app.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import app.entity.Account;
import app.entity.Agreement;
import app.entity.AgreementReport;
import app.entity.AgreementTarif;
import app.entity.CompanyAccount;
import app.entity.CompanyAddress;
import app.entity.CompanyDirector;
import app.entity.Job;
import app.entity.Specification;
import app.entity.SpecificationPayment;
import app.entity.SpecificationReport;
import app.repository.AccountRepository;
import app.repository.AgreementRepository;
import app.repository.AgreementTarifRepository;
import app.repository.CompanyAccountRepository;
import app.repository.CompanyAddressRepository;
import app.repository.CompanyDirectorRepository;
import app.repository.SpecificationRepository;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Controller
@RequestMapping
public class ReportController extends BaseController {

	private static final String PARAM_DATA_SOURCE_SPEC = "dataSourceSpec";
	private static final String PARAM_DATA_SOURCE_CERT = "dataSourceCert";
	private static final String PARAM_DATA_SOURCE_AGR = "dataSourceAgr";
	private static final String PARAM_VIEW_NAME_SPEC = "specificationReport";
	private static final String PARAM_VIEW_NAME_CERT = "certificateReport";
	private static final String PARAM_VIEW_NAME_AGR = "agreementReport";

	private static final Logger logger = LoggerFactory.getLogger(ReportController.class);

	@Autowired(required = true)
	private AgreementRepository agreementRepository;
	
	@Autowired(required = true)
	private SpecificationRepository specificationRepository;
	
	@Autowired(required = true)
	private AgreementTarifRepository tarifRepository;
	
	@Autowired(required = true)
	private CompanyDirectorRepository compDirectorRepository;
	
	@Autowired(required = true)
	private CompanyAddressRepository compAddressRepository;
	
	@Autowired(required = true)
	private CompanyAccountRepository compAccountRepository;
	
	@Autowired(required = true)
	private AccountRepository accountRepository;
	
	@RequestMapping(value = REQUEST_MAPPING_PRINTPDF_SPEC, method = RequestMethod.GET)
	public ModelAndView generatePdfReportSpec(@RequestParam Integer id, ModelAndView modelAndView) {
		modelAndView = generateReport(id, PARAM_DATA_SOURCE_SPEC, PARAM_VIEW_NAME_SPEC);
		return modelAndView;
	}
	
	@RequestMapping(value = REQUEST_MAPPING_PRINTPDF_CERT, method = RequestMethod.GET)
	public ModelAndView generatePdfReportCert(@RequestParam Integer id, ModelAndView modelAndView) {
		modelAndView = generateReport(id, PARAM_DATA_SOURCE_CERT, PARAM_VIEW_NAME_CERT);
		return modelAndView;
	}
	
	@RequestMapping(value = REQUEST_MAPPING_PRINTPDF_AGR, method = RequestMethod.GET)
	public ModelAndView generatePdfReportAgr(@RequestParam Integer id, ModelAndView modelAndView) {
		modelAndView = generateAgreementReport(id, PARAM_DATA_SOURCE_AGR, PARAM_VIEW_NAME_AGR);
		return modelAndView;
	}
	
	private ModelAndView generateAgreementReport(Integer id, String dataSourceParam, String viewNameParam) {
		logger.info("<<-------------- Begin to generate Agreement PDF report -------------->>");
		Map<String, Object> parameters = new HashMap<String, Object>();
		List<AgreementReport> reports = createAgreementReport(id);
		JRDataSource jrDataSource = new JRBeanCollectionDataSource(reports);
		logger.info("<<-------------- jrDataSource created -------------->>");
		parameters.put(dataSourceParam, jrDataSource);
		logger.info("<<-------------- jrDataSource put into parameters -------------->>");
		logger.info("<<-------------- pdfReport bean has been declared in the jasper-views.xml file -------------->>");
		ModelAndView modelAndView = new ModelAndView(viewNameParam, parameters);
		logger.info("<<-------------- Out of generating PDF report -------------->>");
		return modelAndView;
	}

	private ModelAndView generateReport(Integer id, String dataSourceParam, String viewNameParam) {
		logger.info("<<-------------- Begin to generate PDF report -------------->>");
		Map<String, Object> parameters = new HashMap<String, Object>();
		List<SpecificationReport> reports = createReport(id);
		JRDataSource jrDataSource = new JRBeanCollectionDataSource(reports);
		logger.info("<<-------------- jrDataSource created -------------->>");
		parameters.put(dataSourceParam, jrDataSource);
		logger.info("<<-------------- jrDataSource put into parameters -------------->>");
		logger.info("<<-------------- pdfReport bean has been declared in the jasper-views.xml file -------------->>");
		ModelAndView modelAndView = new ModelAndView(viewNameParam, parameters);
		logger.info("<<-------------- Out of generating PDF report -------------->>");
		return modelAndView;
	}

	private List<SpecificationReport> createReport(Integer id) {
		List<SpecificationReport> reports = new ArrayList<>();
		Specification specification = specificationRepository.findOne(id);
		SpecificationReport report = new SpecificationReport();
		AgreementTarif currentRate = tarifRepository.findAgreementTarifBySpecificationId(id);
		CompanyDirector director = compDirectorRepository.findActualDirectorBySpecificationId(id);
		CompanyAddress companyAddress = compAddressRepository.findActualCompanyAddressBySpecificationId(id);
		CompanyAccount companyAccount = compAccountRepository.findActualCompanyAccountBySpecificationId(id);
		Account spdAccount = accountRepository.findActualSpdAccountBySpdId(specification.getAgreement().getSpd().getId());
		report.setAgreementTitle(specification.getAgreement().getNumber());
		report.setAgreementDate(specification.getAgreement().getDateStart());
		report.setSpecificationNumber(specification.getSpecificationNumber());
		report.setSpecificationStartDate(specification.getDateStart());
		report.setSpecificationFinalDate(specification.getDateFinish());
		report.setSpecificationSum(specification.getSpecificationSum());
		report.setConfiguringHours((Integer.toString(specification.getConfiguringHours()) == null) ? 0 : specification.getConfiguringHours());
		logger.info("<<-------------- " + report.getConfiguringHours() + " --------------->>");
		report.setProgrammingHours(specification.getProgrammingHours());
		logger.info("<<-------------- " + report.getProgrammingHours() + " --------------->>");
		report.setArchitectingHours(specification.getArchitectingHours());
		logger.info("<<-------------- " + report.getArchitectingHours() + " --------------->>");
		report.setConfiguringRate(currentRate.getConfiguring());
		report.setProgrammingRate(currentRate.getProgramming());
		report.setArchitectingRate(currentRate.getArchitecting());
		report.setCompanyName(specification.getAgreement().getCompany().getTitle());
		report.setCompanyTaxId(specification.getAgreement().getCompany().getEdrpou());
		report.setCompanyInn(specification.getAgreement().getCompany().getInn().equals(null) ? "-" : specification.getAgreement().getCompany().getInn());
		report.setCompanyVatCertificate(specification.getAgreement().getCompany().getVatCertificate().equals(null) ? "-" : specification.getAgreement().getCompany().getVatCertificate());
		report.setCompanyAddress(companyAddress.getPresentation());
		report.setCompanyAccount(companyAccount.getPresentation());
		report.setCompanyDirectorShortName(director.getShortName());
		report.setCompanyDirectorFullName(director.getFullName());
		report.setCompanyDirectorPost(director.getPost());
		report.setSpdFullName(specification.getAgreement().getSpd().getSpdFullName());
		report.setSpdAlias(specification.getAgreement().getSpd().getAlias());
		report.setSpdInn(specification.getAgreement().getSpd().getInn());
		report.setSpdAddress(specification.getAgreement().getSpd().getAddress().toString());
		report.setSpdAccount(spdAccount.toString());
		report.setRegInfoDescription(specification.getAgreement().getSpd().getRegistrationInfo().getDescription());
		report.setRegInfoDated(specification.getAgreement().getSpd().getRegistrationInfo().getDated());
		List<Job> jobs = new ArrayList<Job>(specification.getJobs());
		for (Job job : jobs) {
			logger.info("<<-------------- " + job.getConfiguringHours() + ", " + job.getProgrammingHours() + ", " + job.getArchitectingHours() + " --------------->>");
			int zero = 0;
			if (job.getConfiguringHours() == null) job.setConfiguringHours(zero);
			if (job.getProgrammingHours() == null) job.setProgrammingHours(zero);
			if (job.getArchitectingHours() == null) job.setArchitectingHours(zero);
			logger.info("<<-------------- " + job.getConfiguringHours() + ", " + job.getProgrammingHours() + ", " + job.getArchitectingHours() + " --------------->>");
		}
		report.setJobs(jobs);
		List<SpecificationPayment> payments = new ArrayList<>(specification.getSpecPayments());
		report.setPayments(payments);
		report.setQuantityOfPayments(payments.size());
		reports.add(report);
		return reports;
	}
	
	private List<AgreementReport> createAgreementReport(Integer id) {
		List<AgreementReport> reports = new ArrayList<>();
		Agreement agreement = agreementRepository.findOne(id);
		AgreementReport report = new AgreementReport();
		CompanyDirector director = compDirectorRepository.findActualDirectorByAgreementId(id);
		CompanyAddress companyAddress = compAddressRepository.findActualCompanyAddressByAgreementId(id);
		CompanyAccount companyAccount = compAccountRepository.findActualCompanyAccountByAgreementId(id);
		Account spdAccount = accountRepository.findActualSpdAccountBySpdId(agreement.getSpd().getId());
		report.setAgreementTitle(agreement.getNumber());
		logger.info("<<-------------- AgreementTitle = '" + report.getAgreementTitle() + "' --------------->>");
		report.setAgreementDate(agreement.getDateStart());
		logger.info("<<-------------- AgreementDate = '" + report.getAgreementDate() + "' --------------->>");
		report.setCompanyName(agreement.getCompany().getTitle());
		logger.info("<<-------------- CompanyName = '" + report.getCompanyName() + "' --------------->>");
		report.setCompanyTaxId(agreement.getCompany().getEdrpou());
		logger.info("<<-------------- CompanyTaxId = '" + report.getCompanyTaxId() + "' --------------->>");
		report.setCompanyInn(agreement.getCompany().getInn().equals(null) ? "-" : agreement.getCompany().getInn());
		report.setCompanyVatCertificate(agreement.getCompany().getVatCertificate().equals(null) ? "-" : agreement.getCompany().getVatCertificate());
		report.setCompanyAddress(companyAddress.getPresentation());
		report.setCompanyAccount(companyAccount.getPresentation());
		report.setCompanyDirectorShortName(director.getShortName());
		report.setCompanyDirectorFullName(director.getFullName());
		report.setCompanyDirectorPost(director.getPost());
		report.setSpdFullName(agreement.getSpd().getSpdFullName());
		report.setSpdAlias(agreement.getSpd().getAlias());
		report.setSpdInn(agreement.getSpd().getInn());
		report.setSpdAddress(agreement.getSpd().getAddress().toString());
		report.setSpdAccount(spdAccount.toString());
		report.setRegInfoDescription(agreement.getSpd().getRegistrationInfo().getDescription());
		report.setRegInfoDated(agreement.getSpd().getRegistrationInfo().getDated());
		reports.add(report);
		return reports;
	}

}
