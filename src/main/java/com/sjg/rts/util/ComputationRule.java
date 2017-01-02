package com.sjg.rts.util;

/**
 * Created by fuqingjian on 2016/11/22.
 */
public class ComputationRule {

    public static boolean isDoubleNum(Integer value) {
        if (value.intValue() % 2 == 0) {
            return true;
        }
        return false;
    }

    public static boolean isSingleBigNum(Integer value) {
        if (value.intValue() >= 5) {
            return true;
        }
        return false;
    }

    public static boolean isSUMBigNum(Integer value) {
        if (value >= 23) {
            return true;
        }
        return false;
    }

    public static boolean isSUMLeopardQ3(Integer firstValue, Integer secondValue, Integer thirdValue, Integer fourthValue,
                                   Integer fifthValue) {

        if (firstValue.intValue() == secondValue.intValue() && secondValue.intValue() == thirdValue.intValue()) {
            return true;
        }
        return false;
    }

    public static boolean isSUMLeopardZ3(Integer firstValue, Integer secondValue, Integer thirdValue, Integer fourthValue,
                                   Integer fifthValue) {

        if (secondValue.intValue() == thirdValue.intValue() && thirdValue.intValue() == fourthValue.intValue()) {
            return true;
        }
        return false;
    }

    public static boolean isSUMLeopardH3(Integer firstValue, Integer secondValue, Integer thirdValue, Integer fourthValue,
                                   Integer fifthValue) {
        if (thirdValue.intValue() == fourthValue.intValue() && fourthValue.intValue() == fifthValue.intValue()) {
            return true;
        }
        return false;
    }

    public static boolean isSUMStraightQ3(Integer firstValue, Integer secondValue, Integer thirdValue, Integer fourthValue,
                                    Integer fifthValue) {
        Integer start3Num[] = { firstValue, secondValue, thirdValue };
        bubbleSort(start3Num);
        if ((start3Num[1].intValue() - start3Num[0].intValue() == 1)
                && (start3Num[2].intValue() - start3Num[1].intValue() == 1)) {
            return true;
        }
        if (start3Num[0].intValue() == 0 && start3Num[1].intValue() == 8 && start3Num[2].intValue() == 9) {
            return true;
        }
        if (start3Num[0].intValue() == 0 && start3Num[1].intValue() == 1 && start3Num[2].intValue() == 9) {
            return true;
        }
        return false;
    }

    public static boolean isSUMStraightZ3(Integer firstValue, Integer secondValue, Integer thirdValue, Integer fourthValue,
                                    Integer fifthValue) {
        Integer mid3Num[] = { secondValue, thirdValue, fourthValue };
        bubbleSort(mid3Num);
        if ((mid3Num[1].intValue() - mid3Num[0].intValue() == 1)
                && (mid3Num[2].intValue() - mid3Num[1].intValue() == 1)) {
            return true;
        }
        if (mid3Num[0].intValue() == 0 && mid3Num[1].intValue() == 8 && mid3Num[2].intValue() == 9) {
            return true;
        }
        if (mid3Num[0].intValue() == 0 && mid3Num[1].intValue() == 1 && mid3Num[2].intValue() == 9) {
            return true;
        }
        return false;
    }

    public static boolean isSUMStraightH3(Integer firstValue, Integer secondValue, Integer thirdValue, Integer fourthValue,
                                    Integer fifthValue) {
        Integer end3Num[] = { thirdValue, fourthValue, fifthValue };
        bubbleSort(end3Num);
        if ((end3Num[1].intValue() - end3Num[0].intValue() == 1)
                && (end3Num[2].intValue() - end3Num[1].intValue() == 1)) {
            return true;
        }
        if (end3Num[0].intValue() == 0 && end3Num[1].intValue() == 8 && end3Num[2].intValue() == 9) {
            return true;
        }
        if (end3Num[0].intValue() == 0 && end3Num[1].intValue() == 1 && end3Num[2].intValue() == 9) {
            return true;
        }
        return false;
    }

    // 冒泡排序
    public static void bubbleSort(Integer[] numbers) {
        Integer temp; // 记录临时中间值
        Integer size = numbers.length; // 数组大小
        for (Integer i = 0; i < size - 1; i++) {
            for (Integer j = i + 1; j < size; j++) {
                if (numbers[i] > numbers[j]) { // 交换两数的位置
                    temp = numbers[i];
                    numbers[i] = numbers[j];
                    numbers[j] = temp;
                }
            }
        }
    }

    public static boolean isSUMPairedQ3(Integer firstValue, Integer secondValue, Integer thirdValue, Integer fourthValue,
                                  Integer fifthValue) {
        if (firstValue.intValue() == secondValue.intValue() || secondValue.intValue() == thirdValue.intValue()
                || thirdValue.intValue() == firstValue.intValue()) {
            return true;
        }
        return false;
    }

    public static boolean isSUMPairedZ3(Integer firstValue, Integer secondValue, Integer thirdValue, Integer fourthValue,
                                  Integer fifthValue) {
        if (secondValue.intValue() == thirdValue.intValue() || thirdValue.intValue() == fourthValue.intValue()
                || secondValue.intValue() == fourthValue.intValue()) {
            return true;
        }
        return false;
    }

    public static boolean isSUMPairedH3(Integer firstValue, Integer secondValue, Integer thirdValue, Integer fourthValue,
                                  Integer fifthValue) {
        if (thirdValue.intValue() == fourthValue.intValue() || fourthValue.intValue() == fifthValue.intValue()
                || thirdValue.intValue() == fifthValue.intValue()) {
            return true;
        }

        return false;
    }

    public static boolean isSUMHalfstraightQ3(Integer firstValue, Integer secondValue, Integer thirdValue,
                                        Integer fourthValue, Integer fifthValue) {
        Integer start3Num[] = { firstValue, secondValue, thirdValue };
        bubbleSort(start3Num);
        if ((start3Num[1].intValue() - start3Num[0].intValue() == 1)
                || (start3Num[2].intValue() - start3Num[1].intValue() == 1)) {
            return true;
        }
        if ((start3Num[2].intValue() == 9 && start3Num[0].intValue() == 0)) {
            return true;
        }
        return false;
    }

    public static boolean isSUMHalfstraightZ3(Integer firstValue, Integer secondValue, Integer thirdValue,
                                        Integer fourthValue, Integer fifthValue) {
        Integer mid3Num[] = { secondValue, thirdValue, fourthValue };
        bubbleSort(mid3Num);
        if ((mid3Num[1].intValue() - mid3Num[0].intValue() == 1)
                || (mid3Num[2].intValue() - mid3Num[1].intValue() == 1)) {
            return true;
        }
        if ((mid3Num[2].intValue() == 9 && mid3Num[0].intValue() == 0)) {
            return true;
        }
        return false;
    }

    public static boolean isSUMHalfstraightH3(Integer firstValue, Integer secondValue, Integer thirdValue,
                                        Integer fourthValue, Integer fifthValue) {
        Integer end3Num[] = { thirdValue, fourthValue, fifthValue };
        bubbleSort(end3Num);
        if ((end3Num[1].intValue() - end3Num[0].intValue() == 1)
                || (end3Num[2].intValue() - end3Num[1].intValue() == 1)) {
            return true;
        }
        if ((end3Num[2].intValue() - end3Num[0].intValue() == 9)) {
            return true;
        }
        if ((end3Num[2].intValue() == 9 && end3Num[0].intValue() == 0)) {
            return true;
        }
        return false;
    }
}
