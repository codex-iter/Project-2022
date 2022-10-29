import 'package:bmi/src/constants/names.dart';
import 'package:bmi/src/constants/size_configuration.dart';
import 'package:bmi/src/controllers/bmi_controller.dart';
import 'package:flutter/material.dart';
import 'package:get/get.dart';
import '../../constants/constant.dart';

class FifthContainer extends StatelessWidget {
  const FifthContainer({super.key});

  @override
  Widget build(BuildContext context) {
    return SizedBox(
      height: getProportionateScreenHeight(40),
      width: double.infinity,
      child: ElevatedButton(
        style: ElevatedButton.styleFrom(
          backgroundColor: kPrimaryColor,
          shape: const StadiumBorder(),
        ),
        onPressed: () {
          Get.find<BmiController>().onTappedCalculatedBmi();
        },
        child: Text(
          kBmiButton,
          style: fonts().copyWith(
            fontSize: getProportionateScreenHeight(18),
            color: Colors.white,
          ),
        ),
      ),
    );
  }
}
