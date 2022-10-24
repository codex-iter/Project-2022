import 'package:bmi/src/constants/constant.dart';
import 'package:bmi/src/constants/names.dart';
import 'package:bmi/src/constants/size_configuration.dart';
import 'package:bmi/src/controllers/bmi_controller.dart';
import 'package:flutter/material.dart';
import 'package:get/get.dart';

class SecondContainer extends StatelessWidget {
  const SecondContainer({super.key});

  // bool isActive = false;
  @override
  Widget build(BuildContext context) {
    return Column(
      mainAxisAlignment: MainAxisAlignment.start,
      crossAxisAlignment: CrossAxisAlignment.start,
      children: [
        SizedBox(
          height: getProportionateScreenHeight(40),
        ),
        Row(
          mainAxisAlignment: MainAxisAlignment.spaceBetween,
          children: [
            Expanded(
              child: GestureDetector(
                onTap: () {
                  Get.find<BmiController>().changeColorFemale();
                },
                child: Obx(
                  () => Container(
                    height: 100,
                    decoration: decoration().copyWith(
                      color: Get.find<BmiController>().femaleColor.value,
                    ),
                    child: Column(
                      children: [
                        SizedBox(
                          height: getProportionateScreenHeight(10),
                        ),
                        Icon(
                          Icons.female,
                          size: 38,
                          color:
                              Get.find<BmiController>().femaleIconColor.value,
                        ),
                        const SizedBox(
                          height: 8,
                        ),
                        Obx(()=> Text(
                          kFemalText,
                          style: fonts().copyWith(
                            fontSize: 18,
                            color:
                                Get.find<BmiController>().femaleTextColor.value,
                          ),
                        ),)
                      ],
                    ),
                  ),
                ),
              ),
            ),
            SizedBox(
              width: getProportionateScreenWidth(14),
            ),
            Expanded(
              child: GestureDetector(
                onTap: () {
                  Get.find<BmiController>().changeColorMale();
                },
                child: Obx(
                  () => Container(
                    height: 100,
                    decoration: decoration().copyWith(
                      color: Get.find<BmiController>().maleColor.value,
                    ),
                    child: Column(
                      children: [
                        SizedBox(
                          height: getProportionateScreenHeight(10),
                        ),
                        Icon(
                          Icons.male,
                          size: 38,
                          color: Get.find<BmiController>().maleIconColor.value,
                        ),
                        const SizedBox(
                          height: 8,
                        ),
                        Text(
                          kMaleText,
                          style: fonts().copyWith(
                            fontSize: 18,
                            color:
                                Get.find<BmiController>().maleTextColor.value,
                          ),
                        ),
                      ],
                    ),
                  ),
                ),
              ),
            ),
          ],
        )
      ],
    );
  }
}
