package study.boot.proj.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BoardController {

    @GetMapping("/list")
    public static String list() {
        return "list";
    }

    @PostMapping("/detail")
    public static String detail(String boardseq) {

        return "detail";
    }

}
