import 'dart:developer';

import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:user_expenses/models/transactions.dart';

import './components/card_toolbar.dart';
import './components/transaction_card.dart';
import 'package:dynamic_color/dynamic_color.dart';

void main() {
  SystemChrome.setSystemUIOverlayStyle(
    const SystemUiOverlayStyle(
      statusBarColor: Colors.transparent,
    ),
  );
  runApp(const ExpensesApp());
}

class ExpensesApp extends StatefulWidget {
  const ExpensesApp({super.key});

  @override
  State<ExpensesApp> createState() => _ExpensesAppState();
}

class _ExpensesAppState extends State<ExpensesApp> {
  @override
  Widget build(BuildContext context) {
    return DynamicColorBuilder(
      builder: (lightDynamic, darkDynamic) {
        return MaterialApp(
          title: 'Flutter Demo',
          theme: ThemeData(
              useMaterial3: true,
              colorScheme: lightDynamic,
              scaffoldBackgroundColor: lightDynamic?.background,
              inputDecorationTheme: InputDecorationTheme(
                contentPadding: const EdgeInsets.all(20),
                border: OutlineInputBorder(
                  borderRadius: BorderRadius.circular(20),
                ),
                fillColor: lightDynamic?.primary.withOpacity(.04),
                filled: true,
              )),
          home: const MyHomePage(title: 'Flutter Demo Home Page'),
        );
      },
    );
  }
}

class MyHomePage extends StatefulWidget {
  const MyHomePage({super.key, required this.title});

  final String title;

  @override
  State<MyHomePage> createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  final List<Transaction> transactions = [
    Transaction(
        title: "title", amount: 23.45, dateTime: DateTime(2022, 09, 08)),
    Transaction(
        title: "2343141", amount: 23.45, dateTime: DateTime(2022, 09, 08)),
  ];

  @override
  Widget build(BuildContext context) {
    return SafeArea(
      child: Scaffold(
        body: Padding(
          padding: const EdgeInsets.only(top: 20, left: 15, right: 15),
          child: Center(
            child: ConstrainedBox(
              constraints: const BoxConstraints(maxWidth: 500),
              child: Column(
                children: <Widget>[
                  const CardToolbar(),
                  const MyForm(),
                  Expanded(
                    child: ListView.builder(
                      itemCount: 2,
                      itemBuilder: (context, index) =>
                          TransactionCard(transactions[index]),
                    ),
                  )
                ],
              ),
            ),
          ),
        ),
      ),
    );
  }
}

class MyForm extends StatefulWidget {
  const MyForm({super.key});

  @override
  State<MyForm> createState() => _MyFormState();
}

class _MyFormState extends State<MyForm> {
  final GlobalKey<FormState> _formKey = GlobalKey<FormState>();
  String? _title;
  String? _amount;

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.symmetric(vertical: 30),
      child: Form(
        key: _formKey,
        child: Column(
          children: [
            TextFormField(
              onSaved: (newValue) => _title = newValue,
              validator: (value) {
                if (value == null || value.isEmpty) {
                  return "Amount can't be empty";
                }
                return null;
              },
              decoration: const InputDecoration(
                labelText: "Title",
              ),
            ),
            const SizedBox(
              height: 15,
            ),
            TextFormField(
              onSaved: (newValue) => _amount = newValue,
              decoration: const InputDecoration(
                labelText: "Amount",
              ),
            ),
            const SizedBox(
              height: 20,
            ),
            ElevatedButton(
              style: ElevatedButton.styleFrom(
                backgroundColor: Theme.of(context).colorScheme.primary,
                foregroundColor: Theme.of(context).colorScheme.onPrimary,
              ),
              onPressed: () {
                if (!(_formKey.currentState?.validate() ?? true)) {
                  return;
                }
                _formKey.currentState?.save();
                log("${_title.toString()} ${_amount.toString()}");
              },
              child: const Text("Submit"),
            )
          ],
        ),
      ),
    );
  }
}
