package com.ruiyi.module_two;

import com.ruiyi.module_one.BaseController;

public class CController extends BaseController {


    public void ShowInfo() {
        System.out.println("CController. Id="+super.Id+",Name="+super.Name);
    }
}
