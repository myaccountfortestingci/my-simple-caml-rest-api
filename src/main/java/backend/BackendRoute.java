package backend;

import io.fabric8.annotations.Alias;
import io.fabric8.annotations.ServiceName;
import org.apache.activemq.camel.component.ActiveMQComponent;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

import javax.inject.Inject;

/**
 * Created by Thomas on 28-7-2017.
 */
public class BackendRoute extends RouteBuilder {

	@Inject
	@Alias("jms")
	@ServiceName("activemq-broker-service")
	private ActiveMQComponent activeMQComponent;

	private final String activeMQUri = "jms:queue:messages";

	@Inject
	@Message
	private Processor processor;

	public void configure() throws Exception {
		from(activeMQUri)
		  .process(processor);
	}
}
