package Dinomic.Betting;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Keys;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.Transfer;
import org.web3j.utils.Convert;

import java.math.BigDecimal;
import java.math.BigInteger;

@Configuration
public class BettingApplicationConfig {

    private static Logger LOG = LogManager.getLogger(BettingApplicationConfig.class);

    @Value("${blockchain.network.url:null}")
    private String bcUrl;

    @Value("${blockchain.mainAccount.address:null}")
    private String mainAddress;

    @Value("${blockchain.mainAccount.privateKey:null}")
    private String mainPrivateKey;

    @Bean
    public Web3j getWeb3j() throws Exception {
        try {
            Web3j web3j = Web3j.build(new HttpService(bcUrl));
//        checkConnectionSuccessfully(web3j);

            LOG.info("Connected to Ethereum client version: "
                    + web3j.web3ClientVersion().send().getWeb3ClientVersion());
//            System.out.println("Connected to Ethereum client version: "
//                    + web3j.web3ClientVersion().send().getWeb3ClientVersion());

            return web3j;
        } catch (Exception e) {
            LOG.error("Cannot connect to BC network !!! Please check");
            throw new Exception("Cannot connect to BC network !!! Please check");
        }
    }

    private void checkConnectionSuccessfully(Web3j web3j) throws Exception {
        try {
            final ECKeyPair randomEcKeyPair = Keys.createEcKeyPair();
            final String randomAddress = "0x" + Keys.getAddress(randomEcKeyPair);

            if (mainAddress == null || mainPrivateKey == null) {
                throw new Exception("cannot get main account config");
            }
            final TransactionReceipt receipt = Transfer.sendFunds(web3j, Credentials.create(mainPrivateKey),
                    randomAddress, BigDecimal.ONE, Convert.Unit.ETHER).send();

            Thread.sleep(10000);

            EthGetBalance balance = web3j.ethGetBalance(randomAddress, DefaultBlockParameterName.LATEST).send();
            if (BigInteger.ZERO.equals(balance.getBalance())) {
                throw new Exception("cannot get main account balance info");
            }
        } catch (Exception e) {
            throw new Exception (e.getMessage());
        }

    }

}
