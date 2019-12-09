package com.example.web;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
public class FilesController {

    @RequestMapping(value = "/upload", method = RequestMethod.GET)
    public String upload(Model model) {
        model.addAttribute("message", "文件上传实验");
        return "upload";
    }


    @RequestMapping(value = "/fileupload")
    public String handleFormupload(@RequestParam("uploadfile")List<MultipartFile> uploadfile){
        return "";
    }
}
