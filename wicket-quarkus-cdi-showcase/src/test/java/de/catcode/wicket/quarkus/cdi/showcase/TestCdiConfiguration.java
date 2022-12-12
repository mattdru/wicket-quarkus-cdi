package de.catcode.wicket.quarkus.cdi.showcase;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.inject.Inject;
import org.apache.wicket.Application;
import org.apache.wicket.Component;
import org.apache.wicket.application.IComponentInstantiationListener;
import de.catcode.wicket.quarkus.cdi.CdiConfiguration;
import de.catcode.wicket.quarkus.cdi.ComponentInjector;

public class TestCdiConfiguration extends CdiConfiguration {

	private final Map<Class<?>, Object> mockRegistry;

	public TestCdiConfiguration(final Map<Class<?>, Object> mockRegistry) {
		this.mockRegistry = mockRegistry;
	}

	@Override
	public void configure(final Application application) {
		super.configure(application);
		final List<IComponentInstantiationListener> toRemove = new ArrayList<>();
		application.getComponentInstantiationListeners().forEach(l -> {
			if (l.getClass().getName().equals(ComponentInjector.class.getName())) {
				toRemove.add(l);
			}
		});
		toRemove.forEach(r -> application.getComponentInstantiationListeners().remove(r));
		application.getComponentInstantiationListeners().add(new TestComponentInjector(mockRegistry));
	}

	public static class TestComponentInjector implements IComponentInstantiationListener {

		private final Map<Class<?>, Object> mockRegistry;

		public TestComponentInjector(final Map<Class<?>, Object> mockRegistry) {
			this.mockRegistry = mockRegistry;
		}

		@Override
		public void onInstantiation(final Component component) {
			final Class<? extends Component> clazz = component.getClass();
			final List<Field> injectableFields = Arrays.stream(clazz.getDeclaredFields())
				.filter(field -> field.isAnnotationPresent(Inject.class))
				.toList();
			injectableFields.forEach(field -> {
				field.setAccessible(true);
				final Object value = mockRegistry.get(field.getType());
				if (Objects.isNull(value)) {
					throw new IllegalStateException("No Mock present for desired class: " + field.getType().getName());
				}
				try {
					field.set(component, value);
				} catch (IllegalAccessException e) {
					throw new RuntimeException("Unhandled exception occurred.", e);
				}
			});
		}
	}
}
