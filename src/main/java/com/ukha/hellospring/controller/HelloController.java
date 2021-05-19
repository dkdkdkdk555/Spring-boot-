package com.ukha.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello") //Http Get방식으로 해당 url의 요청을 받음
    public String hello(Model model){
        model.addAttribute("data", "hello!");
        return "hello"; /* resources/templates 의 hello를 찾아서 실행시켜라
            하면 viewResolver가 화면을 찾아서 처리한다.
        */
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(value = "name", required = false) String name, Model model){ /*
        @RequestParam를 해부면 해당 key값의 파라미터를 반드시 전달해야한다. required=true가 default값이기 때문,
        전달하지 않으면 Bad request 400 이 뜬다.
        required=false로 두면 정의한 파라미터를 반드시 전달하지 않아도 된다.
    */
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody // Http body(몸체)부에 이 응답 내용을 직접 넣어주겠다.
    public String helloString(@RequestParam("name") String name) {
        return "hello" + name;
    }

    //데이터를 내놓라고 하면..
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    static class Hello{
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
