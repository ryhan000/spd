package app.entity;

import static org.junit.Assert.assertThat;

import static org.hamcrest.CoreMatchers.is;
import org.junit.Test;

public class AddressTest {
	
	@Test
	public void testAddressPresentation() {
		Address address = new Address();
		address.setCountry("Україна");
		address.setRegion("Львівська область");
		address.setCity("м. Львів");
		address.setStreet("вул. Ст. Бандери");
		address.setBuilding("123");
		address.setFlat("23");
		address.setZip("12334");
		assertThat(address.getPresentation(), is("Україна, 12334, Львівська область, м. Львів, вул. Ст. Бандери, буд. 123, кв. 23"));
	}
	
	@Test
	public void testAddressPresentationWithoutRegion() {
		Address address = new Address();
		address.setCountry("Україна");
		address.setCity("м. Київ");
		address.setStreet("вул. Ст. Бандери");
		address.setBuilding("123");
		address.setFlat("23");
		address.setZip("12334");
		assertThat(address.getPresentation(), is("Україна, 12334, м. Київ, вул. Ст. Бандери, буд. 123, кв. 23"));
	}
	
	@Test
	public void testAddressPresentationWithoutRegionStreetBuildingFlat() {
		Address address = new Address();
		address.setCountry("Україна");
		address.setCity("м. Київ");
		address.setStreet("вул. Ст. Бандери, буд. 123, кв. 23");
		address.setZip("12334");
		assertThat(address.getPresentation(), is("Україна, 12334, м. Київ, вул. Ст. Бандери, буд. 123, кв. 23"));
	}
	
	@Test
	public void testAddressPresentationWithoutZip() {
		Address address = new Address();
		address.setCountry("Україна");
		address.setCity("м. Київ");
		address.setStreet("вул. Ст. Бандери, буд. 123, кв. 23");
//		address.setZip("");
		assertThat(address.getPresentation(), is("Україна, м. Київ, вул. Ст. Бандери, буд. 123, кв. 23"));
	}
	
}
