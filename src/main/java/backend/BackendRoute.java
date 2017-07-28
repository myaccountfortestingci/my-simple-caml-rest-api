package backend;

import org.apache.camel.Endpoint;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.cdi.Uri;

import javax.inject.Inject;

/**
 * Created by Thomas on 28-7-2017.
 */
public class BackendRoute extends RouteBuilder {

	@Inject
	@Uri("activemq:queue:messages")
	private Endpoint activeMQEndpoint;

	@Inject
	@Message
	private Processor processor;

	public void configure() throws Exception {
		from(activeMQEndpoint)
		  .process(processor);
	}
}
