import 'package:connect/services/globalVariable.dart';
import 'package:connect/views/landing.dart';
import 'package:connect/views/loginPage.dart';
import 'package:flutter_secure_storage/flutter_secure_storage.dart';
import 'package:flutter/material.dart';

final storage = new FlutterSecureStorage();

Future<Null> loadData(context) async {
	Map<String, String> data = await storage.readAll();
	if(data['logged'] == 'true') {
		landing = Landing();
	}
	else {
		final mode = Theme.of(context).brightness;
		landing = LoginScreen();
	}
}

Future<Null> writeData(Map<String, String> data) async {
	data.forEach((key, value) async => await storage.write(key: key, value: value));
}