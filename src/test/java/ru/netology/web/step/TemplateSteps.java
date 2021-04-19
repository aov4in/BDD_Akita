package ru.netology.web.step;


import com.codeborne.selenide.Selenide;
import cucumber.api.java.ru.Когда;
import cucumber.api.java.ru.Пусть;
import cucumber.api.java.ru.Тогда;
import lombok.val;
import ru.alfabank.alfatest.cucumber.api.AkitaScenario;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.TransferPage;
import ru.netology.web.page.DashboardPage;
import ru.netology.web.page.LoginPage;
import ru.netology.web.page.VerificationPage;

import static com.codeborne.selenide.Selenide.page;
import static org.junit.Assert.*;
import static ru.alfabank.tests.core.helpers.PropertyLoader.loadProperty;

public class TemplateSteps {
    private final AkitaScenario scenario = AkitaScenario.getInstance();

    @Пусть("^пользователь залогинен с именем \"([^\"]*)\" и паролем \"([^\"]*)\"$")
    public void loginWithNameAndPassword(String login, String password) {
        val loginUrl = loadProperty("loginUrl");
        Selenide.open(loginUrl);

        scenario.setCurrentPage(page(LoginPage.class));
        val loginPage = (LoginPage) scenario.getCurrentPage().appeared();
        val authInfo = new DataHelper.AuthInfo(login, password);
        scenario.setCurrentPage(loginPage.validLogin(authInfo));

        val verificationPage = (VerificationPage) scenario.getCurrentPage().appeared();
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        scenario.setCurrentPage(verificationPage.validVerify(verificationCode));
    }


    @Когда("^он переводит \"([^\"]*)\" рублей с карты с номером \"([^\"]*)\" на свою \"1\" карту с главной страницы;$")
    public void shouldTransferCard2ToCard1(String amountTransfer, String CardFromTransfer)  {
        val dashboardPage = (DashboardPage) scenario.getCurrentPage().appeared();
        scenario.setCurrentPage(dashboardPage.validToTransferCard1());
        val transferPage = (TransferPage)scenario.getCurrentPage().appeared();
        scenario.setCurrentPage(transferPage.validVerify1(amountTransfer,CardFromTransfer));
        scenario.getCurrentPage().appeared();
    }

    @Тогда("^баланс его \"([^\"]*)\" карты из списка на главной странице должен стать \"([^\"]*)\" рублей\\.$")
    public void balanceFirstCardVerify(String firstCard, String sum) throws Throwable {
        val dashBoardPage = (DashboardPage) scenario.getCurrentPage().appeared();
        val actualResultFirst = dashBoardPage.getFirstCardBalance();
        assertEquals(String.valueOf(actualResultFirst), sum);
    }
}
