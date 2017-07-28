package backend;

import org.apache.log4j.Logger;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

/**
 * Created by Thomas on 28-7-2017.
 */
public class LoggerProducer {

	@Produces
	public Logger produceLogger(InjectionPoint injectionPoint) {
		final Class ip = injectionPoint.getMember().getDeclaringClass();
		return Logger.getLogger(ip);
	}

}
