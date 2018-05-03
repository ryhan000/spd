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
import app.entity.Account;
import app.entity.Address;
import app.entity.Payment;
import app.entity.PaymentType;
import app.entity.RegistrationInfo;
import app.entity.SPD;
import app.repository.AccountRepository;
import app.repository.PaymentRepository;
import app.repository.PaymentTypeRepository;
import app.repository.SPDRepository;

@Controller
@Transactional
public class SPDController extends BaseController {
	
	private static final Logger logger = LoggerFactory.getLogger(SPDController.class);

	@Autowired(required = true)
	private SPDRepository spdRepository;
	
	@Autowired(required = true)
	private AccountRepository accountRepository;
	
	@Autowired(required = true)
	private PaymentRepository paymentRepository;
	
	@Autowired(required = true)
	private PaymentTypeRepository paymentTypeRepository;

	@RequestMapping(value = REQUEST_MAPPING_SPDS, method = RequestMethod.GET)
	public String getAllSPD(Model model) {
		logger.info("Entering to the getAllSPD() method***");
		model.addAttribute("spds", spdRepository.findAll());
		return "spd/getAll";
	}

	@RequestMapping(value = REQUEST_MAPPING_SPD, params = PARAM_ADD, method = RequestMethod.GET)
	public String getAddSPD() {
		logger.info("<== Entering to the getAddSPD() method ... ==>");
		return "spd/add";
	}

	@RequestMapping(value = REQUEST_MAPPING_SPD, params = PARAM_EDIT, method = RequestMethod.GET)
	public String getEditSPD(@RequestParam int id, Model model) {
		logger.info("<== Entering to the getEditSPD() method ... ==>");
		model.addAttribute("spd", spdRepository.findOne(id));
		return "spd/edit";
	}

	@RequestMapping(value = REQUEST_MAPPING_SPD, method = RequestMethod.GET)
	public String getViewSPD(@RequestParam int id, Model model) {
		logger.info("<== Enter to 'getViewSPD()' method ... ==>");
		SPD spd = spdRepository.findOne(id);
		model.addAttribute("spd", spd);
		model.addAttribute("paymentTypes", paymentTypeRepository.findAll());
		logger.info("<== Out of 'getViewSPD()' method ... ==>");
		return "spd/view";
	}

	@RequestMapping(value = REQUEST_MAPPING_SPD, params = PARAM_ADD, method = RequestMethod.POST)
	public String postAddSPD(@RequestParam String surname, @RequestParam String firstname,
			@RequestParam String lastname, @RequestParam String alias, @RequestParam String inn,
			@RequestParam String passport, @RequestParam String description, @RequestParam Date dated,
			@RequestParam String country, @RequestParam String region, @RequestParam String city,
			@RequestParam String street, @RequestParam String building, @RequestParam String flat,
			@RequestParam String zip) {
		logger.info("<== Enter to 'postAddSPD()' method ... ==>");
		logger.info("<== Saving new 'SPD' ... ==>");
		Address address = new Address(country, region, city, street, building, flat, zip);
		RegistrationInfo regInfo = new RegistrationInfo(description, dated);
		SPD spd = new SPD(surname, firstname, lastname, alias, inn, passport, address, regInfo);
		spd = spdRepository.save(spd);
		logger.info("<== Saving new 'SPD' with ID=" + spd.getId() + " was successful ==>");
		logger.info("<== Out of 'postAddSPD()' method ... ==>");
		return "redirect:" + spd.getUrl();
	}

	@RequestMapping(value = REQUEST_MAPPING_SPD, params = PARAM_EDIT, method = RequestMethod.POST)
	public String postEditSPD(@RequestParam Integer id, @RequestParam String surname, @RequestParam String firstname,
			@RequestParam String lastname, @RequestParam String alias, @RequestParam String inn,
			@RequestParam String passport, @RequestParam String description, @RequestParam Date dated,
			@RequestParam String country, @RequestParam String region, @RequestParam String city,
			@RequestParam String street, @RequestParam String building, @RequestParam String flat,
			@RequestParam String zip) {
		logger.info("<== Enter to 'postEditSPD()' method ... ==>");
		SPD spd = spdRepository.findOne(id);
		Address address = spd.getAddress();
		RegistrationInfo regInfo = spd.getRegistrationInfo();
		logger.info("<== Starting update 'SPD' by ID=" + spd.getId() + " ==>");
		logger.info("<== Updating 'Address' ... ==>");
		address.setCountry(country);
		address.setRegion(region);
		address.setCity(city);
		address.setStreet(street);
		address.setBuilding(building);
		address.setFlat(flat);
		address.setZip(zip);
		logger.info("<== Updating 'RegInfo' ... ==>");
		regInfo.setDescription(description);
		regInfo.setDated(dated);
		logger.info("<== Updating 'SPD' ... ==>");
		spd.setSurname(surname);
		spd.setFirstname(firstname);
		spd.setLastname(lastname);
		spd.setAlias(alias);
		spd.setInn(inn);
		spd.setPassport(passport);
		spd.setAddress(address);
		spd.setRegistrationInfo(regInfo);
		spd = spdRepository.save(spd);
		logger.info("<== Updating of 'SPD' with ID=" + spd.getId() + " was successful ==>");
		logger.info("<== Out of 'postEditSPD()' method ... ==>");
		return "redirect:" + spd.getUrl();
	}

	@RequestMapping(value = REQUEST_MAPPING_SPD, params = PARAM_DELETE, method = RequestMethod.POST)
	public String postDeleteSPD(@RequestParam int id) {
		logger.info("<== Enter to 'postDeleteSPD()' method ... ==>");
		logger.info("***Starting delete 'SPD' by ID=" + id + " ==>");
		spdRepository.delete(id);
		logger.info("<== 'SPD' with ID=" + id + " was deleted from DB ==>");
		logger.info("<== Out of 'postDeleteSPD()' method ... ==>");
		return "redirect:spds";
	}

