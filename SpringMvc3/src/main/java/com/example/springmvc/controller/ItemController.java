package com.example.springmvc.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.springmvc.pojo.Items;

@Controller
public class ItemController {

    private List<Items> list;

    public ItemController() {
        //List<Items> list = new ArrayList<Items>();
        list = new ArrayList<Items>();
        list.add(new Items(1, "1华为 荣耀8", 2399f, new Date(), "质量好！1"));
        list.add(new Items(2, "2华为 荣耀8", 2399f, new Date(), "质量好！2"));
        list.add(new Items(3, "3华为 荣耀8", 2399f, new Date(), "质量好！3"));
        list.add(new Items(4, "4华为 荣耀8", 2399f, new Date(), "质量好！4"));
        list.add(new Items(5, "5华为 荣耀8", 2399f, new Date(), "质量好！5"));
        list.add(new Items(6, "6华为 荣耀8", 2399f, new Date(), "质量好！6"));
    }

    @RequestMapping(value = "/item/itemlist.action")
    public ModelAndView itemList() {


        ModelAndView mav = new ModelAndView();
        mav.addObject("itemList", list);
        mav.setViewName("itemList");
        return mav;
    }

    @RequestMapping(value = "/item/itemEdit.action")
    public ModelAndView itemEdit(Integer id) {
        Items item = null;
        for (Items items : list) {
            if (items.getId() == id) {
                item = items;
            }
        }
        ModelAndView mav = new ModelAndView();
        mav.addObject("item", item);
        mav.setViewName("editItem");
        return mav;
    }
}
