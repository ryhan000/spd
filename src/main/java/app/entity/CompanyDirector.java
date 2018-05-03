package app.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "company_director")
public class CompanyDirector extends UrlEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "company_id")
	@JsonBackReference
	private Company company;

	@Column(name = "post", length = 50)
	private String post;

	@Column(name = "full_name", length = 255)
	private String fullName;

	@Column(name = "short_name", length = 100)
	private String shortName;

	@Temporal(TemporalType.DATE)
	@Column(name = "employment_date")
	private Date employmentDate;

	@Temporal(TemporalType.DATE)
	@Column(name = "fired_date")
	private Date firedDate;

	public CompanyDirector() {
	}

	public CompanyDirector(Company company, String post, String fullName, String shortName, Date employmentDate) {
		this.company = company;
		this.post = post;
		this.fullName = fullName;
		this.shortName = shortName;
		this.employmentDate = employmentDate;
	}

	public CompanyDirector(Company company, String post, String fullName, String shortName, Date employmentDate,
			Date firedDate) {
		this.company = company;
		this.post = post;
		this.fullName = fullName;
		this.shortName = shortName;
		this.employmentDate = employmentDate;
		this.firedDate = firedDate;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public Date getEmploymentDate() {
		return employmentDate;
	}

	public void setEmploymentDate(Date employmentDate) {
		this.employmentDate = employmentDate;
	}

	public Date getFiredDate() {
		return firedDate;
	}

	public void setFiredDate(Date firedDate) {
		this.firedDate = firedDate;
	}

}
