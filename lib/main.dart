import 'package:connect/home.dart';
import 'package:connect/services/auth.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
        title: 'Flutter Demo',
        theme: ThemeData(
          primarySwatch: Colors.blue,
            brightness: Brightness.light
        ),
        darkTheme: ThemeData(
            brightness: Brightness.dark
        ),
        home: Home()
    );
  }
}


