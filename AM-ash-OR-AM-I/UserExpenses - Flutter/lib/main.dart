import 'package:flutter/material.dart';
import 'package:flutter/scheduler.dart';
import 'package:flutter/services.dart';
import 'package:google_fonts/google_fonts.dart';
import 'package:user_expenses/screens/home_screen.dart';

import 'package:dynamic_color/dynamic_color.dart';

var brightness = SchedulerBinding.instance.window.platformBrightness;
bool isDarkMode = false; //brightness == Brightness.dark;
void main() {
  SystemChrome.setSystemUIOverlayStyle(
    const SystemUiOverlayStyle(
      statusBarColor: Colors.transparent,
    ),
  );
  runApp(const ExpensesApp());
}

class ExpensesApp extends StatelessWidget {
  const ExpensesApp({super.key});

  @override
  Widget build(BuildContext context) {
    SystemChrome.setSystemUIOverlayStyle(
      isDarkMode ? SystemUiOverlayStyle.light : SystemUiOverlayStyle.dark,
    );
    return DynamicColorBuilder(
      builder: (lightDynamic, darkDynamic) {
        final color = isDarkMode ? darkDynamic : lightDynamic;
        return MaterialApp(
          title: 'Flutter Demo',
          theme: ThemeData(
              useMaterial3: true,
              colorScheme:
                  color ?? ColorScheme.fromSeed(seedColor: Colors.indigo),
              scaffoldBackgroundColor: color?.background,
              textTheme: GoogleFonts.poppinsTextTheme(const TextTheme()),
              inputDecorationTheme: InputDecorationTheme(
                contentPadding: const EdgeInsets.all(20),
                border: OutlineInputBorder(
                  borderRadius: BorderRadius.circular(20),
                ),
                fillColor: color?.primary.withOpacity(.04),
                filled: true,
              )),
          home: const MyHomePage(title: 'Flutter Demo Home Page'),
        );
      },
    );
  }
}
