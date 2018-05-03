package app.entity;

import java.util.Date;

public abstract class BaseReport {

	private String agreementTitle;
	private Date agreementDate;
	private String companyName;
	private String companyTaxId;
	private String companyInn;
	private String companyVatCertificate;
	private String companyAddress;
	private String companyAccount;
	private String companyDirectorFullName;
	private String companyDirectorShortName;
	private String companyDirectorPost;
	private String spdAlias;
	private String spdFullName;
	private String spdAddress;
	private String spdAccount;
	private String spdInn;
	private String regInfoDescription;
	private Date regInfoDated;

	public BaseReport() {
	}

	public String getAgreementTitle() {
		return agreementTitle;
	}

	public void setAgreementTitle(String agreementTitle) {
		this.agreementTitle = agreementTitle;
	}

	public Date getAgreementDate() {
		return agreementDate;
	}

	public void setAgreementDate(Date agreementDate) {
		this.agreementDate = agreementDate;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyTaxId() {
		return companyTaxId;
	}

	public void setCompanyTaxId(String companyTaxId) {
		this.companyTaxId = companyTaxId;
	}

	public String getCompanyInn() {
		return companyInn;
	}

	public void setCompanyInn(String companyInn) {
		this.companyInn = companyInn;
	}

	public String getCompanyVatCertificate() {
		return companyVatCertificate;
	}

	public void setCompanyVatCertificate(String companyVatCertificate) {
		this.companyVatCertificate = companyVatCertificate;
	}

	public String getCompanyAddress() {
		return companyAddress;
	}

	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}

	public String getCompanyAccount() {
		return companyAccount;
	}

	public void setCompanyAccount(String companyAccount) {
		this.companyAccount = companyAccount;
	}

	public String getCompanyDirectorFullName() {
		return companyDirectorFullName;
	}

	public void setCompanyDirectorFullName(String companyDirectorFullName) {
		this.companyDirectorFullName = companyDirectorFullName;
	}

	public String getCompanyDirectorShortName() {
		return companyDirectorShortName;
	}

	public void setCompanyDirectorShortName(String companyDirectorShortName) {
		this.companyDirectorShortName = companyDirectorShortName;
	}

	public String getCompanyDirectorPost() {
		return companyDirectorPost;
	}

	public void setCompanyDirectorPost(String companyDirectorPost) {
		this.companyDirectorPost = companyDirectorPost;
	}

	public String getSpdAlias() {
		return spdAlias;
	}

	public void setSpdAlias(String spdAlias) {
		this.spdAlias = spdAlias;
	}

	public String getSpdFullName() {
		return spdFullName;
	}

	public void setSpdFullName(String spdFullName) {
		this.spdFullName = spdFullName;
	}

	public String getSpdAddress() {
		return spdAddress;
	}

	public void setSpdAddress(String spdAddress) {
		this.spdAddress = spdAddress;
	}

	public String getSpdAccount() {
		return spdAccount;
	}

	public void setSpdAccount(String spdAccount) {
		this.spdAccount = spdAccount;
	}

	public String getSpdInn() {
		return spdInn;
	}

	public void setSpdInn(String spdInn) {
		this.spdInn = spdInn;
	}

	public String getRegInfoDescription() {
		return regInfoDescription;
	}

	public void setRegInfoDescription(String regInfoDescription) {
		this.regInfoDescription = regInfoDescription;
	}

	public Date getRegInfoDated() {
		return regInfoDated;
	}

	public void setRegInfoDated(Date regInfoDated) {
		this.regInfoDated = regInfoDated;
	}

}
