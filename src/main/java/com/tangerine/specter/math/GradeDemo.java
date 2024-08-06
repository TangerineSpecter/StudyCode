package com.tangerine.specter.math;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class GradeDemo {


    public static void main(String[] args) {
        // 假设你有一批 totalGrade 分数
        BigDecimal[] totalGrades = {
                new BigDecimal(1000),
                new BigDecimal(500),
                new BigDecimal(200),
                new BigDecimal(100),
                new BigDecimal(50)
        };

        // 获取最大 totalGrade
        BigDecimal maxTotalGrade = getMaxTotalGrade(totalGrades);

        // 归一化到0-100
        BigDecimal[] normalizedGrades = new BigDecimal[totalGrades.length];
        for (int i = 0; i < totalGrades.length; i++) {
            normalizedGrades[i] = normalizeWithLogAdjusted(totalGrades[i], maxTotalGrade);
            System.out.println("Original: " + totalGrades[i] + ", Normalized: " + normalizedGrades[i]);
        }
    }

    private static BigDecimal getMaxTotalGrade(BigDecimal[] totalGrades) {
        BigDecimal maxTotalGrade = BigDecimal.ZERO;
        for (BigDecimal grade : totalGrades) {
            if (grade.compareTo(maxTotalGrade) > 0) {
                maxTotalGrade = grade;
            }
        }
        return maxTotalGrade;
    }

    private static BigDecimal normalizeWithLogAdjusted(BigDecimal totalGrade, BigDecimal maxTotalGrade) {
        // 对数变换
        BigDecimal logTotalGrade = new BigDecimal(Math.log(totalGrade.doubleValue() + 1));
        BigDecimal logMaxTotalGrade = new BigDecimal(Math.log(maxTotalGrade.doubleValue() + 1));

        // 归一化到0-100
        BigDecimal normalizedGrade = logTotalGrade.multiply(new BigDecimal(100))
                .divide(logMaxTotalGrade, 2, RoundingMode.HALF_UP);

        return normalizedGrade;
    }

}
