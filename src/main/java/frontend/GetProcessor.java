package frontend;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

/**
 * Created by Thomas on 28-7-2017.
 */
@Get
public class GetProcessor implements Processor {
	public void process(Exchange exchange) throws Exception {
		exchange.getOut().setBody("Hello");
		exchange.getOut().setHeader(Exchange.HTTP_RESPONSE_CODE, 200);
	}
}
