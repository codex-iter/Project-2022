import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:google_fonts/google_fonts.dart';
import 'package:provider/provider.dart';
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
              colorScheme: lightDynamic ??
                  ColorScheme.fromSeed(seedColor: Colors.indigo),
              scaffoldBackgroundColor: lightDynamic?.background,
              textTheme: GoogleFonts.poppinsTextTheme(const TextTheme()),
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
  @override
  Widget build(BuildContext context) {
    return ChangeNotifierProvider(
      create: (context) => TransactionsModel(),
      child: SafeArea(
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
                    Consumer<TransactionsModel>(
                      builder: (context, model, child) => Expanded(
                        child: ListView.builder(
                          itemCount: model.transactions.length,
                          itemBuilder: (context, index) =>
                              TransactionCard(model.transactions[index]),
                        ),
                      ),
                    )
                  ],
                ),
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
  final List<bool> _selections = List.generate(3, (index) => (index == 1));
  final List<IconData> icons = [
    Icons.currency_rupee_rounded,
    Icons.attach_money_rounded,
    Icons.euro_symbol_rounded,
  ];

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
