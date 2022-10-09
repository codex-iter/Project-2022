import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:user_expenses/models/transactions.dart';

class TransactionCard extends StatelessWidget {
  final Transaction transaction;

  const TransactionCard(
    this.transaction, {
    Key? key,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.all(8.0),
      child: DecoratedContainer(
        child: Padding(
          padding: const EdgeInsets.all(6.0),
          child: Row(
            children: [
              Container(
                alignment: Alignment.center,
                padding: const EdgeInsets.symmetric(horizontal: 10),
                height: 60,
                decoration: BoxDecoration(
                  border: Border.all(
                    color: Theme.of(context).colorScheme.primary,
                  ),
                  borderRadius: BorderRadius.circular(20),
                ),
                child: Row(
                  children: [
                    Icon(
                      context.select<TransactionsModel, IconData>(
                        (model) => model.currency,
                      ),
                      size: 20,
                      color: Theme.of(context).colorScheme.primary,
                    ),
                    Text(
                      transaction.amount.toString(),
                      style: TextStyle(
                        color: Theme.of(context).colorScheme.primary,
                        fontSize: 18,
                        fontWeight: FontWeight.bold,
                      ),
                    ),
                  ],
                ),
              ),
              const SizedBox(
                width: 20,
              ),
              Expanded(
                child: Column(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    Text(
                      transaction.title,
                      style: const TextStyle(
                        fontWeight: FontWeight.bold,
                        fontSize: 16,
                      ),
                    ),
                    Text(
                      transaction.date,
                      style: Theme.of(context).textTheme.caption,
                    ),
                    Text(
                      transaction.time,
                      style: Theme.of(context).textTheme.caption,
                    ),
                  ],
                ),
              )
            ],
          ),
        ),
      ),
    );
  }
}

class DecoratedContainer extends StatelessWidget {
  const DecoratedContainer({required this.child, super.key});
  final Widget child;
  @override
  Widget build(BuildContext context) {
    return Container(
      decoration: BoxDecoration(
          color: Theme.of(context).colorScheme.background,
          borderRadius: BorderRadius.circular(20),
          boxShadow: [
            BoxShadow(
              color: Theme.of(context).colorScheme.primaryContainer,
              blurRadius: 5,
              spreadRadius: 1,
            )
          ]),
      child: child,
    );
  }
}
