import 'package:connect/UI/loginPage.dart';
import 'package:flutter/material.dart';

class Home extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
  	final ThemeData theme = Theme.of(context);
  	final mode = theme.brightness;
  	print(mode);
    return Scaffold(
		body: LoginScreen(
			primaryColor: Colors.blue,
			backgroundColor: mode == Brightness.dark ? Color(0x2A2A2A) : Colors.white70,
			backgroundImage: mode == Brightness.dark ? AssetImage('assets/images/full-bloom-dark.jpg') : AssetImage('assests/images/full-bloom-light.png'),
		),
	);
  }
}
