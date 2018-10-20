package web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.Map;
import java.util.Set;

/**
 * <p>
 *     测试专用
 * </p>
 *
 * @author huqingniu
 * @version 1.0.0
 * @date 2018/10/16
 */
@Slf4j
@Controller
public class WebTestController {

    @RequestMapping(value = "/test")
    public void service(HttpServletRequest request, HttpServletResponse response) throws Exception{
        log.info("有请求进入... 请求方式:{}",request.getMethod());
        log.info("请求http的协议版本:{}",request.getProtocol());
        log.info("项目名称:{}",request.getContextPath());
        log.info("完整请求路径:{}",request.getRequestURI());
        log.info("获取请求参数:{}",request.getQueryString());
        log.info("获取对象:{}",request.getRemoteUser());
        Enumeration<String> enumeration =  request.getHeaderNames();
        log.info("获取请求头列表:");
        while (enumeration.hasMoreElements()){
            String header = enumeration.nextElement();
            log.info("{} = {}",header,request.getHeader(header));
        }
        Map<String, String[]> map = request.getParameterMap();
        Set<Map.Entry<String, String[]>> set = map.entrySet();
        log.info("获取request服务:{}",set.size());
        for (Map.Entry<String,String[]> entry : set){
            log.info("key:{},value:{}",entry.getKey(),entry.getValue());
        }

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(request.getInputStream(),"UTF-8"));
        StringBuilder stringBuilder = new StringBuilder();
        String tempStr = "";
        while ((tempStr = bufferedReader.readLine()) != null){
            stringBuilder.append(tempStr);
        }
        log.info("获取到流内容:{}",stringBuilder.toString());

        response.setHeader("Content-type","text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("欢迎访问!");
    }

    @RequestMapping(value = "/test1")
    public String service1(@RequestParam("content") String content){
        log.info("有请求进入... 请求内容:{}",content);
        return "欢迎访问!";
    }

}
