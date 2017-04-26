package com.camunda.demo.dmn.intro;

import org.apache.ibatis.logging.LogFactory;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.camunda.bpm.engine.variable.VariableMap;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.engine.test.Deployment;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.core.NestedExceptionUtils;

import static org.camunda.bpm.engine.test.assertions.ProcessEngineTests.*;
import static org.junit.Assert.*;

import org.camunda.bpm.consulting.process_test_coverage.ProcessTestCoverage;
import org.camunda.bpm.dmn.engine.DmnDecisionTableResult;

/**
 * Test case starting an in-memory database-backed Process Engine.
 */
@Deployment(resources = "Risikopruefung.dmn")
public class DecisionEngineTest {

  @Rule
  public ProcessEngineRule rule = new ProcessEngineRule();

  @Test
  public void testHochwertfahrzeug() {    
    VariableMap variables = Variables //
        .putValue("age", 40l)
        .putValue("carManufacturer", "BMW")
        .putValue("carType", "X3");
    
	  DmnDecisionTableResult result = processEngine().getDecisionService().evaluateDecisionTableByKey( //
	      "risk", //
	      variables);
	  
	  assertEquals(1, result.getResultList().size());
	  assertEquals("Hochwertfahrzeug", result.getSingleResult().get("risk"));
    assertEquals("gelb", result.getSingleResult().get("riskAssessment"));
  }

  @After
  public void calculateCoverageForAllTests() throws Exception {
    ProcessTestCoverage.calculate(rule.getProcessEngine());
  }  

}
