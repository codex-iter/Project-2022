import 'package:bmi/src/constants/constant.dart';
import 'package:bmi/src/constants/names.dart';
import 'package:bmi/src/controllers/bmi_controller.dart';
import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:syncfusion_flutter_sliders/sliders.dart';

import '../../constants/size_configuration.dart';

class ThirdContainer extends StatelessWidget {
  const ThirdContainer({super.key});

  @override
  Widget build(BuildContext context) {
    return Container(
      decoration: decoration(),
      height: getProportionateScreenHeight(180),
      child: Column(
        children: [
          SizedBox(
            height: getProportionateScreenHeight(10),
          ),
          Text(kHeightText,
              style: fonts().copyWith(
                  fontSize: getProportionateScreenHeight(24),
                  color: Colors.black)),
          Obx(
            () => Text(
              Get.find<BmiController>().height.value.round().toString(),
              style: fonts().copyWith(
                  color: Colors.black,
                  fontWeight: FontWeight.bold,
                  fontSize: getProportionateScreenHeight(48)),
            ),
          ),
          SizedBox(
            height: getProportionateScreenHeight(10),
          ),
          Obx(
            () => SfSlider(
              activeColor: kPrimaryColor,
              inactiveColor: kPrimaryColor.withOpacity(0.4),
              min: 0.0,
              max: 500.0,
              value: Get.find<BmiController>().height.value,
              interval: 20,
              // showTicks: true,
              //  showLabels: true,
              enableTooltip: true,
              minorTicksPerInterval: 1,
              onChanged: (dynamic r) {
                Get.find<BmiController>().changeHeight(r);
              },
            ),
          )
        ],
      ),
    );
  }
}
