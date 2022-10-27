package com.its.member.controller;

import com.its.member.dto.MemberDTO;
import com.its.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MemberController {
    @Autowired
    private MemberService memberService;

    @GetMapping("/save")
    public String saveForm(){
        return "memberSave";
    }


    @PostMapping("/save")
    public String memberSave(@ModelAttribute MemberDTO memberDTO) {
        boolean saveResult = memberService.save(memberDTO);
        if (saveResult) {
            return "memberLogin";
        } else {
            return "index";
        }
    }

    @GetMapping("/login")
    public String loginForm(){
        return "memberLogin";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute MemberDTO memberDTO, Model model) {
        MemberDTO loginResult = memberService.login(memberDTO);
        model.addAttribute("loginResult", loginResult);
            return "index";
    }

    @GetMapping("/members")
    public String memberList(Model model){
        List<MemberDTO> memberList = memberService.memberList();
        model.addAttribute("memberList", memberList);
        return "memberList";
    }

    @GetMapping("/member")
    public String findByid(@RequestParam("id") Long id, Model model) {
        MemberDTO memberDTO = memberService.findByid(id);
        model.addAttribute("result", memberDTO);
        return "memberDetail";
    }

//    @GetMapping("/delete")
//    public String delete(@RequestParam("delete") Long delete, Model model) {
//        MemberDTO deleteResult = memberService.delete(delete);
//        model.addAttribute("result", deleteResult);
//        return "memberList";
//
//    }




}
