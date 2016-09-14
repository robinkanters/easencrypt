package com.robinkanters.easencrypt.crypt;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.InvalidKeyException;

public interface SymmetricalCipher {
    String encrypt(String plain, SecretKey key);
    String decrypt(String cipher, byte[] key) throws InvalidKeyException;

    SecretKey generateKey();
}
