import 'package:flutter/material.dart';
import 'package:google_fonts/google_fonts.dart';

class BillSplit extends StatefulWidget {
  const BillSplit({Key? key}) : super(key: key);

  @override
  State<BillSplit> createState() => _BillSplitState();
}

class _BillSplitState extends State<BillSplit> {
  TextStyle style1 =
      GoogleFonts.montserrat(fontSize: 18, fontWeight: FontWeight.w700);
  @override
  Widget build(BuildContext context) {
    return Scaffold(
        body: SafeArea(
      child: Container(
        margin: EdgeInsets.only(left: 20, right: 20),
        child: Column(
          children: [
            Container(
              alignment: Alignment.centerLeft,
              margin: EdgeInsets.only(top: 30),
              child: Text("Bill Splitter",
                  style: GoogleFonts.montserrat(
                      fontSize: 25, fontWeight: FontWeight.w700)),
            ),
            SizedBox(height: 10),
            Container(
                width: MediaQuery.of(context).size.width,
                height: 100,
                decoration: const BoxDecoration(color: Colors.yellow),
                child: Row(
                  mainAxisAlignment: MainAxisAlignment.spaceBetween,
                  children: [
                    Padding(
                      padding: const EdgeInsets.all(15.0),
                      child: Column(
                          crossAxisAlignment: CrossAxisAlignment.start,
                          children: [
                            Text("Total",
                                style: GoogleFonts.montserrat(
                                    fontSize: 20, fontWeight: FontWeight.w700)),
                            Text("45",
                                style: GoogleFonts.montserrat(
                                    fontSize: 20, fontWeight: FontWeight.w700))
                          ]),
                    ),
                    Padding(
                      padding: const EdgeInsets.all(15.0),
                      child: Row(
                        children: [
                          Column(
                            crossAxisAlignment: CrossAxisAlignment.start,
                            children: [
                              Text(
                                "Friends",
                                style: style1,
                              ),
                              Text(
                                "Tax",
                                style: style1,
                              ),
                              Text(
                                "Tip",
                                style: style1,
                              )
                            ],
                          ),
                          SizedBox(width: 10),
                          Column(
                            crossAxisAlignment: CrossAxisAlignment.start,
                            children: [
                              Text(
                                "10",
                                style: style1,
                              ),
                              Text(
                                "14 %",
                                style: style1,
                              ),
                              Text(
                                "15",
                                style: style1,
                              )
                            ],
                          )
                        ],
                      ),
                    ),
                  ],
                )),
            SizedBox(height: 15),
            Text("Number of friends",
                style: GoogleFonts.montserrat(
                    fontSize: 20, fontWeight: FontWeight.w700)),
            Slider(
                min: 0,
                max: 15,
                divisions: 15,
                activeColor: Colors.orange,
                inactiveColor: Colors.grey,
                value: 12,
                onChanged: (value) {}),
            SizedBox(height: 10),
            Row(children: [
              Container(
                width: MediaQuery.of(context).size.width / 2,
                height: 70,
                decoration: BoxDecoration(
                    color: Colors.yellow,
                    borderRadius: BorderRadius.circular(20)),
                child: Column(children: [
                  Text("TIP",
                      style: GoogleFonts.montserrat(
                          fontSize: 20, fontWeight: FontWeight.w700)),
                  Row(
                      mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                      children: [
                        Container(
                            width: 40,
                            height: 40,
                            child: FloatingActionButton(
                                onPressed: () {},
                                backgroundColor: Colors.grey[400],
                                child:
                                    Icon(Icons.remove, color: Colors.black))),
                        Text(
                          "20",
                          style: GoogleFonts.montserrat(
                              fontSize: 27, fontWeight: FontWeight.w700),
                        ),
                        Container(
                            width: 40,
                            height: 40,
                            child: FloatingActionButton(
                                onPressed: () {},
                                backgroundColor: Colors.grey[400],
                                child: Icon(Icons.add, color: Colors.black)))
                      ])
                ]),
              ),
              SizedBox(
                width: 10,
              ),
              Container(
                  width: MediaQuery.of(context).size.width / 3,
                  height: 70,
                  decoration: BoxDecoration(
                    color: Colors.yellow,
                    borderRadius: BorderRadius.circular(20),
                  ),
                  child: Padding(
                    padding: const EdgeInsets.all(7.0),
                    child: TextField(
                      keyboardType: TextInputType.number,
                      decoration: InputDecoration(
                          border: OutlineInputBorder(
                              borderRadius: BorderRadius.circular(20)),
                          labelText: "Tax in %",
                          labelStyle: GoogleFonts.montserrat(
                              fontSize: 15, fontWeight: FontWeight.w700)),
                    ),
                  ))
            ]),
            SizedBox(height: 20),
          ],
        ),
      ),
    ));
  }
}
