package ru.netology.web.page;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import ru.alfabank.alfatest.cucumber.annotations.Name;
import ru.alfabank.alfatest.cucumber.api.AkitaPage;


@Name("Трансферпейдж")
public class TransferPage extends AkitaPage {
    @Name("Сумма")
    @FindBy(css = "[data-test-id=amount] .input__control")
    private SelenideElement amountField;

    @Name("Откуда")
    @FindBy(css = "[data-test-id=from] .input__control")
    private SelenideElement from;

    @Name("Перевести")
    @FindBy(css = "[data-test-id=action-transfer]")
    private SelenideElement transferButton;

    @Name("Отмена")
    @FindBy(css = "[data-test-id=action-cancel]")
    private SelenideElement cancelButton;

    public DashboardPage validVerify1(String amount, String cardNumber) {
        amountField.setValue(amount);
        from.setValue(cardNumber);
        transferButton.click();
        return Selenide.page(DashboardPage.class);
    }

}
