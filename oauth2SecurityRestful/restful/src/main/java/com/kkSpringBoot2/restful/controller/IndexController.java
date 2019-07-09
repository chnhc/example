package com.kkSpringBoot2.restful.controller;

import com.kkSpringBoot2.common.response.BaseResponse;
import com.kkSpringBoot2.common.response.BaseResponseStatus;
import com.kkSpringBoot2.common.response.HttpResponse;
import com.kkSpringBoot2.other.DAOS.entity.name.Name;
import com.kkSpringBoot2.other.Service.name.NameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * description:
 * author: ckk
 * create: 2019-06-29 10:01
 */
@RestController
@RequestMapping("/index")
public class IndexController {

    @Autowired
    NameService nameService;

    // 获取主页信息
    @GetMapping("/getIndex")
    @PreAuthorize("hasRole('admin')")
    public Name getIndex() {
        return nameService.selectNameById();
    }

    // 获取主页信息
    @PreAuthorize("hasRole('user')")
    @GetMapping("/getIndex1")
    public String getIndex1() {
        return "index string";
    }

}
