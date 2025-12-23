package com.payment;

import com.payment.client.PaymentSimulatorClient;
import com.payment.exception.PaymentNetworkException;
import com.payment.model.PaymentMessage;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Slf4j
public class PaymentSimulatorTest {

    private PaymentSimulatorClient client;

    @BeforeClass
    public void setup() {
        client = new PaymentSimulatorClient();
    }

    @Test(description = "Verify that a standard transaction request returns a 00 Success code")
    public void testSuccessfulTransaction() throws PaymentNetworkException {
        log.info("Starting Test: Successful Transaction");

        String request = PaymentMessage.buildRequest(PaymentMessage.SALE_REQUEST, 100.00);
        String response = client.executeTransaction(request);

        Assert.assertNotNull(response, "Response should not be null");
        Assert.assertTrue(response.contains(PaymentMessage.SUCCESS_CODE),
                "Expected success code 00 but received: " + response);

        log.info("Test Passed: Success code received.");
    }

    @Test(description = "Verify that an invalid amount or specific flag triggers a decline")
    public void testDeclinedTransaction() throws PaymentNetworkException {
        log.info("Starting Test: Declined Transaction");

        String request = PaymentMessage.buildRequest(PaymentMessage.SALE_REQUEST, 9999.00);
        String response = client.executeTransaction(request);

        Assert.assertFalse(response.contains(PaymentMessage.SUCCESS_CODE),
                "Transaction should have been declined but returned success.");
        log.info("Test Passed: Decline logic verified.");
    }
}
