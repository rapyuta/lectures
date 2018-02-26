package com.ohhoonim.hr.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ohhoonim.hr.service.DeptService;
import com.ohhoonim.hr.service.EmpService;
import com.ohhoonim.vo.DeptVo;
import com.ohhoonim.vo.EmpVo;

@Controller
public class DeptEmpController {
	
	@Resource(name="deptService")
	DeptService deptService;
	
	@Resource(name="empService")
	EmpService empService;
	
	@RequestMapping("/hr/deptEmpList.do")
	public String deptEmpList(@RequestParam Map<String, String> req, ModelMap model) {
		
		String dname = req.get("dname") == null ? "" : req.get("dname");
		String deptno = req.get("deptno") == null ? "" : req.get("deptno");
		String containNoDept = req.get("containNoDept") == null ? "" : req.get("containNoDept");
		
		DeptVo deptVo = new DeptVo();
		deptVo.setDname(dname);
		List<DeptVo> deptList = deptService.deptList(deptVo);
		
		List<EmpVo> empList = new ArrayList<EmpVo>();
		if (deptno != null && !deptno.equals("")) {
			EmpVo empVo = new EmpVo();
			empVo.setDeptno(deptno);
			if(containNoDept != null && !containNoDept.equals("")) {
				empList = empService.noDeptEmpList(empVo);
				model.addAttribute("containNoDept", "checked");
			} else {
				empList = empService.deptEmpList(empVo);
				model.addAttribute("containNoDept", "");
			}
		} 
		
		model.addAttribute("dname", dname);
		model.addAttribute("deptList", deptList);
		model.addAttribute("empList", empList);
		
		return "hr/deptEmpList";
	}
}
