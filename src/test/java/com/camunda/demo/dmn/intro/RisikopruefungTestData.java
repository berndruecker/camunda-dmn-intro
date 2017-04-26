package com.camunda.demo.dmn.intro;

import java.util.Arrays;
import java.util.List;

public class RisikopruefungTestData {

  private int alter;
  private String hersteller;
  private String typ;

  private List<String> expectedRisiko;
  private List<String> expectedRisikoBewertung;
  
  public int getAlter() {
    return alter;
  }
  public void setAlter(int alter) {
    this.alter = alter;
  }
  public String getHersteller() {
    return hersteller;
  }
  public void setHersteller(String hersteller) {
    this.hersteller = hersteller;
  }
  public String getTyp() {
    return typ;
  }
  public void setTyp(String typ) {
    this.typ = typ;
  }
  public List<String> getExpectedRisiko() {
    return expectedRisiko;
  }
  public void setExpectedRisiko(String expectedRisiko) {  
    if (expectedRisiko!=null && expectedRisiko.length()>0) {
      this.expectedRisiko = Arrays.asList(expectedRisiko.split(","));
    }
  }
  public List<String> getExpectedRisikoBewertung() {
    return expectedRisikoBewertung;
  }
  public void setExpectedRisikoBewertung(String expectedRisikoBewertung) {
    if (expectedRisikoBewertung!=null && expectedRisikoBewertung.length()>0) {
      this.expectedRisikoBewertung = Arrays.asList(expectedRisikoBewertung.split(","));
    }
  }
  @Override
  public String toString() {
    return "[alter=" + alter + ", hersteller=" + hersteller + ", typ=" + typ + ", expectedRisiko=" + expectedRisiko
        + ", expectedRisikoBewertung=" + expectedRisikoBewertung + "]";
  }
 

}
