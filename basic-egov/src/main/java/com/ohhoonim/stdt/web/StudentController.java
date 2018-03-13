package com.ohhoonim.stdt.web;

import java.io.File;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ohhoonim.common.Constants;
import com.ohhoonim.common.util.Paging;
import com.ohhoonim.common.util.Utils;
import com.ohhoonim.stdt.service.StudentService;
import com.ohhoonim.vo.EmpVo;
import com.ohhoonim.vo.StudentVo;

@Controller
public class StudentController {
	@Resource(name = "studentService")
	StudentService studentService;

	private static final Logger LOGGER = Logger.getLogger(StudentController.class);
	public final static String KEY_LIST = "list";
	public final static String KEY_LIST_COUNT = "listCount";

	// http://localhost:8080/basic-egov/stdt/studentList.do
	@RequestMapping("/stdt/studentList.do")
	public String studentList(@RequestParam Map<String, String> req, ModelMap model) throws Exception {
		String memberName = Utils.toEmptyBlank(req.get("searchName"));
		String memberGender = Utils.toEmptyBlank(req.get("searchGender"));

		String pageNo = req.get("pageNo") == null ? "1" : req.get("pageNo");
		String pageSize = req.get("pageSize") == null ? "10" : req.get("pageSize");

		StudentVo stdtVo = new StudentVo();
		stdtVo.setMemberName(memberName);
		stdtVo.setMemberGender(memberGender);
		stdtVo.setPageNo(Integer.parseInt(pageNo));
		stdtVo.setPageSize(Integer.parseInt(pageSize));

		Map<String, Object> listMap = studentService.studentList(stdtVo);

		model.addAttribute(StudentController.KEY_LIST, listMap.get("list"));
		model.addAttribute("memberName", memberName);
		model.addAttribute("memberGender", memberGender);

		Paging paging = new Paging();
		paging.setPageNo(Integer.parseInt(pageNo));
		paging.setPageSize(Integer.parseInt(pageSize));
		paging.setTotalCount((int) listMap.get("listCount"));

		model.addAttribute("paging", paging);

		return "stdt/studentList";
	}

	@RequestMapping("/stdt/studentDetail.do")
	public String studentDetail(@RequestParam Map<String, String> req, ModelMap model) {
		String memberId = Utils.toEmptyBlank(req.get("memberId"));
		StudentVo reqVo = new StudentVo();
		reqVo.setMemberId(memberId);
		StudentVo stdtVo = studentService.studentDetail(reqVo);

		model.addAttribute("stdt", stdtVo);

		return "stdt/studentDetail";
	}

	@RequestMapping("/stdt/studentAddView.do")
	public String studentAddView() {
		return "stdt/studentAddView";
	}

	@RequestMapping("/stdt/studentAdd.do")
	// public String studentAdd(@RequestParam Map<String, String> req,
	// RedirectAttributes ra) throws Exception {
	public String studentAdd(MultipartHttpServletRequest request, RedirectAttributes ra) throws Exception {

		String redirectUrl = "redirect:/stdt/studentList.do";
		// 필수값 체크
		String[] keys = { "memberId", "memberPw", "memberName" };
		// if(Utils.checkParamIsEmpty(req, keys)) {
		if ( /* Utils.checkParamIsEmpty(request.getParameterMap(), keys) */ false) {
			redirectUrl = "redirect:/stdt/studentAddView.do";
			Map<String, Object> redirectParam = new HashMap<String, Object>();
			// redirectParam.put("inputValues", req);
			redirectParam.put("inputValues", request.getParameterMap());
			redirectParam.put("failMsg", "Please check your input");
			ra.addFlashAttribute("redirectParam", redirectParam);
			redirectUrl = "redirect:/stdt/studentAddView.do";
		} else {
			// StudentVo vo = Utils.mappingReqparamToVo(req, StudentVo.class);
			// int addedCnt = studentService.addStudent(vo);
			// Map req = request.getParameterMap();
			// StudentVo vo = Utils.mappingReqparamToVo(req, StudentVo.class);
			StudentVo vo = new StudentVo();
			vo.init();
			vo.setMemberId(request.getParameter("memberId"));
			vo.setMemberPw(request.getParameter("memberPw"));
			vo.setMemberName(request.getParameter("memberName"));

			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date indate = sf.parse(request.getParameter("memberBirth"));
			vo.setMemberBirth(new java.sql.Date(indate.getTime()));

			vo.setMemberGender(request.getParameter("memberGender"));
			vo.setMemberEmail(request.getParameter("memberEmail"));

			MultipartFile file = request.getFile("file");
			String contextPath = request.getContextPath();
			int addedCnt = studentService.addStudent(vo, file, contextPath);
		}

		return redirectUrl;
	}

