import 'package:connect/components/connectBox.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_icons/flutter_icons.dart';
import 'package:liquid_pull_to_refresh/liquid_pull_to_refresh.dart';


class ConnectPage extends StatefulWidget {
  @override
  _ConnectPageState createState() => _ConnectPageState();
}

class _ConnectPageState extends State<ConnectPage> {



  @override
  Widget build(BuildContext context) {
    return LiquidPullToRefresh(
		onRefresh: () {},
		child: SingleChildScrollView(
			child: Column(
				children: <Widget>[
					ConnectBox(
						hash: 'L45q#1oL00WW\$_jZIubH0Kay~WoL',
						image: 'https://firebasestorage.googleapis.com/v0/b/nisb-connect.appspot.com/o/14188.jpg?alt=media&token=aa6be35e-83cf-4ecd-a8ad-1b082501b6bf',
						topic: 'Apple',
						likes: 100,
					),
					ConnectBox(
						hash: 'L55XfTX80erYWBayj[ay00nP~DTI',
						image: 'https://firebasestorage.googleapis.com/v0/b/nisb-connect.appspot.com/o/D.png?alt=media&token=9df1ceca-c989-468e-ad79-bb29335ba103',
						topic: 'Developers',
						likes: 100,
					)
				],
			),
		),
		showChildOpacityTransition: true,
	);
  }
}
