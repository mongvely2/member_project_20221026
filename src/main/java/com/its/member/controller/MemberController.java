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

import javax.servlet.http.HttpSession;
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


//    @PostMapping("/login")
//    public String login1(@RequestParam("memberEmail") String memberEmail,
//                         @RequestParam("memberPassword") String memberPasswrd) {
//        boolean loginResult = memberService.login(memberEmail, memberPassword);
//    }

    @PostMapping("/login")
    public String login(@ModelAttribute MemberDTO memberDTO, Model model, HttpSession session) {
        MemberDTO loginResult = memberService.login(memberDTO);
        model.addAttribute("loginResult", loginResult);
//        세션에 로그인한 사용자의 이메일을 저장
        session.setAttribute("loginEmail", memberDTO.getMemberEmail());
        model.addAttribute("modelEmail", memberDTO.getMemberEmail());
            return "memberMain";
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
//    public String delete(@RequestParam("delete") int delete, Model model) {
//        int deleteResult = memberService.delete(delete);
//        System.out.println("delete = " + delete + ", model = " + model);
//        model.addAttribute("result", deleteResult);
//        return "memberList";
//
//    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") Long id, Model model) {
        memberService.delete(id);
//        1. 삭제 후 목록을 DB에서 가져오고 memberList.jsp로 간다.
//        List<MemberDTO> memberDTOList = memberService.memberList();
//        model.addAttribute("memberList", memberDTOList);
//        return "memberList";

//        2.redirect 방식을 이용하여 /members("memberList(findAll) 메서드 불러오기") 주소 요청
        return "redirect:/members";



    }






}
