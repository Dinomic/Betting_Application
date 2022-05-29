package Dinomic.Betting.Services;

import Dinomic.Betting.DAOs.Account;
import Dinomic.Betting.Security.IAccountRepository;
import Dinomic.Betting.Services.Interfaces.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService implements IAccountService {

    @Autowired
    private IAccountRepository accountRepository;

    public Account getAccountByName(String name) {
        if (name.isEmpty()){
            return null;
        }
        return accountRepository.findByUsername(name).orElse(null);
    }
}
