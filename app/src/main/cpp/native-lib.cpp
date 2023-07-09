#include <jni.h>
#include <string>
#include <iostream>

extern "C"
JNIEXPORT jstring JNICALL
Java_com_test_nano_1suite_activities_Splash_00024Companion_baseAPIURL(JNIEnv *env, jobject thiz) {
    std::string baseURL = "https://fakestoreapi.com/";
    return env->NewStringUTF(baseURL.c_str());
}