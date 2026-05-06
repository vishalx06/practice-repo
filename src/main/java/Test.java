import java.util.*;

public class Test {
    /*
    * merge two lists and remove duplicates
        list1 = 1,2,3,4,5
        list2 = 3,4,5,6,7
    * */
    public static void main(String[] args) {
        List<Integer> list1 = Arrays.asList(1,2,3,4,5);
        List<Integer> list2 = Arrays.asList(3,4,5,6,7);

        Set<Integer> set = new LinkedHashSet<>();
        set.addAll(list1);
        set.addAll(list2);

        List<Integer> result = new ArrayList<>(set);

        System.out.println(result);

    }
}



//SELECT MAX(salary) as second_hightes_salary
//    FROM Employee
//            where salary < ( SELECT MAX(salary) FROM Employee);





