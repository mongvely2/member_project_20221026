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

//    HttpSession session 사용시 메서드에 추가해야함
    @PostMapping("/login")
    public String login(@ModelAttribute MemberDTO memberDTO, HttpSession session, Model model) {
        boolean loginResult = memberService.login(memberDTO);
        if (loginResult) {
            session.setAttribute("loginEmail", memberDTO.getMemberEmail());
            model.addAttribute("modelEmail", memberDTO.getMemberEmail());
            return "memberMain";
        } else {
            return "memberLogin";
        }

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

    @GetMapping ("/update")
    public String updateForm(HttpSession session, Model model) {
//        session 값을 가져오기
//        getAttribute 리턴타입: abstract, abstract 타입보다 큰 타입은 없음으로 형변환 해줘야 함
        String memberEmail = (String) session.getAttribute("loginEmail");

//        memberEmail로 DB에서 해당 회원의 전체정보 조회
        MemberDTO memberDTO = memberService.updateLogin(memberEmail);
        model.addAttribute("member", memberDTO);

        return "memberUpdate";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute MemberDTO memberDTO) {
        boolean result = memberService.update(memberDTO);
        if (result) {
//            로그인 회원의 memberDetail.jsp 를 띄우려면 /member 메서드를 가져와야함
//
            return "redirect:/member?id="+memberDTO.getId();
        } else {
            return "index";
        }

    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "index";
    }








}
