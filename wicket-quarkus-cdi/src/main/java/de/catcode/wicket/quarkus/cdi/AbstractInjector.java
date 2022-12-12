package de.catcode.wicket.quarkus.cdi;

public abstract class AbstractInjector {

	public AbstractInjector() {
	}

//	protected <T> void postConstrcut(T instance) {
//		NonContextual.of(instance).postContruct(instance);
//	}

	protected <T> void inject(T instance) {
		NonContextual.of(instance).inject(instance);
	}
}
