package de.catcode.wicket.quarkus.cdi.showcase;

import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;
import de.catcode.wicket.quarkus.cdi.CdiConfiguration;
import de.catcode.wicket.quarkus.cdi.showcase.page.HomePage;

public class ShowcaseApplication extends WebApplication {

	@Override
	public Class<? extends Page> getHomePage() {
		return HomePage.class;
	}

	@Override
	protected void init() {
		super.init();
		new CdiConfiguration().configure(this);
	}
}
