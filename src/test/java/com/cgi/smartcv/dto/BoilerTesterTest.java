package com.cgi.smartcv.dto;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

public class BoilerTesterTest {

    BoilerConnector boilerConnector;
    BoilerController boilerController;

    @BeforeEach
    public void setup() {
        boilerConnector = new BoilerConnector();
        boilerController = new BoilerController();
    }

    @Test
    public void startBoiler() {
        Assert.assertEquals(true, boilerConnector.runBoilerSimulator());
    }

    @Test
    public void connectBoiler() {
        Assert.assertEquals(true, boilerConnector.connectBoiler());
    }

    @Test
    public void getInfoFromBoiler() {

    }
}
