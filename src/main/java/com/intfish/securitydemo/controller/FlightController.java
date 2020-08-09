package com.intfish.securitydemo.controller;

import com.intfish.securitydemo.entity.Flight;
import com.intfish.securitydemo.util.R;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


@RestController
@RequestMapping("flight")
public class FlightController {
    public static AtomicInteger idBuilder = new AtomicInteger(1);

    @PostMapping("add")
    public R add(@RequestBody Flight param){
        param.setId(idBuilder.getAndIncrement());
        System.out.println("添加实验: " + param);
        return R.ok(param);
    }
    @GetMapping("delete")
    public R delete(@RequestParam Integer id){
        System.out.println("删除实验: "+id);
        return R.ok();
    }

    @PostMapping("update")
    public R update(@RequestBody Flight param){
        System.out.println("修改实验: " + param);
        return R.ok(param);
    }

    @PostMapping("list")
    public R list(@RequestBody Flight param){
        System.out.println("查询实验: " + param);
        return R.ok(Collections.singletonList(param));
    }
}
