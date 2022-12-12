package de.catcode.wicket.quarkus.cdi.showcase;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import de.catcode.wicket.quarkus.cdi.showcase.business.ShowcaseBusinessService;
import de.catcode.wicket.quarkus.cdi.showcase.page.HomePage;

public class HomePageTest {

	private WicketTestApplication testApplication;
	private WicketTester tester;

	private ShowcaseBusinessService showcaseBusinessServiceMock;

	@BeforeEach
	public void before() {

		testApplication = new WicketTestApplication();

		showcaseBusinessServiceMock = Mockito.mock(ShowcaseBusinessService.class);
		testApplication.registerMock(ShowcaseBusinessService.class, showcaseBusinessServiceMock);

		tester = new WicketTester(testApplication) {
			@Override
			protected String createPageMarkup(final String componentId) {
				// Overwrite, denn sonst legt Wicket in der Page die Component auf einen Span.
				// Wir brauchen aber ein Div.
				return "<html><head></head><body><div wicket:id='" + componentId +
					"'></div></body></html>";
			}
		};
	}

	@Test
	public void testRenderPage() {

		Mockito.when(showcaseBusinessServiceMock.getRandomNumber()).thenReturn(1234);

		final HomePage page = tester.startPage(HomePage.class);
		tester.assertRenderedPage(HomePage.class);
//		System.out.println(tester.getLastResponseAsString());
		tester.assertComponent("testLabel", Label.class);
		Assertions.assertEquals(page.get("testLabel").getDefaultModelObjectAsString(), "1234");
	}
}
