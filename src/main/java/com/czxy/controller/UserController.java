package com.czxy.controller;

import com.czxy.domain.User;
import com.czxy.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("page/user")
public class UserController {
    @Resource
    private UserService userService;


    @PostMapping("login")
    public ResponseEntity<Boolean> login(User user, HttpSession session, HttpServletResponse response) {
        User userObj = userService.findUserByNameAndPassword(user);
        if (userObj!=null){
            //成功
            //将用户保存到session
            session.setAttribute("user",userObj);
            return ResponseEntity.ok(true);
        }else {
            return ResponseEntity.ok(false);
        }

    }

    //展示时间
    @GetMapping("/time")
    public ResponseEntity<String> getTime(HttpSession session) {
        String time = (String) session.getAttribute("time");
        return ResponseEntity.ok(time);
    }


    @GetMapping("/name")
    public ResponseEntity<User> getName(HttpSession session){
        User user = (User) session.getAttribute("user");
        System.out.println(user);
        return ResponseEntity.ok(user);
    }



}
