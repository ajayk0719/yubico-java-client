package com.yubico.client.v2.impl;

import com.yubico.client.v2.YubicoResponse;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Created by IntelliJ IDEA.
 * User: lwid
 * Date: 1/26/11
 * Time: 12:03 PM
 * To change this template use File | Settings | File Templates.
 */
public class YubicoResponseImplTest {

    @Test
    public void testParserForNullArg() {
        try {
            YubicoResponse response = new YubicoResponseImpl(null);
            fail("Expected an IOException to be thrown.");
        } catch (IOException ioe) {
        }
    }

    @Test
    public void testParser() {
        String testData=    "h=lPuwrWh8/5ZuRBN1q+v7/pCOfYo=\n" +
                            "t=2011-01-26T11:48:21Z0323\n" +
                            "otp=cccccccfhcbeceeiinhjfjhfjutfvrjetfkjlhbduvdd\n" +
                            "nonce=askjdnkagfdgdgdgggggggddddddddd\n" +
                            "status=REPLAYED_OTP\n";

        try {
            YubicoResponse response = new YubicoResponseImpl(new ByteArrayInputStream(testData.getBytes("UTF-8")));
            assertEquals("2011-01-26T11:48:21Z0323",response.getT());
            assertEquals("lPuwrWh8/5ZuRBN1q+v7/pCOfYo=", response.getH());
            assertEquals("REPLAYED_OTP", response.getStatus().toString());
            assertEquals("cccccccfhcbeceeiinhjfjhfjutfvrjetfkjlhbduvdd", response.getOtp());
            assertEquals("askjdnkagfdgdgdgggggggddddddddd", response.getNonce());
        } catch (IOException ioe) {
            fail("Encountered an exception");
        }
    }


}

