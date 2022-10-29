import 'package:bmi/src/constants/size_configuration.dart';
import 'package:flutter/material.dart';
import 'components/fifth_container.dart';
import 'components/first_container.dart';
import 'components/fourth_container.dart';
import 'components/second_container.dart';
import 'components/third_container.dart';

class Home extends StatelessWidget {
  const Home({super.key});

  @override
  Widget build(BuildContext context) {
    SizeConfig().init(context);
    return Scaffold(
      body: SingleChildScrollView(
        child: Column(
          children: [
            const FirstContainer(),
            Padding(
              padding: const EdgeInsets.all(18.0),
              child: Column(
                children: [
                  const SecondContainer(),
                   SizedBox(
                    height: getProportionateScreenHeight(32),
                  ),
                  const ThirdContainer(),
                   SizedBox(
                    height: getProportionateScreenHeight(32),
                  ),
                  const FourthContainer(),
                  SizedBox(
                    height: getProportionateScreenHeight(32),
                  ),
                  const FifthContainer(),
                ],
              ),
            )
          ],
        ),
      ),
    );
  }
}
