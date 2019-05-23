package com.echarts.datashow;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Map;

@Controller
public class HelloSpringBoot {
    @RequestMapping("hello")
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/websocket",method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Map<String, String[]> groupdata(HttpServletRequest request, HttpSession session){
        while (true) {
            Map<String,String[]> map = new HashMap<>();
            WeblogService wb = new WeblogService();
            map = wb.queryWeblogs();
            map.put("titleSum",wb.titleCount());
            return map;
        }
    }
}
