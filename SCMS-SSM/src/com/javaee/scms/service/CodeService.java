package com.javaee.scms.service;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.javaee.scms.exception.BizzException;
import com.javaee.scms.mapper.CodeMapper;
import com.javaee.scms.pojo.Code;
import com.javaee.scms.pojo.User;

@Component("codeService")
public class CodeService {
	@Autowired
	@Qualifier("codeMapper")
	CodeMapper codeDao;
	
	public Map<String, Object> addCode(MultipartFile codefile, String path,
			User loginUser, String intro) throws IllegalStateException, IOException, SQLException, BizzException {
		Map<String, Object> result = new HashMap<String, Object>();
		//文件上传和新增代码的业务逻辑
		//将文件存到服务器的指定位置
		//1. 路径存在性
		//2. 文件名重复的问题
		if(codefile.getSize() > 0){
			String filename = generateFilename(codefile.getOriginalFilename());
			if(filename.endsWith(".zip") || filename.endsWith(".rar")){
				File file = new File(path, filename);
				if(!file.getParentFile().exists()){
					file.getParentFile().mkdir();
				}
				//将上传的文件保存在服务器上的指定目录
				codefile.transferTo(file);
				
				//向数据库插入一条新的Code数据
				//(id=null, codename="codefile.getname()", 
				//  filepath="文件存放好之后的位置", 
				//  intro=输入的intro
				//  owner="当前登录用户", 
				//  addTime="当前系统时间")
				Code code = new Code();
				code.setCodename(codefile.getOriginalFilename());
				code.setFilepath("resources/codefile/" + filename );
				code.setIntro(intro);
				code.setOwner(loginUser);
				code.setAddTime(new Timestamp(System.currentTimeMillis()));
				//CodeDao codeDao = new CodeDao();
				System.out.println(code);
				codeDao.add(code);
				
				
				result.put("isSuccess", true);
				result.put("message", "上传成功！");
			}else{
				//类型错误，报错
				/*result.put("isSuccess", false);
				result.put("message", "必须上传.zip或者.rar文件");*/
				throw new BizzException("必须上传.zip或者.rar文件");
			}
		}else{
			//空文件，报错
			/*result.put("isSuccess", false);
			result.put("message", "文件不可为空");*/
			throw new BizzException("文件不可为空");
		}
		return result;
		
		
		
	}

	private String generateFilename(String originalFilename) {
		//JavaEE01-类加载器和反射机制.zip --> JavaEE01-类加载器和反射机制-时间戳.zip
		String prefix = originalFilename.substring(0, originalFilename.lastIndexOf("."));
		String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
		String filename = prefix + "-" + System.currentTimeMillis() + suffix;
		return filename;
	}

	public List<Code> findAllCode() throws SQLException {
		//CodeDao codeDao = new CodeDao();
		return codeDao.findAllCode();
	}

	public Code findById(Integer id) throws SQLException {
		//CodeDao codeDao = new CodeDao();
		return codeDao.findById(id);
	}

	public void update(Code code) throws SQLException {
		//CodeDao codeDao = new CodeDao();
		codeDao.update(code);
	}

	public void delete(Integer id, String pathPrefix) throws SQLException {
		//CodeDao codeDao = new CodeDao();
		Code code = codeDao.findById(id);
		File file = new File(pathPrefix + code.getFilepath());
		if(file.exists()){
			file.delete();
		}
		
		codeDao.delete(id);
	}
	

}
