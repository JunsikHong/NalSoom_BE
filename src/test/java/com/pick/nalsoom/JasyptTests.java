package com.pick.nalsoom;

import org.assertj.core.api.Assertions;
import org.jasypt.encryption.StringEncryptor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.pick.nalsoom.Config.JasyptConfig;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = JasyptConfig.class)
public class JasyptTests {
    
    @Autowired
	private StringEncryptor jasyptEncryptor;

	@Test
	void custom_jasypt_test() {
		String encrypted = jasyptEncryptor.encrypt("test");
		System.out.println("encrypted: " + encrypted);

		String decrypted = jasyptEncryptor.decrypt(encrypted);
		System.out.println("decrypted: " + decrypted);
		Assertions.assertThat(decrypted).isEqualTo("test");
	}

}
