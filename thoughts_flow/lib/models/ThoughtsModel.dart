import 'package:scoped_model/scoped_model.dart';
import 'package:thoughts_flow/entities/Thought.dart';

class ThoughtsModel extends Model {
  List<Thought> thoughts = [];

  addThoughts(String body) {
    thoughts.add(_buildThought(body));

    notifyListeners();
  }

  deleteThought(Thought thought) {
    thoughts.remove(thought);

    notifyListeners();
  }

  _buildThought(String body) => Thought(body);
}
