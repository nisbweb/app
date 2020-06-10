import 'package:flutter/material.dart';
import 'package:flutter_icons/flutter_icons.dart';

class DrawerScreen extends StatefulWidget {
  @override
  _DrawerScreenState createState() => _DrawerScreenState();
}

class _DrawerScreenState extends State<DrawerScreen> {
  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: EdgeInsets.symmetric(vertical: 40, horizontal: 20),
      child: Column(
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
              onPressed: null,
              icon: Icon(FlutterIcons.settings_applications_mdi),
              label: Text('Settings')
          )
        ],
      ),
    );
  }
}
