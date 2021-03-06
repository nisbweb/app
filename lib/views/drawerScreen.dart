import 'package:connect/views/homeScreen.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_icons/flutter_icons.dart';

class DrawerScreen extends StatefulWidget {
  @override
  _DrawerScreenState createState() => _DrawerScreenState();
}

class _DrawerScreenState extends State<DrawerScreen> {
  @override
  Widget build(BuildContext context) {
    return Container(
      padding: EdgeInsets.symmetric(vertical: 40, horizontal: 20),
      color: Colors.teal,
      child: Stack(
        fit: StackFit.expand,
        children: <Widget>[
          Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            mainAxisSize: MainAxisSize.max,
            children: <Widget>[
              Row(
                mainAxisAlignment: MainAxisAlignment.start,
                crossAxisAlignment: CrossAxisAlignment.center,
                children: <Widget>[
                  CircleAvatar(
                    backgroundImage: AssetImage('assets/images/avataaars.png'),
                    radius: 35,
                    backgroundColor: Colors.transparent,
                  ),
                  SizedBox(width: 10,),
                  Column(
                    crossAxisAlignment: CrossAxisAlignment.start,
                    mainAxisAlignment: MainAxisAlignment.center,
                    children: <Widget>[
                      Text(
                        'Iresh Sharma',
                        style: TextStyle(
                            fontSize: 24,
                            fontWeight: FontWeight.w600,
                            color: Colors.white
                        ),
                      ),
                      Text(
                        'iresharma@outlook.com',
                        style: TextStyle(
                            fontWeight: FontWeight.w100,
                            color: Colors.white
                        ),
                      )
                    ],
                  )
                ],
              ),
              SizedBox(height: MediaQuery.of(context).size.height * 0.2,),
              FlatButton.icon(
                  onPressed: () => print('hi'),
                  icon: Icon(CupertinoIcons.bookmark),
                  label: Text('Bookmarks')
              ),
              FlatButton.icon(
                  onPressed: () => print('hi'),
                  icon: Icon(FlutterIcons.settings_applications_mdi),
                  label: Text('Settings')
              )
            ],
          ),
        ],
      )
    );
  }
}
