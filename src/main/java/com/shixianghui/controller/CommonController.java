package com.shixianghui.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.shixianghui.base.dao.XtDict;
import com.shixianghui.base.response.BaseResponse;
import com.shixianghui.base.utils.MessageUtils;
import com.shixianghui.base.utils.Utils;
import com.shixianghui.service.IDictSercice;

@RestController
@RequestMapping("comm")
public class CommonController {

	@Autowired
	private IDictSercice<XtDict> DictSercice;
	/**
	 * 获得对应字典列表
	 * @param type 1：用户类型, 2:商品类型,3:商品单位
	 */
	@GetMapping("dictList")
	public Object getDictItemsByType(String type){
		BaseResponse resp = new BaseResponse();
		type = Utils.getPreStr(type);
		List<XtDict> items = DictSercice.getListByType(type);
		resp.setObject(items);
		return resp;
	}
	
	/**
	 * 上传图片
	 */
	@PostMapping("uploadByFile")
	public Object uploadByFile(@RequestParam("file") MultipartFile file,
			HttpServletRequest request){
		BaseResponse resp = new BaseResponse();
		String contentType = file.getContentType();
        String fileName = file.getOriginalFilename();
        String newFileName = System.currentTimeMillis()+"."+fileName.split("\\.")[1];
        String filePath = "E:/uploadImgs/imgupload/";
        try {
        	File targetFile = new File(filePath);  
            if(!targetFile.exists()){    
                targetFile.mkdirs();    
            }
            FileOutputStream out = new FileOutputStream(filePath+newFileName);
            out.write(file.getBytes());
            out.flush();
            out.close();
        } catch (Exception e) {
        	resp.setCode(MessageUtils.CODE_FAILUER);
			resp.setMessage(MessageUtils.MESSAGE_FAILUER);
			return resp;
        }
        
        resp.setObject(newFileName);
        resp.setCode(MessageUtils.CODE_SUCCEED);
		resp.setMessage(MessageUtils.MESSAGE_LOGIN_SUCCEED);
        return resp;
	}
	
	/**
	 * 获取上传的文件内容
	 * 
	 * @param path
	 *            路径
	 * @param fileName
	 *            文件名
	 * @param fileExt
	 *            文件扩展名
	 * @param response
	 *            应答
	 */
	@GetMapping("getImgByUrl")
	public void getImgByUrl(@RequestParam String fileName,
			HttpServletRequest request, HttpServletResponse response) {
		//跨域允许配置（现，允许所有）
		response.setHeader("Access-Control-Allow-Origin", request.getHeader("origin"));
		String[] imgUrl = fileName.split("\\.");
		String filePath = "E:/uploadImgs/imgupload/"+fileName;
		File file = new File(filePath);
		if (!file.exists() || !file.canRead()) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return;
		}
//			write(UploadManager.instance().getRealFilePath(path + "/" + fileName+"_compress." + fileExt), fileExt, request, response);
			write(filePath,imgUrl[1], request, response);
		
	}
	/**
	 * 图片写入操作
	 * @param fileUrl
	 * @param fileExt
	 * @param response
	 * @return
	 */
	private HttpServletResponse write(String fileUrl, String fileExt, HttpServletRequest request, HttpServletResponse response){
		File file = new File(fileUrl);
		if (!file.exists() || !file.canRead()) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return response;
		}
		try {
			FileInputStream inputStream = new FileInputStream(fileUrl);
			OutputStream outputStream = response.getOutputStream();
			FileNameMap fileNameMap = URLConnection.getFileNameMap();
			String contentType = fileNameMap.getContentTypeFor("1." + fileExt);
			response.setContentType(contentType);

			byte[] buffer = new byte[32 * 1024];
			while (true) {
				int bytes = inputStream.read(buffer);
				if (bytes <= 0) {
					break;
				}
				outputStream.write(buffer, 0, bytes);
			}
			inputStream.close();
			outputStream.flush();
			outputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}
}
