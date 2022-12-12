package de.catcode.wicket.quarkus.cdi.showcase;

import java.util.HashMap;
import java.util.Map;
import org.apache.wicket.Page;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;

public class WicketTestApplication extends WebApplication {

	private Map<Class<?>, Object> mockRegistry = new HashMap<>();

	@Override
	public Class<? extends Page> getHomePage() {
		return TestHomePage.class;
	}

	@Override
	protected void init() {
		super.init();
		new TestCdiConfiguration(mockRegistry).configure(this);
	}

	public void registerMock(final Class<?> clazz, Object mockInstance) {
		mockRegistry.put(clazz, mockInstance);
	}

	public Map<Class<?>, Object> getMockRegistry() {
		return mockRegistry;
	}

	public static class TestHomePage extends WebPage {

	}
}
