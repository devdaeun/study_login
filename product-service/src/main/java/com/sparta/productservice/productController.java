package com.sparta.productservice;

import com.sparta.commonmodule.aop.RoleCheck;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/products")
public class productController {

    @GetMapping("/test")
    public ResponseEntity<?> getproduct(@RequestHeader("user_id") String user_id,
                                        @RequestHeader("role") String role,
                                        @RequestHeader("slack_name") String slackName) {
        return ResponseEntity.ok("Hello World " + user_id + " " + role + " " + slackName);
    }

    @RoleCheck("ROLE_MASTER,ROLE_COMPANY")
    @GetMapping("/test2")
    public ResponseEntity<?> getproduct2() {
        return ResponseEntity.ok("Hello World");
    }

    @GetMapping("/test3")
    public ResponseEntity<?> getproduct3() {
        return ResponseEntity.ok("Hello World3");
    }

}
