/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.library.rest;

import cz.fi.muni.pa165.library.api.dto.MemberDTO;
import cz.fi.muni.pa165.library.api.service.MemberService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
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

    protected final Log logger = LogFactory.getLog(getClass());

    @Autowired
    public MemberService memberService;

    private void doAutoLogin() {
        List<SimpleGrantedAuthority> auths = new java.util.ArrayList<SimpleGrantedAuthority>();
        auths.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        Authentication auth = new UsernamePasswordAuthenticationToken("rest", null, auths);
        SecurityContextHolder.getContext().setAuthentication(auth);
    }

    @RequestMapping(value = "/api/member/find", method = RequestMethod.GET, produces = "application/json")
    public List<MemberDTO> apiFindMembers(
                                          @RequestParam(value = "name", defaultValue = "") String name,
                                          @RequestParam(value = "address", defaultValue = "") String address,
                                          @RequestParam(value = "email", defaultValue = "") String email) {

        logger.error("ya tut");
        doAutoLogin();
        List<MemberDTO> memberList = new ArrayList<MemberDTO>();
        if (name.length() > 0) {
            memberList = memberService.findMembersByName(name);
        } else if (address.length() > 0) {
            memberList = memberService.findMembersByAddress(address);
        } else if (email.length() > 0) {
            List<MemberDTO> m = memberService.findMembersByEmail(email);
            memberList.addAll(m);
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
