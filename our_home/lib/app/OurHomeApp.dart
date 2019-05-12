import 'package:flutter/material.dart';
import 'package:our_home/app/HomeScreen.dart';

class OurHomeApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      initialRoute: '/',
      routes: {
        '/': (context) => HomeScreen(),
        '/second': (context) => SecondRoute(),
      },
    );
  }
}
