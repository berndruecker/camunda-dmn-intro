package com.camunda.demo.dmn.intro;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ExcelUtils {

  private static Logger logger = LoggerFactory.getLogger(ExcelUtils.class);

  public static List<Object[]> getTestData() {
    InputStream inputStream = ExcelUtils.class.getResourceAsStream("/Testdaten.xlsx");

    XSSFSheet sheet;

    try (XSSFWorkbook workbook = new XSSFWorkbook(inputStream)) {
      // Get first sheet from the workbook
      sheet = workbook.getSheetAt(0);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    // Get iterator to all the rows in current sheet
    Iterator<Row> rowIterator = sheet.iterator();

    if (rowIterator.hasNext()) {
      rowIterator.next();
    }

    List<Object[]> testDataList = new ArrayList<>();

    while (rowIterator.hasNext()) {
      Row row = rowIterator.next();

      if (row.getLastCellNum() != 6) {
        logger.error("Unable to read row into TestData object. Wrong number of cells [" + row.getLastCellNum() + "]");
      } else {
        RisikopruefungTestData testData = new RisikopruefungTestData();

        testData.setAlter((int) row.getCell(1).getNumericCellValue());
        testData.setHersteller(row.getCell(2).getStringCellValue());
        testData.setTyp(row.getCell(3).getStringCellValue());
        
        String stringCellValue = row.getCell(4).getStringCellValue();
        if (stringCellValue!=null) {
          testData.setExpectedRisiko(stringCellValue);
          testData.setExpectedRisikoBewertung(row.getCell(5).getStringCellValue());
        }

        testDataList.add(new Object[] { testData });
      }

    }

    return testDataList;
  }
}
