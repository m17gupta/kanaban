package com.capstoneproject.taskdata.proxy;

import com.capstoneproject.taskdata.domain.CommonUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="authentication-server",url= "localhost:8881")
public interface EmployeeProxy {
    @PostMapping("/employee-app/v1/employee-add")
    public ResponseEntity<?> sendEmployeeObjectToAuthApp( @RequestBody CommonUser commonUser);
}
