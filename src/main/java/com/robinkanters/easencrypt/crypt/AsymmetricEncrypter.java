package com.robinkanters.easencrypt.crypt;

import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;

public interface AsymmetricEncrypter {
    @SuppressWarnings("WeakerAccess")
    KeyPair generateKeyPair() throws NoSuchAlgorithmException;

    @SuppressWarnings("WeakerAccess")
    KeyPair generateKeyPair(int keySize) throws NoSuchAlgorithmException;

    byte[] encrypt(byte[] text, KeyPair keyPair);

    byte[] decrypt(byte[] text, KeyPair keyPair);
}
