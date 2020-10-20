#include <stdio.h>
#include <jni.h>
#include "TesteNative.h"

JNIEXPORT void JNICALL
Java_TesteNative_imprimir(JNIEnv *env, jobject obj) {
  printf("Olá! Invocando um método nativo!");
  return;
}