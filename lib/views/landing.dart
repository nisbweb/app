import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_icons/flutter_icons.dart';
import 'package:bubbled_navigation_bar/bubbled_navigation_bar.dart';


class Landing extends StatefulWidget {
  @override
  _LandingState createState() => _LandingState();
}

class _LandingState extends State<Landing> {
	String title;

	@override
  void initState() {
    super.initState();
    title = "Home";
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
		  icon: Icon(FlutterIcons.chat_bubble_outline_mdi, size: 30, color: Colors.greenAccent),
		  activeIcon: Icon(FlutterIcons.chat_bubble_outline_mdi, size: 35, color: Colors.white),
		  title: Text('	Talk', style: TextStyle(color: Colors.white, fontSize: 12, fontWeight: FontWeight.w800),),
		  bubbleColor: Colors.greenAccent
	  ),
	  BubbledNavigationBarItem(
		  icon: Icon(FlutterIcons.user_astronaut_faw5s, size: 30, color: Colors.deepOrangeAccent),
		  activeIcon: Icon(FlutterIcons.user_astronaut_faw5s, size: 35, color: Colors.white),
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
    return Scaffold(
		body: PageView(
			children: <Widget>[
				Center(
					child: Text(
						'1',
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
				),Center(
					child: Text(
						'4',
						style: CupertinoTheme.of(context).textTheme.navLargeTitleTextStyle,
					),
				),

			],
			controller: _pageController,
			onPageChanged: (index) {
				_menuPositionController.animateToPosition(index);
			},
		),
		bottomNavigationBar: BubbledNavigationBar(
			onTap: (index) {
				_pageController.animateToPage(
					index,
					duration: Duration(milliseconds: 250),
					curve: Curves.easeIn
				);
			},
			backgroundColor: mode == Brightness.dark ? Color.fromRGBO(33, 33, 33, 1) : Colors.white,
			items: Items,
			initialIndex: 0,
			controller: _menuPositionController,
		),
	);
  }
}
