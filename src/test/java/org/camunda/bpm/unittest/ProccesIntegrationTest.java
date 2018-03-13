package org.camunda.bpm.unittest;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.runtime.JobQuery;
import org.camunda.bpm.engine.task.Task;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.junit.Rule;
import org.junit.Test;
public class ProccesIntegrationTest {
 
	 @Rule
	  public ProcessEngineRule engineRule = new ProcessEngineRule();

	  @Deployment(resources = "f.bpmn")
	  @Test
	  public void test() throws Exception {
	    RuntimeService runtimeService = engineRule.getRuntimeService();
	    TaskService taskService = engineRule.getTaskService();

	    runtimeService.startProcessInstanceByKey("pizzaOrderProcess");

	    waitForAsyncJobs();

	    List<Task> tasks = taskService.createTaskQuery().taskName("make the pizza").list();
	    assertThat(tasks).isNotEmpty();

	    for (Task task : tasks) {
	      taskService.complete(task.getId());
	    }

	    tasks = taskService.createTaskQuery().taskName("deliver the pizza").list();
	    assertThat(tasks).isNotEmpty();

	    for (Task task : tasks) {
	      taskService.complete(task.getId());
	    }

	    waitForAsyncJobs();

	    assertThat(runtimeService.createProcessInstanceQuery().list()).isEmpty();
	  }

	  private void waitForAsyncJobs() throws InterruptedException {
	    JobQuery jobQuery = engineRule.getManagementService().createJobQuery().executable();

	    while(jobQuery.count() > 0) {
	      Thread.sleep(500);
	    }
	  }

}