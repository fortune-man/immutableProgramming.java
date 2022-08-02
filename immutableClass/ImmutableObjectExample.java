package immutableClass;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


// 최종 접근자(final accessor)를 클래스에 설정하는 것 유일한 방법
// 메서드 오버라이딩을 사용해선 안 된다.
public class ImmutableObjectExample {
    private final int a;
    private final Integer b;
    private final List<Integer>c;

    public ImmutableObjectExample(int a, Integer b, List<Integer> c) {
        this.a = a;// primitive -> 불변이므로 불필요
        this.b = b; // Integer는 불변이므로 불필요
        this.c = new ArrayList<>(c); // 리스트가 불변 객체가 아니기 때문에 방어 복제본을 만들고
        // 필드에 할당해야 한다.
    }

    ImmutableObjectExample originalInstance = new ImmutableObjectExample(10, 20, Arrays.asList(0, 1, 2));
    ImmutableObjectExample copyInstance = new ImmutableObjectExample(
            originalInstance.getA() + 1,
            originalInstance.getB(),
            originalInstance.getC());

    public int getA() {
        return a;
    }

    public Integer getB() {
        return b;
    }

    public List<Integer> getC() {
        //defensive copy 반환 필요
        return c.stream().collect(Collectors.toList());Collectors.toList()
    }

    public String basicConcatenation(String[] args){
        String result = "";
        for (String string : strings) {
            result += string;
        }
    }

    public String efficientConcatenation(String[] args){
        // create a mutable object
        StringBuilder builder = new StringBuilder();
        for (String string : strings) {
            builder.append(string);
        }
        // 가변 객체를 불변 객체로 변환하여 반환
        return builder.toString();
    }
}
