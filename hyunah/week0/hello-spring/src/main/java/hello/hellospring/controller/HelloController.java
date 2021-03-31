package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "hello!!");
        return "hello";
        // resources/templates/hello.html을 화면에 띄워라
        // 컨트롤러에서 리턴 값으로 문자를 반환하면 viewResolver가 화면을 찾아 처리
        // 스프링 부트 템플릿엔진 기본 viewName 매핑
        // resources:templates/{viewName}.html
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }
    // mvc 방식

    @GetMapping("hello-spring")
    @ResponseBody
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name;
    }

    @GetMapping("hello-api")
    @ResponseBody
    // responsebody가 없으면 viewResolver한테 resources에서 해당 파일 보여줘라고 하는데
    // 없으면 리턴값을 데이터 형식으로 넘겨줘야한다는 것을 의미
    // HttpMessageConverter가 동작해서
    // 객체면 JsonConverter, 문자열이면 StringConverter가 동작
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }
    // api 방식

    static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
