package Dinomic.Betting.Services.Interfaces;

import Dinomic.Betting.DAOs.Account;

public interface IBCService {
    void createNewWalletForAccount(Account account, String password) throws Exception;
}
