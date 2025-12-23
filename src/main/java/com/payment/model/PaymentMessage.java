package com.payment.model;

public class PaymentMessage {

    public static final String SALE_REQUEST = "0200";
    public static final String SUCCESS_CODE = "00";
    public static final String DECLINE_CODE = "05";

    public static String buildRequest(String type, double amount) {
        return String.format("TYPE:%s AMOUNT:%.2f", type, amount);
    }
}
