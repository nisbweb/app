import 'package:connect/views/loginPage.dart';
import 'package:connect/views/splashScreen.dart';
import 'package:connect/views/e404.dart';
import 'package:flutter/material.dart';

Map<String, Widget Function(BuildContext)> Router() {
	var routes = {
		'/': (_) => SplashScreen(),
		'/login': (_) => LoginScreen(),
		'/404': (_) => E404()
	};
	return routes;
}