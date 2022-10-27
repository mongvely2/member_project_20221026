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

    public MemberDTO login(MemberDTO memberDTO) {
        return memberRepository.login(memberDTO);
        }

    public List<MemberDTO> memberList() {
        return memberRepository.memberList();
    }

    public MemberDTO findByid(Long id) {
        MemberDTO result = memberRepository.findByid(id);
        return result;
    }

//    public MemberDTO delete(Long delete) {
//        MemberDTO deleteResult = memberRepository.delete(delete);
//        return deleteResult;
//    }
}
