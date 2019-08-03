package com.uniritter.upphotos;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UpphotosApplicationTests {

	@Test
	public void contextLoads()
	{
		String expectedMessage = "Upphotos Application";
		String message = String.format("%s %s", "Upphotos", "Application");

		assertTrue(message.equals(expectedMessage));
	}

}
