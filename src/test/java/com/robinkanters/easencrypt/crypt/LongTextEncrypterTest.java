package com.robinkanters.easencrypt.crypt;

import com.robinkanters.easencrypt.cipher.AesCipher;
import com.robinkanters.easencrypt.cipher.DesCipher;
import org.junit.Before;
import org.junit.Test;

import java.security.KeyPair;

import static java.lang.System.out;
import static java.util.Arrays.asList;
import static org.junit.Assert.*;

public class LongTextEncrypterTest {
    private KeyPair keyPair;
    private AsymmetricEncrypter rsaEncrypter;

    @Before
    public void setUp() throws Exception {
        rsaEncrypter = new RsaEncrypter();
        keyPair = rsaEncrypter.generateKeyPair(512);
    }

    @Test
    public void longText() throws Exception {
        String plain = "Hello, world!";

        LongTextEncrypter longTextEncrypter = new LongTextEncrypterImpl(new DesCipher(), rsaEncrypter);

        String encrypted = longTextEncrypter.encrypt(plain, keyPair.getPublic());
        assertNotEquals(plain, encrypted);
        assertEquals(plain, longTextEncrypter.decrypt(encrypted, keyPair.getPrivate()));

        writeln("Encrypted with DES as:",
                encrypted,
                "");
    }

    private void writeln(String... x) {
        asList(x).forEach(out::println);
    }

    @Test
    public void aes() throws Exception {
        String plain = "Hello, world!";

        LongTextEncrypter longTextEncrypter = new LongTextEncrypterImpl(new AesCipher(), rsaEncrypter);

        String encrypted = longTextEncrypter.encrypt(plain, keyPair.getPublic());
        assertNotEquals(plain, encrypted);
        assertEquals(plain, longTextEncrypter.decrypt(encrypted, keyPair.getPrivate()));

        writeln("Encrypted with AES as:");
        writeln(encrypted);
    }
}