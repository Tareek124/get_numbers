import 'dart:async';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return const MaterialApp(
      debugShowCheckedModeBanner: false,
      home: HomeScreen(),
    );
  }
}

class HomeScreen extends StatefulWidget {
  const HomeScreen({super.key});

  @override
  State<HomeScreen> createState() => _HomeScreenState();
}

class _HomeScreenState extends State<HomeScreen> {
  static const MethodChannel methodChannel = MethodChannel('getNumbers');

  String myNumber = '';

  Future<void> _getTwoNumbers() async {
    String number;
    Map<String, int> arguments = {"num1": 12, "num2": 15};
    try {
      final int? result =
          await methodChannel.invokeMethod('getTwoNumbers', arguments);
      number = 'Number Is: $result';
    } on PlatformException catch (e) {
      print(e.toString());
      number = "";
    }
    setState(() {
      myNumber = number;
    });
  }

  @override
  Widget build(BuildContext context) {
    return Material(
      child: Column(
        mainAxisAlignment: MainAxisAlignment.spaceEvenly,
        children: <Widget>[
          Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: <Widget>[
              Text(myNumber),
              Padding(
                padding: const EdgeInsets.all(16.0),
                child: ElevatedButton(
                  onPressed: _getTwoNumbers,
                  child: const Text('Get Numbers'),
                ),
              ),
            ],
          ),
        ],
      ),
    );
  }
}
