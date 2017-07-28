package backend;

import org.apache.camel.cdi.Main;

/**
 * Created by Thomas on 28-7-2017.
 */
public class Application {

	public static void main(String[] args) throws Exception {
		Main main = new Main();
		main.run(args);
		main.addRouteBuilder(new BackendRoute());
	}

}
