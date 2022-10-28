package com.its.member.repository;

import com.its.member.dto.MemberDTO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Member;
import java.util.List;

@Repository
public class MemberRepository {
    @Autowired
    private static SqlSessionTemplate sql;
    public int save(MemberDTO memberDTO) {
        return sql.insert("Member.save", memberDTO);
    }

//    public MemberDTO login(String memberEmail, String memberPassword) {
//        MemberDTO memberDTO = new MemberDTO();
//        memberDTO.setMemberEmail(memberEmail);
//        memberDTO.setMemberPassword(memberPassword);
//        return sql.selectOne("Member.login", memberDTO);
//    }

    public MemberDTO login(MemberDTO memberDTO) {
        return sql.selectOne("Member.login", memberDTO);
    }

    public List<MemberDTO> memberList() {
        return sql.selectList("Member.memberList");
    }

    public MemberDTO findByid(Long id) {
        return sql.selectOne("Member.id", id);
    }

    public void delete(Long id) {
        sql.delete("Member.delete", id);
    }

    public MemberDTO updateLogin(String memberEmail) {
        return sql.selectOne("Member.memberEmail", memberEmail);
    }

    public int update(MemberDTO memberDTO) {
        return sql.update("Member.update", memberDTO);
    }

    public static MemberDTO loginCheck(String insertEmail) {
        return sql.selectOne("Member.loginCheck", insertEmail);
    }


//    public int delete(int delete) {
//        return sql.delete("Member.delete", delete);
//    }
}
