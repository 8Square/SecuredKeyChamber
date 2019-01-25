# SecuredKeyChamber
A better way to Encrypt and Decrypt the String values.

### Features
Encrypt and Decrypt the messages with **[AES](https://en.wikipedia.org/wiki/Advanced_Encryption_Standard)**(Advanced Encryption Standard) Algorithm.

### Install And Setup
1. Add the **securedkeychambermodule-release.aar** to your libs directory
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
The key,initVector,charset,algorithm and transformations are present in the .cpp file
### Advance
The key 

* Developed By-[Ram Mandal](https://www.linkedin.com/in/ram-mandal-90470b88/)
* Associated With -[Eight Squarei](http://8squarei.com/)

