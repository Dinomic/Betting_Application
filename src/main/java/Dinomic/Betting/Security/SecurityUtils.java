package Dinomic.Betting.Security;

import Dinomic.Betting.DAOs.Account;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;


public class SecurityUtils {

    private static Authentication getAuthentication ()
    {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public static String getCurrentAccountName () {
        return getAuthentication().getName();
    }

}
