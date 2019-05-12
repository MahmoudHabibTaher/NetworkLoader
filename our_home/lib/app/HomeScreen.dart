import 'package:flutter/material.dart';
import 'package:flutter/widgets.dart';
import 'package:our_home/app/DefaultAppBar.dart';
import 'package:our_home/app/models/SecondRouteArgs.dart';

class HomeScreen extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: defaultAppBar(title: "Home", init: (AppBar appBar) {}),
      body: Center(
        child: RaisedButton(
          child: Text('Open Route'),
          onPressed: () {
            Navigator.pushNamed(context, '/second');
          },
        ),
      ),
    );
  }
}

class SecondRoute extends StatelessWidget {
  @override
  Widget build(BuildContext context) {

    final SecondRouteArgs args = ModalRoute.of(context).settings.arguments;

    return Scaffold(
      appBar: defaultAppBar(title: "Second Route", init: (AppBar appBar) {}),
      body: Center(
        child: RaisedButton(
          child: Text('Go Back'),
          onPressed: () {
            Navigator.pop(context);
          },
        ),
      ),
    );
  }
}
