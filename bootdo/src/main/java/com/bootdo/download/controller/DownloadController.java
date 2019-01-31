package com.bootdo.download.controller;

import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bootdo.common.utils.Query;

@RequestMapping("/download")
@Controller
public class DownloadController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@GetMapping("/exec")
	String user(Model model) {
		return "/download/exec";
	}
	
	@RequestMapping(value="/exportExcel")
    public void exportExcel(@RequestParam Map<String, Object> params,HttpServletRequest request,HttpServletResponse response) throws Exception{
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String filename = "测试导出功能"+format.format(new Date().getTime())+".xls";
        
        response.setContentType("application/ms-excel;charset=UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename="+new String(filename.getBytes(),"iso-8859-1"));
        
        OutputStream out = response.getOutputStream();
        try {
            Query query = new Query(params);
            String type = request.getParameter("type");
            /*
            //导出当前页面数据
            if(type.equals("1")){
                List<XXXDO> XxxDOs = XxxService.list(query);
                ExcelExportUtil4DIY.exportToFile(XxxDOs,out);
            }
            //导出全部数据
            if(type.equals("2")){
                List<XXXDO> XxxDOs = XxxService.list(null);
                ExcelExportUtil4DIY.exportToFile(XxxDOs,out);
            }
            //导出符合条件的全部数据
            if(type.equals("3")){
                query.remove("offset");
                query.remove("limit");
                List<XXXDO> XxxDOs = XxxService.list(query);
                ExcelExportUtil4DIY.exportToFile(XxxDOs,out);
            }
            */
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("exportExcel出错"+e.getMessage());
        }finally{
            out.close();
        }
    }
}
