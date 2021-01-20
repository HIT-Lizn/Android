#include <jni.h>
#include <string>
#include "Test001.h"

extern "C" JNIEXPORT jstring JNICALL
Java_com_example_jnitest_MainActivity_stringFromJNI(
        JNIEnv* env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    hello += Test001::testSTR();
    return env->NewStringUTF(hello.c_str());
}
