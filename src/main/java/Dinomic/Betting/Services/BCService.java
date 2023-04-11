package Dinomic.Betting.Services;

import Dinomic.Betting.DAOs.Account;
import Dinomic.Betting.Services.Interfaces.IBCService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.crypto.*;
import org.web3j.protocol.Web3j;

import java.io.File;

@Service
public class BCService implements IBCService {

    private static Logger LOGGER = LogManager.getLogger(BCService.class);

    @Autowired
    Web3j localWeb3j;

    @Override
    public void createNewWalletForAccount(Account account, String password) throws Exception {
        if (Strings.isBlank(password)){
            throw new Exception("Invalid password when creating Blockchain account!");
        }
        try {

            String fileName = WalletUtils.generateFullNewWalletFile(password, new File("/wallets"));
//
//            ECKeyPair keyPair = Keys.createEcKeyPair();
//            WalletFile newWallet = Wallet.createLight(password, keyPair);
//

        } catch (Exception e) {
            LOGGER.error("ERROR WHILE CREATE NEW WALLET");
        }

    }
}
