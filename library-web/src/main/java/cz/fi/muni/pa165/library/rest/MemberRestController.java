/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.library.rest;

import cz.fi.muni.pa165.library.api.dto.MemberDTO;
import cz.fi.muni.pa165.library.api.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Rest-Api for member.
 *
 * @author michal.lukac
 */
@RestController
public class MemberRestController {

    @Autowired
    public MemberService memberService;

    //delete?
    @RequestMapping(value = "/api/member/get/{number}", method = RequestMethod.GET, produces = "application/json")
    public MemberDTO apiGetMember(ModelMap model, @PathVariable("number") int number) {
        MemberDTO member = memberService.findMemberByIdMember(number);
        return member;
    }

    @RequestMapping(value = "/api/member/find", method = RequestMethod.GET, produces = "application/json")
    public List<MemberDTO> apiFindMembers(@RequestParam(value = "name", defaultValue = "") String name,
                                          @RequestParam(value = "address", defaultValue = "") String address,
                                          @RequestParam(value = "email", defaultValue = "") String email) {
        List<MemberDTO> memberList = new ArrayList<MemberDTO>();
        if (name.length() > 0) {
            memberList = memberService.findMembersByName(name);
        } else if (address.length() > 0) {
            memberList = memberService.findMembersByAddress(address);
        } else if (email.length() > 0) {
            MemberDTO m = memberService.findMemberByEmail(email);
            memberList.add(m);
        }
        return memberList;
    }

    @RequestMapping(value = "/api/member/delete/{number}", method = RequestMethod.GET, produces = "application/json")
    public MemberDTO apiDeleteMember(ModelMap model, @PathVariable("number") int number) {
        MemberDTO member = memberService.findMemberByIdMember(number);
        memberService.deleteMember(member);
        return member;
    }

    @RequestMapping(value = "/api/member/add/", method = RequestMethod.POST)
    public String apiSaveMember(@RequestBody @Valid MemberDTO member) {
        memberService.insertMember(member);
        return "Saved person: " + member.toString();
    }

    @RequestMapping(value = "/api/member/update/", method = RequestMethod.POST)
    public String apiUpdateMember(@RequestBody @Valid MemberDTO member) {
        memberService.updateMember(member);
        return "Saved person: " + member.toString();
    }
}
