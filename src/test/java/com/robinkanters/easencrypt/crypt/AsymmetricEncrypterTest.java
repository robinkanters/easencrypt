package com.robinkanters.easencrypt.crypt;

import org.junit.Before;
import org.junit.Test;

import java.security.KeyPair;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AsymmetricEncrypterTest {
    private KeyPair keyPair;

    @Before
    public void setUp() throws Exception {
        final AsymmetricEncrypter rsaEncrypter = new RsaEncrypter();
        keyPair = rsaEncrypter.generateKeyPair();
    }

    @Test
    public void encrypt() throws Exception {
        assertEncryptDecryptReturnsInputString("Hello, world!");
    }

    @Test(expected = RsaEncrypter.EncryptionException.class)
    public void longText_ShouldThrowException() throws Exception {
        String plainText = "";
        for (int i=0; i<20; i++) {
            plainText += String.format("Hello world, %s!", i);
        }

        assertEncryptDecryptReturnsInputString(plainText);
    }

    private void assertEncryptDecryptReturnsInputString(String plainText) {
        final AsymmetricEncrypter rsaEncrypter = new RsaEncrypter();
        byte[] encrypted = rsaEncrypter.encrypt(plainText.getBytes(), keyPair.getPublic());
        byte[] decrypted = rsaEncrypter.decrypt(encrypted, keyPair.getPrivate());

        assertTrue(encrypted.length > 0);
        assertEquals(plainText, new String(decrypted));
    }
}