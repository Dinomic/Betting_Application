package Dinomic.Betting.Security;

import Dinomic.Betting.DAOs.Account;
import Dinomic.Betting.DAOs.Authority;
import Dinomic.Betting.DOMs.SignUpForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    IAccountRepository accountRepository;

    @Autowired
    @Lazy
    PasswordEncoder encoder;

    @GetMapping("/sign-up")
    public String signUp(Model model){
        model.addAttribute("signUpForm", new SignUpForm());
        return "sign-up";
    }

    @GetMapping("/sign-in")
    public String signIn(Model model){
        return "sign-in";
    }

    @PostMapping("/process_register")
    public String processRegister(@ModelAttribute SignUpForm signUpForm) {
        String encodedPassword = encoder.encode(signUpForm.getPassword());
        Account account = new Account();
        account.setUsername(signUpForm.getUsername());
        account.setPassword(encodedPassword);
        account.setEmail(signUpForm.getEmail());
        List<Authority> authorities = new ArrayList<>();
        Authority authority = new Authority();
        authority.setAccount(account);
        authorities.add(authority);
        account.setAuthorities(authorities);
        accountRepository.save(account);
        return "redirect:/auth/sign-in?newCreated";
    }
}
