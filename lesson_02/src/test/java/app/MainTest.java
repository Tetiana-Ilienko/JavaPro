package app;


import org.junit.Assert;
import org.junit.Test;

public class MainTest {

    // 1. Проверяем, действительно ли метод выдаёт ошибку при отриц.значении.
    // 2. Проверим, правильный ли размер массива.
    // 3. Проверим работу метода при граничных значениях (0).
    // 4. Проверим, действительно ли соседние элементы отличаются на 1.
    // 5. Точечно проверим внутренние значения в массиве.
    // 6. Проверим, что метод возвращает не null.
    @Test(expected = IllegalArgumentException.class)
    public void checkExpectedException() {
        Main.getArray(-1);

    }

    @Test
    public void checkIfArraySizeIsCorrect() {
        int[] result = Main.getArray(7);
        int expectedSize = 7;
        int actualSize = result.length;
        Assert.assertEquals(expectedSize, actualSize);
    }

    @Test
    public void checkIfArraySize0() {
        int[] result = Main.getArray(0);
        int expectedSize = 0;
        int actualSize = result.length;
        Assert.assertEquals(expectedSize, actualSize);
    }

    @Test
    public void checkDifferBetweenElements() {
        int[] array = Main.getArray(10);
        int expectedResult = 1;
        for (int i = array.length - 1; i > 0; i--) {
            int actualResult = array[i] - array[i - 1];
            Assert.assertEquals(expectedResult, actualResult);

        }

    }

    @Test
    public void checkValueElements() {
        int[] result = Main.getArray(10);
        for (int i = 0; i < result.length; i++) {
            Assert.assertEquals(i + 1, result[i]);
        }

    }
    @Test
    public void checkResultNotNull(){
        int[] result = Main.getArray(10);
        Assert.assertNotNull(result);

    }


}