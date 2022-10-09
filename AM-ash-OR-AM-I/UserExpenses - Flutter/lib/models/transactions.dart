import 'package:flutter/material.dart';

class TransactionsModel with ChangeNotifier {
  List<Transaction> transactions = [];
  IconData _currency = Icons.attach_money_rounded;

  IconData get currency => _currency;
  set currency(IconData currency) {
    _currency = currency;
    notifyListeners();
  }

  void addTransaction(String title, double amount) {
    transactions.add(
      Transaction(
        title: title,
        amount: amount,
        dateTime: DateTime.now(),
      ),
    );
    notifyListeners();
  }
}

class Transaction {
  final String title;
  final double amount;
  final DateTime dateTime;
  Transaction({
    required this.title,
    required this.amount,
    required this.dateTime,
  });
}
