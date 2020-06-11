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
	bool isSearch;
	double widthAn;
	bool isProfile;

	@override
  void initState() {
    super.initState();
    title = "Home";
    xOffset = 0;
    yOffset = 0;
    scaleFactor = 1;
    isDrawer = false;
	isSearch = false;
	widthAn = 0;
	isProfile = false;
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
    return AnimatedContainer(
		transform: Matrix4.translationValues(xOffset, yOffset, 0)..scale(scaleFactor),
		height: MediaQuery.of(context).size.height,
		duration: Duration(milliseconds: 300),
		child: Scaffold(
			appBar: isProfile ? null : AppBar(
				shape: RoundedRectangleBorder(
					borderRadius: BorderRadius.circular(15),
				),
				elevation: 10,
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
								xOffset = MediaQuery.of(context).size.width * 0.75;
								yOffset = MediaQuery.of(context).size.height * 0.2;
								scaleFactor = 0.6;
								isDrawer = true;
							});
						}
					},
				),
				actions: <Widget>[
					Padding(
						padding: EdgeInsets.only(right: 10),
						child: isSearch ? AnimatedContainer(
							duration: Duration(milliseconds: 250),
							width: widthAn,
							child: TextField(
								decoration: InputDecoration(
									hintText: 'Search ..',
									prefixIcon: Icon(FlutterIcons.search1_ant)
								)
							),
						)
							: Row(
							children: <Widget>[
								Column(
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
								IconButton(
									icon: Icon(FlutterIcons.search1_ant),
									onPressed: () {
										setState(() {
											isSearch = true;
										});
										Future.delayed(Duration(milliseconds: 50), () {
											setState(() {
											  widthAn = MediaQuery.of(context).size.width * 0.75;
											});
										});
									},
								)
							],
						),
					)
				],
			),
			body: Stack(
				fit: StackFit.expand,
				children: <Widget>[
					PageView(
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
							if(index == 0) {
								setState(() {
									title = 'Home';
									isProfile = false;
								});
							}
							else if(index == 1) {
								setState(() {
									title = 'Events';
									isProfile = false;
								});
							}
							else if(index == 2) {
								setState(() {
									title = 'Spaces';
									isProfile = false;
								});
							}
							else if(index == 3) {
								setState(() {
									isProfile = true;
								});
							}
							_menuPositionController.animateToPosition(index);
						},
					),
					isSearch ? GestureDetector(
						child: Container(
							color: Colors.transparent,
						),
						onTap: () {
							setState(() {
								widthAn = 0;
							});
							Future.delayed(Duration(milliseconds: 250), () {
								setState(() {
									isSearch = false;
								});
							});
						},
					) : Container()
				],
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