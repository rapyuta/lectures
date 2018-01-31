package com.ohhoonim.excelView;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.ohhoonim.common.Constants;
import com.ohhoonim.vo.StudentVo;

import egovframework.rte.fdl.excel.util.AbstractPOIExcelView;
@Component("studentExcelView")
public class StudentExcelView   extends AbstractPOIExcelView {

	private static final Logger LOGGER  = LoggerFactory.getLogger(StudentExcelView.class);
	
	@Override
	protected void buildExcelDocument(Map model, XSSFWorkbook wb, HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		Map<String, Object> excelMap= (Map<String, Object>) model.get(Constants.EXCEL_MAP);
		String title = (String)excelMap.get(Constants.SHEET_TITLE);
		List<StudentVo> list = (List<StudentVo>)excelMap.get(Constants.LIST);
 
        XSSFSheet sheet = wb.createSheet("data");
        sheet.setDefaultColumnWidth(12);
 
        // put text in first cell
        XSSFCell cell = getCell(sheet, 0, 0);
        setText(cell, title);
 
        // set header information
        Field[] headers = StudentVo.class.getDeclaredFields();
        String[] headersGetter = new String[headers.length];
        for(int i = 0; i < headers.length; i++) {
        	String fieldName = headers[i].getName();
        	setText(getCell(sheet, 2, i), fieldName);
        	String methodName = "get" + 
        			fieldName.substring(0,1).toUpperCase() + 
        			fieldName.substring(1, fieldName.length());
        	headersGetter[i] = methodName;
        }

        // append datas
        for (int i = 0; i < list.size(); i++) {
        	StudentVo row = (StudentVo) list.get(i);
        	for (int j = 0; j < headers.length; j++) {
        		Method getter = StudentVo.class.getMethod(headersGetter[j]);
        		cell = getCell(sheet, 3 + i, j); 
        		setText(cell, getter.invoke(row).toString());
        	}
        }
        
        resp.setHeader("Content-Disposition", String.format("attachment; filename=\"test.xlsx\""));
	}
}
