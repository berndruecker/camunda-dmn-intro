package com.camunda.demo.dmn.intro;

import java.util.Map;

public class IfElseMess {

  public int decideForSomething(Map<String, Object> parameters) {
    if (parameters.get("something") == "this") {
      if ((int) parameters.get("a") > 5) {
        return 1;
      } else if ((int) parameters.get("a") == 5) {
        return 2;
      } else if ((int) parameters.get("a") < 5) {
        return 3;
      }
    } else if (parameters.get("something") == "that") {
      if ((int) parameters.get("b") > 7) {
        return 1;
      } else if ((int) parameters.get("b") < 7) {
        return 4;        
      }
    } else {
      return 5;      
    }
    return 0;
  }
}
