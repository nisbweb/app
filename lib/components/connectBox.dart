import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_blurhash/flutter_blurhash.dart';


class ConnectBox extends StatefulWidget {

	final String hash;
	final String image;
	final String topic;
	final int likes;

  const ConnectBox({Key key, this.hash, this.image, this.topic, this.likes}) : super(key: key);


  @override
  _ConnectBoxState createState() => _ConnectBoxState();
}

class _ConnectBoxState extends State<ConnectBox> {

	bool isLike;
	bool bookmark;
	int likes;

	@override
	void initState() {
		// TODO: implement initState
		super.initState();
		isLike = false;
		bookmark = false;
		likes = widget.likes;
	}

  @override
  Widget build(BuildContext context) {
    return Container(
		margin: EdgeInsets.all(10),
		padding: EdgeInsets.all(10),
		height: MediaQuery.of(context).size.height/2 - 100,
		decoration: BoxDecoration(
			borderRadius: BorderRadius.circular(30),
		),
		child: Stack(
			fit: StackFit.expand,
			children: <Widget>[
				BlurHash(
					hash: widget.hash,
					imageFit: BoxFit.fill,
					image: widget.image,
				),
				Column(
					mainAxisAlignment: MainAxisAlignment.spaceBetween,
					crossAxisAlignment: CrossAxisAlignment.start,
					children: <Widget>[
						Text(
							widget.topic,
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
													bookmark ? CupertinoIcons.bookmark_solid : CupertinoIcons.bookmark,
													size: 35,
													color: Colors.white,
												),
												onPressed: () {
													setState(() {
														bookmark = !bookmark;
													});
												},
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
				)
			],
		),
	);
  }
}
