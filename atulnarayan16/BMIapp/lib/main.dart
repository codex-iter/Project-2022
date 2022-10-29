import 'package:bmi/src/app.dart';
import 'package:flutter/material.dart';

void main() {
  runApp(const MyApp());
}

// import 'package:flutter/material.dart';

// void main() => runApp(const MyApp());

// class MyApp extends StatelessWidget {
//   const MyApp({Key? key}) : super(key: key);

//   static const String _title = 'BMI calculator';

//   @override
//   Widget build(BuildContext context) {
//     return MaterialApp(
//       title: _title,
//       home: Scaffold(
//         appBar: AppBar(title: const Text(_title)),
//         body: const MyStatefulWidget(),
//         backgroundColor: Colors.amber[50],
//       ),
//     );
//   }
// }

// class MyStatefulWidget extends StatefulWidget {
//   const MyStatefulWidget({Key? key}) : super(key: key);

//   @override
//   State<MyStatefulWidget> createState() => _MyStatefulWidgetState();
// }

// int res = 0;
// var p = "";

// class _MyStatefulWidgetState extends State<MyStatefulWidget> {
//   TextEditingController heightController = TextEditingController();
//   TextEditingController weightController = TextEditingController();

//   @override
//   Widget build(BuildContext context) {
//     return Padding(
//       padding: const EdgeInsets.all(10),
//       child: ListView(
//         children: <Widget>[
//           Container(
//               alignment: Alignment.center,
//               padding: const EdgeInsets.all(10),
//               child: const Text(
//                 'BMI Calculator',
//                 style: TextStyle(
//                     color: Colors.red,
//                     fontWeight: FontWeight.w500,
//                     fontSize: 30),
//               )),
//           Container(
//             padding: const EdgeInsets.all(10),
//             child: TextField(
//               controller: heightController,
//               decoration: const InputDecoration(
//                 border: OutlineInputBorder(),
//                 hintText: 'Enter your height in centimeteres',
//               ),
//             ),
//           ),
//           Container(
//             padding: const EdgeInsets.all(10),
//             child: TextField(
//               controller: weightController,
//               decoration: const InputDecoration(
//                 border: OutlineInputBorder(),
//                 hintText: 'Enter your weight in kilograms',
//               ),
//             ),
//           ),
//           ElevatedButton(
//               child: const Text('Submit'),
//               onPressed: () {
//                 setState(() {});
//                 res = double.parse(weightController.text) ~/
//                     (double.parse(heightController.text) /
//                         100 *
//                         double.parse(heightController.text) /
//                         100);
//                 if (res <= 18.5) {
//                   p = "Underweight".toString();
//                 } else if (res < 18.5 && res <= 24.9) {
//                   p = "Normal Weight".toString();
//                 } else if (res < 25.0 && res <= 29.9) {
//                   p = "Over weight".toString();
//                 } else {
//                   p = "Obese".toString();
//                 }
//               }),
//           Text(
//             p.toString(),
//             textAlign: TextAlign.center,
//             style: const TextStyle(
//               color: Colors.cyan,
//               fontSize: 30,
//             ),
//           )
//         ],
//       ),
//     );
//   }
// }
