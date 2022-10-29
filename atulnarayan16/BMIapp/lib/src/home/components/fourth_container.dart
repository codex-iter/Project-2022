import 'package:bmi/src/constants/constant.dart';
import 'package:bmi/src/constants/names.dart';
import 'package:bmi/src/constants/size_configuration.dart';
import 'package:bmi/src/controllers/bmi_controller.dart';
import 'package:flutter/material.dart';
import 'package:get/get.dart';

class FourthContainer extends StatelessWidget {
  const FourthContainer({super.key});

  @override
  Widget build(BuildContext context) {
    return Row(
      children: [
        Expanded(
          child: Container(
            decoration: decoration(),
            child: Column(
              children: [
                SizedBox(
                  height: getProportionateScreenHeight(10),
                ),
                Text(
                  kWeightText,
                  style: fonts()
                      .copyWith(fontSize: getProportionateScreenHeight(14)),
                ),
                Obx(
                  () => Text(
                    Get.find<BmiController>().weight.value.toString(),
                    style: fonts()
                        .copyWith(fontSize: getProportionateScreenHeight(38)),
                  ),
                ),
                SizedBox(
                  height: getProportionateScreenHeight(10),
                ),
                Row(
                  mainAxisAlignment: MainAxisAlignment.center,
                  children: [
                    ElevatedButton(
                        style: ElevatedButton.styleFrom(
                            backgroundColor: kPrimaryColor,
                            shape: const CircleBorder()),
                        onPressed: () {
                          Get.find<BmiController>().subWeight();
                        },
                        child: const Icon(
                          Icons.remove,
                        )),
                    ElevatedButton(
                      style: ElevatedButton.styleFrom(
                        shape: const CircleBorder(),
                        backgroundColor: kPrimaryColor,
                      ),
                      onPressed: () {
                        Get.find<BmiController>().addWeight();
                      },
                      child: const Icon(
                        Icons.add,
                      ),
                    )
                  ],
                )
              ],
            ),
          ),
        ),
        SizedBox(
          width: getProportionateScreenWidth(8),
        ),
        Expanded(
          child: Container(
            decoration: decoration(),
            child: Column(
              children: [
                SizedBox(
                  height: getProportionateScreenHeight(10),
                ),
                Text(
                  kAgeText,
                  style: fonts()
                      .copyWith(fontSize: getProportionateScreenHeight(18)),
                ),
                Obx(
                  () => Text(
                    Get.find<BmiController>().age.value.toString(),
                    style: fonts()
                        .copyWith(fontSize: getProportionateScreenHeight(38)),
                  ),
                ),
                SizedBox(
                  height: getProportionateScreenHeight(10),
                ),
                Row(
                  mainAxisAlignment: MainAxisAlignment.center,
                  children: [
                    ElevatedButton(
                        style: ElevatedButton.styleFrom(
                            backgroundColor: kPrimaryColor,
                            shape: const CircleBorder()),
                        onPressed: () {
                          Get.find<BmiController>().subAge();
                        },
                        child: const Icon(
                          Icons.remove,
                        )),
                    ElevatedButton(
                      style: ElevatedButton.styleFrom(
                          backgroundColor: kPrimaryColor,
                          shape: const CircleBorder()),
                      onPressed: () {
                        Get.find<BmiController>().subAge();
                      },
                      child: const Icon(
                        Icons.add,
                      ),
                    ),
                  ],
                )
              ],
            ),
          ),
        ),
      ],
    );
  }
}
