package frontend;

import io.fabric8.annotations.Factory;
import io.fabric8.annotations.ServiceName;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.camel.component.ActiveMQComponent;

/**
 * Created by Thomas on 31-7-2017.
 */
public class ActiveMQComponentFactory {

	@Factory
	@ServiceName
	public ActiveMQComponent create(@ServiceName ActiveMQConnectionFactory connectionFactory) {
		ActiveMQComponent component = new ActiveMQComponent();
		component.setConnectionFactory(connectionFactory);
		return component;
	}

}
