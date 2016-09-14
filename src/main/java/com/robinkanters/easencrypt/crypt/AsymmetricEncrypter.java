package com.robinkanters.easencrypt.crypt;

import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

public interface AsymmetricEncrypter {
    @SuppressWarnings("WeakerAccess")
    KeyPair generateKeyPair() throws NoSuchAlgorithmException;

    @SuppressWarnings("WeakerAccess")
    KeyPair generateKeyPair(int keySize) throws NoSuchAlgorithmException;

    byte[] encrypt(byte[] text, PublicKey keyPair);

    byte[] decrypt(byte[] text, PrivateKey keyPair);
}