	@RequestMapping(value = REQUEST_MAPPING_SPD_ACCOUNT, params = PARAM_ADD, method = RequestMethod.POST)
	public String postAddAccount(@RequestParam int spdId, @RequestParam String accountNumber, @RequestParam String mfo,
			@RequestParam String bankName) {
		logger.info("<== Enter to 'postAddAccount()' method ... ==>");
		SPD spd = spdRepository.findOne(spdId);
		logger.info("<== Adding new 'Account' for 'SPD='" + spd.getAlias() + "' ==>");
		Account account = new Account(spd, accountNumber, mfo, bankName);
		account = accountRepository.save(account);
		logger.info("<== Saving new 'Account' with ID=" + account.getId() + " for 'SPD='" + spd.getAlias() +  " was successeful ==>");
		logger.info("<== Out of 'postAddAccount()' method ... ==>");
		return "redirect:" + spd.getUrl();
	}

	@RequestMapping(value = REQUEST_MAPPING_SPD_ACCOUNT, params = PARAM_EDIT, method = RequestMethod.POST)
	public String postEditAccount(@RequestParam int id, @RequestParam String accountNumber, @RequestParam String mfo,
			@RequestParam String bankName) {
		logger.info("<== Enter to 'postEditAccount()' method ... ==>");
		Account account = accountRepository.findOne(id);
		SPD spd = account.getSpd();
		logger.info("<== Starting update 'Account' by ID=" + account.getId() + " ==>");
		account.setSpd(spd);
		account.setAccountNumber(accountNumber);
		account.setMfo(mfo);
		account.setBankName(bankName);
		account = accountRepository.save(account);
		logger.info("<== Updating of 'Account' with ID=" + account.getId() + " was successful ==>");
		logger.info("<== Out of 'postEditAccount()' method ... ==>");
		return "redirect:" + spd.getUrl();
	}

	@RequestMapping(value = REQUEST_MAPPING_SPD_ACCOUNT, params = PARAM_DELETE, method = RequestMethod.POST)
	public String postDeleteAccount(@RequestParam int id) {
		logger.info("<== Enter to 'postDeleteAccount()' method ... ==>");
		Account account = accountRepository.findOne(id);
		SPD spd = account.getSpd();
		logger.info("<== Starting delete 'Account' with ID=" + account.getId() + " ==>");
		accountRepository.delete(account);
		logger.info("<== Deleting of 'Account' with ID=" + account.getId() + " was successful ==>");
		logger.info("<== Out of 'postDeleteAccount()' method ... ==>");
		return "redirect:" + spd.getUrl();
	}
	
	@RequestMapping(value = REQUEST_MAPPING_SPD_PAYMENT, params = PARAM_ADD, method = RequestMethod.POST)
	public String postAddPayment(@RequestParam int spdId, @RequestParam("payment_type_id") int paymentTypeId, @RequestParam Double value,
			@RequestParam Date dateStart, @RequestParam Date dateFinish) {
		logger.info("<== Enter to 'postAddPayment()' method ... ==>");
		SPD spd = spdRepository.findOne(spdId);
		PaymentType paymentType = paymentTypeRepository.findOne(paymentTypeId);
		logger.info("<== Adding new 'Payment' for 'SPD='" + spd.getAlias() + "' ==>");
		Payment payment = new Payment(spd, paymentType, value, dateStart, dateFinish);
		payment = paymentRepository.save(payment);
		logger.info("<== Saving new 'Payment' with ID=" + payment.getId() + " for 'SPD='" + spd.getAlias() +  " was successeful ==>");
		logger.info("<== Out of 'postAddPayment()' method ... ==>");
		return "redirect:" + spd.getUrl();
	}
	
	@RequestMapping(value = REQUEST_MAPPING_SPD_PAYMENT, params = PARAM_EDIT, method = RequestMethod.POST)
	public String postEditPayment(@RequestParam int id, @RequestParam("payment_type_id") int paymentTypeId, @RequestParam Double value,
			@RequestParam Date dateStart, @RequestParam Date dateFinish) {
		logger.info("<== Enter to 'postEditPayment()' method ... ==>");
		Payment payment = paymentRepository.findOne(id);
		SPD spd = payment.getSpd();
		logger.info("<== Starting update 'Payment' by ID=" + payment.getId() + " ==>");
		payment.setSpd(spd);
		payment.setPaymentType(paymentTypeRepository.findOne(paymentTypeId));
		payment.setValue(value);
		payment.setDateStart(dateStart);
		payment.setDateFinish(dateFinish);
		logger.info("<== Updating of 'Payment' with ID=" + payment.getId() + " was successful ==>");
		logger.info("<== Out of 'postEditPayment()' method ... ==>");
		return "redirect:" + spd.getUrl();
	}
	
	@RequestMapping(value = REQUEST_MAPPING_SPD_PAYMENT, params = PARAM_DELETE, method = RequestMethod.POST)
	public String postDeletePayment(@RequestParam int id) {
		logger.info("<== Enter to 'postDeletePayment()' method ... ==>");
		Payment payment = paymentRepository.findOne(id);
		SPD spd = payment.getSpd();
		logger.info("<== Starting delete 'Payment' with ID=" + payment.getId() + " ==>");
		paymentRepository.delete(payment);
		logger.info("<== Deleting of 'Payment' with ID=" + payment.getId() + " was successful ==>");
		logger.info("<== Out of 'postDeletePayment()' method ... ==>");
		return "redirect:" + spd.getUrl();
	}
	
}
