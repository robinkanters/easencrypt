# EasEncrypt

The simple encryption library for Java.

## Introduction

The Java encryption and cipher APIs can be a bit challenging, especially for people who are 
inexperienced in encryption. This library is meant to be an abstraction/simplification so that 
everyone can easily integrate RSA/DSA and/or AES/DES into their projects.

## Example

First, initialize the encryptors with just 3 lines of code:

```java
// Initialize the RSA cipher. This can be swapped out for a custom implementation.
AsymmetricEncrypter rsaEncrypter = new RsaEncrypter();

// Generate a keypair. Since this is the default Java keypair, it's compatible with external code.
KeyPair keyPair = rsaEncrypter.generateKeyPair();

// Initialize an encrypter with a cipher (which can be swapped out for other implementations).
LongTextEncrypter encrypter = new LongTextEncrypterImpl(new DesCipher(), rsaEncrypter);
```

...and you're off to the races! Now just encrypt and decrypt whenever you like:

### Encrypt

```java
String encrypted = encrypter.encrypt("Hello world!", keyPair.getPublic());
```

### Decrypt

```java
String decrypted = encrypter.decrypt(encrypted, keyPair.getPrivate()); // "Hello world!"
```

## Installation

Since EasEncrypt is only hosted on Github, you need to install [JitPack](https://jitpack.io) into
 your gradle project:

```grails
repositories {
    ...
    maven { url "https://jitpack.io" }
}
```

Then, you can add EasEncrypt to your dependencies:

```grails
dependencies {
    compile 'com.github.robinkanters:easencrypt:0.1'
}
```

Your final `build.gradle` file will look something like this:

```grails
group 'com.yourname'
version '1.0-SNAPSHOT'

apply plugin: 'java'

sourceCompatibility = 1.8
targetCompatibility = 1.8

repositories {
    mavenCentral()
    maven { url "https://jitpack.io" }
}

dependencies {
    // your other dependencies here
    
    compile 'com.github.robinkanters:easencrypt:0.1'
    
    // your other dependencies here
}
```

## License

EasEncrypt is distributed under the GNU General Public License v2.0. Please see [the official 
documentation](https://www.gnu.org/licenses/old-licenses/gpl-2.0.en.html) or the LICENSE file in 
this repository for more information.
