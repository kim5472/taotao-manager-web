package com.taotao.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileSystemUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.taotao.common.utils.JsonUtils;
import com.taotao.utils.FastDFSClient;
/**
 * 实现图片上传功能
 * @author Administrator
 *
 */
@Controller
public class PictureController {

	@Value("${IMAGE_SERVER_URL}")
	private String IMAGE_SERVER_URL;
	
	@RequestMapping("/pic/upload")
	@ResponseBody
	public String picUpload(MultipartFile uploadFile){
		// 接受上传文件
		String originalFilename = uploadFile.getOriginalFilename();
		// 取扩展名
		String extName = FilenameUtils.getExtension(originalFilename);
		// 上传到图片服务器
		try {
			FastDFSClient fastDFSClient = new FastDFSClient("classpath:resource/client.conf");
			String url = fastDFSClient.uploadFile(uploadFile.getBytes(), extName);
			url = IMAGE_SERVER_URL + url;
			// 响应上传图片的url
			Map<String,Object> result = new HashMap<>();
			// 上传成功，图片会回显（url），相当于从图片服务器又下载下来
			result.put("error", 0);
			result.put("url", url);
			// 解决图片上传的兼容性有小问题,使用转换为字符串传输
			return JsonUtils.objectToJson(result);
			
		} catch (Exception e) {
			e.printStackTrace();
			Map<String,Object> result = new HashMap<>();
			result.put("error", 1);
			result.put("message", "图片上传失败");
			return JsonUtils.objectToJson(result);
		}
		
		
		
	}
	
}
