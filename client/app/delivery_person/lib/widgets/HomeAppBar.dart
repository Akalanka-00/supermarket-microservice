import 'package:flutter/material.dart';

class HomeAppBar extends StatelessWidget implements PreferredSizeWidget {
  @override
  Widget build(BuildContext context) {
    return AppBar(
      title: const Text("FlashMart"),
      // leading: GestureDetector(child: Icon(Icons.arrow_back_ios), onTap: () => Navigator.pop(context),),
      leading: null,
      backgroundColor: Colors.green.shade400,
      // actions: <Widget>[
      //   IconButton(
      //       onPressed: () {

      //       },
      //       icon: Icon(Icons.delivery_dining))
      // ],
    );
  }

  @override
  Size get preferredSize => const Size(double.infinity, 60);
}
