package de.catcode.wicket.quarkus.cdi.showcase;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import org.apache.wicket.protocol.http.WicketFilter;

@WebFilter(value = "/showcase/*",
	initParams = {
		@WebInitParam(name = "applicationClassName", value = "de.catcode.wicket.quarkus.cdi.showcase.ShowcaseApplication")
	}
)
public class ShowcaseAppFilter extends WicketFilter {

}
