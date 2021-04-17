package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import lombok.val;
import org.openqa.selenium.support.FindBy;
import ru.alfabank.alfatest.cucumber.annotations.Name;
import ru.alfabank.alfatest.cucumber.api.AkitaPage;

import java.util.Collection;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

@Name("Дашбоард")
public class DashboardPage extends AkitaPage {
    @FindBy(css = "[data-test-id=dashboard]")
    private SelenideElement heading;

    @Name("Пополнить баланс первой карты")
    @FindBy(css = "[data-test-id='92df3f1c-a033-48e6-8390-206f6b1f56c0'] button")
    private SelenideElement upBalanceCard1;

    @Name("Пополнить баланс второй карты")
    @FindBy(css = "[data-test-id='0f3f5c2a-249e-4c3d-8287-09f7a039391d'] button")
    private SelenideElement upBalanceCard2;

    @Name("Обновить страницу")
    @FindBy(css = "[data-test-id=action-reload]")
    private SelenideElement updateBalance;

    @Name("Отменить действия")
    @FindBy(css = "data-test-id=action-cancel")
    private SelenideElement cancelTransfer;

    @Name("Карты")
    @FindBy(css = ".list__item")
    private ElementsCollection cards;

    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";

    DashboardPage(){
        heading.shouldBe(visible);
    }

    public int getFirstCardBalance() {
        val text = cards.first().text();
        return extractBalance(text);
    }
    public int getSecondCardBalance() {
        val text = cards.last().text();
        return extractBalance(text);
    }

    private int extractBalance(String text) {
        val start = text.indexOf(balanceStart);
        val finish = text.indexOf(balanceFinish);
        val value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }

    public TransferPage validToTransferCard1() {
        upBalanceCard1.click();
        return Selenide.page(TransferPage.class);
    }

    public TransferPage validToTransferCard2() {
        upBalanceCard2.click();
        return Selenide.page(TransferPage.class);
    }

    public TransferPage TransferCardPage(){
        updateBalance.click();
        return Selenide.page(TransferPage.class);
    }

}
