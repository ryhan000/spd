package app.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "company_account")
@NamedQueries({
	@NamedQuery(
		name = CompanyAccount.FIND_ACTUAL_COMPANY_ACCOUNT_BY_AGREEMENT_ID,
		query = "select ca from CompanyAccount ca where ca.company.id = (select a.company.id from Agreement a where a.id = :agreementId) and ca.dateStart = (select max(ca.dateStart) from CompanyAccount ca where ca.company.id = (select a.company.id from Agreement a where a.id = :agreementId) and ca.dateStart <= (select a.dateStart from Agreement a where a.id = :agreementId))"),
	@NamedQuery(
		name = CompanyAccount.FIND_ACTUAL_COMPANY_ACCOUNT_BY_SPECIFICATION_ID,
		query = "select ca from CompanyAccount ca where ca.company.id = (select a.company.id from Agreement a, Specification s where a.id = s.agreement.id and s.id = :specificationId) and ca.dateStart = (select max(ca.dateStart) from CompanyAccount ca where ca.company.id = (select a.company.id from Agreement a, Specification s where a.id = s.agreement.id and s.id = :specificationId) and ca.dateStart <= (select s.dateStart from Specification s where s.id = :specificationId))")
})
public class CompanyAccount extends UrlEntity implements Serializable {

	public static final String FIND_ACTUAL_COMPANY_ACCOUNT_BY_AGREEMENT_ID = "CompanyAccount.findActualCompanyAccountByAgreementId";
	public static final String FIND_ACTUAL_COMPANY_ACCOUNT_BY_SPECIFICATION_ID = "CompanyAccount.findActualCompanyAccountBySpecificationId";
	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "company_id")
	@JsonBackReference
	private Company company;

	@Column(name = "presentation", length = 255)
	private String presentation;

	@Temporal(TemporalType.DATE)
	@Column(name = "date_start")
	private Date dateStart;

	public CompanyAccount() {
	}

	public CompanyAccount(Company company, String presentation, Date dateStart) {
		this.company = company;
		this.presentation = presentation;
		this.dateStart = dateStart;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public String getPresentation() {
		return presentation;
	}

	public void setPresentation(String presentation) {
		this.presentation = presentation;
	}

	public Date getDateStart() {
		return dateStart;
	}

	public void setDateStart(Date dateStart) {
		this.dateStart = dateStart;
	}

}
