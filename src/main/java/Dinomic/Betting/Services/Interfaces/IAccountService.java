package Dinomic.Betting.Services.Interfaces;

import Dinomic.Betting.DAOs.Account;
import org.springframework.stereotype.Service;

@Service
public interface IAccountService {
    Account getAccountByName(String name);
}
