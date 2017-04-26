package com.camunda.demo.dmn.intro;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class SysoutRiskAdapter implements JavaDelegate {

  @Override
  public void execute(DelegateExecution ctx) throws Exception {
     System.out.println("Risk: " + ctx.getVariable("risk"));
  }

}
