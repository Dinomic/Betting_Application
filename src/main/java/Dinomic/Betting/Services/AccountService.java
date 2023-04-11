package Dinomic.Betting.Services;

import Dinomic.Betting.DAOs.Account;
import Dinomic.Betting.Security.IAccountRepository;
import Dinomic.Betting.Services.Interfaces.IAccountService;
import Dinomic.Betting.Services.Interfaces.IBCService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService implements IAccountService {

    @Autowired
    private IAccountRepository accountRepository;

    @Autowired
    private IBCService bcService;

    public Account getAccountByName(String name) {
        if (name.isEmpty()){
            return null;
        }
        return accountRepository.findByUsername(name).orElse(null);
    }

    @Override
    public void addRandomNewWallet(Account account, String password) throws Exception {
        bcService.createNewWalletForAccount(account, password);
    }
}
