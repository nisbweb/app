import 'package:connect/services/router.dart';
//import 'package:connect/services/fireStore.dart';
import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:connect/services/secureStorage.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:firebase_analytics/firebase_analytics.dart';
import 'package:connect/services/firebaseCloudMessaging.dart';

final FirebaseAnalytics analytics = FirebaseAnalytics();

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {

    void Startup(context) async {
        await loadData(context);
    }

  @override
  Widget build(BuildContext context) {
      Startup(context);
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


