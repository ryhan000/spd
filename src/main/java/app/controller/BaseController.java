package app.controller;

public abstract class BaseController {
	
	static final String ATTRIBUTE_ESV_TAXES = "esvTaxes";
	static final String ATTRIBUTE_SIMPLE_TAXES = "simpleTaxes";
	
	static final String PAGE_REDIRECT = "redirect:";
	static final String PAGE_REDIRECT_TO_TAXES = PAGE_REDIRECT + "taxes";
	static final String PAGE_REDIRECT_TO_AGREEMENTS = PAGE_REDIRECT + "agreements";
	static final String PAGE_REDIRECT_TO_COMPANIES = PAGE_REDIRECT + "companies";
	
	static final String PAGE_AGREEMENT_EDIT = "agreement/edit";
	static final String PAGE_AGREEMENT_ADD = "agreement/add";
	static final String PAGE_AGREEMENTS = "agreement/getAll";
	static final String PAGE_COMPANY_EDIT = "company/edit";
	static final String PAGE_COMPANIES = "company/getAll";
	static final String PAGE_TAXES = "tax/getAll";
	static final String PAGE_SPECIFICATION_EDIT = "specification/edit";
	
	static final String PARAM_ADD = "add";
	static final String PARAM_DELETE = "delete";
	static final String PARAM_EDIT = "edit";
	
	static final String REQUEST_MAPPING_ABOUT = "/about";
	static final String REQUEST_MAPPING_AGREEMENT = "/agreement";
	static final String REQUEST_MAPPING_AGREEMENTS = REQUEST_MAPPING_AGREEMENT + "s";
	static final String REQUEST_MAPPING_AGREEMENT_FILE = "/agreementFile";
	static final String REQUEST_MAPPING_AGREEMENT_TARIF = "/agreementTarif";
	static final String REQUEST_MAPPING_BLANK = "/";
	static final String REQUEST_MAPPING_CALCULATION = "/calculation";
	static final String REQUEST_MAPPING_COMPANY = "/company";
	static final String REQUEST_MAPPING_COMPANIES = "/companies";
	static final String REQUEST_MAPPING_COMPANY_ACCOUNT = "/companyAccount";
	static final String REQUEST_MAPPING_COMPANY_ADDRESS = "/companyAddress";
	static final String REQUEST_MAPPING_COMPANY_DIRECTOR = "/companyDirector";
	static final String REQUEST_MAPPING_ESV_TAX = "/esvtax";
	static final String REQUEST_MAPPING_JOB = "/job";
	static final String REQUEST_MAPPING_LOGIN = "/login";
	static final String REQUEST_MAPPING_MAIN = "/main";
	static final String REQUEST_MAPPING_PRINTPDF_AGR = "/agreement/printpdf/agr";
	static final String REQUEST_MAPPING_PRINTPDF_CERT = "/specification/printpdf/cert";
	static final String REQUEST_MAPPING_PRINTPDF_SPEC = "/specification/printpdf/spec";
	static final String REQUEST_MAPPING_REGISTER = "/register";
	static final String REQUEST_MAPPING_SIMPLE_TAX = "/simpletax";
	static final String REQUEST_MAPPING_SPD = "/spd";
	static final String REQUEST_MAPPING_SPDS = "/spds";
	static final String REQUEST_MAPPING_SPD_ACCOUNT = "/account";
	static final String REQUEST_MAPPING_SPD_PAYMENT = "/payment";
	static final String REQUEST_MAPPING_SPECIFICATION = "/specification";
	static final String REQUEST_MAPPING_SPECIFICATIONPAYMENT = "/specificationpayment";
	static final String REQUEST_MAPPING_TAXES = "/taxes";
	static final String REQUEST_MAPPING_USERS = "/users";
	static final String REQUEST_MAPPING_USER = "/user";

	static final String ROLE_ADMIN = "ROLE_ADMIN";
	static final String ROLE_USER = "ROLE_USER";

}
