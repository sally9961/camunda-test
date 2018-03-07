package ge.psda.camunda_delegates;

import java.io.PrintWriter;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.impl.util.json.JSONObject;

public class SaveResponseToFile_B implements JavaDelegate {

	public void execute(DelegateExecution execution) throws Exception {
		Object jsonObjectData = new JSONObject();

		jsonObjectData = execution.getVariable("restResponse");
		Object machineName = execution.getVariable("b_machines");

		String path = "D:\\BPML\\digital_stamp\\"+machineName+"\\stampData.json";
		try {
			PrintWriter pw = new PrintWriter(path, "UTF-8");
			pw.write(jsonObjectData.toString());
			pw.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

}
