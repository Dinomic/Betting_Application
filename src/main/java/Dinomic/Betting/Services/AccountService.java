package Dinomic.Betting.Services;

import Dinomic.Betting.DAOs.Account;
import Dinomic.Betting.Security.IAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

public class AccountService {

    @Autowired
    private IAccountRepository accountRepository;

    public Account getAccountByName(String name) {
        if (name.isEmpty()){
            return null;
        }
        return accountRepository.findByUsername(name).orElse(null);
    }
}
