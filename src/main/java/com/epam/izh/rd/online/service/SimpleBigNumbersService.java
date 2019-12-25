package com.epam.izh.rd.online.service;

import java.math.BigDecimal;
import java.math.BigInteger;

public class SimpleBigNumbersService implements BigNumbersService {

    /**
     * Метод делит первое число на второе с заданной точностью
     * Например 1/3 с точностью 2 = 0.33
     *
     * @param range точность
     * @return результат
     */
    @Override
    public BigDecimal getPrecisionNumber(int a, int b, int range) {
        return BigDecimal.valueOf(a).divide(BigDecimal.valueOf(b), range, BigDecimal.ROUND_DOWN);
    }

    /**
     * Метод находит простое число по номеру
     *
     * @param range номер числа, считая с числа 2
     * @return простое число
     */
    @Override
    public BigInteger getPrimaryNumber(int range) {
        BigInteger number = BigInteger.valueOf(0);
        int count = 0;
        for (long i = 2; i > 0; i++) {
            if (BigInteger.valueOf(i).isProbablePrime((int) (i))) {
                count++;
                if (count == range) {
                    number = BigInteger.valueOf(i);
                    break;
                }
            }
        }
        return number;
    }
}
