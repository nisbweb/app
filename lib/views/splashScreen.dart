import 'dart:async';
import 'package:connect/components/colourLoader.dart';
import 'package:connect/home.dart';
import 'package:flutter/material.dart';

class SplashScreen extends StatefulWidget {
  @override
  _SplashScreenState createState() => _SplashScreenState();
}

class _SplashScreenState extends State<SplashScreen> {

	@override
  void initState() {
    super.initState();
    Timer(
		Duration(seconds: 5),
		() => Navigator.of(context).pushReplacement(new MaterialPageRoute(builder: (_) => Home()))
	);
  }

  @override
  Widget build(BuildContext context) {
  	final ThemeData theme = Theme.of(context);
  	final mode = theme.brightness;
    return Scaffold(
		body: Stack(
			fit: StackFit.expand,
			children: <Widget>[
				Container(
					decoration: BoxDecoration(
						color: mode == Brightness.dark ? Color(0x2A2A2A) : Colors.white70
					),
				),
				Column(
					mainAxisAlignment: MainAxisAlignment.start,
					children: <Widget>[
						Expanded(
							flex: 2,
							child: Container(
								child: Column(
									mainAxisAlignment: MainAxisAlignment.center,
									children: <Widget>[
										mode == Brightness.dark ? Image.asset('assets/images/nisb-dark.png') : Image.asset('assets/images/nisb-light.png')
									],
								),
							),
						),
						Expanded(
							flex: 1,
							child: Column(
								mainAxisAlignment: MainAxisAlignment.center,
								children: <Widget>[
									ColorLoader4(),
									SizedBox(height: 20,),
									Text(
										'All tech on campus',
										style: TextStyle(
											color: mode == Brightness.light ? Colors.black45 : Colors.white70
										),
									)
								],
							),
						)
					],
				)
			],
		),
	);
  }
}
