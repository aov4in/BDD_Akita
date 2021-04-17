package ru.netology.web.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Value;

public class DataHelper {
    private DataHelper() {}

    @Value
    public static class AuthInfo {
        String login;
        String password;
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    public static AuthInfo getOtherAuthInfo(AuthInfo original) {
        return new AuthInfo("petya", "123qwerty");
    }

    @Value
    public static class VerificationCode {
        private String code;
    }

    public static VerificationCode getVerificationCodeFor(AuthInfo authInfo) {
        return new VerificationCode("12345");
    }

    @Value
    @AllArgsConstructor
    public static class Cards {
        String cardNumber;
        String startBalance;
    }

    public static Cards getFirstTransferData() {
        return new Cards("5559000000000001", "10000");
    }

    public static Cards getSecondTransferData() {
        return new Cards("5559000000000002", "10000");
    }

    public static int getBalanceAfterTransfer(int balanceBefore, int value) {
        return balanceBefore - value;
    }

    public static int getBalanceAfterGet(int balanceBefore, int value) {
        return balanceBefore + value;
    }
}
