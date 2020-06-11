import 'package:connect/home.dart';
import 'package:connect/services/auth.dart';
import 'package:connect/views/landing.dart';
import 'package:flutter/material.dart';
import 'package:flutter_icons/flutter_icons.dart';

class LoginScreen extends StatelessWidget {

	final Color primaryColor;
	final Color backgroundColor;
	final AssetImage backgroundImage;

	TextEditingController _controllerEmail = new TextEditingController();
	TextEditingController _controllerPass = new TextEditingController();

	LoginScreen({
		Key key,
		this.primaryColor, this.backgroundColor, this.backgroundImage
	});

	@override
	Widget build(BuildContext context) {
		final ThemeData theme = Theme.of(context);
		var mode = theme.brightness;
		return SingleChildScrollView(
			child: Container(
				height: MediaQuery.of(context).size.height,
				decoration: BoxDecoration(
					color: this.backgroundColor,
				),
				child: Column(
					crossAxisAlignment: CrossAxisAlignment.start,
					mainAxisSize: MainAxisSize.max,
					children: <Widget>[
						new ClipPath(
							clipper: MyClipper(),
							child: Container(
								decoration: BoxDecoration(
									image: new DecorationImage(
										image: this.backgroundImage,
										fit: BoxFit.cover,
									),
								),
								alignment: Alignment.center,
								padding: EdgeInsets.only(top: MediaQuery.of(context).size.height * 0.2, bottom: MediaQuery.of(context).size.height * 0.2),
								child: Column(
									children: <Widget>[
										Text(
											"NISB Connect",
											style: TextStyle(
												fontSize: 50.0,
												fontWeight: FontWeight.bold,
												color: this.primaryColor),
										),
										Text(
											"Your one stop location for everything NISB",
											style: TextStyle(
												fontSize: 15.0,
												fontWeight: FontWeight.bold,
												color: this.primaryColor),
										),
									],
								),
							),
						),
						Padding(
							padding: const EdgeInsets.only(left: 40.0),
							child: Text(
								"Email",
								style: TextStyle(color: Colors.grey, fontSize: 16.0),
							),
						),
						Container(
							decoration: BoxDecoration(
								border: Border.all(
									color: Colors.grey.withOpacity(0.5),
									width: 1.0,
								),
								borderRadius: BorderRadius.circular(20.0),
							),
							margin:
							const EdgeInsets.symmetric(vertical: 10.0, horizontal: 20.0),
							child: Row(
								children: <Widget>[
									new Padding(
										padding:
										EdgeInsets.symmetric(vertical: 10.0, horizontal: 15.0),
										child: Icon(
											Icons.person_outline,
											color: Colors.grey,
										),
									),
									Container(
										height: 30.0,
										width: 1.0,
										color: Colors.grey.withOpacity(0.5),
										margin: const EdgeInsets.only(left: 00.0, right: 10.0),
									),
									new Expanded(
										child: TextField(
											decoration: InputDecoration(
												border: InputBorder.none,
												hintText: 'Enter your email',
												hintStyle: TextStyle(color: Colors.grey),
											),
											keyboardType: TextInputType.emailAddress,
											controller: _controllerEmail,
											keyboardAppearance: mode,
										),
									)
								],
							),
						),
						Padding(
							padding: const EdgeInsets.only(left: 40.0),
							child: Text(
								"Password",
								style: TextStyle(color: Colors.grey, fontSize: 16.0),
							),
						),
						Container(
							decoration: BoxDecoration(
								border: Border.all(
									color: Colors.grey.withOpacity(0.5),
									width: 1.0,
								),
								borderRadius: BorderRadius.circular(20.0),
							),
							margin:
							const EdgeInsets.symmetric(vertical: 10.0, horizontal: 20.0),
							child: Row(
								children: <Widget>[
									new Padding(
										padding:
										EdgeInsets.symmetric(vertical: 10.0, horizontal: 15.0),
										child: Icon(
											Icons.lock_open,
											color: Colors.grey,
										),
									),
									Container(
										height: 30.0,
										width: 1.0,
										color: Colors.grey.withOpacity(0.5),
										margin: const EdgeInsets.only(left: 00.0, right: 10.0),
									),
									new Expanded(
										child: TextField(
											decoration: InputDecoration(
												border: InputBorder.none,
												hintText: 'Enter your password',
												hintStyle: TextStyle(color: Colors.grey),
											),
											obscureText: true,
											controller: _controllerPass,
											keyboardAppearance: mode,
										),
									)
								],
							),
						),
						Container(
							margin: const EdgeInsets.only(top: 20.0),
							padding: const EdgeInsets.only(left: 20.0, right: 20.0),
							child: new Row(
								children: <Widget>[
									new Expanded(
										child: FlatButton(
											shape: new RoundedRectangleBorder(
												borderRadius: new BorderRadius.circular(30.0)),
											splashColor: this.primaryColor,
											color: this.primaryColor,
											child: new Row(
												children: <Widget>[
													new Padding(
														padding: const EdgeInsets.only(left: 20.0),
														child: Text(
															"LOGIN",
															style: TextStyle(color: Colors.white),
														),
													),
													new Expanded(
														child: Container(),
													),
													new Transform.translate(
														offset: Offset(10.0, 0.0),
														child: new Container(
															padding: const EdgeInsets.all(5.0),
															child: FlatButton(
																shape: new RoundedRectangleBorder(
																	borderRadius:
																	new BorderRadius.circular(28.0)),
																splashColor: Colors.white,
																color: Colors.white,
																child: Icon(
																	FlutterIcons.login_ant,
																	color: this.primaryColor,
																),
																onPressed: () => {},
															),
														),
													)
												],
											),
											onPressed: () {
												login();
												Navigator.of(context).pushReplacement(
													new MaterialPageRoute(builder: (_) => Landing())
												);
											},
										),
									),
								],
							),
						),
						Container(
							margin: const EdgeInsets.only(top: 10.0),
							padding: const EdgeInsets.only(left: 20.0, right: 20.0),
							child: new Row(
								children: <Widget>[
									new Expanded(
										child: FlatButton(
											shape: new RoundedRectangleBorder(
												borderRadius: new BorderRadius.circular(30.0)),
											splashColor: Color(0xFF3B5998),
											color: Color(0xff3B5998),
											child: new Row(
												children: <Widget>[
													new Padding(
														padding: const EdgeInsets.only(left: 20.0),
														child: Text(
															"LOGIN WITH GOOGLE",
															style: TextStyle(color: Colors.white),
														),
													),
													new Expanded(
														child: Container(),
													),
													new Transform.translate(
														offset: Offset(10.0, 0.0),
														child: new Container(
															padding: const EdgeInsets.all(5.0),
															child: FlatButton(
																shape: new RoundedRectangleBorder(
																	borderRadius:
																	new BorderRadius.circular(28.0)),
																splashColor: Colors.white,
																color: Colors.white,
																child: Icon(
																	FlutterIcons.google_ant,
																	color: Color(0xff3b5998),
																),
																onPressed: () => handleSignIn(),
															),
														),
													)
												],
											),
											onPressed: () {
												handleSignIn();
												Navigator.of(context).pushReplacement(
													new MaterialPageRoute(builder: (_) => Home())
												);
											},
										),
									),
								],
							),
						),
						Container(
							margin: const EdgeInsets.only(top: 20.0),
							padding: const EdgeInsets.only(left: 20.0, right: 20.0),
							child: new Row(
								children: <Widget>[
									new Expanded(
										child: FlatButton(
											shape: new RoundedRectangleBorder(
												borderRadius: new BorderRadius.circular(30.0)),
											color: Colors.transparent,
											child: Container(
												padding: const EdgeInsets.only(left: 20.0),
												alignment: Alignment.center,
												child: Text(
													"DON'T HAVE AN ACCOUNT?",
													style: TextStyle(color: this.primaryColor),
												),
											),
											onPressed: () => {},
										),
									),
								],
							),
						),


					],
				),
			),
		);
	}

	void login() {
		print('logging in');
	}
}

class MyClipper extends CustomClipper<Path> {
	@override
	Path getClip(Size size) {
		Path p = new Path();
		p.lineTo(size.width, 0.0);
		p.lineTo(size.width, size.height * 0.85);
		p.arcToPoint(
			Offset(0.0, size.height * 0.85),
			radius: const Radius.elliptical(50.0, 10.0),
			rotation: 0.0,
		);
		p.lineTo(0.0, 0.0);
		p.close();
		return p;
	}

	@override
	bool shouldReclip(CustomClipper oldClipper) {
		return true;
	}
}