package com.ohhoonim.common.util;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ohhoonim.vo.EmpVo;

import egovframework.rte.fdl.excel.util.AbstractPOIExcelView;

public class SimplePOIExcelView   extends AbstractPOIExcelView {

	private static final Logger LOGGER  = LoggerFactory.getLogger(SimplePOIExcelView.class);
	
	@Override
	protected void buildExcelDocument(Map model, XSSFWorkbook wb, HttpServletRequest req, HttpServletResponse resp) throws Exception {
		XSSFCell cell = null;
		 
        LOGGER.debug("### buildExcelDocument start !!!");
 
        XSSFSheet sheet = wb.createSheet("User List");
        sheet.setDefaultColumnWidth(12);
 
        // put text in first cell
        cell = getCell(sheet, 0, 0);
        setText(cell, "User List");
 
        // set header information
        setText(getCell(sheet, 2, 0), "empno");
        setText(getCell(sheet, 2, 1), "ename");
 
        Map<String, Object> map= (Map<String, Object>) model.get("empMap");
        List<EmpVo> empList = (List<EmpVo>) map.get("empList");
 
        if (empList.size() > 0) {
        	Object obj = empList.get(0);
        }
 
        for (int i = 0; i < empList.size(); i++) {
 
        		LOGGER.debug("### buildExcelDocument Map : {} started!!", i);
 
        		EmpVo emp = (EmpVo) empList.get(i);
 
 	            cell = getCell(sheet, 3 + i, 0);
 	            setText(cell, emp.getEmpno());
 
 	            cell = getCell(sheet, 3 + i, 1);
 	            setText(cell, emp.getEname());
        }
		
	}

	
}
