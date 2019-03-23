package com.ws.controller;

import com.ws.bean.Catalog;
import com.ws.bean.Section;
import com.ws.service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class SectionController {

    @Autowired
    private SectionService sectionService;
    //查询课程
    @RequestMapping("querySection")
    @ResponseBody
    public List<Catalog> querySection(Integer id){
        List<Catalog> list = sectionService.querySection(id);
        return list;
    }



    //查询章 节
    @RequestMapping("querySectionNode")
    @ResponseBody
    public List<Section> querySectionNode(HttpSession session){
        Integer kid = (Integer) session.getAttribute("kid");
        return sectionService.querySectionNode(kid);
    }
    //存储进入课程id   进入章 节页面
    @RequestMapping("queryShowKecheng")
    @ResponseBody
    public void queryShowKecheng(HttpSession session,Integer id){
        Integer kid = (Integer) session.getAttribute("kid");
        if(kid!=null){
            session.removeAttribute("kid");
            session.setAttribute("kid",id);
        }else{
            session.setAttribute("kid",id);
        }
    }
}
