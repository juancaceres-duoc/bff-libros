package com.example.bff_libros.client;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "passwordResetClient", url = "${azure.functions.reset-url}")
public interface PasswordResetClient {

    @PostMapping("/biblioteca-requestPasswordReset")
    String requestPasswordReset(@RequestBody Map<String, String> request);
}
