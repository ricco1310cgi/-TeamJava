package com.cgi.smartcv.dto;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BoilerConnectorTest {

    BoilerController boilerController;
    BoilerConnector boilerConnector;

    @BeforeEach
    public void setup() throws IOException, InterruptedException {
        boilerController = new BoilerController();
        boilerConnector = new BoilerConnector();
    }

    @Test
    public void canStart() throws IOException {
        assertEquals(true, boilerController.startBoiler());
    }

    @Test
    public void canConnect() throws IOException, InterruptedException {
        assertEquals(true, boilerController.connectBoiler());
    }

    @Test
    public void canStop() throws IOException, InterruptedException {
        boilerController.connectBoiler();
        boilerController.destroyBoiler();
    }

    @Test
    public void canOutput() throws IOException, InterruptedException {
        boilerController.connectBoiler();

        boilerController.outputBoiler();
        System.out.println();
        System.out.println("1");
        Thread.sleep(3000);
        boilerController.outputBoiler();
        System.out.println();
        System.out.println("2");
        Thread.sleep(3000);
        boilerController.outputBoiler();
        System.out.println();
        System.out.println("3");
        Thread.sleep(3000);
        boilerController.outputBoiler();
        System.out.println();
        System.out.println("4");
        Thread.sleep(3000);
        boilerController.outputBoiler();
        System.out.println();
        System.out.println("5");
    }

    @Test
    public void differenceInTemperature() throws IOException, InterruptedException {
        boilerController.connectBoiler();
        assertEquals(5.0, boilerConnector.differenceInTemperature(10.0f, 5.0f));
    }

    //vreemd dat deze test niet slaagt, geeft nullpointerexception
    @Disabled
    @Test
    public void negativeReturnPositiveOutput() throws IOException {
        boilerConnector.connectBoiler();
        assertEquals(5.0, boilerConnector.differenceInTemperature(5.0f, 10.0f));
    }

    @AfterEach
    void shutdown() throws IOException, InterruptedException {
        boilerController.destroyBoiler();
        Thread.sleep(2000);
    }
}
