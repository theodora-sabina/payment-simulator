package com.payment.client;

import com.payment.config.SimulatorConfig;
import com.payment.exception.PaymentNetworkException;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.ConnectException;

@Slf4j
public class PaymentSimulatorClient {

    public String executeTransaction(String payload) throws PaymentNetworkException {
        try (Socket socket = new Socket(SimulatorConfig.HOST, SimulatorConfig.PORT);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            socket.setSoTimeout(SimulatorConfig.TIMEOUT);
            out.println(payload + "\r\n");
            out.flush();

            log.info("Message sent: " + payload);

            String response = in.readLine();
            log.info("Response received: " + response);

            if (response == null) {
                throw new PaymentNetworkException("Simulator closed connection without sending a response.", null);
            }

            return response;

        } catch (ConnectException e) {
            throw new PaymentNetworkException("Connection refused: Is the simulator running on port " + SimulatorConfig.PORT + "?", e);
        } catch (SocketTimeoutException e) {
            throw new PaymentNetworkException("Simulator timed out after " + SimulatorConfig.TIMEOUT + "ms.", e);
        } catch (IOException e) {
            throw new PaymentNetworkException("General network failure during transaction.", e);
        }
    }
}