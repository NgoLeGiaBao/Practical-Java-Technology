public class Program {
    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("Invalid expression");
            return;
        }

        try {
            double num1 = Double.parseDouble(args[0]);
            String operator = args[1];
            double num2 = Double.parseDouble(args[2]);
            
            double result;
            switch (operator) {
                case "+" -> result = num1 + num2;
                case "-" -> result = num1 - num2;
                case "x" -> result = num1 * num2;
                case "/" -> {
                    if (num2 == 0) {
                        System.out.println("Cannot divide by zero");
                        return;
                    }
                    result = num1 / num2;
                }
                case "^" -> result = Math.pow(num1, num2);
                default -> {
                    System.out.println("Unsupported operator");
                    return;
                }
            }
            System.out.println(result);
        } catch (NumberFormatException e) {
            System.out.println("Invalid expression");
        }
    }
}
