package de.catcode.wicket.quarkus.cdi;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Set;
import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.enterprise.inject.spi.InjectionTarget;
import io.quarkus.arc.Arc;
import io.quarkus.arc.ArcContainer;

public class WicketQuarkusInjectionTarget<T> implements InjectionTarget<T> {

	private final List<Field> injectableFields;

	public WicketQuarkusInjectionTarget(final List<Field> injectableFields) {
		this.injectableFields = injectableFields;
	}

	@Override
	public T produce(final CreationalContext<T> ctx) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void dispose(final T instance) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Set<InjectionPoint> getInjectionPoints() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void inject(final T instance, final CreationalContext<T> ctx) {
		final ArcContainer container = Arc.container();
		injectableFields.forEach(injectableField -> {
			injectableField.setAccessible(true);
			try {
				// ggf auch darüber io.quarkus.arc.impl.BeanManagerImpl.getInjectableReference
				final Object fieldValue = container.instance(injectableField.getType()).get();
//				System.out.println("Is Proxy: " + fieldValue.getClass().getSimpleName().contains("_ClientProxy") + " " + fieldValue.getClass().getSimpleName());
				injectableField.set(instance, fieldValue);
			} catch (IllegalAccessException e) {
				throw new RuntimeException("Unhandled exception occurred.", e);
			}
		});
	}

	@Override
	public void postConstruct(final T instance) {

	}

	@Override
	public void preDestroy(final T instance) {

	}
}
