package ge.psda.camunda_test;
import org.camunda.bpm.application.ProcessApplication;
import org.camunda.bpm.application.impl.ServletProcessApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
 

@ProcessApplication
public class DemoProcessApplication extends ServletProcessApplication {
	public Logger slf4jLogger = (Logger) LoggerFactory.getLogger(DemoProcessApplication.class);
	
}
