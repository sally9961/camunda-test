package ge.psda.camunda_delegates;

import java.io.PrintWriter;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.impl.util.json.JSONObject;

public class SaveResponseToFile_Q implements JavaDelegate {

	public void execute(DelegateExecution execution) throws Exception {
		Object jsonObjectData1 = new JSONObject();

		jsonObjectData1 = execution.getVariable("restResponse");
		Object machineName = execution.getVariable("q_machines");

		String path = "D:\\BPML\\digital_stamp\\"+machineName+"\\stampData.json";
		try {
			PrintWriter pw = new PrintWriter(path, "UTF-8");
			pw.write(jsonObjectData1.toString());
			pw.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

}
