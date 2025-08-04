package main;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;

public class CountWordClumpsTest {

    // ฟังก์ชันที่ต้องทดสอบ (คัดลอกมาจากโจทย์)
    public static int countClumps(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int count = 0;
        int prev = nums[0];
        boolean inClump = false;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == prev && !inClump) {
                inClump = true;
                count += 1;
            }
            if (nums[i] != prev) {
                prev = nums[i];
                inClump = false;
            }
        }
        return count;
    }

    // ข้อมูล test case ทั้งหมด
    static Stream<Arguments> provideClumpTestCases() {
        return Stream.of(
            // TC1: null
            Arguments.of((int[])null, 0),                    // Expected: 0
            // TC2: []
            Arguments.of(new int[]{}, 0),                    // Expected: 0
            // TC3: [1]
            Arguments.of(new int[]{1}, 0),                   // Expected: 0
            // TC4: [1,1]
            Arguments.of(new int[]{1,1}, 1),                 // Expected: 1
            // TC5: [1,2]
            Arguments.of(new int[]{1,2}, 0),                 // Expected: 0
            // TC6: [1,1,2,2]
            Arguments.of(new int[]{1,1,2,2}, 2),             // Expected: 2
            // TC7: [1,1,1]
            Arguments.of(new int[]{1,1,1}, 1)                // Expected: 1
        );
    }

    @ParameterizedTest
    @MethodSource("provideClumpTestCases")
    void testCountClumps(int[] input, int expected) {
        assertEquals(expected, countClumps(input));
    }
}