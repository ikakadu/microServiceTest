package com.it.rest;

import com.it.excel.ImportExcelTest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.InputStream;

@RestController
public class ReadFileFromWeb {

    @PostMapping("fileUpload")
    public String readExcel(@RequestParam("file") MultipartFile file,
                            HttpServletRequest request, HttpServletResponse response){
        System.out.println("fileUpload开始处理："+file.getOriginalFilename());
        // 判断文件是否为空
        if (!file.isEmpty()) {
            try {
                // 文件保存路径
                String filePath = request.getSession().getServletContext().getRealPath("/") + "upload/" + file.getOriginalFilename();//类linux系统
//                String filePath = request.getSession().getServletContext().getRealPath("/") + "upload\\" + file.getOriginalFilename();//window系统
                System.out.println("filePath: "+filePath);
                // 转存文件

                if(!new File(filePath).exists()){
                    new File(filePath).mkdirs();
                }
                file.transferTo(new File(filePath));
                //开始解析excel文件
                String res = ImportExcelTest.readExcel(filePath);
                return res;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        return null;
    }

    /***
     * 读取上传文件中得所有文件并返回
     *
     * @return
     */
    @RequestMapping("list")
    public ModelAndView list(HttpServletRequest request, HttpServletResponse response) {
        String filePath = request.getSession().getServletContext().getRealPath("/") + "upload/";
        ModelAndView mav = new ModelAndView("list");
        File uploadDest = new File(filePath);
        String[] fileNames = uploadDest.list();
        for (int i = 0; i < fileNames.length; i++) {
            //打印出文件名
            System.out.println(fileNames[i]);
        }
        return mav;
    }
}
