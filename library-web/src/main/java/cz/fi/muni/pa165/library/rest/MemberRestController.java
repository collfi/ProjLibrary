/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.library.rest;

import cz.fi.muni.pa165.service.api.MemberService;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author michal.lukac
 */
@RestController
public class MemberRestController {
    
    public MemberService memberService;
    
}
