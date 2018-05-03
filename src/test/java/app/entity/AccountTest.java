package app.entity;

import static org.junit.Assert.assertThat;

import static org.hamcrest.CoreMatchers.is;
import org.junit.Test;

public class AccountTest {
	
	@Test
	public void testAccountPresentation() {
		Account account = new Account();
		account.setAccountNumber("26006901362973");
		account.setBankName("АТ ОТП БАНК (м. Київ)");
		account.setMfo("300658");
		
		assertThat(account.getPresentation(), is("26006901362973, в АТ ОТП БАНК (м. Київ), МФО 300658"));
	}
	
}
