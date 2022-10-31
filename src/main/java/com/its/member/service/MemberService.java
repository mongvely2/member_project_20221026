package com.its.member.service;

import com.its.member.dto.MemberDTO;
import com.its.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;
    public boolean save(MemberDTO memberDTO) {
        int insertResult = memberRepository.save(memberDTO);
        if (insertResult > 0) {
            return true;
        } else {
            return false;
        }
    }

//    public boolean login(memberEmail, memberPassword) {
//        MemberDTO member = memberRepository.login1(memberEmail, memberPassword);
//        if
//    }

    public boolean login(MemberDTO memberDTO) {
        MemberDTO result = memberRepository.login(memberDTO);
        if (result != null) {
            return true;
        } else {
            return false;
        }
    }


    public List<MemberDTO> memberList() {
        return memberRepository.memberList();
    }

    public MemberDTO findByid(Long id) {
//        MemberDTO result = memberRepository.findByid(id);
//        return result;  // 아래랑 같은 문법_ 두 줄을 한 줄로
        return memberRepository.findByid(id);
    }

    public void delete(Long id) {
        memberRepository.delete(id);

    }

    public MemberDTO updateLogin(String memberEmail) {
        return memberRepository.updateLogin(memberEmail);
    }

    public boolean update(MemberDTO memberDTO) {
        int result = memberRepository.update(memberDTO);
        if (result>0) {
            return true;
        } else {
            return false;
        }
    }

    public String emailDuplicateCheck(String insertEmail) {
        String checkResult = memberRepository.emailDuplicateCheck(insertEmail);
        if (checkResult == null) {
            return "ok";
        } else {
            return "no";
        }
    }

//    public boolean loginCheck(String insertEmail) {
//        MemberDTO memberDTO = MemberRepository.loginCheck(insertEmail);
//        if (memberDTO != null) {
//            return true;
//        } else {
//            return false;
//        }
//    }


//    public int delete(int delete) {
//        return memberRepository.delete(delete);
////        int result = memberRepository.delete(delete);
////        return result;
//    }
}
