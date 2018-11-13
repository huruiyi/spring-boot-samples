package com.example;

import com.example.module_two.CController;
import org.junit.Test;

public class Demo01 {

    @Test
    public void  Demo(){
        CController controller=new CController();
        controller.Id=123;
        controller.Name="DemoController";
        controller.ShowInfo();
    }
}
