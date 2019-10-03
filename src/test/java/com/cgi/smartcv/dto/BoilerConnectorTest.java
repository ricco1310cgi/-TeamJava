package com.cgi.smartcv.dto;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BoilerConnectorTest {

    BoilerController boilerController;

    @BeforeEach
    public void setup() {
        boilerController = new BoilerController();
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

    @AfterEach
    void shutdown() throws IOException, InterruptedException {
        boilerController.destroyBoiler();
        Thread.sleep(2000);
    }

}