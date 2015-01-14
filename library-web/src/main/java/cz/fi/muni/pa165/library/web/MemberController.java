package cz.fi.muni.pa165.library.web;

import cz.fi.muni.pa165.library.api.constants.Department;
import cz.fi.muni.pa165.library.api.dto.BookDTO;
import cz.fi.muni.pa165.library.api.exceptions.DuplicationException;
import cz.fi.muni.pa165.library.api.dto.MemberDTO;
import cz.fi.muni.pa165.library.api.service.LoanService;
import cz.fi.muni.pa165.library.api.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Martin Malik <37428@mail.muni.cz>
 */
@Controller
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class MemberController {

    @Autowired
    public MemberService memberService;

    @Autowired
    public LoanService loanService;

    @RequestMapping(value = "/member/management", method = RequestMethod.GET)
    public String membermanagement(ModelMap model) {
        return "membermanagement";
    }

    @RequestMapping(value = "/member/addformular", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView addformular() {
        MemberDTO member = new MemberDTO();
        return new ModelAndView("addmember", "member", member);
    }

    @RequestMapping(value = "/member/addpost", method = RequestMethod.POST)
    public String addpost(@ModelAttribute("member") @Valid MemberDTO member, BindingResult bindingResult, ModelMap model,
            RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("name", member.getName());
            redirectAttributes.addFlashAttribute("email", member.getEmail());
            redirectAttributes.addFlashAttribute("address", member.getAddress());
            redirectAttributes.addFlashAttribute("password", member.getPassword());
            redirectAttributes.addFlashAttribute("isAdmin", member.getIsAdmin());
            if (bindingResult.getFieldError("email") == null) {
                redirectAttributes.addFlashAttribute("error", "missing");
            } else {
                redirectAttributes.addFlashAttribute("error", "email");
            }
            return "redirect:/member/addformular";
        }
        try {
            memberService.insertMember(member);
        } catch (DuplicationException e) {
            redirectAttributes.addFlashAttribute("name", member.getName());
            redirectAttributes.addFlashAttribute("email", member.getEmail());
            redirectAttributes.addFlashAttribute("address", member.getAddress());
            redirectAttributes.addFlashAttribute("password", member.getPassword());
            redirectAttributes.addFlashAttribute("isAdmin", member.getIsAdmin());
            redirectAttributes.addFlashAttribute("error", "duplicate");
            return "redirect:/member/addformular";
        }

        model.addAttribute(member);

        return "redirect:/member/showmembers";
    }

    @RequestMapping("/member/id/{number}")
    public String showmember(ModelMap model, @PathVariable("number") long number) {
        MemberDTO member = memberService.findMemberByIdMember(number);
        model.addAttribute("member", member);
        model.addAttribute("list", loanService.findAllLoansByMember(member, false));
        return "showmember";
    }

    @RequestMapping("/member/edit/{number}")
    public String editmember(ModelMap model, @PathVariable("number") long number) {
        MemberDTO member = memberService.findMemberByIdMember(number);
        model.addAttribute("member", member);

        return "editmember";
    }

    @RequestMapping(value = "/member/editpost", method = RequestMethod.POST)
    public String editpost(@ModelAttribute("member") @Valid MemberDTO member, BindingResult bindingResult,
                           ModelMap model, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {

            redirectAttributes.addFlashAttribute("name", member.getName());
            redirectAttributes.addFlashAttribute("email", member.getEmail());
            redirectAttributes.addFlashAttribute("address", member.getAddress());
            redirectAttributes.addFlashAttribute("password", member.getPassword());
            redirectAttributes.addFlashAttribute("isAdmin", member.getIsAdmin());
            if (bindingResult.getFieldError("email") == null) {
                redirectAttributes.addFlashAttribute("error", "missing");
            } else {
                redirectAttributes.addFlashAttribute("error", "email");
            }
            return "redirect:/member/edit/" + String.valueOf(member.getIdMember());

        }

        try { 
            memberService.updateMember(member);
        } catch (DuplicationException e) {
            redirectAttributes.addFlashAttribute("name", member.getName());
            redirectAttributes.addFlashAttribute("email", member.getEmail());
            redirectAttributes.addFlashAttribute("address", member.getAddress());
            redirectAttributes.addFlashAttribute("password", member.getPassword());
            redirectAttributes.addFlashAttribute("isAdmin", member.getIsAdmin());
            redirectAttributes.addFlashAttribute("error", "duplicate");
            return "redirect:/member/addformular";
        }
        model.addAttribute("member", member);

        return "redirect:/member/id/" + String.valueOf(member.getIdMember());
    }

    @RequestMapping("/member/delete/{number}")
    public String deletepost(ModelMap model, @PathVariable("number") long number) {
        memberService.deleteMember(memberService.findMemberByIdMember(number));
        return "redirect:/member/showmembers";
    }

    @RequestMapping(value = "/member/showmembers", method = RequestMethod.GET)
    public String showbooks(ModelMap model) {

        List<MemberDTO> list = memberService.findAllMembers();
        model.addAttribute("list", list);

        return "showmembers";
    }

    @RequestMapping("/member/findmember")
    public ModelAndView findbooks(ModelMap model) {
        ModelAndView mav = new ModelAndView("findmember");

        mav.addObject("search", new SearchModel());

        return mav;
    }

    @RequestMapping(value = "/member/findmember/result")
    private ModelAndView processSearch(@ModelAttribute SearchModel search) {
        ModelAndView mav = new ModelAndView("findmember");
        if (search.getSearch() == null) {
            mav.addObject("search", new SearchModel());
            mav.addObject("list", new ArrayList<MemberDTO>());
            return mav;
        }
        mav.addObject("search", search);

        if (search.getSearch().equals("email")) {
            List<MemberDTO> l = new ArrayList<>();
            l.addAll(memberService.findMembersByEmail(search.getInput()));
            mav.addObject("list", l);
        } else if (search.getSearch().equals("Name")) {
            mav.addObject("list", memberService.findMembersByName(search.getInput()));
        }
        return mav;
    }
}
