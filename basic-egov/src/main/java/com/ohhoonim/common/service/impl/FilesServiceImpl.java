package com.ohhoonim.common.service.impl;

import java.io.File;
import java.net.URLEncoder;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ohhoonim.common.service.FilesService;
import com.ohhoonim.dao.FilesDao;
import com.ohhoonim.vo.FilesVo;

@Service("filesService")
public class FilesServiceImpl implements FilesService {

	// @Resource(name="filesDao")
	@Resource(name = "filesDao")
	FilesDao dao;

	public FilesVo selectFiles(FilesVo vo) {
		return dao.selectFiles(vo);
	}

	// public int addFiles(FilesVo vo) {
	// return dao.addFiles(vo);
	// }

	public String addFiles(MultipartFile file, String contextPath) throws Exception {
		//꺼내올 정보
		String fileName = file.getOriginalFilename(); // 업로드한 파일명
		String contentType = file.getContentType(); // 파일의유형
		
		//추가로 셋팅할 정보
		String uploadedFileName = System.currentTimeMillis() + UUID.randomUUID().toString()  //서버시간(ms포함) + randomUUID + 확장자
										 + fileName.substring(fileName.lastIndexOf(".")); 		    //서버에 저장할 파일명		
		
		//OS마다 경로 설정하는 것이 다를 수 있다.
		//Windows라면 : C:\Upload
		//Linux or Unix 계열이라면 : /Upload 와 같이 셋팅해줘야한다.
		String uploadPath = "/upload";					//서버에 저장할 경로(자동으로 C드라이브의 upload 폴더로 지정된다.)

		if (file.getSize() != 0) {
			file.transferTo(new File(uploadPath + "/" + uploadedFileName));
		}
		String nextFilesId = dao.getNextId();
		
		//다운로드 링크정보
		String downlink = contextPath + "/common/download.do?filesId=" + nextFilesId;

		FilesVo vo = new FilesVo();
		vo.setFilesId(nextFilesId);
		vo.setFilesNm(fileName);
		vo.setFilesUfn(uploadedFileName);
		vo.setFilesSize(file.getSize() + "");
		vo.setFilesType(contentType);
		vo.setFilesDl(downlink);

		dao.addFiles(vo);

		return nextFilesId;
	}

	public int updateFiles(FilesVo vo) {
		return dao.updateFiles(vo);
	}

	public int deleteFiles(FilesVo vo) {
		return dao.deleteFiles(vo);
	}

}
