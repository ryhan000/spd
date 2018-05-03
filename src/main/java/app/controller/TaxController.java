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

import app.entity.ESVTax;
import app.entity.SimpleTax;
import app.repository.ESVTaxRepository;
import app.repository.SimpleTaxRepository;

@Controller
@Transactional
public class TaxController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(TaxController.class);

	@Autowired(required = true)
	private ESVTaxRepository esvTaxRepository;
	
	@Autowired(required = true)
	private SimpleTaxRepository simpleTaxRepository;
	
	@RequestMapping(value = REQUEST_MAPPING_TAXES, method = RequestMethod.GET)
	public String getAllTaxes(Model model) {
		logger.info("<==((((((((( Entering to the getAllUsers() method )))))))))==>");
		model.addAttribute(ATTRIBUTE_ESV_TAXES, esvTaxRepository.findAll());
		model.addAttribute(ATTRIBUTE_SIMPLE_TAXES, simpleTaxRepository.findAll());
		return PAGE_TAXES;
	}
	
	@RequestMapping(value = REQUEST_MAPPING_ESV_TAX, params = PARAM_ADD, method = RequestMethod.POST)
	public String postAddEsvTax(@RequestParam Double value, @RequestParam Date dateStart) {
		logger.info("<==((((((((( Enter to 'postAddEsvTax()' method ... )))))))))==>");
		logger.info("<==((((((((( Adding new 'ESVTax' )))))))))==>");
		ESVTax esvTax = new ESVTax(value, dateStart);
		esvTax = esvTaxRepository.save(esvTax);
		logger.info("<==((((((((( Saving new 'ESVTax' with ID=" + esvTax.getId() + " was successeful )))))))))==>");
		logger.info("<==((((((((( Out of 'postAddEsvTax()' method ... )))))))))==>");
		return PAGE_REDIRECT_TO_TAXES;
	}
	
	@RequestMapping(value = REQUEST_MAPPING_ESV_TAX, params = PARAM_EDIT, method = RequestMethod.POST)
	public String postEditEsvTax(@RequestParam Integer id, @RequestParam Double value, @RequestParam Date dateStart) {
		logger.info("<==((((((((( Enter to 'postEditEsvTax()' method ... )))))))))==>");
		ESVTax esvTax = esvTaxRepository.findOne(id);
		logger.info("<==((((((((( Starting update 'ESVTax' by ID=" + esvTax.getId() + "' )))))))))==>");
		esvTax.setValue(value);
		esvTax.setDateStart(dateStart);
		esvTax = esvTaxRepository.save(esvTax);
		logger.info("<==((((((((( Updating of 'ESVTax' with ID=" + esvTax.getId() + " was successful )))))))))==>");
		logger.info("<==((((((((( Out of 'postEditEsvTax()' method ... )))))))))==>");
		return PAGE_REDIRECT_TO_TAXES;
	}
	
	@RequestMapping(value = REQUEST_MAPPING_ESV_TAX, params = PARAM_DELETE, method = RequestMethod.POST)
	public String postDeleteEsvTax(@RequestParam int id) {
		logger.info("<==((((((((( Enter to 'postDeleteEsvTax()' method ... )))))))))==>");
		ESVTax esvTax = esvTaxRepository.findOne(id);
		logger.info("<==((((((((( Starting delete 'ESVTax' with ID=" + esvTax.getId() + " )))))))))==>");
		esvTaxRepository.delete(esvTax);
		logger.info("<==((((((((( Deleting of 'ESVTax' with ID=" + esvTax.getId() + " was successful )))))))))==>");
		logger.info("<==((((((((( Out of 'postDeleteEsvTax()' method ... )))))))))==>");
		return PAGE_REDIRECT_TO_TAXES;
	}
	
	@RequestMapping(value = REQUEST_MAPPING_SIMPLE_TAX, params = PARAM_ADD, method = RequestMethod.POST)
	public String postAddSimpleTax(@RequestParam Double value, @RequestParam Date dateStart) {
		logger.info("<==((((((((( Enter to 'postAddSimpleTax()' method ... )))))))))==>");
		logger.info("<==((((((((( Adding new 'SimpleTax' )))))))))==>");
		SimpleTax simpleTax = new SimpleTax(value, dateStart);
		simpleTax = simpleTaxRepository.save(simpleTax);
		logger.info("<==((((((((( Saving new 'SimpleTax' with ID=" + simpleTax.getId() + " was successeful )))))))))==>");
		logger.info("<==((((((((( Out of 'postAddSimpleTax()' method ... )))))))))==>");
		return PAGE_REDIRECT_TO_TAXES;
	}
	
	@RequestMapping(value = REQUEST_MAPPING_SIMPLE_TAX, params = PARAM_EDIT, method = RequestMethod.POST)
	public String postEditSimpleTax(@RequestParam Integer id, @RequestParam Double value, @RequestParam Date dateStart) {
		logger.info("<==((((((((( Enter to 'postEditSimpleTax()' method ... )))))))))==>");
		SimpleTax simpleTax = simpleTaxRepository.findOne(id);
		logger.info("<==((((((((( Starting update 'SimpleTax' by ID=" + simpleTax.getId() + "' )))))))))==>");
		simpleTax.setValue(value);
		simpleTax.setDateStart(dateStart);
		simpleTax = simpleTaxRepository.save(simpleTax);
		logger.info("<==((((((((( Updating of 'SimpleTax' with ID=" + simpleTax.getId() + " was successful )))))))))==>");
		logger.info("<==((((((((( Out of 'postEditSimpleTax()' method ... )))))))))==>");
		return PAGE_REDIRECT_TO_TAXES;
	}
	
	@RequestMapping(value = REQUEST_MAPPING_SIMPLE_TAX, params = PARAM_DELETE, method = RequestMethod.POST)
	public String postDeleteSimpleTax(@RequestParam int id) {
		logger.info("<==((((((((( Enter to 'postDeleteSimpleTax()' method ... )))))))))==>");
		SimpleTax esvTax = simpleTaxRepository.findOne(id);
		logger.info("<==((((((((( Starting delete 'SimpleTax' with ID=" + esvTax.getId() + " )))))))))==>");
		simpleTaxRepository.delete(esvTax);
		logger.info("<==((((((((( Deleting of 'SimpleTax' with ID=" + esvTax.getId() + " was successful )))))))))==>");
		logger.info("<==((((((((( Out of 'postDeleteSimpleTax()' method ... )))))))))==>");
		return PAGE_REDIRECT_TO_TAXES;
	}
	
}
