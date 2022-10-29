import 'package:bmi/src/constants/constant.dart';
import 'package:bmi/src/constants/names.dart';
import 'package:bmi/src/constants/size_configuration.dart';
import 'package:flutter/material.dart';

class FirstContainer extends StatelessWidget {
  const FirstContainer({
    Key? key,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    SizeConfig().init(context);
    Size size = MediaQuery.of(context).size;
    return SizedBox(
      height: getProportionateScreenHeight(150),
      width: double.infinity,
      child: CustomPaint(
        size: Size(size.width, (size.width * 0.2).toDouble()),
        painter: RPSCustomPainter(),
        child: SafeArea(
          child: Column(
            children: [
              SizedBox(
                height: getProportionateScreenHeight(10),
              ),
              Text(
                kHomeAppBarName,
                style: fonts().copyWith(
                  fontWeight: FontWeight.bold,
                  color: Colors.white,
                ),
              ),
            ],
          ),
        ),
      ),
    );
  }
}

class RPSCustomPainter extends CustomPainter {
  @override
  void paint(Canvas canvas, Size size) {
    Paint paint0 = Paint()
      ..color = kPrimaryColor
      ..style = PaintingStyle.fill
      ..strokeWidth = 1.0;

    Path path0 = Path();
    path0.moveTo(0, size.height);
    path0.lineTo(0, 0);
    path0.lineTo(size.width, 0);
    path0.lineTo(size.width, size.height);
    path0.quadraticBezierTo(size.width * 0.9645500, size.height * 0.9474000,
        size.width * 0.9098000, size.height * 0.9352000);
    path0.quadraticBezierTo(size.width * 0.8428500, size.height * 0.9497000,
        size.width * 0.8125000, size.height * 0.9924000);
    path0.quadraticBezierTo(size.width * 0.7665000, size.height * 0.9396000,
        size.width * 0.7115000, size.height * 0.9340000);
    path0.quadraticBezierTo(size.width * 0.6487500, size.height * 0.9380000,
        size.width * 0.6105000, size.height * 0.9970000);
    path0.quadraticBezierTo(size.width * 0.5602100, size.height * 0.9227000,
        size.width * 0.4999300, size.height * 0.9163600);
    path0.quadraticBezierTo(size.width * 0.4366300, size.height * 0.9245600,
        size.width * 0.4090000, size.height * 0.9980000);
    path0.quadraticBezierTo(size.width * 0.3696500, size.height * 0.9183400,
        size.width * 0.3000100, size.height * 0.9180200);
    path0.quadraticBezierTo(size.width * 0.2325500, size.height * 0.9241000,
        size.width * 0.2100000, size.height * 0.9960000);
    path0.quadraticBezierTo(size.width * 0.1845000, size.height * 0.9285000,
        size.width * 0.1000000, size.height * 0.9170000);
    path0.quadraticBezierTo(size.width * 0.0280000, size.height * 0.9120000, 0,
        size.height * 0.9980000);

    canvas.drawPath(path0, paint0);
  }

  @override
  bool shouldRepaint(covariant CustomPainter oldDelegate) {
    return true;
  }
}
