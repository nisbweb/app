import 'package:connect/views/drawerScreen.dart';
import 'package:connect/views/homeScreen.dart';
import 'package:flutter/material.dart';

class Landing extends StatefulWidget {
  @override
  _LandingState createState() => _LandingState();
}

class _LandingState extends State<Landing> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
		backgroundColor: Colors.teal,
		body: Stack(
			children: <Widget>[
				DrawerScreen(),
				HomeScreen()
			],
		),
	);
  }
}
