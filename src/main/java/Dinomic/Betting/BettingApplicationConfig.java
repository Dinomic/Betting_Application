package Dinomic.Betting;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.EthBlockNumber;
import org.web3j.protocol.http.HttpService;

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

            LOG.info("Connected to Ethereum client version: "
                    + web3j.web3ClientVersion().send().getWeb3ClientVersion());

            EthBlockNumber result = web3j.ethBlockNumber().sendAsync().get();
            LOG.info(" The Block Number is: " + result.getBlockNumber().toString());

            return web3j;
        } catch (Exception e) {
            LOG.error("Cannot connect to BC network !!! Please check");
            throw new Exception("Cannot connect to BC network !!! Please check");
        }
    }
}
