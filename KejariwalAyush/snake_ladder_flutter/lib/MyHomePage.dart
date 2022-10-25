import 'package:flutter/material.dart';
import 'package:snakeladder/SnakeLadder.dart';

class MyHomePage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Snakes & Ladders'),
        automaticallyImplyLeading: false,
      ),
      body: Center(
        child: Column(
          children: <Widget>[
            Text(
              'Let\'s PLAY ',
              textAlign: TextAlign.center,
              style: TextStyle(fontSize: 20, fontWeight: FontWeight.bold),
            ),
            SizedBox(
              height: 20,
            ),
            ListTile(
              leading: Icon(Icons.computer),
              title: Text('Computer vs Player '),
              trailing: Icon(Icons.person_outline),
              onTap: () {
                Navigator.push(
                    context,
                    MaterialPageRoute(
                      builder: (context) => SnakeLadder(
                        isComp: true,
                      ),
                    ));
              },
            ),
            ListTile(
              leading: Icon(Icons.person_outline),
              title: Text('Player vs Player'),
              trailing: Icon(Icons.person_outline),
              onTap: () {
                Navigator.push(
                    context,
                    MaterialPageRoute(
                      builder: (context) => SnakeLadder(
                        isComp: false,
                      ),
                    ));
              },
            ),
          ],
        ),
      ),
    );
  }
}
