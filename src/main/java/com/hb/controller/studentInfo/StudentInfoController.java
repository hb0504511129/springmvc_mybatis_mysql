package com.hb.controller.studentInfo;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hb.models.StudentInfo;
import com.hb.service.StudentInfoService;

/**
 */
@Controller
@RequestMapping(value = "/student")
public class StudentInfoController {

    @Autowired
    private StudentInfoService studentInfoService;

    @RequestMapping(value="/test", method = {RequestMethod.GET})
    public String modelAutoBind(HttpServletRequest request, Model model){
    	StudentInfo studentInfo = new StudentInfo();
    	studentInfo.setBirthday(new Date());
    	studentInfo.setName("张三");
    	studentInfo.setPassword("123");
    	model.addAttribute("studentInfo", studentInfo);
        return "test/studentInfo";
    }
    
    @RequestMapping(value="/test", method = {RequestMethod.POST})
    public String test(HttpServletRequest request, Model model, StudentInfo studentInfo) throws NoSuchFieldException, SecurityException{

        if(!model.containsAttribute("studentInfo")){
        	
        	studentInfo = studentInfoService.query(studentInfo);
            
            model.addAttribute("studentInfo", studentInfo);
         
        }
        return "test/studentInfoShow";
    }

    @ResponseBody
    @RequestMapping("test2")
    public List<StudentInfo> requestTest7(
            @RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize
    ) {
        return studentInfoService.selectPage(pageNum, pageSize);
    }
}
