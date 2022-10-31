package com.its.member.controller;

import com.its.member.dto.MemberDTO;
import com.its.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

//    ajax

    @GetMapping("/ajax-ex")
    public String ajaxEx() {
        return "ajaxEx";
    }

    @GetMapping("/ajax1")
    public @ResponseBody String ajax1() {    // ajax 사용시 반드시 @ResponseBody 붙여줘야함, 그렇지 않으면 리턴.jsp로 찾아감
        System.out.println("MemberController.ajax1");
        return "ok";
    }

    @PostMapping("/ajax2")
    public @ResponseBody String ajax2() {
        System.out.println("MemberController.ajax2");
        return "good";
    }

    @GetMapping("/ajax3")
    public @ResponseBody String ajax3(@RequestParam("value1") String value1,
                                      @RequestParam("value2") String value2) {
        System.out.println("MemberController.ajax3");
        System.out.println("value1 = " + value1 + ", value2 = " + value2);
        return "vvv";
    }


    @PostMapping("/ajax4")
    public @ResponseBody String ajax4(@RequestParam("value1") String value1,
                                      @RequestParam("value2") String value2) {
        System.out.println("MemberController.ajax4");
        System.out.println("value1 = " + value1 + ", value2 = " + value2);
        String value3 = "i am mong";
        return value3 + "  welcome to codingworld";
    }

    @PostMapping("/ajax5")
    public @ResponseBody MemberDTO ajax5(@RequestParam("value1") String value1,  // 리턴 타입: MemberDTO
                                      @RequestParam("value2") String value2) {
        System.out.println("MemberController.ajax5");
        System.out.println("value1 = " + value1 + ", value2 = " + value2);
        MemberDTO memberDTO = memberService.findByid(1L);
        return memberDTO;
    }

    @PostMapping("/ajax6")
    public @ResponseBody List<MemberDTO> ajax6(@RequestParam("value1") String value1,  // 리턴 타입: List<MemberDTO>
                                         @RequestParam("value2") String value2) {
        System.out.println("MemberController.ajax6");
        System.out.println("value1 = " + value1 + ", value2 = " + value2);
        List<MemberDTO> memberDTOList = memberService.memberList();
        return memberDTOList;
    }

    @PostMapping("/duplicate-check")
    public @ResponseBody String emailDuplicateCheck(@RequestParam("inputEmail") String insertEmail) {
        System.out.println("insertEmail = " + insertEmail);
        String checkResult = memberService.emailDuplicateCheck(insertEmail);
        return checkResult;
//        boolean result = memberService.loginCheck(insertEmail);
//        if (result) {
//            return "Ok";
//        } else {
//            return "No";
//        }
    }

    @GetMapping("/detail-ajax")
    public @ResponseBody MemberDTO detailMember(@RequestParam("id") Long id) {
        System.out.println("findId = " + id);
//        MemberDTO memberDTO = memberService.detailMember(findId);
//        return memberDTO;
        return memberService.findByid(id);
    }


}
