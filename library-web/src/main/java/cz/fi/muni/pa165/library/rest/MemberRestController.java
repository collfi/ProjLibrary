/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.library.rest;

import cz.fi.muni.pa165.DAException;
import cz.fi.muni.pa165.datatransferobject.BookDTO;
import cz.fi.muni.pa165.datatransferobject.MemberDTO;
import cz.fi.muni.pa165.service.api.MemberService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Rest-Api for member.
 * @author michal.lukac
 */
@RestController
public class MemberRestController {
    
    @Autowired
    public MemberService memberService;

    @RequestMapping(value="/api/member/get/{number}", method=RequestMethod.GET, produces = "application/json")
    public MemberDTO apiGetMember(ModelMap model, @PathVariable("number") int number)
    {
        try {
            MemberDTO member = memberService.findMemberByIdMember(number);
            return member;
        } catch(DAException e) {
            return null;
        }
    }

    @RequestMapping(value="/api/member/find", method=RequestMethod.GET, produces = "application/json")
    public List<MemberDTO> apiFindMembers(@RequestParam(value="name", defaultValue="") String name,
            @RequestParam(value="address", defaultValue="") String address)
    {
        List<MemberDTO> memberList = new ArrayList<MemberDTO>();
        try {
            if(name.length() > 0) {
                memberList = memberService.findMembersByName(name);
            }
            else if(address.length() > 0) {
                memberList = memberService.findMembersByAddress(address);
            }

            return memberList;
        } catch(DAException e) {
            return null;
        }
    }    
    
    @RequestMapping(value="/api/member/delete/{number}", method=RequestMethod.GET, produces = "application/json")
    public MemberDTO apiDeleteMember(ModelMap model, @PathVariable("number") int number)
    {
        try {
            MemberDTO member = memberService.findMemberByIdMember(number);
            memberService.deleteMember(member);
            return member;
        } catch(DAException e) {
            return null;
        }
    }
    
    @RequestMapping(value="/api/member/add/", method=RequestMethod.POST)
    public String apiSaveMember(@RequestBody MemberDTO member) {
        try {
            memberService.insertMember(member);
            return "Saved person: " + member.toString();
        } catch(DAException e) {
            return null;
        }
    }

    @RequestMapping(value="/api/member/update/", method=RequestMethod.POST)
    public String apiUpdateMember(@RequestBody MemberDTO member) {
        try {
            memberService.updateMember(member);
            return "Saved person: " + member.toString();
        } catch(DAException e) {
            return null;
        }
    }
}