	@RequestMapping("/stdt/studentModifyView.do")
	public String studentModifyView(@RequestParam Map<String, String> req, ModelMap model) {
		String memberId = Utils.toEmptyBlank(req.get("memberId"));
		StudentVo reqVo = new StudentVo();
		reqVo.setMemberId(memberId);
		StudentVo stdtVo = studentService.studentDetail(reqVo);

		model.addAttribute("stdt", stdtVo);

		return "stdt/studentModifyView";
	}

	@RequestMapping("/stdt/studentModify.do")
	public String studentModify(@RequestParam Map<String, String> req, RedirectAttributes ra) throws Exception {
		String redirectUrl = "redirect:/stdt/studentDetail.do";
		// 필수값 체크
		String[] keys = { "memberId", "memberPw", "memberName" };
		if (Utils.checkParamIsEmpty(req, keys)) {
			redirectUrl = "redirect:/stdt/studentAddView.do";
			Map<String, Object> redirectParam = new HashMap<String, Object>();
			redirectParam.put("inputValues", req);
			redirectParam.put("failMsg", "Please check your input");
			ra.addFlashAttribute("redirectParam", redirectParam);
			redirectUrl = "redirect:/stdt/studentModifyView.do";
		} else {
			StudentVo vo = Utils.mappingReqparamToVo(req, StudentVo.class);
			int addedCnt = studentService.modifyStudent(vo);

			ra.addAttribute("memberId", vo.getMemberId());
		}

		return redirectUrl;
	}

	@RequestMapping("/stdt/studentRemove.do")
	public String studentRemove(@RequestParam Map<String, String> req, ModelMap model) {
		String memberId = Utils.toEmptyBlank(req.get("memberId"));
		if (!memberId.equals("")) {
			StudentVo reqVo = new StudentVo();
			reqVo.setMemberId(memberId);
			int removedCnt = studentService.removeStudent(reqVo);
		}
		return "redirect:/stdt/studentList.do";
	}

	@RequestMapping("/stdt/studentXls.do")
	public ModelAndView empListXls(@RequestParam HashMap<String, String> req, ModelAndView model) throws Exception {

		String memberName = Utils.toEmptyBlank(req.get("searchName"));
		String memberGender = Utils.toEmptyBlank(req.get("searchGender"));

		StudentVo stdtVo = new StudentVo();
		stdtVo.setMemberName(memberName);
		stdtVo.setMemberGender(memberGender);

		Map<String, Object> listMap = studentService.studentList(stdtVo);

		Map<String, Object> excelMap = new HashMap<String, Object>();
		excelMap.put(Constants.LIST, listMap.get("list"));
		excelMap.put(Constants.SHEET_TITLE, "학생목록");

		model.addObject(Constants.EXCEL_MAP, excelMap);
		model.addObject(Constants.FILE_NAME, Utils.fileNameWithTimestamp("student"));

		model.setViewName("studentExcelView");

		return model;
	}

	@RequestMapping("/stdt/registerWithExcel.do")
	@ResponseBody
	public HashMap<String, Object> registerWithExcel(MultipartHttpServletRequest request) throws Exception {
		HashMap<String, Object> result = new HashMap<String, Object>();

		MultipartFile file = request.getFile("file");
		Workbook wb = new XSSFWorkbook(file.getInputStream());
		// 여기서부터 vo매핑 로직 작성해주면됨.

		return result;
	}
}