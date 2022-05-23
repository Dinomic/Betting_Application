package Dinomic.Betting.Controllers;

import Dinomic.Betting.DAOs.Account;
import Dinomic.Betting.Security.SecurityUtils;
import Dinomic.Betting.Services.Interfaces.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    IAccountService accountService;

    @GetMapping({"/", "/home"})
    public String home(){
        Account account = accountService.getAccountByName(SecurityUtils.getCurrentAccountName());
        return "home";
    }

}
