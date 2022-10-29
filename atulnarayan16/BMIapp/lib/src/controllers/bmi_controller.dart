import 'package:bmi/src/constants/constant.dart';
import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'dart:math' as math;

class BmiController extends GetxController {
  static Rx<bool> isActive = false.obs;
  Rx<bool> isActiveMale = false.obs;
  Rx<Color> selectedColor = const Color(0xFFFFFDFF).obs;
  Rx<Color> femaleColor = const Color(0xFFFFFDFF).obs;
  Rx<Color> maleColor = const Color(0xFFFFFDFF).obs;
  Rx<double> height = 42.0.obs;
  Rx<int> weight = 42.obs;
  Rx<int> age = 30.obs;

  Rx<Color> maleTextColor = Colors.black.obs;
  Rx<Color> maleIconColor = Colors.black.obs;
  Rx<Color> femaleTextColor = Colors.black.obs;
  Rx<Color> femaleIconColor = Colors.black.obs;

  void changeColorFemale() {
    femaleColor.value == const Color(0xFFFFFDFF)
        ? femaleColor.value = kPrimaryColor
        : femaleColor.value = Colors.white;

    textColorFemale();
    iconColorFemale();
  }

  void changeColorMale() {
    maleColor.value == const Color(0xFFFFFDFF)
        ? maleColor.value = kPrimaryColor
        : maleColor.value = Colors.white;
  }

  void textColorFemale() {
    femaleColor.value == const Color(0xFFFFFDFF)
        ? femaleTextColor.value = Colors.black
        : femaleTextColor.value = Colors.white;
  }

  void iconColorFemale() {
    femaleColor.value == const Color(0xFFFFFDFF)
        ? femaleIconColor.value = Colors.black
        : femaleIconColor.value = Colors.white;
  }

  void textColorMale() {
    maleColor.value == const Color(0xFFFFFDFF)
        ? maleTextColor.value = Colors.black
        : maleTextColor.value = Colors.white;
  }

  void iconColorMale() {
    maleColor.value == const Color(0xFFFFFDFF)
        ? maleIconColor.value = Colors.black
        : maleIconColor.value = Colors.white;
  }

  void changeHeight(double kHeight) {
    height.value = kHeight;
  }

  void addWeight() {
    weight.value++;
  }

  void subWeight() {
    weight.value--;
  }

  void addAge() {
    age.value++;
  }

  void subAge() {
    age.value--;
  }

  void onTappedCalculatedBmi() {
    double bmi = weight.value / math.pow(height.value / 100, 2);
    checkBmiAndNotifyUser(bmi);
  }

  checkBmiAndNotifyUser(double bmi) {
    if (bmi >= 25) {
      showSnakbar(
          "You have a higher than normal body weight. Try to exercise more",
          "BMI Status is overweight",
          Colors.red);
    } else if (bmi > 18.5) {
      showSnakbar("You have a normal body weight. Good job!",
          "BMI Status is normal", Colors.green);
    } else {
      showSnakbar(
          "You have a lower than normal body weight. You can eat a bit more.",
          "BMI Status is underweight",
          Colors.orange);
    }
  }

  void showSnakbar(String text, String status, Color color) {
    Get.snackbar(status, text,
        backgroundColor: color,
        colorText: Colors.white,
        animationDuration: const Duration(seconds: 3));
  }
}
