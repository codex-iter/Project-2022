import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:user_expenses/components/heading_toolbar.dart';

import '../components/transaction_card.dart';
import '../models/transactions.dart';

class MyHomePage extends StatelessWidget {
  const MyHomePage({super.key, required this.title});

  final String title;

  @override
  Widget build(BuildContext context) {
    return ChangeNotifierProvider(
      create: (context) => TransactionsModel(),
      child: GestureDetector(
        onTap: () {
          FocusScopeNode currentFocus = FocusScope.of(context);
          if (!currentFocus.hasPrimaryFocus) {
            currentFocus.unfocus();
          }
        },
        child: Scaffold(
          body: SafeArea(
            child: Padding(
              padding: const EdgeInsets.only(top: 20, left: 15, right: 15),
              child: Center(
                child: ConstrainedBox(
                  constraints: const BoxConstraints(maxWidth: 500),
                  child: Column(
                    children: <Widget>[
                      const HeadingToolbar(),
                      const MyForm(),
                      buildTransactions(context),
                    ],
                  ),
                ),
              ),
            ),
          ),
        ),
      ),
    );
  }

  Consumer<TransactionsModel> buildTransactions(BuildContext context) {
    return Consumer<TransactionsModel>(
      builder: (_, model, __) {
        if (model.transactions.isNotEmpty) {
          return Expanded(
            child: ListView(
              children: [
                ...model.transactions
                    .map((Transaction transaction) =>
                        TransactionCard(transaction))
                    .toList()
              ],
            ),
          );
        } else {
          return Expanded(
            child: Center(
              child: Text(
                "Feels Lonely :(",
                style: TextStyle(
                  fontSize: 20,
                  fontWeight: FontWeight.bold,
                  color: Theme.of(context)
                      .colorScheme
                      .onBackground
                      .withOpacity(.7),
                ),
              ),
            ),
          );
        }
      },
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
  final List<bool> _selections = List.generate(3, (index) => (index == 1));
  final List<IconData> icons = [
    Icons.currency_rupee_rounded,
    Icons.attach_money_rounded,
    Icons.euro_symbol_rounded,
  ];

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.symmetric(vertical: 20),
      child: Form(
        key: _formKey,
        child: Column(
          children: [
            TextFormField(
              onSaved: (newValue) => _title = newValue,
              textInputAction: TextInputAction.next,
              validator: (value) {
                if (value == null || value.isEmpty) {
                  return "Title can't be empty";
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
              textInputAction: TextInputAction.done,
              validator: (value) {
                if (value == null || value.isEmpty) {
                  return "Amount can't be empty";
                } else if (double.tryParse(value) == null) {
                  return "Amount must be digits only";
                }
                return null;
              },
              decoration: const InputDecoration(
                labelText: "Amount",
              ),
            ),
            const SizedBox(
              height: 20,
            ),
            Row(
              mainAxisAlignment: MainAxisAlignment.spaceAround,
              children: [
                ToggleButtons(
                  isSelected: _selections,
                  borderRadius: BorderRadius.circular(20),
                  onPressed: (index) => setState(() {
                    for (int i = 0; i < _selections.length; i++) {
                      if (i == index) {
                        _selections[i] = true;
                      } else {
                        _selections[i] = false;
                      }
                    }
                    final model = context.read<TransactionsModel>();
                    model.currency = icons[index];
                  }),
                  children: [
                    Icon(icons[0]),
                    Icon(icons[1]),
                    Icon(icons[2]),
                  ],
                ),
                const SizedBox(
                  width: 20,
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
                    context.read<TransactionsModel>().addTransaction(
                          _title!,
                          double.tryParse(_amount!) ?? 0,
                        );
                  },
                  child: const Text("Submit"),
                ),
              ],
            )
          ],
        ),
      ),
    );
  }
}
