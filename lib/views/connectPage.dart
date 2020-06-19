import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_icons/flutter_icons.dart';


class ConnectPage extends StatefulWidget {
  @override
  _ConnectPageState createState() => _ConnectPageState();
}

class _ConnectPageState extends State<ConnectPage> {

	bool isLike;
	int likes;

	@override
  void initState() {
    // TODO: implement initState
    super.initState();
    isLike = false;
    likes = 103;
  }

  @override
  Widget build(BuildContext context) {
    return SingleChildScrollView(
		child: Column(
			children: <Widget>[
				Container(
					margin: EdgeInsets.all(10),
					padding: EdgeInsets.all(10),
					height: MediaQuery.of(context).size.height/2,
					decoration: BoxDecoration(
						borderRadius: BorderRadius.circular(30),
						color: Colors.black
					),
					child: Column(
						mainAxisAlignment: MainAxisAlignment.spaceBetween,
						crossAxisAlignment: CrossAxisAlignment.start,
						children: <Widget>[
							Text(
								'Topic',
								style: TextStyle(
									fontWeight: FontWeight.w800,
									fontSize: 35,
									color: Colors.white
								),
							),
							Row(
								crossAxisAlignment: CrossAxisAlignment.center,
								mainAxisAlignment: MainAxisAlignment.spaceBetween,
								children: <Widget>[
									Container(
										child: Row(
											mainAxisAlignment: MainAxisAlignment.spaceEvenly,
											mainAxisSize: MainAxisSize.max,
											children: <Widget>[
												IconButton(
													icon: Icon(
														CupertinoIcons.bookmark,
														size: 35,
														color: Colors.white,
													),
													onPressed: () => print('Bookmarked'),
												),
												IconButton(
													icon: Icon(
														isLike ? CupertinoIcons.heart_solid : CupertinoIcons.heart,
														color: isLike ? Colors.pink : Colors.white,
														size: 35,
													),
													onPressed: () {
														setState(() {
															if(!isLike) {
																likes = likes + 1;
																isLike = !isLike;
															}
															else if(isLike) {
																likes = likes - 1;
																isLike = !isLike;
															}
														});
													},
												),
												Text(
													likes.toString(),
													style: TextStyle(
														color: Colors.white,
														fontSize: 15,
														fontWeight: FontWeight.w400
													),
												),
											],
										),
									),
									RaisedButton(
										shape: RoundedRectangleBorder(
											borderRadius: BorderRadius.circular(20)
										),
										color: Colors.white,
										child: Text(
											'Read More',
											style: TextStyle(
												color: Colors.black
											),
										),
										onPressed: () => print('Reading more...'),
									)
								],
							)
						],
					),
				),
			],
		),
	);
  }
}
