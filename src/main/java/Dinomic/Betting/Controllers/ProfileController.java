package Dinomic.Betting.Controllers;

import Dinomic.Betting.DAOs.Account;
import Dinomic.Betting.Security.SecurityUtils;
import Dinomic.Betting.Services.BCService;
import Dinomic.Betting.Services.Interfaces.IAccountService;
import Dinomic.Betting.Utils.IConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    private static Logger LOGGER = LogManager.getLogger(BCService.class);

    @Autowired
    IAccountService accountService;

    @GetMapping("/")
    public String profileHome(Model model){

        Account account = accountService.getAccountByName(SecurityUtils.getCurrentAccountName());
        if (account != null) {
            LOGGER.info("check account name: {}", account.getUsername());
        } else {
            LOGGER.error("no user found");
            return "home";
        }
        model.addAttribute("accountWallets", account.getWallets());
        return "profile";
    }

    @GetMapping("/addNewWallet")  // change this to POST
    public String profileAddNewWallet(Model model){
        System.out.println("something cool");
        return "profile";
    }
}
