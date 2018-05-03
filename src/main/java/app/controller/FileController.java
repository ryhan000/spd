package app.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import app.entity.Agreement;
import app.entity.AgreementFile;
import app.entity.SPD;
import app.repository.AgreementFileRepository;
import app.repository.AgreementRepository;

@Controller
@Transactional
public class FileController extends BaseController {
	
	private static final Logger logger = LoggerFactory.getLogger(FileController.class);
	
	@Autowired(required = true)
	private AgreementRepository agreementRepository;
	
	@Autowired(required = true)
	private AgreementFileRepository agreementFileRepository;
	

	@RequestMapping(value = REQUEST_MAPPING_AGREEMENT_FILE, params = PARAM_ADD, method = RequestMethod.POST)
	public String postAddAgreementFile(@RequestParam Integer agreementId, @RequestParam String filename, 
			@RequestParam MultipartFile file, @RequestParam String description) throws IOException {
		logger.info("<== Enter to 'postAddAgreementFile()' method ... ==>");
		Agreement agreement = agreementRepository.findOne(agreementId);
		byte[] fileData = file.getBytes();
		AgreementFile agreementFile = new AgreementFile(description, filename, fileData);
		agreementFile = agreementFileRepository.save(agreementFile);
		
//		logger.info("<== Saving new 'Agreement' for 'SPD='" + spd.getAlias() + "' ==>");
//		Agreement agreement = new Agreement(spd, number, dateStart);
//		agreement = agreementRepository.save(agreement);
//		logger.info("<== Adding default zero tarif for new agreement with ID=" + agreement.getId() + " ==>");
//		postAddAgreementTarif(agreement.getId(), 0d, 0d, 0d, dateStart);
//		logger.info("<== Saving new 'Agreement' with ID=" + agreement.getId() + " for 'SPD=" + spd.getAlias() + "' was successful ==>");
//		logger.info("<== Out of 'postAddAgreement()' method ... ==>");
		
		return PAGE_REDIRECT + agreement.getUrl();
	}
	
}
