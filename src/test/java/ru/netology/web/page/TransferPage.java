package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.FindBy;
import ru.alfabank.alfatest.cucumber.annotations.Name;
import ru.alfabank.alfatest.cucumber.api.AkitaPage;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.back;


@Name("Трансферпейдж")
public class TransferPage extends AkitaPage {
    @FindBy(linkText = "Пополнение карты")
    private SelenideElement heading;

    @Name("Сумма")
    @FindBy(css = "[class='input__box'] [type='text']")
    private SelenideElement amount;

    @Name("Откуда")
    @FindBy(css = "[class='input__box'] [type='tel']")
    private SelenideElement from;

    @Name("Перевести")
    @FindBy(css = "[data-test-id=action-transfer]")
    private SelenideElement transferButton;

    @Name("Отмена")
    @FindBy(css = "[data-test-id=action-cancel]")
    private SelenideElement cancelButton;

    public TransferPage(){
        heading.shouldBe(Condition.visible);
    }

    public DashboardPage validVerify1(DataHelper.Cards transferInfo, int value) {
        amount.sendKeys(Keys.chord(Keys.CONTROL,"a"));
        amount.sendKeys(Keys.BACK_SPACE);
        amount.setValue(String.valueOf(value));
        from.sendKeys(Keys.chord(Keys.CONTROL,"a"));
        from.sendKeys(Keys.DELETE);
        from.setValue(transferInfo.getCardNumber());
        transferButton.click();
        return Selenide.page(DashboardPage.class);
    }

    public DashboardPage noValidVerify(DataHelper.Cards transferInfo, int value) {
        amount.sendKeys(Keys.chord(Keys.CONTROL,"a"));
        amount.sendKeys(Keys.BACK_SPACE);
        amount.setValue(String.valueOf(value));
        from.sendKeys(Keys.chord(Keys.CONTROL,"a"));
        from.sendKeys(Keys.DELETE);
        from.setValue(transferInfo.getCardNumber());
        cancelButton.click();
        return Selenide.page(DashboardPage.class);
    }

}
