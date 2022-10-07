import 'package:flare_flutter/flare_actor.dart';
import 'package:flutter/material.dart';

import 'MyHomePage.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Snake & Ladder',
      debugShowCheckedModeBanner: false,
      theme: ThemeData(
        primarySwatch: Colors.indigo,
        visualDensity: VisualDensity.adaptivePlatformDensity,
      ),
      home: MyHomePage(),
    );
  }
}

// ignore: must_be_immutable
class Flare extends StatefulWidget {
  String animation = 'still';
  Flare({this.animation});
  @override
  _FlareState createState() => _FlareState();
}

class _FlareState extends State<Flare> {
  @override
  Widget build(BuildContext context) {
    return new FlareActor(
      "assets/DiceRoll.flr",
      alignment: Alignment.center,
      fit: BoxFit.fitHeight,
      animation: widget.animation,
    );
  }
}
