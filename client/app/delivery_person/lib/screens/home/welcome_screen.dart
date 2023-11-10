import 'package:delivery_person/screens/home/fragments/home_fragment.dart';
import 'package:delivery_person/screens/home/fragments/notification_fragment.dart';
import 'package:delivery_person/screens/home/fragments/orders_fragment.dart';
import 'package:delivery_person/screens/home/fragments/profile_fragment.dart';
import 'package:delivery_person/widgets/HomeAppBar.dart';
import 'package:flutter/material.dart';

class WelcomeScreen extends StatefulWidget {
  const WelcomeScreen({super.key});

  @override
  State<WelcomeScreen> createState() => _WelcomeScreenState();
}

class _WelcomeScreenState extends State<WelcomeScreen> {
  int _currentIndex = 0;
  final screens = [
    HomeFragment(),
    OrdersFragment(),
    NotificationFragment(),
    ProfileFragment()
  ];
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: HomeAppBar(),
      body: screens[_currentIndex],
      bottomNavigationBar: BottomNavigationBar(
          onTap: (index) => setState(() => _currentIndex = index),
          selectedItemColor: Colors.green.shade400,
          type: BottomNavigationBarType.fixed,
          currentIndex: _currentIndex,
          items: [
            BottomNavigationBarItem(
                icon: const Icon(Icons.home),
                label: "Home",
                backgroundColor: Colors.green.shade400),
            BottomNavigationBarItem(
                icon: const Icon(Icons.menu_book),
                label: "Orders",
                backgroundColor: Colors.green.shade400),
            BottomNavigationBarItem(
                icon: const Icon(Icons.notifications),
                label: "Notification",
                backgroundColor: Colors.green.shade400),
            BottomNavigationBarItem(
                icon: const Icon(Icons.supervised_user_circle),
                label: "Profile",
                backgroundColor: Colors.green.shade400),
          ]),
    );
  }
}
