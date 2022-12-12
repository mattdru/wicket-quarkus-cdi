package de.catcode.wicket.quarkus.cdi.showcase.page;

import javax.inject.Inject;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.Model;
import de.catcode.wicket.quarkus.cdi.showcase.business.ShowcaseBusinessService;

public class HomePage extends WebPage {

	@Inject
	ShowcaseBusinessService showcaseBusinessService;

	@Override
	protected void onInitialize() {
		super.onInitialize();

		add(new Label("testLabel", Model.of(showcaseBusinessService.getRandomNumber())));
	}
}
