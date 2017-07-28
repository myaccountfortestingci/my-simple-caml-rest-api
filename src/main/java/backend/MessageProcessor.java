package backend;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by Thomas on 28-7-2017.
 */
@Message
public class MessageProcessor implements Processor {

	@Inject
	private Logger logger;

	public void process(Exchange exchange) throws Exception {
		final String message = (String) exchange.getIn().getBody();

		logger.info(message);

		exchange.getOut().setHeader(Exchange.HTTP_RESPONSE_CODE, 200);
		exchange.getOut().setBody("Message received");
	}
}
