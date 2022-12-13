package approach;

import approach.CurrentPage;

public class BuyFeatures {

    static public void buyTokens(CurrentPage currentPage, int count) {

         String balanceUser = currentPage.getCurrentUser().getCredentials().getBalance();
         String newBalance = Integer.toString(Integer.parseInt(balanceUser) - count);
         currentPage.getCurrentUser().getCredentials().setBalance(newBalance);

         int tokensUser = currentPage.getCurrentUser().getTokensCount();
         currentPage.getCurrentUser().setTokensCount(tokensUser + count);
    }

    static public void buyPremiumAccount(CurrentPage currentPage) {

        currentPage.getCurrentUser().getCredentials().setAccountType("premium");
        int tokensUser = currentPage.getCurrentUser().getTokensCount();
        currentPage.getCurrentUser().setTokensCount(tokensUser - 10);
    }
}
