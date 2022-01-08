package com.closememo.admin.controller.view;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Controller
@RequestMapping("/admin")
public @interface ViewController {

}
