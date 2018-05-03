//package app.entity;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import app.repository.CustomUserRepository;
//
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations="classpath:application-context.xml")
//public class CustomUserTest {
//	
//	@Autowired(required = true)
//	private CustomUserRepository userRepository;
//
//	@Test
//	public void insertUser() {
//		
//		CustomUser user = new CustomUser();
//		user.setFirstName("Apollon");
//		user.setLastName("Saifullin");
//		user.setUsername("apollo-s");
//		user.setPassword("test");
//
//		userRepository.save(user);
//	}
//}
