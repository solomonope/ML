/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innovworks.jobmanager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Folorunsho Solomon
 */
@Controller
@RequestMapping("/")
public class KimonoJobManagerController {

    @Autowired
    KimonoJobManager kimonoJobManager;

    public String index(){
        
        
        return null;
    }
}
