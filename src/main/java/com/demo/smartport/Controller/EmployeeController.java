package com.demo.smartport.Controller;

import com.demo.smartport.Entity.UserAndEmployee.EmployeeConfirmReq;
import com.demo.smartport.Entity.FlagResp;
import com.demo.smartport.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee")
@CrossOrigin
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/confirm")
    public FlagResp confirmEmployee(@RequestBody EmployeeConfirmReq req){
        return employeeService.confirmEmployee(req);
    }
}
