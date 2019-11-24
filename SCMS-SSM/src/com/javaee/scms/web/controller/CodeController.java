package com.javaee.scms.web.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.javaee.scms.exception.BizzException;
import com.javaee.scms.exception.SystemException;
import com.javaee.scms.pojo.Code;
import com.javaee.scms.pojo.User;
import com.javaee.scms.service.CodeService;

@Controller
public class CodeController {
	@Autowired
	@Qualifier("codeService")
	CodeService codeService;
	
	//查询一览
	@RequestMapping("/codes")
	public ModelAndView list() throws SystemException{
		ModelAndView mv = new ModelAndView();
		//CodeService codeService = new CodeService();
		List<Code> codelist;
		try {
			codelist = codeService.findAllCode();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SystemException(e);
		}
		mv.addObject("codelist", codelist);
		mv.setViewName("codelist");
		return mv;
	}
	//查询一览
	@RequestMapping("/admin")
	public ModelAndView admin() throws SystemException{
		ModelAndView mv = new ModelAndView();
		//CodeService codeService = new CodeService();
		List<Code> codelist;
		try {
			codelist = codeService.findAllCode();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SystemException(e);
		}
		mv.addObject("codelist", codelist);
		mv.setViewName("admin");
		return mv;
	}
	//显示新增输入页面
	@RequestMapping(value="/code", method=RequestMethod.GET)
	public String toAdd(){
		return "upload";
	}
	//执行新增动作
	@RequestMapping(value="/code", method=RequestMethod.POST)
	public ModelAndView add(@RequestParam("codefile") MultipartFile codefile,
			@RequestParam("intro") String intro,
			HttpServletRequest request,
			HttpSession session) throws SystemException, BizzException{
		ModelAndView mv = new ModelAndView();
		
		User loginUser = (User)session.getAttribute("loginUser");
		String path = request.getServletContext().getRealPath("/") + "resources\\codefile\\";
		//CodeService codeService = new CodeService();
		Map<String, Object> result;
		try {
			result = codeService.addCode(codefile, path, loginUser, intro);
		} catch (IllegalStateException e) {
			e.printStackTrace();
			throw new SystemException(e);
		} catch (IOException e) {
			e.printStackTrace();
			throw new SystemException(e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SystemException(e);
		} catch (BizzException e) {
			e.printStackTrace();
			throw e;
		}
		Boolean isSuccess = (Boolean)result.get("isSuccess");
		String message = (String)result.get("message");
		if(isSuccess){
			mv.setViewName("redirect:/codes");
		}else{
			mv.setViewName("bizzerror");
			mv.addObject("message", message);
		}
		
		
		return mv;
	}
	
	@RequestMapping(value="/code/{id}", method=RequestMethod.GET)
	public String toEdit(@ModelAttribute("editCode") Code editCode, Model model){
		model.addAttribute("code", editCode);
		return "codeedit";
	}
	
	@RequestMapping(value="/code/{id}", method=RequestMethod.PUT)
	public ModelAndView edit(@ModelAttribute("editCode") Code code) throws SystemException{
		ModelAndView mv = new ModelAndView();
		
		//CodeService codeService = new CodeService();
		try {
			codeService.update(code);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SystemException(e);
		}
		
		mv.setViewName("redirect:/admin");
		return mv;
	}
	
	@RequestMapping(value="/code/{id}", method=RequestMethod.DELETE)
	public String delete(@PathVariable("id") Integer id, HttpServletRequest request) throws SQLException{
		//CodeService codeService = new CodeService();
		String pathPrefix = request.getServletContext().getRealPath("/");
		codeService.delete(id, pathPrefix);
		return "redirect:/admin";
	}
	
	@ModelAttribute
	public void getEditCode(Code code, Map<String, Object> map) throws SystemException{
		if(code.getId()!=null){
			//CodeService codeService = new CodeService();
			try {
				code = codeService.findById(code.getId());
			} catch (SQLException e) {
				e.printStackTrace();
				throw new SystemException(e);
			}
			map.put("editCode", code);
		}
	}
}
