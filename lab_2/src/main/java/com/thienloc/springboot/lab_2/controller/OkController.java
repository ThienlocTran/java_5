package com.thienloc.springboot.lab_2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class OkController {
        @RequestMapping("/ok")
        public String ok() {
            return "ok";
        }

        @RequestMapping("ok/1")
        public String m1(){return "ok1";}

        @RequestMapping("/ok/2")
        public String m2(){return "ok2";}
        @RequestMapping("/ok/3")
        public String m3(){return "ok3";}
}
