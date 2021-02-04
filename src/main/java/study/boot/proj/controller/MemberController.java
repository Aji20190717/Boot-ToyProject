package study.boot.proj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import study.boot.proj.dto.Member;
import study.boot.proj.service.MemberService;

@Controller
public class MemberController {

    @Autowired
    private MemberService biz;

    @GetMapping("/")
    public String homePage() {
        return "main";
    }

    @GetMapping("/register")
    public String regist() {
        return "register";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/registerRes")
    public String registerRes(Member member, Model model) {
        int res = biz.RegisterInsert(member);
        if(res > 0) {
            return null;
        }
        model.addAttribute("msg", "회원가입에 실패하였습니다.");
        return "error";
    }
}
