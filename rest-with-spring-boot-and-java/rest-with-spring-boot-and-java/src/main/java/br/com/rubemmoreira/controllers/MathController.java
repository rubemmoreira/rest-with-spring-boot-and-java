package br.com.rubemmoreira.controllers;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/math")
public class MathController {

    // http://localhost:8080/math/sum/3/5
    @RequestMapping("/sum/{numberOne}/{numberTwo}")
    public double sum(
        @PathVariable("numberOne") String numberOne,
        @PathVariable("numberTwo") String numberTwo
    ) {
        if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
            throw new IllegalArgumentException("Invalid number format!");
        }
        return convertToDouble(numberOne) + convertToDouble(numberTwo);
    }

    // http://localhost:8080/math/subtraction/3/5
    @RequestMapping("/subtraction/{numberOne}/{numberTwo}")
    public double subtraction(
        @PathVariable("numberOne") String numberOne,
        @PathVariable("numberTwo") String numberTwo
    ) {
        if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
            throw new IllegalArgumentException("Invalid number format!");
        }
        return convertToDouble(numberOne) - convertToDouble(numberTwo);
    }

    // http://localhost:8080/math/division/{numberOne}/{numberTwo}
    @RequestMapping("/division/{numberOne}/{numberTwo}")
    public double division(
        @PathVariable("numberOne") String numberOne,
        @PathVariable("numberTwo") String numberTwo
    ) {
        if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
            throw new IllegalArgumentException("Invalid number format!");
        }
        
        double divisor = convertToDouble(numberTwo);
        if (divisor == 0) {
            throw new IllegalArgumentException("Division by zero is not allowed!");
        }
        
        return convertToDouble(numberOne) / divisor;
    }

    // Métodos auxiliares DEVEM estar fora dos métodos principais
    private Double convertToDouble(String strNumber) {
        if (strNumber == null) return 0D;
        
        String number = strNumber.replaceAll(",", ".");
        try {
            return Double.parseDouble(number);
        } catch (NumberFormatException e) {
            return 0D;
        }
    }

    private boolean isNumeric(String strNumber) {
        if (strNumber == null) return false;
        
        String number = strNumber.replaceAll(",", ".");
        try {
            Double.parseDouble(number);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}