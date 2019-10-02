package com.cgi.smartcv.dto;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalTime;
import java.util.Timer;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class BoilerConnectorTest {

    BoilerController boilerController;

    @BeforeEach
    public void setup() {
        boilerController = new BoilerController();
    }

    @Test
    public void canConnect() throws IOException {
        assertEquals(true, boilerController.connectBoiler());
    }

    @Test
    public void canExit() throws IOException, InterruptedException {
        Timer timer = new Timer();
        boilerController.connectBoiler();

        boilerController.outputBoiler();
        System.out.println("1");
        System.out.println();
        Thread.sleep(3000);
        boilerController.outputBoiler();
        System.out.println("2");
        System.out.println();
        Thread.sleep(3000);
        boilerController.outputBoiler();
        System.out.println("3");
        System.out.println();
        Thread.sleep(3000);
        boilerController.outputBoiler();
        System.out.println("4");
        System.out.println();
        Thread.sleep(3000);
        boilerController.destroyBoiler();
    }

    @AfterEach
    void shutdown() throws IOException {
        boilerController.destroyBoiler();
    }

}