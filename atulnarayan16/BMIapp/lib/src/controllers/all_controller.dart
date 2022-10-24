import 'package:get/get.dart';
import 'bmi_controller.dart';

class AllController extends Bindings {
  @override
  void dependencies() {
    Get.lazyPut(() => BmiController());
  }
}
