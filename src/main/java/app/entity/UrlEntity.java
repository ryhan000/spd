package app.entity;

import javax.persistence.MappedSuperclass;

import com.fasterxml.jackson.annotation.JsonIgnore;

@MappedSuperclass
public class UrlEntity extends BaseEntity {

	@JsonIgnore
	public String getUrl() {
		return getClass().getSimpleName().toLowerCase() + "?id=" + getId();
	}

}
