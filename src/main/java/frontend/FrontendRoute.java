package frontend;

import io.fabric8.annotations.Alias;
import io.fabric8.annotations.ServiceName;
import org.apache.activemq.camel.component.ActiveMQComponent;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

import javax.inject.Inject;

/**
 * Created by Thomas on 28-7-2017.
 */
public class FrontendRoute extends RouteBuilder {

	private final String putMessageUri = "direct:putMessage";

	private final String getMessageUri = "direct:putMessage";

	private final String activeMQUri = "jms:queue:messages";

	@Inject
	@Alias("jms")
	@ServiceName("activemq-broker-service")
	private ActiveMQComponent activeMQComponent;

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
		  	.to(getMessageUri)
		  	.endRest()
		  .put("questions/{id}/{content}")
		  	.route()
		  	.to(putMessageUri)
		  	.endRest()
	  	;

		configureGetEndpoint();
		configurePutEndpoint();
	}

	private void configureGetEndpoint() {
		from(getMessageUri)
		  .setBody(simple("${header.id}"))
		  .process(getProcessor);
	}

	private void configurePutEndpoint() {
		from(putMessageUri)
		  .setBody(simple("${header.id}=${header.content}"))
		  .to(activeMQUri);
	}

}
