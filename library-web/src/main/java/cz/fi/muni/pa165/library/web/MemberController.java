package cz.fi.muni.pa165.library.web;

import cz.fi.muni.pa165.datatransferobject.MemberDTO;
import cz.fi.muni.pa165.service.api.MemberService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String addpost(@ModelAttribute("pa165") @Valid MemberDTO member, BindingResult bindingResult, ModelMap model,
            RedirectAttributes redirectAttributes) {
    
        if (bindingResult.hasErrors()) {
                
            redirectAttributes.addFlashAttribute("name", member.getName());
            redirectAttributes.addFlashAttribute("email", member.getEmail());
            redirectAttributes.addFlashAttribute("address", member.getAddress());
            redirectAttributes.addFlashAttribute("error", "missing");
            return "redirect:/member/addformular";
        }
        
        memberService.insertMember(member);
        model.addAttribute(member);
        
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
        
        return "editmember";
    }
    
    @RequestMapping(value = "/member/editpost", method = RequestMethod.POST)
    public String editpost(@ModelAttribute("pa165") @Valid MemberDTO member, BindingResult bindingResult, ModelMap model,
            RedirectAttributes redirectAttributes) {
        
        if (bindingResult.hasErrors()) {
                
            redirectAttributes.addFlashAttribute("name", member.getName());
            redirectAttributes.addFlashAttribute("email", member.getEmail());
            redirectAttributes.addFlashAttribute("address", member.getAddress());
            redirectAttributes.addFlashAttribute("error", "missing");
            return "redirect:/member/edit/" + String.valueOf(member.getIdMember());
            
        }
        
        try {
            memberService.updateMember(member);
        } catch (JpaSystemException jse) {
                redirectAttributes.addFlashAttribute("name", member.getName());
            redirectAttributes.addFlashAttribute("email", member.getEmail());
            redirectAttributes.addFlashAttribute("address", member.getAddress());
            redirectAttributes.addFlashAttribute("error", "missing");
            return "redirect:/member/edit/" + String.valueOf(member.getIdMember());
        }
        
        model.addAttribute("member", member);
        
        return "redirect:/member/id/" + String.valueOf(member.getIdMember());
    }
    
    @RequestMapping("/member/delete/{number}")
    public String deletepost(ModelMap model, @PathVariable("number") long number)
    {
        memberService.deleteMember(memberService.findMemberByIdMember(number));
        return "redirect:/member/showmembers";
    }
    
    @RequestMapping(value = "/member/showmembers",method = RequestMethod.GET)
    public String showbooks(ModelMap model) {
            
        List<MemberDTO> list = memberService.findAllMembers();
        model.addAttribute("list", list);
            
        return "showmembers";
    }
}
