package app.service;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class Crypt {
    public static final Charset CHARSET = StandardCharsets.UTF_16;
    private static final char DELIMITER = ':';
    private static final MessageDigest digest;
    private static final SecureRandom random;

    static {
        try {
            digest = MessageDigest.getInstance("SHA-512");
        } catch (NoSuchAlgorithmException e) {
            throw new AssertionError(e);
        }
        random = new SecureRandom();
        String password = "1234qwer";
        String hash = hash(password);
        if (!compare(password, hash)) {
            throw new AssertionError();
        }
    }

    public static String hash(String password) {
        if (password == null)
            return null;
        String salt = generateSalt();
        return salt + DELIMITER + hash(password, salt);
    }

    public static boolean compare(String password, String hash) {
        if (password == null || hash == null)
            return false;
        int i = hash.indexOf(DELIMITER);
        if (!(i > -1 && i + 1 < hash.length()))
            return false;
        String salt = hash.substring(0, i);
        String expected = hash.substring(i + 1);
        String actual = hash(password, salt);
        return expected.equals(actual);
    }

    private static String hash(String password, String salt) {
        synchronized (digest) {
            digest.reset();
            digest.update(password.getBytes(CHARSET));
            digest.update(salt.getBytes(CHARSET));
            return new String(digest.digest(), CHARSET);
        }
    }

    private static String generateSalt() {
        synchronized (random) {
            byte[] salt = new byte[32];
            random.nextBytes(salt);
            return new String(salt, CHARSET);
        }
    }
}