package lambdasinaction.chap6;

import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.function.BinaryOperator;

import static java.util.stream.Collectors.*;
import static lambdasinaction.chap6.Dish.menu;

public class Summarizing {

    public static void main(String... args) {
        System.out.println("Nr. of dishes: " + howManyDishes());
        System.out.println("The most caloric dish is: " + findMostCaloricDish());
        System.out.println("The most caloric dish is: " + findMostCaloricDishUsingComparator());
        System.out.println("Total calories in menu: " + calculateTotalCalories());
        System.out.println("Average calories in menu: " + calculateAverageCalories());
        System.out.println("Menu statistics: " + calculateMenuStatistics());
        System.out.println("Short menu: " + getShortMenu());
        System.out.println("Short menu comma separated: " + getShortMenuCommaSeparated());
    }


    private static long howManyDishes() {
        return menu.stream().collect(counting());
    }

    private static Dish findMostCaloricDish() {
        return menu.stream().collect(reducing((d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2)).get();
    }

    private static Dish findMostCaloricDishUsingComparator() {
        // 比较器， 按照卡路里大小
        Comparator<Dish> dishCaloriesComparator = Comparator.comparingInt(Dish::getCalories);
        BinaryOperator<Dish> moreCaloricOf = BinaryOperator.maxBy(dishCaloriesComparator);
        return menu.stream().collect(reducing(moreCaloricOf)).get();
    }

    /**
     * 求和
     *
     * @return
     */
    private static int calculateTotalCalories() {
        return menu.stream().collect(summingInt(Dish::getCalories));
    }

    /**
     * 求平均值
     *
     * @return
     */
    private static Double calculateAverageCalories() {
        return menu.stream().collect(averagingInt(Dish::getCalories));
    }

    /**
     * 所有的统计值
     *
     * @return
     */
    private static IntSummaryStatistics calculateMenuStatistics() {
        return menu.stream().collect(summarizingInt(Dish::getCalories));
    }

    /**
     * 流的连接
     *
     * @return
     */
    private static String getShortMenu() {
        return menu.stream().map(Dish::getName).collect(joining());
    }

    /**
     * 连接，加分割符
     *
     * @return
     */
    private static String getShortMenuCommaSeparated() {
        return menu.stream().map(Dish::getName).collect(joining(", "));
    }
}
