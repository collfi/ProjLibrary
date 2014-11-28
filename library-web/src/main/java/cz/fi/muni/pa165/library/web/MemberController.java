package cz.fi.muni.pa165.library.web;

import cz.fi.muni.pa165.datatransferobject.MemberDTO;
import cz.fi.muni.pa165.service.api.MemberService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Martin Malik <37428@mail.muni.cz>
 */
@Controller
public class MemberController {
    
    @Autowired
    public MemberService memberService;
    
    @RequestMapping(value = "/member/management",method = RequestMethod.GET)
    public String membermanagement(ModelMap model) {
	return "membermanagement";
    }
    
    @RequestMapping(value = "/member/addformular",method = RequestMethod.GET)
    public String addformular(ModelMap model) {
	return "addmember";
    }
    
    @RequestMapping(value = "/member/addpost", method = RequestMethod.POST)
    public String addpost(@ModelAttribute("pa165")MemberDTO member, ModelMap model) {
        
        memberService.insertMember(member);
        model.addAttribute(member);
        
        MemberDTO mem = memberService.findMemberByEmail(member.getEmail());
                
        System.out.println("member/addpose member" + member);
        System.out.println("member/addpose model" + model.toString());
        
        //member predtym zlo
        String response = "redirect:/member/id/" + String.valueOf(mem.getIdMember()); 
        System.out.println("response: " + response);
        
        return "redirect:/member/showmembers";
    }
    
    @RequestMapping("/member/id/{number}")
    public String showmember(ModelMap model, @PathVariable("number") long number)
    {
        MemberDTO member = memberService.findMemberByIdMember(number);
        model.addAttribute("member", member);
        
        return "showmember";
    }
    
    @RequestMapping("/member/edit/{number}")
    public String editmember(ModelMap model, @PathVariable("number") long number)
    {
        MemberDTO member = memberService.findMemberByIdMember(number);
        model.addAttribute("member", member);
        
        System.out.println("member v edit" + member);
            
        return "editmember";
    }
    
    @RequestMapping(value = "/member/editpost", method = RequestMethod.POST)
    public String editpost(ModelMap model, @RequestParam("idMember") long idMember, 
            @RequestParam("name") String name, @RequestParam("email") String email,
            @RequestParam("address") String address) {
        
        System.out.println("edit post" + idMember);
        
        MemberDTO member = memberService.findMemberByIdMember(idMember);
        
        System.out.println("edit post1 "   + member);
        
        member.setIdMember(idMember);
        member.setName(name);
        member.setEmail(email);
        member.setAddress(address);
        memberService.updateMember(member);
        
        System.out.println("editpost " + model.toString());
        
        model.addAttribute("member", member);
        
        System.out.println("editpost " + model.toString());
        
        System.out.println("edit post2 "   + member);
        
        System.out.println("edit post3 "   + memberService.findMemberByIdMember(idMember));
        
        
        
        return "redirect:/member/id/" + String.valueOf(member.getIdMember());
    }
    
    @RequestMapping("/member/delete/{number}")
    public String deletepost(ModelMap model, @PathVariable("number") long number)
    {
        memberService.deleteMember(memberService.findMemberByIdMember(number));
        
        //oznam o vymazani clena
        return "redirect:/member/showmembers";
    }
    
    @RequestMapping(value = "/member/showmembers",method = RequestMethod.GET)
    public String showbooks(ModelMap model) {
            
        List<MemberDTO> list = memberService.findAllMembers();
        model.addAttribute("list", list);
            
        return "showmembers";
    }
}
