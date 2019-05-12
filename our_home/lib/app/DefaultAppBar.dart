import 'package:flutter/material.dart';

AppBar defaultAppBar({String title, void init(AppBar appBarr)}) {
  final appBar = AppBar(
    title: Text(title),
  );

  init(appBar);

  return appBar;
}
