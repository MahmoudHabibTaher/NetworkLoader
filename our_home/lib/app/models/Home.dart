import 'package:our_home/app/models/Item.dart';

class Home {
  String name;
  String description;

  List<Item> items = List();

  Home(this.name, this.description, this.items);

  addItem(Item item) {
    items.add(item);
  }
}
