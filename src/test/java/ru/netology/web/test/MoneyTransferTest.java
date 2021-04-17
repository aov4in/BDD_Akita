package ru.netology.web.test;

import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.DashboardPage;
import ru.netology.web.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;

public class MoneyTransferTest {

    @Test
    void shouldTransferCard2ToCard1(){
        val loginPage = open("http://localhost:9999/", LoginPage.class);
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        val dashboardPage =  verificationPage.validVerify(verificationCode);
        val value = 400;
        val firstCardBalance = dashboardPage.getFirstCardBalance();
        val secondCardBalance = dashboardPage.getSecondCardBalance();
        val firstCardBalanceAfter = DataHelper.getBalanceAfterGet(firstCardBalance, value);
        val secondCardBalanceAfter = DataHelper.getBalanceAfterTransfer(secondCardBalance, value);
        val transferInfo = DataHelper.getSecondTransferData();
        val transferPage = dashboardPage.validToTransferCard1();
        transferPage.validVerify1(transferInfo, value);
        val actualCurrentFirstCard = dashboardPage.getFirstCardBalance();
        Assertions.assertEquals(firstCardBalanceAfter, actualCurrentFirstCard);
        val actualCurrentSecondCard = dashboardPage.getSecondCardBalance();
        Assertions.assertEquals(secondCardBalanceAfter, actualCurrentSecondCard);
    }

    @Test
    void shouldTransferCard1ToCard2(){
        val loginPage = open("http://localhost:9999/", LoginPage.class);
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        val dashboardPage =  verificationPage.validVerify(verificationCode);
        val value = 600;
        val firstCardBalance = dashboardPage.getFirstCardBalance();
        val secondCardBalance = dashboardPage.getSecondCardBalance();
        val firstCardBalanceAfter = DataHelper.getBalanceAfterTransfer(firstCardBalance, value);
        val secondCardBalanceAfter = DataHelper.getBalanceAfterGet(secondCardBalance, value);
        val transferInfo = DataHelper.getFirstTransferData();
        val transferPage = dashboardPage.validToTransferCard2();
        transferPage.validVerify1(transferInfo, value);
        val actualCurrentFirstCard = dashboardPage.getFirstCardBalance();
        Assertions.assertEquals(firstCardBalanceAfter, actualCurrentFirstCard);
        val actualCurrentSecondCard = dashboardPage.getSecondCardBalance();
        Assertions.assertEquals(secondCardBalanceAfter, actualCurrentSecondCard);
    }

    @Test
    void shouldCancelTransferCardToCard(){
        val loginPage = open("http://localhost:9999/", LoginPage.class);
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        val dashboardPage =  verificationPage.validVerify(verificationCode);
        val value = 111000;
        val transferInfo = DataHelper.getSecondTransferData();
        val transferPage = dashboardPage.validToTransferCard1();
        transferPage.noValidVerify(transferInfo, value);
    }

    @Test
    void shouldBadTransfer(){
        val loginPage = open("http://localhost:9999/", LoginPage.class);
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        val dashboardPage =  verificationPage.validVerify(verificationCode);
        val value = 60000;
        val firstCardBalance = dashboardPage.getFirstCardBalance();
        val secondCardBalance = dashboardPage.getSecondCardBalance();
        val firstCardBalanceAfter = DataHelper.getBalanceAfterTransfer(firstCardBalance, value);
        val secondCardBalanceAfter = DataHelper.getBalanceAfterGet(secondCardBalance, value);
        val transferInfo = DataHelper.getFirstTransferData();
        val transferPage = dashboardPage.validToTransferCard2();
        transferPage.validVerify1(transferInfo, value);
        val actualCurrentFirstCard = dashboardPage.getFirstCardBalance();
        Assertions.assertEquals(firstCardBalanceAfter, actualCurrentFirstCard);
        val actualCurrentSecondCard = dashboardPage.getSecondCardBalance();
        Assertions.assertEquals(secondCardBalanceAfter, actualCurrentSecondCard);

    }

}
