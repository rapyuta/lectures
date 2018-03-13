package com.ohhoonim.common.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ohhoonim.common.service.FilesService;
import com.ohhoonim.vo.FilesVo;

@Controller
public class FileUpDownController {
	/*
	@RequestMapping(value = "/imageFileControl.do")
	public String imageFileControl() {

		return "/exam/imageFileControl";

	}
	 */
	
	@Resource(name = "filesService")
	FilesService filesService;
	
	@RequestMapping("/common/download.do")
		public void download(HttpServletRequest request, HttpServletResponse response) throws Exception {
			String filesId = request.getParameter("filesId"); 
	
			FilesVo vo = new FilesVo();
			vo.setFilesId(filesId);
			FilesVo fileInfo = filesService.selectFiles(vo);
			if (fileInfo != null) {
				byte fileByte[] = FileUtils.readFileToByteArray(new File("C:\\Upload\\"+fileInfo.getFilesUfn()));
			     
			    response.setContentType("application/octet-stream");
			    response.setContentLength(fileByte.length);
			    response.setHeader("Content-Disposition", "attachment; fileName=\"" + URLEncoder.encode(fileInfo.getFilesNm(),"UTF-8")+"\";");
			    response.setHeader("Content-Transfer-Encoding", "binary");
			    response.getOutputStream().write(fileByte);
			     
			    response.getOutputStream().flush();
			    response.getOutputStream().close();
	
			} else {
				PrintWriter out = response.getWriter();
				out.print("파일을 찾을 수 없습니다.");
			}
	 	}
	
	
	@RequestMapping(value = "/common/imageFileUpload.do", produces = "application/json; charset=utf8")
	@ResponseBody
	public HashMap<String, Object> image(MultipartHttpServletRequest request) throws Exception {
		MultipartFile file = request.getFile("file");
		HashMap<String, Object> result = new HashMap<String, Object>();
		
		
		String name = file.getName();
		String fileName = file.getOriginalFilename();
		String contentType = file.getContentType();
		String uploadedFileName = System.currentTimeMillis() + UUID.randomUUID().toString()
				+ fileName.substring(fileName.lastIndexOf("."));
		String uploadPath = "/upload";

		if (file.getSize() != 0) {
			file.transferTo(new File(uploadPath + "/" + uploadedFileName));
		}

//		String downlink = request.getContextPath() + "/common/image.do?of=" + URLEncoder.encode(fileName, "UTF-8") + "&f="
//				+ URLEncoder.encode(uploadedFileName, "UTF-8");
		String downlink = request.getContextPath() + "/common/image.do?of=" + URLEncoder.encode(fileName, "UTF-8")
						+ "&f=" + URLEncoder.encode(uploadedFileName, "UTF-8");

		FilesVo vo = new FilesVo();
//		vo.setFileN(fileName);
//		vo.setFileU(uploadedFileName);
//		vo.setFileS(file.getSize()+"");
//		vo.setcType(contentType);
//		vo.setLink(downlink);
		vo.setFilesNm(fileName);
		vo.setFilesUfn(uploadedFileName);
				vo.setFilesSize(file.getSize() + "");
				vo.setFilesType(contentType);
				vo.setFilesDl(downlink);

		result.put("file", vo);

		return result;
	}

	@RequestMapping("/common/image.do")
	public void fileDownload2(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String f = request.getParameter("f"); // 저장된 파일이름
		String of = request.getParameter("of");// 원래 파일이름
		of = new String(of.getBytes("ISO8859_1"), "UTF-8");

		String path = "/upload";
		String fullPath = path + "/" + f;
		File downloadFile = new File(fullPath);

		response.setContentLength((int) downloadFile.length());

		// 다운로드 창을 띄우기 위한 헤더 조작
		response.setContentType("image/jpg");
		response.setHeader("Content-Disposition", "attachment;filename=" + new String(of.getBytes(), "ISO8859_1"));

		response.setHeader("Content-Transfer-Encoding", "binary");

		FileInputStream fin = new FileInputStream(downloadFile);
		ServletOutputStream sout = response.getOutputStream();

		byte[] buf = new byte[1024];
		int size = -1;

		while ((size = fin.read(buf, 0, buf.length)) != -1) {
			sout.write(buf, 0, size);
		}
		fin.close();
		sout.close();

	}
}
