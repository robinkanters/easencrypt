package com.robinkanters.easencrypt.crypt;

import java.security.InvalidKeyException;
import java.security.PrivateKey;
import java.security.PublicKey;

public interface LongTextEncrypter {
    String encrypt(String plain, PublicKey publicKey);

    String decrypt(String encrypted, PrivateKey privateKey) throws InvalidKeyException;
}
