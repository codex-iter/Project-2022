import 'package:flutter/material.dart';
import 'package:google_fonts/google_fonts.dart';

BoxDecoration decoration() {
  return const BoxDecoration(
    borderRadius: BorderRadius.all(Radius.circular(8.0)),
    color: Color(0xFFFFFDFF),
    boxShadow: [
      BoxShadow(
        color: Color.fromARGB(255, 234, 230, 230),
        offset: Offset(0, 2),
      ),
      BoxShadow(
        color: Color.fromARGB(255, 234, 230, 230),
        offset: Offset(0, -2),
      ),
      BoxShadow(
        color: Color.fromARGB(255, 234, 230, 230),
        offset: Offset(1, 0),
      ),
      BoxShadow(
        color: Color.fromARGB(255, 234, 230, 230),
        offset: Offset(-1, 0),
      ),
    ],
  );
}

Color kPrimaryColor = const Color(0xff5F3FBA);

TextStyle fonts() {
  return GoogleFonts.roboto(
    fontSize: 24.0,
    color: Colors.black,
    fontWeight: FontWeight.normal,
  );
}
