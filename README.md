# SecuredKeyChamber
A better way to Encrypt and Decrypt the String values.

### Features
Encrypt and Decrypt the messages with **[AES](https://en.wikipedia.org/wiki/Advanced_Encryption_Standard)**(Advanced Encryption Standard) Algorithm.

### Install And Setup
1. Add the **`securedkeychambermodule-release.aar`** to your libs directory(find under app/libs)
2. Add the aar file to your app level build gradle

```
dependencies{
implementation(name: 'securedkeychambermodule-release', ext: 'aar')
implementation fileTree(include: ['*.jar'], dir: 'libs')
...
}
```

### Usages
Create the object of SecuredKeyChamber

```SecuredKeyChamber keyChamber = new SecuredKeyChamber();```

##### Now you can encrypt or decrypt the string using 

```
String encryptedMessage = keyChamber.encrypt("text to encrypt");
String decryptedMessage = keyChamber.decrypt(encryptedMessage);
```

### Advanced
All the parameters required for AES encryption and decryption are written in C++, which makes the SecuredKeyChamber Highly secured. And good news is it is hard to deassemble the `.so` file, `.so` file contains the jni functions which provides the required parameters (Key,ivParameterIspec,Charset,Algorith and transformation) for AES encryption and decryption.

If you want to customize the above parameters you can modify the `securedkeychambermodule` with desired parameters.



* Developed By-[Ram Mandal](https://www.linkedin.com/in/ram-mandal-90470b88/)
* Associated With -[Eight Squarei](http://8squarei.com/)

