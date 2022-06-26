package Dinomic.Betting.Services;

import Dinomic.Betting.Services.Interfaces.IBCService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Keys;
import org.web3j.crypto.Wallet;
import org.web3j.crypto.WalletFile;
import org.web3j.protocol.Web3j;

@Service
public class BCService implements IBCService {

    private static Logger LOG = LogManager.getLogger(BCService.class);

    @Autowired
    Web3j localWeb3j;

    @Override
    public String createNewBCAccount(){
        try {
            ECKeyPair keyPair = Keys.createEcKeyPair();
            WalletFile newWallet = Wallet.createLight("myPassword", keyPair);

        } catch (Exception e) {
            LOG.error("ERROR WHILE CREATE NEW WALLET");
        }

        return Strings.EMPTY;
    }
}
