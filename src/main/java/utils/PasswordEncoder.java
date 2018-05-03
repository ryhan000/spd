package utils;

import org.springframework.security.crypto.password.StandardPasswordEncoder;

public class PasswordEncoder {

	public static String getEncodedPassword(String rawPassword) {
		StandardPasswordEncoder encoder = new StandardPasswordEncoder();
		String encodedPassword = encoder.encode(rawPassword);
		return encodedPassword;
	}

}
