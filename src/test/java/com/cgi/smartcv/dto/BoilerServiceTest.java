package com.cgi.smartcv.dto;

import com.cgi.smartcv.persistence.BoilerRepository;
import com.cgi.smartcv.persistence.BoilerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.Assert.assertEquals;

public class BoilerServiceTest {

    BoilerService boilerService;

    @BeforeEach
    public void setup(@Mock BoilerRepository boilerRepository) {
        boilerService = new BoilerService();
    }

    @Test
    public void convertFloatToInt() {
        assertEquals(23.5, boilerService.convertIntToDouble(235), 23.5);
    }
}
