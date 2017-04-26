package com.camunda.demo.dmn.intro;

import org.camunda.bpm.application.PostDeploy;
import org.camunda.bpm.application.ProcessApplication;
import org.camunda.bpm.application.impl.ServletProcessApplication;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.repository.DeploymentBuilder;
import org.camunda.bpm.model.bpmn.Bpmn;

/**
 * Process Application exposing this application's resources the process engine. 
 */
@ProcessApplication
public class CamundaBpmProcessApplication extends ServletProcessApplication {

  private static final String PROCESS_DEFINITION_KEY = "wjax-dmn";

  /**
   * In a @PostDeploy Hook you can interact with the process engine and access 
   * the processes the application has deployed. 
   */
  @PostDeploy
  public void onDeploymentFinished(ProcessEngine processEngine) {

    // start an initial process instance
//    Map<String, Object> variables = new HashMap<String, Object>();
//    variables.put("name", "John");
//    
//    processEngine.getRuntimeService().startProcessInstanceByKey(PROCESS_DEFINITION_KEY, variables);
  }

  public void createDeployment(String processArchiveName, DeploymentBuilder deploymentBuilder) {
    
    deploymentBuilder
        .addModelInstance("dmn-sample.bpmn", 
           Bpmn.createExecutableProcess("dmn-sample")
            .startEvent()
                .camundaFormField().camundaId("age").camundaType("long").camundaLabel("Age").camundaFormFieldDone()
                .camundaFormField().camundaId("carManufacturer").camundaType("string").camundaLabel("Car manufacturer").camundaFormFieldDone()
                .camundaFormField().camundaId("carType").camundaType("string").camundaLabel("Car type").camundaFormFieldDone()
            .businessRuleTask().name("Determine risk")
                .camundaDecisionRef("riskAssessment")
                .camundaResultVariable("risk").camundaMapDecisionResult("resultList")        
            .serviceTask().name("Print risk")
                .camundaClass(SysoutRiskAdapter.class.getName())          
            .endEvent()
            .done()
        );
  }
  
}
