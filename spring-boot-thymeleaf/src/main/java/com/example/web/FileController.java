package com.example.web;

import com.example.bean.ExcelData;
import com.example.bean.ImportExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping(value = "file")
public class FileController {

    @Autowired
    private ImportExcelUtil excelUtils;

    /*
     返回视图页
*/
    @RequestMapping(value = "/index")
    public ModelAndView index() {
        ModelAndView model = new ModelAndView();
        model.setViewName("/file/index");
        return model;
    }

    @PostMapping("/uploadFile")
    @ResponseBody
    public void uploadFile(@RequestParam("file") MultipartFile multipartFile) throws IOException {

        List<ExcelData> list = excelUtils.readFile(multipartFile, ExcelData.class);
        for (ExcelData excelData : list) {
            System.out.println(excelData.toString());
        }

        System.out.println("ok............");

    }


}
