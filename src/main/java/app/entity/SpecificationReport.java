package app.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class SpecificationReport extends BaseReport implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer specificationNumber;
	private Date specificationStartDate;
	private Date specificationFinalDate;
	private Double specificationSum;
	private Double configuringRate;
	private Double programmingRate;
	private Double architectingRate;
	private Integer configuringHours;
	private Integer programmingHours;
	private Integer architectingHours;
	private List<Job> jobs;
	private List<SpecificationPayment> payments;
	private Integer quantityOfPayments;

	public SpecificationReport() {
	}

	public Integer getSpecificationNumber() {
		return specificationNumber;
	}

	public void setSpecificationNumber(Integer specificationNumber) {
		this.specificationNumber = specificationNumber;
	}

	public Date getSpecificationStartDate() {
		return specificationStartDate;
	}

	public void setSpecificationStartDate(Date specificationStartDate) {
		this.specificationStartDate = specificationStartDate;
	}

	public Date getSpecificationFinalDate() {
		return specificationFinalDate;
	}

	public void setSpecificationFinalDate(Date specificationFinalDate) {
		this.specificationFinalDate = specificationFinalDate;
	}

	public Double getSpecificationSum() {
		return specificationSum;
	}

	public void setSpecificationSum(Double specificationSum) {
		this.specificationSum = specificationSum;
	}

	public Double getConfiguringRate() {
		return configuringRate;
	}

	public void setConfiguringRate(Double configuringRate) {
		this.configuringRate = configuringRate;
	}

	public Double getProgrammingRate() {
		return programmingRate;
	}

	public void setProgrammingRate(Double programmingRate) {
		this.programmingRate = programmingRate;
	}

	public Double getArchitectingRate() {
		return architectingRate;
	}

	public void setArchitectingRate(Double architectingRate) {
		this.architectingRate = architectingRate;
	}

	public Integer getConfiguringHours() {
		return configuringHours;
	}

	public void setConfiguringHours(Integer configuringHours) {
		this.configuringHours = configuringHours;
	}

	public Integer getProgrammingHours() {
		return programmingHours;
	}

	public void setProgrammingHours(Integer programmingHours) {
		this.programmingHours = programmingHours;
	}

	public Integer getArchitectingHours() {
		return architectingHours;
	}

	public void setArchitectingHours(Integer architectingHours) {
		this.architectingHours = architectingHours;
	}

	public List<Job> getJobs() {
		return jobs;
	}

	public void setJobs(List<Job> jobs) {
		this.jobs = jobs;
	}

	public List<SpecificationPayment> getPayments() {
		return payments;
	}

	public void setPayments(List<SpecificationPayment> payments) {
		this.payments = payments;
	}

	public Integer getQuantityOfPayments() {
		return quantityOfPayments;
	}

	public void setQuantityOfPayments(Integer quantityOfPayments) {
		this.quantityOfPayments = quantityOfPayments;
	}

}
