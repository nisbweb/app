import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

class E404 extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Stack(
      fit: StackFit.expand,
      children: <Widget>[
        Image.asset('assets/images/e404.png'),
        Center(
          child: Text(
              'You weren\'t supposed to be here',
            style: CupertinoTheme.of(context).textTheme.navLargeTitleTextStyle,
          ),
        )
      ],
    );
  }
}
