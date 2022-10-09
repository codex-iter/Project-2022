import 'package:flutter/material.dart';
import 'package:provider/provider.dart';

import '../models/transactions.dart';

class HeadingToolbar extends StatelessWidget {
  const HeadingToolbar({
    Key? key,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Selector<TransactionsModel, double>(
      builder: (_, value, __) => Container(
        width: double.infinity,
        padding: const EdgeInsets.all(10),
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
        child: Column(
          children: [
            Text(
              "Expense Tracker",
              style: TextStyle(
                fontSize: 18,
                letterSpacing: 1.5,
                fontWeight: FontWeight.w600,
                color: Theme.of(context).colorScheme.onPrimary,
              ),
            ),
            Row(
              mainAxisAlignment: MainAxisAlignment.center,
              children: [
                Selector<TransactionsModel, IconData>(
                  builder: (_, value, __) => Icon(
                    value,
                    color: Theme.of(context).colorScheme.onPrimary,
                  ),
                  selector: (_, model) => model.currency,
                ),
                Text(
                  double.parse(value.toStringAsFixed(2)).toString(),
                  style: TextStyle(
                      color: Theme.of(context).colorScheme.onPrimary,
                      fontSize: 18,
                      fontWeight: FontWeight.bold),
                ),
              ],
            ),
          ],
        ),
      ),
      selector: (_, model) => model.total,
    );
  }
}
