package com.yanyun.controller;

import com.yanyun.domain.UserDTO;
import com.yanyun.holder.LoginUserHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xcai
 * @version 1.0
 * @date 2020/11/30/20:56
 * @description
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private LoginUserHolder loginUserHolder;

    @GetMapping("/currentUser")
    public UserDTO currentUser() {
        return loginUserHolder.getCurrentUser();
    }
}
