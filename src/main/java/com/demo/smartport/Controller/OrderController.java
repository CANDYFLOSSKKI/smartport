package com.demo.smartport.Controller;

import com.demo.smartport.Entity.FlagResp;
import com.demo.smartport.Entity.Order.OrderHandleReq;
import com.demo.smartport.Entity.Order.OrderInfo;
import com.demo.smartport.Entity.Order.OrderRowListResp;
import com.demo.smartport.Entity.Order.OrderUpdateReq;
import com.demo.smartport.Service.OrderService;
import com.demo.smartport.Util.Handler.ImgUploadHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private ImgUploadHandler imgUploadHandler;

    @PostMapping("/update/portrait")
    public FlagResp updateApplyPortrait(@RequestParam MultipartFile file) throws Exception {
        return imgUploadHandler.uploadOrderFile(file);
    }

    @PostMapping("/add")
    public FlagResp addOne(@RequestBody OrderHandleReq req){
        return orderService.addOne(req);
    }

    @GetMapping("/get/list")
    public OrderRowListResp getList(@RequestParam String account){
        return orderService.getList(account);
    }

    @GetMapping("/get/info")
    public OrderInfo getInfo(@RequestParam String appkey){
        return orderService.getInfo(appkey);
    }

    @PostMapping("/update")
    public FlagResp updateOne(@RequestBody OrderUpdateReq req){
        return orderService.updateOne(req);
    }

    @GetMapping("/del")
    public FlagResp delOne(@RequestParam String appkey){
        return orderService.delOne(appkey);
    }

    @GetMapping("/process")
    public FlagResp processOne(@RequestParam String appkey){
        return orderService.processOne(appkey);
    }
}
