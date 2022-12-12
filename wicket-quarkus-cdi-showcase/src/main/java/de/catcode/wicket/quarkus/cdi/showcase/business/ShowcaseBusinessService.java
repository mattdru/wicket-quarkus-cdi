package de.catcode.wicket.quarkus.cdi.showcase.business;

import java.util.Random;
import javax.enterprise.context.RequestScoped;

@RequestScoped
public class ShowcaseBusinessService {

	public Integer getRandomNumber() {
		final Random random = new Random();
		return random.nextInt();
	}
}
