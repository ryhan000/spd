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
import app.entity.Agreement;
import app.entity.AgreementTarif;
import app.entity.Job;
import app.entity.Specification;
import app.entity.SpecificationPayment;
import app.repository.AgreementRepository;
import app.repository.AgreementTarifRepository;
import app.repository.CalculationRepository;
import app.repository.JobRepository;
import app.repository.SpecificationPaymentRepository;
import app.repository.SpecificationRepository;
import utils.BeanUtil;

@Controller
@Transactional
public class SpecificationController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(SpecificationController.class);

	@Autowired(required = true)
	private SpecificationRepository specificationRepository;
	
	@Autowired(required = true)
	private CalculationRepository calculationRepository;
	
	@Autowired(required = true)
	private AgreementRepository agreementRepository;
	
	@Autowired(required = true)
	private AgreementTarifRepository tarifRepository;
	
	@Autowired(required = true)
	private JobRepository jobRepository;
	
	@Autowired(required = true)
	private SpecificationPaymentRepository specPaymentRepository;
	
	@RequestMapping(value = REQUEST_MAPPING_SPECIFICATION, method = RequestMethod.GET)
	public String getEditSpecification(@RequestParam int id, Model model) {
		logger.info("<== Enter to 'getEditSpecification()' method ... ==>");
		Specification specification = specificationRepository.findOne(id);
		model.addAttribute("specification", specification);
		logger.info("<== Finding sum of current Calculations turnover ... ==>");
		Double calculationsTotalAmount = calculationRepository.findSumOfCalculationsBySpecificationId(id);
		model.addAttribute("calculationsTotalAmount", calculationsTotalAmount);
		logger.info("<== Finding next number for future Calculation ... ==>");
		int nextCalculationNumber = calculationRepository.findMaxCalculationNumberBySpecificationId(id);
		model.addAttribute("nextCalculationNumber", nextCalculationNumber + 1);
		AgreementTarif currentTarif = tarifRepository.findAgreementTarifBySpecificationId(specification.getId());
		logger.info("<== Got 'currentTarif' with ID=" + currentTarif.getId() + " ==>");
		model.addAttribute("currentTarif", currentTarif);
		logger.info("<== Out of 'getEditSpecification()' method ... ==>");
		return PAGE_SPECIFICATION_EDIT;
	}

	@RequestMapping(value = REQUEST_MAPPING_SPECIFICATION, params = PARAM_ADD, method = RequestMethod.POST)
	public String postAddSpecification(@RequestParam Integer agreementId, @RequestParam Integer specificationNumber,
			@RequestParam Date dateStart) {
		logger.info("<== Enter to 'postAddSpecification()' method ... ==>");
		Agreement agreement = agreementRepository.findOne(agreementId);
		logger.info("<== Adding new 'Specification' for 'Agreement='" + agreement.getNumber() + "' ==>");
		Specification specification = new Specification(agreement, specificationNumber, dateStart);
		specification = specificationRepository.save(specification);
		logger.info("<== Saving new 'Specification' with ID=" + specification.getId() + " for 'Agreement='"
				+ agreement.getNumber() + " was successeful ==>");
		logger.info("<== Out of 'postAddSpecification()' method ... ==>");
		return PAGE_REDIRECT + specification.getUrl();
	}

	@RequestMapping(value = REQUEST_MAPPING_SPECIFICATION, params = PARAM_EDIT, method = RequestMethod.POST)
	public String postEditSpecification(@RequestParam Integer id, @RequestParam Integer specificationNumber,
			@RequestParam String specificationSum, @RequestParam Date dateStart, @RequestParam Date dateFinish,
			@RequestParam Integer configuringHours, @RequestParam Integer programmingHours,
			@RequestParam Integer architectingHours) {
		logger.info("<== Enter to 'postEditSpecification()' method ... ==>");
		Specification specification = specificationRepository.findOne(id);
		logger.info("<== Starting update 'Specification' by ID=" + specification.getId() + " ==>");
		Agreement agreement = specification.getAgreement();
		specification.setAgreement(agreement);
		specification.setDateStart(dateStart);
		specification.setDateFinish(dateFinish);
		specification.setSpecificationNumber(specificationNumber);
		specification.setSpecificationSum(BeanUtil.convertStringToDouble(specificationSum));
		specification.setConfiguringHours(configuringHours);
		specification.setProgrammingHours(programmingHours);
		specification.setArchitectingHours(architectingHours);
		specification = specificationRepository.save(specification);
		logger.info("<== Updating of 'Specification' with ID=" + specification.getId() + " was successful ==>");
		logger.info("<== Out of 'postEditSpecification()' method ... ==>");
		return PAGE_REDIRECT + agreement.getUrl();
	}

	@RequestMapping(value = REQUEST_MAPPING_SPECIFICATION, params = PARAM_DELETE, method = RequestMethod.POST)
	public String postDeleteSpecification(@RequestParam int id) {
		logger.info("<== Enter to 'postDeleteSpecification()' method ... ==>");
		Specification specification = specificationRepository.findOne(id);
		logger.info("<== Starting delete 'Specification' with ID=" + specification.getId() + " ==>");
		specificationRepository.delete(specification);
		logger.info("<== Deleting of 'Specification' with ID=" + specification.getId() + " was successful ==>");
		logger.info("<== Out of 'postDeleteSpecification()' method ... ==>");
		return PAGE_REDIRECT + specification.getAgreement().getUrl();
	}
	
	@RequestMapping(value = REQUEST_MAPPING_JOB, params = PARAM_ADD, method = RequestMethod.POST)
	public String postAddJob(@RequestParam Integer specificationId, @RequestParam String jobName,
			@RequestParam Integer configuring, @RequestParam Integer programming, @RequestParam Integer architecting) {
		logger.info("<== Enter to 'postAddJob()' method ... ==>");
		Specification specification = specificationRepository.findOne(specificationId);
		logger.info("<== Adding new 'Job' for 'Specification='" + specification.getSpecificationNumber() + "' ==>");
		Job job = new Job(specification, jobName, configuring, programming, architecting);
		job = jobRepository.save(job);
		logger.info("<== Saving new 'Job' with ID=" + job.getId() + " for 'Specification='"
				+ specification.getSpecificationNumber() + " was successeful ==>");
		logger.info("<== Out of 'postAddJob()' method ... ==>");
		return PAGE_REDIRECT + specification.getUrl();
	}
	
	@RequestMapping(value = REQUEST_MAPPING_JOB, params = PARAM_EDIT, method = RequestMethod.POST)
	public String postEditJob(@RequestParam Integer id, @RequestParam String jobName, @RequestParam Integer configuring, 
			@RequestParam Integer programming, @RequestParam Integer architecting) {
		logger.info("<== Enter to 'postEditJob()' method ... ==>");
		Job job = jobRepository.findOne(id);
		logger.info("<== Starting update 'Job' by ID=" + job.getId() + "' ==>");
		job.setJobName(jobName);
		job.setConfiguringHours(configuring);
		job.setProgrammingHours(programming);
		job.setArchitectingHours(architecting);
		job = jobRepository.save(job);
		logger.info("<== Updating of 'Job' with ID=" + job.getId() + " was successful ==>");
		logger.info("<== Out of 'postEditJob()' method ... ==>");
		return PAGE_REDIRECT + job.getSpecification().getUrl();
	}
	
	@RequestMapping(value = REQUEST_MAPPING_JOB, params = PARAM_DELETE, method = RequestMethod.POST)
	public String postDeleteJob(@RequestParam int id) {
		logger.info("<== Enter to 'postDeleteJob()' method ... ==>");
		Job job = jobRepository.findOne(id);
		logger.info("<== Starting delete 'Job' with ID=" + job.getId() + " ==>");
		jobRepository.delete(job);
		logger.info("<== Deleting of 'Job' with ID=" + job.getId() + " was successful ==>");
		logger.info("<== Out of 'postDeleteJob()' method ... ==>");
		return PAGE_REDIRECT + job.getSpecification().getUrl();
	}
	
	@RequestMapping(value = REQUEST_MAPPING_SPECIFICATIONPAYMENT, params = PARAM_ADD, method = RequestMethod.POST)
	public String postAddSpecPayment(@RequestParam Integer specificationId, @RequestParam Integer paymentNumber,
			@RequestParam Double paymentSum, @RequestParam Integer paymentDays, @RequestParam String comment) {
		logger.info("<== Enter to 'postAddSpecPayment()' method ... ==>");
		Specification specification = specificationRepository.findOne(specificationId);
		logger.info("<== Adding new 'SpecificationPayment' for 'Specification='" + specification.getSpecificationNumber() + "' ==>");
		SpecificationPayment specPayment = new SpecificationPayment(specification, paymentNumber, paymentSum, paymentDays, comment);
		specPayment = specPaymentRepository.save(specPayment);
		logger.info("<== Saving new 'SpecificationPayment' with ID=" + specPayment.getId() + " for 'Specification='"
				+ specification.getSpecificationNumber() + " was successeful ==>");
		logger.info("<== Out of 'postAddSpecPayment()' method ... ==>");
		return PAGE_REDIRECT + specification.getUrl();
	}
	
	@RequestMapping(value = REQUEST_MAPPING_SPECIFICATIONPAYMENT, params = PARAM_EDIT, method = RequestMethod.POST)
	public String postEditSpecPayment(@RequestParam Integer id, @RequestParam Integer paymentNumber,
			@RequestParam Double paymentSum, @RequestParam Integer paymentDays, @RequestParam String comment) {
		logger.info("<== Enter to 'postEditSpecPayment()' method ... ==>");
		SpecificationPayment specPayment = specPaymentRepository.findOne(id);
		logger.info("<== Starting update 'SpecificationPayment' by ID=" + specPayment.getId() + "' ==>");
		specPayment.setPaymentNumber(paymentNumber);
		specPayment.setPaymentSum(paymentSum);
		specPayment.setPaymentDays(paymentDays);
		specPayment.setComment(comment);
		specPayment = specPaymentRepository.save(specPayment);
		logger.info("<== Updating of 'SpecificationPayment' with ID=" + specPayment.getId() + " was successful ==>");
		logger.info("<== Out of 'postEditSpecPayment()' method ... ==>");
		return PAGE_REDIRECT + specPayment.getSpecification().getUrl();
	}
	
	@RequestMapping(value = REQUEST_MAPPING_SPECIFICATIONPAYMENT, params = PARAM_DELETE, method = RequestMethod.POST)
	public String postDeleteSpecPayment(@RequestParam int id) {
		logger.info("<== Enter to 'postDeleteSpecPayment()' method ... ==>");
		SpecificationPayment specPayment = specPaymentRepository.findOne(id);
		logger.info("<== Starting delete 'SpecificationPayment' with ID=" + specPayment.getId() + " ==>");
		specPaymentRepository.delete(specPayment);
		logger.info("<== Deleting of 'SpecificationPayment' with ID=" + specPayment.getId() + " was successful ==>");
		logger.info("<== Out of 'postDeleteSpecPayment()' method ... ==>");
		return PAGE_REDIRECT + specPayment.getSpecification().getUrl();
	}

}
