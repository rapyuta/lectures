package com.ohhoonim.common.service;

import org.springframework.web.multipart.MultipartFile;

import com.ohhoonim.vo.FilesVo;

public interface FilesService {
	public FilesVo selectFiles(FilesVo vo);
	//public int addFiles(FilesVo vo);
	public String addFiles(MultipartFile file, String contextPath) throws Exception;
	public int updateFiles(FilesVo vo);
	public int deleteFiles(FilesVo vo);
}
