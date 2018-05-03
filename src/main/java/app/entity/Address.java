package app.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "address")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Address extends UrlEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "country", length = 20)
	private String country;

	@Column(name = "region", length = 75)
	private String region;

	@Column(name = "city", length = 50)
	private String city;

	@Column(name = "street", length = 50)
	private String street;

	@Column(name = "building", length = 20)
	private String building;

	@Column(name = "flat", length = 20)
	private String flat;
	
	@Column(name = "zip", length = 7)
	private String zip;

	public Address() {
	}

	public Address(String country, String region, String city, String street, String building, String flat,
			String zip) {
		super();
		this.country = country;
		this.region = region;
		this.city = city;
		this.street = street;
		this.building = building;
		this.flat = flat;
		this.zip = zip;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getBuilding() {
		return building;
	}

	public void setBuilding(String building) {
		this.building = building;
	}

	public String getFlat() {
		return flat;
	}

	public void setFlat(String flat) {
		this.flat = flat;
	}
	
	@JsonIgnore
	public String getPresentation() {
		StringBuilder addressBuilder = new StringBuilder();
		addressBuilder.append(this.country);
		addressBuilder.append(this.zip == "" || this.zip == null ? "" : ", " + this.zip);
		addressBuilder.append(this.region == "" || this.region == null ? "" : ", " + this.region);
		addressBuilder.append(this.city == "" || this.city == null ? "" : ", " + this.city);
		addressBuilder.append(this.street == "" || this.street == null ? "" : ", " + this.street);
		addressBuilder.append(this.building == "" || this.building == null ? "" : ", буд. " + this.building);
		addressBuilder.append(this.flat == "" || this.flat == null ? "" : ", кв. " + this.flat);
		return addressBuilder.toString();
	}

	@JsonIgnore
	@Override
	public String toString() {
		StringBuilder addressBuilder = new StringBuilder();
		addressBuilder.append(this.country);
		addressBuilder.append(this.zip == "" || this.zip == null ? "" : ", " + this.zip);
		addressBuilder.append(this.region == "" || this.region == null ? "" : ", " + this.region);
		addressBuilder.append(this.city == "" || this.city == null ? "" : ", " + this.city);
		addressBuilder.append(this.street == "" || this.street == null ? "" : ", " + this.street);
		addressBuilder.append(this.building == "" || this.building == null ? "" : ", буд. " + this.building);
		addressBuilder.append(this.flat == "" || this.flat == null ? "" : ", кв. " + this.flat);
		return addressBuilder.toString();
	}
	
}
