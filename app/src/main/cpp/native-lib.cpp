#include <jni.h>
#include <string>
#include <jni.h>
#include <stdio.h>

extern "C" JNIEXPORT jstring JNICALL
Java_com_xieyao_ndkcrashlyticsdemo_MainActivity_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    std::string hello = "Hello from C++, method #stringFromJNI";
    return env->NewStringUTF(hello.c_str());
}

extern "C" JNIEXPORT jstring JNICALL
Java_com_xieyao_ndkcrashlyticsdemo_MainActivity_triggerNdkCrash(
        JNIEnv *env,
        jobject /* this */) {
    FILE *file = fopen("/sdcard/hello.txt", "w+");
//  trigger crash
//    if (file != NULL) {
        fputs("HELLO WORLD!\n", file);
        fflush(file);
        fclose(file);
//    }
    std::string hello = "Hello from C++, method #triggerNdkCrash";
    return env->NewStringUTF(hello.c_str());
}
