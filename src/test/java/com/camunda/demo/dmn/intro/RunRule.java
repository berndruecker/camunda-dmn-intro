package com.camunda.demo.dmn.intro;

import java.util.List;

import org.camunda.bpm.dmn.engine.DmnDecision;
import org.camunda.bpm.dmn.engine.DmnDecisionTableResult;
import org.camunda.bpm.dmn.engine.DmnEngine;
import org.camunda.bpm.dmn.engine.impl.DefaultDmnEngineConfiguration;
import org.camunda.bpm.engine.variable.Variables;

public class RunRule {
  
  public static void main(String[] args) {
    
    DmnEngine engine = new DefaultDmnEngineConfiguration().buildEngine();
    List<DmnDecision> decisions = engine.parseDecisions(
        RunRule.class.getResourceAsStream("/RiskAssessment.dmn"));
    DmnDecision decision = decisions.get(0);
    
    DmnDecisionTableResult result = engine.evaluateDecisionTable(
        decision, 
        Variables
          .putValue("carManufacturer", "BMW")
          .putValue("carType", "X3")
          .putValue("age", 18l));
    
    System.out.println(result);
  }

}
