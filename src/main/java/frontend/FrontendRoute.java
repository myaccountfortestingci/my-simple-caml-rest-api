package frontend;

import org.apache.camel.Endpoint;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.cdi.ContextName;
import org.apache.camel.cdi.Uri;

import javax.inject.Inject;

/**
 * Created by Thomas on 28-7-2017.
 */
public class FrontendRoute extends RouteBuilder {

	@Inject
	@Uri("direct:putMessage")
	private Endpoint putEndpoint;

	@Inject
	@Uri("direct:getMessage")
	private Endpoint getEndpoint;

	@Inject
	@Uri("activemq:queue:messages")
	private Endpoint activeMQEndpoint;

	@Inject
	@Get
	private Processor getProcessor;

	public void configure() throws Exception {
		restConfiguration()
		  .component("jetty")
		  .host("0.0.0.0")
		  .port(4444)
	  	;

		rest("/")
		  .get("questions/{id}")
		  	.route()
		  	.to(getEndpoint)
		  	.endRest()
		  .put("questions/{id}/{content}")
		  	.route()
		  	.to(putEndpoint)
		  	.endRest()
	  	;

		configureGetEndpoint();
		configurePutEndpoint();
	}

	private void configureGetEndpoint() {
		from(getEndpoint)
		  .setBody(simple("${header.id}"))
		  .process(getProcessor);
	}

	private void configurePutEndpoint() {
		from(putEndpoint)
		  .setBody(simple("${header.id}=${header.content}"))
		  .to(activeMQEndpoint);
	}

}
