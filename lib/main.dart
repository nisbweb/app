import 'package:connect/router.dart';
//import 'package:connect/services/fireStore.dart';
import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:firebase_analytics/firebase_analytics.dart';
import 'package:connect/services/firebaseCloudMessaging.dart';

final FirebaseAnalytics analytics = FirebaseAnalytics();

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {

  @override
  Widget build(BuildContext context) {
//      initFire();
      initFCM();

//      Working code .
//      Firestore.instance.collection('test').document().setData({
//          'test': 'testing data again'
//      });
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


