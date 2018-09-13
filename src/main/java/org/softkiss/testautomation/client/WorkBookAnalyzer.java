package org.softkiss.testautomation.client;

import io.qameta.allure.Step;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.softkiss.testautomation.model.AnalyticsData;

public class WorkBookAnalyzer {

    private XSSFWorkbook workBook;

    public WorkBookAnalyzer(File file) throws IOException, InvalidFormatException {

        this.workBook = new XSSFWorkbook(file);
    }

    @Step
    public List<AnalyticsData> getAnalyticsData() {
        //TODO: rework to unify method of data parsing from excel
        List<AnalyticsData> analyticsDataList = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            AnalyticsData analyticsData = new AnalyticsData();
            analyticsData.setGoodName(workBook.getSheetAt(0).getRow(i).getCell(0).getStringCellValue());
            analyticsData.setPrice(Double.valueOf(workBook.getSheetAt(0).getRow(i).getCell(1).getNumericCellValue()));
            analyticsData.setViewed(Double.valueOf(workBook.getSheetAt(0).getRow(i).getCell(2).getNumericCellValue()));
            analyticsData.setSold(Double.valueOf(workBook.getSheetAt(0).getRow(i).getCell(3).getNumericCellValue()));
            analyticsDataList.add(analyticsData);
        }
        return analyticsDataList;
    }

}
