package com.codingrecipe.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/") //시작페이지를 받아주는 메서드를 정의
    public String index() {  //index html로 가라고함,컨트롤러의 핸들러함수, 해당 이름을 가진 view를 찾음
        System.out.println("HomeController.index");
        return "index";
    }
}
