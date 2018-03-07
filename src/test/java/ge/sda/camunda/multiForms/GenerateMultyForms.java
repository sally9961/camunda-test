package ge.sda.camunda.multiForms;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.impl.util.json.JSONObject;

public class GenerateMultyForms implements JavaDelegate{
	public void execute(DelegateExecution arg0) throws Exception {	
			Object jsonObject1 = new JSONObject();

			jsonObject1 = arg0.getVariable("restResponse");
		   
	            arg0.setVariable("jsonData", jsonObject1);
	           
		}
}
