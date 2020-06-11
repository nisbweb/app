import 'package:connect/services/firebaseCloudMessaging.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_icons/flutter_icons.dart';
import 'package:bubbled_navigation_bar/bubbled_navigation_bar.dart';


class HomeScreen extends StatefulWidget {
  @override
  _HomeScreenState createState() => _HomeScreenState();
}

class _HomeScreenState extends State<HomeScreen> {
	String title;

	double xOffset;
	double yOffset;
	double scaleFactor;
	bool isDrawer;

	@override
  void initState() {
    super.initState();
    title = "Home";
    xOffset = 0;
    yOffset = 0;
    scaleFactor = 1;
    isDrawer = false;
  }

  var Items = <BubbledNavigationBarItem>[
	  BubbledNavigationBarItem(
		  icon: Icon(FlutterIcons.layers_fea, size: 30, color: Colors.deepPurple,),
		  activeIcon: Icon(FlutterIcons.layers_fea, size: 35, color: Colors.white70,),
		  bubbleColor: Colors.deepPurpleAccent,
		  title: Text('Connect', style: TextStyle(color: Colors.white, fontSize: 12, fontWeight: FontWeight.w800))
	  ),
	  BubbledNavigationBarItem(
		  icon: Icon(FlutterIcons.event_seat_mdi, size: 30, color: Colors.pinkAccent),
		  activeIcon: Icon(FlutterIcons.event_seat_mdi, size: 35, color: Colors.white),
		  title: Text('Events', style: TextStyle(color: Colors.white, fontSize: 12, fontWeight: FontWeight.w800),),
		  bubbleColor: Colors.pinkAccent
	  ),
	  BubbledNavigationBarItem(
		  icon: Icon(FlutterIcons.ios_planet_ion, size: 32, color: Colors.greenAccent),
		  activeIcon: Icon(FlutterIcons.ios_planet_ion, size: 37, color: Colors.white),
		  title: Text('Spaces', style: TextStyle(color: Colors.white, fontSize: 12, fontWeight: FontWeight.w800),),
		  bubbleColor: Colors.greenAccent
	  ),
	  BubbledNavigationBarItem(
		  icon: Icon(FlutterIcons.user_astronaut_faw5s, size: 28, color: Colors.deepOrangeAccent),
		  activeIcon: Icon(FlutterIcons.user_astronaut_faw5s, size: 33, color: Colors.white),
		  title: Text('Profile', style: TextStyle(color: Colors.white, fontSize: 12, fontWeight: FontWeight.w800),),
		  bubbleColor: Colors.deepOrangeAccent
	  ),
  ];

	PageController _pageController = new PageController(
		initialPage: 0
	);

	MenuPositionController _menuPositionController = new MenuPositionController(
		initPosition: 0
	);

  @override
  Widget build(BuildContext context) {

  	final ThemeData theme = Theme.of(context);
  	var mode = theme.brightness;
  	String title = 'Home';
    return AnimatedContainer(
		transform: Matrix4.translationValues(xOffset, yOffset, 0)..scale(scaleFactor),
		height: MediaQuery.of(context).size.height,
		duration: Duration(milliseconds: 250),
		child: Scaffold(
			appBar: AppBar(
				shape: RoundedRectangleBorder(
					borderRadius: BorderRadius.circular(15),
				),
				elevation: 0,
				leading: IconButton(
					icon: isDrawer ? Icon(FlutterIcons.backburger_mco) : Icon(FlutterIcons.align_left_fea),
					onPressed: () {
						if(isDrawer) {
							setState(() {
								xOffset = 0;
								yOffset = 0;
								scaleFactor = 1;
								isDrawer = false;
							});
						}
						else {
							setState(() {
								xOffset = 230;
								yOffset = 150;
								scaleFactor = 0.6;
								isDrawer = true;
							});
						}
					},
				),
				actions: <Widget>[
					Padding(
						padding: EdgeInsets.only(right: 10),
						child: Column(
							crossAxisAlignment: CrossAxisAlignment.end,
							children: <Widget>[
								Text(
									title,
									style: TextStyle(
										fontSize: 25,
										fontWeight: FontWeight.w800
									),
								),
								Text(
									'Last updated at ${DateTime.now().hour}:${DateTime.now().minute}:${DateTime.now().second}'
								)
							],
						),
					)
				],
			),
			body: PageView(
				children: <Widget>[
					Center(
						child: Text(
							Noti,
							style: CupertinoTheme.of(context).textTheme.navLargeTitleTextStyle,
						),
					),
					Center(
						child: Text(
							'2',
							style: CupertinoTheme.of(context).textTheme.navLargeTitleTextStyle,
						),
					),
					Center(
						child: Text(
							'3',
							style: CupertinoTheme.of(context).textTheme.navLargeTitleTextStyle,
						),
					),
					Center(
						child: Text(
							'4',
							style: CupertinoTheme.of(context).textTheme.navLargeTitleTextStyle,
						),
					)
				],
				controller: _pageController,
				onPageChanged: (index) {
					_menuPositionController.animateToPosition(index);
				},
			),
			bottomNavigationBar: BubbledNavigationBar(
				items: Items,
				onTap: (index) {
					_pageController.animateToPage(
						index,
						duration: Duration(milliseconds: 250),
						curve: Curves.easeIn
					);
				},
				initialIndex: 0,
				controller: _menuPositionController,
				backgroundColor: mode == Brightness.dark ? Colors.black38 : Colors.white,
			),
		),
	);
  }
}