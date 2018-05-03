package app.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "specification_job")
public class Job extends UrlEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name = "specification_id")
	@JsonBackReference
	private Specification specification;
	
	@Column(name = "job_name", length = 900)
	private String jobName;
	
	@Column(name = "configuring_hours")
	private Integer configuringHours;
	
	@Column(name = "programming_hours")
	private Integer programmingHours;
	
	@Column(name = "architecting_hours")
	private Integer architectingHours;

	public Job() {
	}

	public Job(Specification specification, String jobName, Integer configuringHours, Integer programmingHours,
			Integer architectingHours) {
		this.specification = specification;
		this.jobName = jobName;
		this.configuringHours = configuringHours;
		this.programmingHours = programmingHours;
		this.architectingHours = architectingHours;
	}

	public Specification getSpecification() {
		return specification;
	}

	public void setSpecification(Specification specification) {
		this.specification = specification;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
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
	
}
