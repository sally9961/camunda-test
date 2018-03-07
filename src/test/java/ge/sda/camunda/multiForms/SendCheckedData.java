package ge.sda.camunda.multiForms;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.impl.util.json.JSONObject;




public class SendCheckedData implements JavaDelegate{
	Object jsonObject1=new JSONObject();
	public void execute(DelegateExecution arg0) throws Exception {
		jsonObject1 = arg0.getVariable("selectedDocuments"); 
        arg0.setVariable("selectedDocuments", jsonObject1);
	}
 

}
