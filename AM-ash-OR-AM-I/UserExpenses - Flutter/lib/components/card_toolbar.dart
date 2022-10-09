import 'package:flutter/material.dart';

class CardToolbar extends StatelessWidget {
  const CardToolbar({
    Key? key,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return ConstrainedBox(
      constraints: const BoxConstraints(maxWidth: 400),
      child: SizedBox(
        height: 50,
        width: double.infinity,
        child: Container(
          decoration: BoxDecoration(
              borderRadius: BorderRadius.circular(20),
              color: Theme.of(context).colorScheme.primary,
              boxShadow: [
                BoxShadow(
                  spreadRadius: 5,
                  blurRadius: 8,
                  offset: const Offset(0, 1),
                  color: Theme.of(context).colorScheme.primaryContainer,
                )
              ]),
          child: const Align(
            child: Text(
              "Expense Tracker",
              style: TextStyle(
                fontSize: 18,
                letterSpacing: 1.5,
                fontWeight: FontWeight.w600,
                color: Colors.white,
              ),
            ),
          ),
        ),
      ),
    );
  }
}
