package com.company.util;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

/**
 * @author Johnny
 * @category 文件上传工具类
 * @category Spring上传
 */
@Component
public class SSOFileUpload {

	@Value("${fileuploadpath}")
	private String path;

	/**
	 * 图片上传，返回图片访问地址
	 * 
	 * @param request
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	public String upload(HttpServletRequest request) throws IllegalStateException, IOException {
		// 图片地址：
		String fileName = null;
		// 将当前上下文初始化给 CommonsMutipartResolver （多部分解析器）
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		// 检查form中是否有enctype="multipart/form-data"
		if (multipartResolver.isMultipart(request)) {
			// 将request变成多部分request
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			// 获取multiRequest 中所有的文件名
			Iterator<String> iter = multiRequest.getFileNames();

			while (iter.hasNext()) {
				// 一次遍历所有文件
				MultipartFile file = multiRequest.getFile(iter.next().toString());
				if (file != null) {
//					path ="/upload/" + file.getOriginalFilename();
					fileName = file.getOriginalFilename();
					String path = getPath() + fileName;
					System.out.println("地址：" + path);
					// 上传
					file.transferTo(new File(path));
				}
			}
		}
		return fileName;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}
