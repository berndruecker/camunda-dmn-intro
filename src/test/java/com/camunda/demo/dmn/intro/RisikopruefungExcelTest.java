package com.camunda.demo.dmn.intro;

import static org.junit.Assert.assertEquals;

import java.io.InputStream;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.camunda.bpm.dmn.engine.DmnDecision;
import org.camunda.bpm.dmn.engine.DmnDecisionTableResult;
import org.camunda.bpm.dmn.engine.DmnEngine;
import org.camunda.bpm.dmn.engine.test.DmnEngineRule;
import org.camunda.bpm.engine.variable.VariableMap;
import org.camunda.bpm.engine.variable.Variables;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(value = Parameterized.class)
public class RisikopruefungExcelTest {

  @Rule
  public DmnEngineRule dmnEngineRule = new DmnEngineRule();

  public DmnEngine dmnEngine;
  public DmnDecision decision;

  private RisikopruefungTestData testData;

  public RisikopruefungExcelTest(RisikopruefungTestData testData) {
    this.testData = testData;
  }

  @Before
  public void parseDecision() {
    InputStream inputStream = this.getClass().getResourceAsStream("/Risikopruefung.dmn");

    dmnEngine = dmnEngineRule.getDmnEngine();
    decision = dmnEngine.parseDecisions(inputStream).get(0);
  }

  @Parameters(name = "#{index}: {0}")
  public static Collection<Object[]> data() {
    return ExcelUtils.getTestData();
  }

  @Test
  public void testRiskAssessment() {
    VariableMap variables = Variables //
        .putValue("age", this.testData.getAlter())
        .putValue("carManufacturer", this.testData.getHersteller())
        .putValue("carType", this.testData.getTyp());

    DmnDecisionTableResult result = dmnEngine.evaluateDecisionTable(decision, variables);

    if (testData.getExpectedRisiko() == null || testData.getExpectedRisiko().size() == 0) { // there
                                                                                            // should
                                                                                            // be
                                                                                            // no
                                                                                            // risk
      assertEquals(0, result.getResultList().size());
    } else if (testData.getExpectedRisiko().size() == 1) {
      assertEquals(testData.getExpectedRisiko().get(0), result.getSingleResult().get("risk"));
      assertEquals(testData.getExpectedRisikoBewertung().get(0), result.getSingleResult().get("riskAssessment"));
    } else if (testData.getExpectedRisiko().size() > 1) {
      List<Map<String, Object>> resultList = result.getResultList();
      // for (Map<String, Object> singleResult : resultList) {
      // assertThat(testData.getExpectedRisiko(),
      // hasItem((String)singleResult.get("risiko")));
      // assertThat(testData.getExpectedRisiko(),
      // hasItem(singleResult.get("risiko")));
      // assertEquals(testData.getExpectedRisikoBewertung().get(0),
      // result.getSingleResult().get("risikobewertung"));
      // }
    }
  }

}
