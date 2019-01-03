#include <jni.h>
#include <string>

std::string jstring2string(JNIEnv *env, jstring jStr) {
    if (!jStr)
        return "";

    const jclass stringClass = env->GetObjectClass(jStr);
    const jmethodID getBytes = env->GetMethodID(stringClass, "getBytes", "(Ljava/lang/String;)[B");
    const jbyteArray stringJbytes = (jbyteArray) env->CallObjectMethod(jStr, getBytes,
                                                                       env->NewStringUTF("UTF-8"));

    size_t length = (size_t) env->GetArrayLength(stringJbytes);
    jbyte *pBytes = env->GetByteArrayElements(stringJbytes, NULL);

    std::string ret = std::string((char *) pBytes, length);
    env->ReleaseByteArrayElements(stringJbytes, pBytes, JNI_ABORT);

    env->DeleteLocalRef(stringJbytes);
    env->DeleteLocalRef(stringClass);
    return ret;
}

//we have added extern declaration so that JVM can see our function from ndk library
extern "C"
JNIEXPORT jstring JNICALL
Java_com_example_mylibrary_KeyChamber_encrypt(
        JNIEnv *env,
        jobject object, jstring msgToEncrypt) {
//    std::string str = "Hello from C++";

    std::string str = jstring2string(env, msgToEncrypt);

    for (int i = 0; (i < 100 && str[i] != '\0'); i++) {
        str[i] = str[i] + 2; //the key for encryption is 3 that is added to ASCII value
    }

    return env->NewStringUTF(str.c_str());
}


extern "C"
JNIEXPORT jstring JNICALL
Java_com_example_mylibrary_KeyChamber_decrypt(
        JNIEnv *env,
        jobject obj, jstring msgToDecrypt) {

    //string to decrypt
//    std::string str = "Jgnnq\"htqo\"E--";

    std::string str = jstring2string(env, msgToDecrypt);


    //decryption
    for (int i = 0; (i < 100 && str[i] != '\0'); i++) {
        str[i] = str[i] - 2; //the key for encryption is 3 that is subtracted to ASCII value
    }

    return env->NewStringUTF(str.c_str());
}

extern "C"
JNIEXPORT jstring JNICALL
Java_com_a8squarei_securedkeychambermodule_SecuredKeyChamber_getKey(
        JNIEnv *env,
        jobject /*this*/) {
    //must be either 16,24 or 32 bytes/character
    //we are using 24 bytes
    //which then becomes 24*8 = 192 bits
    return env->NewStringUTF("8sqreprjtedkeyAbc!@#xyz=");
}

extern "C"
JNIEXPORT jstring JNICALL
Java_com_a8squarei_securedkeychambermodule_SecuredKeyChamber_getInitVector(
        JNIEnv *env,
        jobject /*this*/) {

    return env->NewStringUTF("encryptionIntVec");
}

extern "C"
JNIEXPORT jstring JNICALL
Java_com_a8squarei_securedkeychambermodule_SecuredKeyChamber_getAlgorithm(
        JNIEnv *env,
        jobject /*this*/) {

    return env->NewStringUTF("AES");
}

extern "C"
JNIEXPORT jstring JNICALL
Java_com_a8squarei_securedkeychambermodule_SecuredKeyChamber_getCharSet(
        JNIEnv *env,
        jobject /*this*/) {

    return env->NewStringUTF("UTF-8");
}

extern "C"
JNIEXPORT jstring JNICALL
Java_com_a8squarei_securedkeychambermodule_SecuredKeyChamber_getTransformation(
        JNIEnv *env,
        jobject /*this*/) {

    return env->NewStringUTF("AES/CBC/PKCS5PADDING");
}

