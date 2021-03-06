package travelbuddy.common;

import java.math.BigInteger;
import java.security.SecureRandom;

/**
* The utility to generate random string.
*/
public final class SessionIdentifierGenerator {

    public static String nextSessionId() {
        return new BigInteger(130, new SecureRandom()).toString(32);
    }
}
