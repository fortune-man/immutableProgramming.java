### Reference
---

본 페이지는 다음을 참조하여 작성되었습니다. 🙇🏻‍♂️

- [why use immutable objects?](https://www.baeldung.com/java-immutable-object)
- [what is purpose of immutable classes?](https://dzone.com/articles/java-using-immutable-classes-for-concurrent-programming)
- [What-are-final-classes-in-Java]([Final](https://www.tutorialspoint.com/What-are-final-classes-in-Java))
- [race condition](https://gyoogle.dev/blog/computer-science/operating-system/Race%20Condition.html)
- [raceConditionWiki](https://en.wikipedia.org/wiki/Race_condition#In_software)

---

### motivation

자료구조 공부를 시작하면서
스토리지와 메모리,
그리고 자바에서 불변 객체라는 단어를 접하게 되었고
유용성과 목적이 무엇인지 질문해봤다.

> 불변 객체란?
- 완전히 생성된 이후 내부 상태 유지가 지속되는 객체
-> 불변 객체 API가 같은 방식으로 동작함을 의미한다고 함
- String 클래스를 살펴보면 API가 replace 메서드로 가변 동작을 제공하는 것처럼 보이지만, 기존 String 객체는 변경되지 않는다는 것을 알 수 있다.

#### ImmutableObject 예시 코드
```java
public class ImmutableObject {
    String name = "JooHyeongKim";
    String newName = name.replace("Kim","");
public class ImmutableObjectTest {

    @Test
    void assertImmutable(){
        //given
        ImmutableObject immutableObject = new ImmutableObject();

        //when
        String fullName = immutableObject.name; // JooHyeongKim
        String newName = immutableObject.newName; // JooHyeong

        //then
        /**
         * BUILD SUCCESSFUL
         */
        assertEquals(fullName, "JooHyeongKim");
        assertEquals(newName, "JooHyeong");

        /** test FAILED
         * expected: <JooHyeongKim> but was: <JooHyeong>
         * 필요:JooHyeongKim
         * 실제   :JooHyeong
         */
         assertEquals(fullName, newName);

    }
}
```
---

### Final
자바에서 불변성을 얻기 전에, [Final](https://www.tutorialspoint.com/What-are-final-classes-in-Java)에 대해 알아야 한다.
>"Final" in Java
- 클래스, 메서드 및 변수의 구현을 마무리하기 위한 최종 수정자.
- 클래스가 서브클래스되는 것을 방지하는 것을 목적으로 최종 선언하는 클래스로 사용
- 클래스가 최종 클래스로 표시되면 어떤 클래스도 최종 클래스의 어떤 기능도 상속할 수 없습니다. 최종 수업을 연장할 수 없다.

변수는 기본적으로 변경할 수 있으며, 보유하고 있는 값을 변경할 수 있음을 의미하지만,Final 키워드를 사용한다면 컴파일러에서 해당 변수의 값을 변경할 수 없는 대신 컴파일 타임 오류를 보고하게 된다.

``` java
final String nameSmallLetter = "joohyeong";
String nameSmallLetter = "jooheyongkim"; // variable nameSmallLetter is already defined
```


Final은 변수가 보유하고 있는 참조를 변경하는 것을 금지하며,
참조하는 객체의 내부 상태를 공개 API 사용을 통해 변경하는 것을 보호하지 않는다.

```java
    @Test
    void finalImmnutable(){

        final List<String> strings = new ArrayList<>();
        Assertions.assertEquals(0,strings.size()); // return true -> BUILD SUCCESSFUL
        strings.add("kim");
        assertEquals(0, strings.size()); // test FAILED
    }
```
- final 키워드로 정의된 strings 리스트의 크기가 변경되는 시점부터
불변 객체가 아니게 되어 테스트가 실패함이 검증되는 모습

---

### Immutable Object API

변수 내용 변경 방지는 불변 객체 API 구축에 사용할 수 있음을 의미한다.

불변 객체의 API 구축 -> API를 어떻게 사용하든 내부 상태가 변경되지 않음을 보장해야 한다고 함.

올바른 방향으로 나아가기 위해선 속성을 선언할 때 final을 사용하라고 한다.

```java
public class Money {

    private final double amount;
    private final Currency currency;

    // ...


    public Money(double amount, Currency currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public double getAmount() {
        return amount;
    }

    public Currency getCurrency() {
        return currency;
    }
}
```

일반적으로 모든 primitive 타입 변수의 경우 자바는 amount의 값이 변하지 않을 것이라고 보장한다.

그러나, 예시에선 오직 currency만이 변하지 않음을 보장하므로
변경으로부터 스스로 보호하기 위해 Currency API에 의존해야 한다.

대부분의 경우, 사용자 지정 값을 보유하기 위해선 객체의 속성이 필요하며,
불변하는 객체의 내부 상태를 초기화하는 장소는 생성자이다.

불변 API의 요구 사항을 충족시키기 위해, 우리의 Money 클래스에는 read-only 메서드만 있다.

reflection API를 사용하여 불변성을 깨고 불변의 객체를 변경할 수 있으나, reflection은 불변 객체의 공개 API를 위반하며, 일반적으로 피해야 하는 것이다.

---

### 불변(immutable)의 유용성


불변 객체의 내부 상태는 시간이 지나도 일정하게 유지된다.
-> 다중 스레드(multiple threads) 간에 안전한 공유가 가능하다.

또한 자유로운 사용이 가능하며, 참조하는 객체 중 어느 것도 차이를 알아차리지 못할 것이다. -> 불변 객체는 부작용이 없다. (immutable objects are side-effects free.)


> 결론
- 불변 객체(Immutable Object)는 시간에 따라 내부 상태를 변경하지 않는다.
- 스레드로부터 안전하다.
- 부작용이 없다.
- -> 멀티 스레드 환경에서 특히 유용하다고 할 수 있다.

---

###

불변의 클래스가 동기화 블록과 그들이 이어질 수 있는 교착 상태를 피하면서 동시 프로그래밍을 더 쉽게 만드는지 봅시다.


불변 클래스는 동시 프로그래밍을 더 쉽게 만든다.
- 동기화된 블록을 사용하지 않고 작업 중간에 값이 변경되지 않도록 한다다.
- 동기화 블록을 피함으로써, 교착 상태는 피할 수 있다.
- 항상 변하지 않는 일관된 상태로 일하고 있기 때문에, 당신은 경쟁 조건(race condition)을 피한다.
  - [race condition](https://gyoogle.dev/blog/computer-science/operating-system/Race%20Condition.html) : 공유 자원에 대해 여러 프로세스가 동시에 접근할 때, 결과값에 영향을 줄 수 있는 상태
(동시 접근 시 자료의 일관성을 해치는 결과가 나타남)
    - [참고](https://en.wikipedia.org/wiki/Race_condition#In_software) - 컴퓨터 프로그램이 동시에 실행되는 여러 코드 경로가 있을 때 소프트웨어에서 경쟁 조건이 발생하고, 여러 코드 경로가 예상과 다른 시간이 걸린다면, 예상과 다른 순서로 끝낼 수 있으며, 이는 예상치 못한 행동으로 인해 소프트웨어 버그를 일으킬 수 있다..



### 동시 프로그래밍을 위한 불변 클래스를 사용하는 방법?

#### Ex) 사용자 로그인 자격 증명 저장하는 클래스 구현
```java
public class ImmutableLogin {
    private final String userName;
    private final String password;

    public ImmutableLogin(String userName, String password) {
        super();
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
}
```


- 불변 클래스를 구현할 때, 필드를 최종으로 선언한다.
-> 컴파일러는 클래스의 생성자가 호출된 후 필드가 수정되지 않았음을 확인할 수 있다.

- final은 필드 수정자라는 점에 유의하자.
  - 필드 자체는 필드가 참조하는 객체가 아니라 불변하게 만든다.
  따라서 최종 필드의 유형은 예제의 클래스 String과 마찬가지로 불변해야 합니다.

 동일한 정보를 저장하는 가변 클래스

```java
public class MutableLogin {
    private String userName;
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
```
