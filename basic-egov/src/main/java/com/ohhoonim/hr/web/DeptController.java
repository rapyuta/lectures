package com.ohhoonim.hr.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ohhoonim.common.ExcepHndlr;
import com.ohhoonim.common.util.Utils;
import com.ohhoonim.hr.service.DeptService;
import com.ohhoonim.vo.DeptVo;

@Controller
public class DeptController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DeptController.class);
	
	@Resource(name="deptService")
	DeptService deptService;
	
	@RequestMapping("/hr/deptList.do")
	public String deptList(@RequestParam Map<String, String> req, ModelMap model) {
		
		String dname = req.get("dname") == null ? "" : req.get("dname");
		
		DeptVo vo = new DeptVo();
		vo.setDname(dname);
		List<DeptVo> list = deptService.deptList(vo);
		
		model.addAttribute("dname", dname);
		model.addAttribute("deptList", list);
		
		return "hr/deptList";
	}
	
	@RequestMapping("/hr/deptAddView.do")
	public String deptAddView(ModelMap model) {
		
		return "hr/deptAdd";
	}
	@RequestMapping("/hr/deptAdd.do")
	public String deptAdd(@RequestParam Map<String, String> req, RedirectAttributes redirectAttr) {
		String returnStr = "redirect:/hr/deptList.do";
		
		String deptno = req.get("DEPTNO") == null ? "" : req.get("DEPTNO");
		String dname = req.get("DNAME") == null ? "" : req.get("DNAME");
		String loc = req.get("LOC") == null ? "" : req.get("LOC");
		
		if (deptno.equals("") || dname.equals("")) {
			
			Map<String, String> attrMap = new HashMap<String, String>();
			attrMap.put("deptno", deptno);
			attrMap.put("dname", dname);
			attrMap.put("loc", loc);
			attrMap.put("msg", "Please check your input");
			
			redirectAttr.addFlashAttribute("rtnParam", attrMap);
			
			returnStr = "redirect:/hr/deptAddView.do";
		} else {
			DeptVo vo = new DeptVo();
			vo.setDeptno(deptno);
			vo.setDname(dname);
			vo.setLoc(loc);
			
			deptService.deptAdd(vo);
		}
		
		return returnStr;
	}
	
	@RequestMapping("/hr/checkDupDeptno.do")
	@ResponseBody
	public Object checkDupDeptno(@RequestParam Map<String, String> req) throws Exception {
		Map<String, Object> json = new HashMap<String, Object>();
		 
		JSONParser parser = new JSONParser();
		JSONObject jsonObj = (JSONObject)parser.parse(req.get("formData"));
		
		LOGGER.debug("param1===========================");
		String param1 = (String)jsonObj.get("param1");
		//LOGGER.debug("param1: " + param1);
		
		LOGGER.debug("map===========================");
		Map<String, String> map = (Map<String, String>)jsonObj.get("map");
		//map.forEach((key, value) -> LOGGER.debug(key + "|" + value));
		
		LOGGER.debug("list===========================");
		List<Map<String, String>> list = (List<Map<String, String>>)jsonObj.get("list");
		//list.forEach((eachMap) -> eachMap.forEach((key, value)->LOGGER.debug(key + "|" + value) ));
		
		/*
		String deptno = Utils.toEmptyBlank(req.get("deptno"));
		
		DeptVo vo = new DeptVo();
		vo.setDeptno(deptno);
		int resultCount = deptService.checkDeptno(vo);
		
		if (resultCount > 0) {
			json.put("isDup", true);
		} else {
			json.put("isDup", false);
		}
		// => {isDup: true}
		*/
		 
		return json;
		
	}
	
	@RequestMapping("/hr/deptDel.do")
	public String removeDept(@RequestParam Map<String, String> req, RedirectAttributes redirect) {
		
		String[] deptnos = Utils.toEmptyBlank(req.get("deptnoList")).split(",");
		LOGGER.debug(deptnos.toString());
		List<String> unremoved = deptService.removeDept(deptnos);
		Map<String, Object> rtnResult = new HashMap<String, Object>();
		rtnResult.put("isRemoved", "ok");
		rtnResult.put("unremoved", unremoved);
		
		redirect.addFlashAttribute("rtnResult", rtnResult);
		
		return "redirect:/hr/deptList.do";
	}

}
















