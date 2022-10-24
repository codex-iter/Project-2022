import 'package:bmi/src/controllers/all_controller.dart';
import 'package:flutter/material.dart';
import 'package:get/get.dart';

import 'home/home.dart';

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return GetMaterialApp(
      initialBinding: AllController(),
      debugShowCheckedModeBanner: false,
      home: const Home(),
    );
  }
}
