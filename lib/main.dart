import 'package:connect/router.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
        title: 'Flutter Demo',
        theme: ThemeData(
            primaryColor: Colors.white,
            brightness: Brightness.light
        ),
        darkTheme: ThemeData(
            brightness: Brightness.dark
        ),
        initialRoute: '/',
        routes: Router(),
    );
  }
}


