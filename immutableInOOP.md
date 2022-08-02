## Referenece
---
- [about-immutability-in-object-oriented-programming](https://dzone.com/articles/about-immutability-in-object-oriented-programming)
- [what is entity object?](https://docs.oracle.com/cd/B10463_01/web.904/b10390/bc_awhatisaneo.htm)
- [Differences between entity and object are](https://www.geeksforgeeks.org/difference-between-entity-and-object/)
- [defensive copy](http://www.javapractices.com/topic/TopicAction.do?Id=15)

---

### 객체지향에서의 불변성
불변성(immutable)이 무엇인지 설명하기 위해, 먼저 우리는 가변성이 무엇인지 이해해야 한다.
>
- 가변성(muttable) :  정체성이 동일하게 유지되는 동안 [엔티티](https://docs.oracle.com/cd/B10463_01/web.904/b10390/bc_awhatisaneo.htm)의 상태를 변경할 수 있는 가능성을 의미한다.

불변성은 그 반대이다. 일단 엔티티가 존재하면, 그 상태는 절대 변하지 않을 것이다.

>참고: [Differences between entity and object are](https://www.geeksforgeeks.org/difference-between-entity-and-object/)
>
| No. | Entity | Object |
|:----------|:----------:|----------:|
|1.	|엔티티는 다른 객체와 구별할 수 있는 실시간 객체|	객체는 취해야 할 모든 속성과 작업을 가진 엔티티|
|2.	|엔티티는 속성을 포함 |	 객체에는 생명 주기, 식별자가 존재|
|3.|	엔티티는 고유하게 식별 가능한 객체 |	 객체는 식별자를 사용하여 식별 |
|4.	 |모든 단체는 식별 목적으로 기본 키를 보유	 |객체는 기본 키로 할당되지 않는다. |
|5.	 |엔티티는 관계형 데이터베이스의 일부|	 객체는 객체 지향 데이터베이스의 일부 |
|6.	 |엔티티는 E-R 다이어그램을 사용하여 직사각형 모양으로 표시된다 |	 객체는 그래픽으로 표현되지 않는다. |
|7. |	 Attribute는 엔티티의 property	 |상속, 캡슐화, 다형성 및 추상화는 객체의 일부 |
|8.	 |예: Example: Computer, Software. |	 예: Example: Minimum age to vote is 18.|

이러한 개념은 OOP에서 어떻게 표현되는가?
- OOP에서는 엔티티가 객체입니다. 그래서, OOP의 불변성 개념은 불변의 물체가 특징이다. 물체가 구성되면, 그것이 사는 동안 같은 상태를 가질 것이다.

객체는 세 가지 특징을 가지고 있다:

- 정체성(identify역할): 객체를 독특하게 만드는 것
- 행동(method책임): 객체가 하는 일
- 상태(state): 객체가 가지고 있는 것

그 특징과 차이점을 이해하는 것은 매우 중요하다.

---

### 자동차🚗 객체가 있다면 어떨까?
- 정체성(identify역할): 번호판
- 행동(method책임): A 지점에서 B 지점으로 운전자를 수송
- 상태(state): 문, 의자, 바퀴, 엔진과 같은 많은 것들. (변경 가능)

자동차 객체를 설계시
- 번호판은 동등한 방법을 구현하는 데 사용하는 것이며, 번호판이 독특하고 변경할 수 없는 방식으로 물체를 설계하는 것이 필수적이다.
- 자동차 객체가 구성되면 전체 애플리케이션을 통해 자동차 객체를 통과할 수 있으며 아무도 번호판을 변경할 수 없다.
- 엔진이나 바퀴는 바뀔 수 있지만, 여전히 같은 차라는 점은 변하지 않는다.




---

### 객체의 불변 상태를 표현하는 방법

그렇다면 우리는 물체의 상태를 어떻게 표현하는가?

#### 1. 접근제어
일반적으로 받아들여지는 응답은 객체의 상태가 필드로 표현된다는 것이다.
객체가 불변하기 위해선 필드가 모든 상황에서 바꿀 수 없다는 것을 의미한다.

>
불변성은 객체의 필드를 변경할 수 없다는 것을 의미 -> 즉, 객체는 많은 곳에서 참조될 수 있다.
- 누구나 모든 공개 메서드를 호출하거나 공개 필드를 수정할 수 있다.
- 그래서 첫 번째 단계로 모든 필드를 비공개로 만들고, 메소드를 통해서만 접근할 수 있도록 하는 것이다.
  - 필드가 공개된다면, 그것을 통제할 수 없고 누구나 그것을 바꿀 수 있기 때문

#### 2. final
또 다른 단계는 모든 필드를 최종적(final)으로 만드는 것이다.
일단 필드가 생성되면 아무도 그것을 변경할 수 없다는 것을 보장한다.

필드가 원시적(primitive)이라면, 비공개로 만들고 최종적으로 만드는 것으로 충분하다. 그러나, 필드가 가변 물체라면, 이것은 그것을 지키기에 충분하지 않다.
그 필드는 실제로 객체가 아니며, 객체가 저장되는 위치를 나타내는 참조일 뿐. 즉, 최종적이고 비공개이기 때문에,
그 참조는 변경할 수 없지만 해당 객체에 접근할 수 있으며 메서드를 호출할 수 있다. 그리고 객체가 변경가능하다면, 그 상태는 바뀔 수 있다.
이 문제를 어떻게 해결할 수 있을까?

getter를 통해 이 객체를 반환해야 한다면, 방어 복사본([Defensive copy](http://www.javapractices.com/topic/TopicAction.do?Id=15))을 만들고 대신 복사본을 반환해야 한다.

#### 예시 - ArrayList of Integer
Integer 객체는 불변하지만, list는 그렇지 않다.
- getter를 통해 반환해야 하는 경우
  - 다른 list를 만들고, 모든 요소를 복사하고, 참조값을 반환한다.
getter를 호출한다면 누구나 list의 참조값만 가질 것이다.
그들은 그것을 수정할 수 있지만, 본래의 list에는 영향을 미치지 않을 것이다.

- setter의 경우
  - 만약 객체가 불변이라면, setter가 없어야 한다는 것은 분명하다.
(setter의 범위는 불변 객체의 경우 허용되지 않는 객체에 필드의 값을 설정하는 것이기 때문)



클래스가 생성자의 매개 변수로 가변 객체를 받으면 필드에 할당되어야 하며, 다시 방어 복사본을 만들고 필드에 복사본을 할당해야 한다.
하지만 방어 사본을 반환하는 것만으로는 충분하지 않다.

생성자에서 변수 객체를 매개 변수로 받고 받은 대로 필드에 할당한다고 가정해 보자.
필드는 private, final이며 getter에서 방어 복사본을 만들고 사본을 반환한다.
하지만 생성자에 복사하지 않았다면 객체는 다른 곳에서 참조될 수 있으며, 수정되어 객체 상태의 변경을 초래할 수 있다.


만약 우리의 getter들이 무시된다면?
- 방어 복사본을 반환하는 대신 원본 필드를 반환하도록 재정의될 수 있다.
- 그래서, 마지막 규칙은  **"method overriding" 을 허용하지 않는 것**이다.
- 이것을 달성하는 방법에는 여러 가지가 있다.
  - 클래스를 최종적으로 만들거나 모든 getter 메서드를 최종적으로 만든다.
  - 클래스에 private 생성자가 있는 경우 확장할 수 없기 때문에 private 생성자와 정적 팩토리 메서드(static factory method)를 사용하여 인스턴스를 만들 수 있다.


### 불변 객체 사용 규칙
>
- 모든 필드를 비공개 및 최종으로 선언
- 필드가 가변 객체라면, 방어 복사본을 만들고 반환할 것
- setter method를 노출금지
- 클래스가 (생성자에서) 필드에 할당되어야 하는 매개 변수로 가변 객체를 받으면, 방어 복사본을 만들고 필드에 복사본을 할당할 것
- getter method 오버라이딩을 허용하지 말 것

자바는 이미 우리에게 불변의 객체를 많이 제공한다.
Integer, Byte, Long, Float, Double, Character, Boolean 및 Short와 같은 모든 기본 래퍼 클래스는 Java에서 불변한다.
객체에 예를 들어 정수인 필드가 있다면, 비공개로 설정하고 최종적으로 보호하기에 충분하다.

```java
public class ImmutableObjectExample {
    private final int a;
    private final Integer b;
    private final List<Integer>c;

    public ImmutableObjectExample(int a, Integer b, List<Integer> c) {
        this.a = a;// a is a primitive, so it doesn’t need a defensive copy
        this.b = b; // b is Integer and Integer is an immutable object. Doesn’t
        // need a defensive copy also;
        this.c = new ArrayList<>(c);
    }
}
```

방어 복사본을 만드는 방법이 생성자에서 getter와 다르다.
(list에 대한 방어적인 복사본을 만드는 방법이 하나도 없다.)
list의 방어 복사본을 만들 때 가장 중요한 것은 그것이 가지고 있는 요소이다.
목록의 요소가 불변의 객체라면 방어 복사본을 만들기 위해, 동일한 요소를 넣는 다른 목록을 만들어야 하며 이를 위한 방법들은 다음과 같다.
- 생성자 사용
- Java 8 Collectors 클래스
- Collections.copy() 정적 메서드를 사용 ..

복사본은 가변 객체이며, 수정할 수 있고, 요소를 추가하거나, 요소를 제거할 수 있지만, 원래 목록에는 영향을 미치지 않는다.
그리고 복사된 목록에 원본 목록과 동일한 객체가 있더라도, 이 경우 정수인 요소는 불변하므로 여러 곳에서 참조되더라도 수정할 수 없다.

기억해라. 생성자와 Getter에서도 방어 복사본을 만들어야 또 다른 옵션이 있다.
__생성자의 매개 변수를 기반으로 불변의 객체를 만들고 대신 저장할 수 있다.__
그런 다음 변경 가능한 객체를 받더라도 객체의 필드는 불변의 복사본이 될 것이며 직접 반환할 수 있게 된다.

#### 일반 list에서 불변 list로 만드는 방법

```java
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
}
```

어쨌든, list는 list이다.
getter는 list 인터페이스를 구현하는 객체를 반환하며
물론 추가 또는 제거와 같은 list의 메서드가 있지만,
이러한 방법 중 하나를 호출하려고 하면 예외가 발생한다.
이것이 변경할 수 없는 list가 구현되는 방법이다.

필드가 변경할 수 있다면, 방어 복사본으로 보호해야 한다.
원시적이거나 불변의 객체라면, 접근자를 private, final로 설정하면 충분하다.

### 불변 객체 변경

불변의 객체를 변경할 수 없지만, 대신 그것을 기반으로 다른 객체를 만들 수 있다.

유일한 할 수 있는 것은 또 다른 방어 복사본이지만, 이번에는 모든 객체를 복사하고 변경을 추가해야 한다.

예제를 살펴보면, 불변 클래스의 인스턴스가 있다면, 기존 인스턴스의 정보로 클래스의 생성자를 호출하여 복사본을 만들 수 있다.
getter 메서드는 원래 객체 필드의 방어 복사본을 반환하므로 해당 getter가 반환한 객체를 사용하여 복사본을 안전하게 구성한다.

#### 예시 인스턴스
```java
ImmutableObjectExample originalInstance = new ImmutableObjectExample(10, 20, Arrays.asList(0, 1,2));
```

첫 번째 필드가 1 증가한 복사본을 만들고 싶다면, 다음과 같이 구성할 수 있다.

```java
ImmutableObjectExample copyInstance = new ImmutableObjectExample(
  originalInstance.getA() + 1,
  originalInstance.getB(),
  originalInstance.getC());
```


매번 생성자를 호출하는 것은 꽤 못생겨 보일 수 있는데,
꼭 그럴 필요 없이 생성자 호출을 위한 몇 가지 factory method을 가짐으로써 코드를 간결하게 할 수 있다.

### Immutable class 구현으로 얻는 장점

불변의 클래스 구현할 때 더 큰 노력을 기울여야 할 것 같다.
왜 그래야 하는가?

>immutable class 구현으로 얻는점
- 불변 객체가 살아있는 동안 동일하게 유지될 것이라는 것을 보장
- 한 객체가 누구에 의한건지 모른 채로 갑자기 변경된 적 있나요? 예를 들면..

```java
public Object method(MyClass a) {
    // do something
    a.setSomething(something);
    // etc etc
    return anObject;
}
```

이것은 실망스러운 놀라움의 근원 중 하나이다.
매개 변수를 수정하는 것은 객체의 상태에 대해 추론하기가 매우 어렵기 때문에 안티패턴이다. 그리고 이러한 안티 패턴은 계속 일어날 것이다.

### 불변 객체의 장단점
- 매개 변수 객체를 보호하는 유일한 방법은 그것을 불변으로 만드는 것이다.
- 메서드가 매개 변수의 상태를 변경하려고 시도하지 않는다는 사실에 의존해서는 안 된다.

#### 멀티스레드 환경에서의 장점
>
1.  변경할 수 없는 불변의 객체는 스레드로부터 안전하다.
2. 불변 객체가 얼마나 많은 스레드에서 사용되든, 그것은 스레드로부터 안전하다.
3. 불변 객체는 방어 복사본을 사용하여 가변 필드에 대한 접근을 제공한다.
3. 그래서 그 필드 중 하나를 사용하는 각 스레드는 실제로 방어 복사본을 사용합니다.
  - 그 객체를 수정하는 일부 코드 블록을 동기화하기 위해 탈출했다는 두려움에서 자유로울 수 있다.

#### Garbage Collection 최적화
불변 객체가 우리 코드에 가져오는 안전 외에 또 다른 장점은 가비지 수집 최적화이다.
> Immutable 객체와 GC의 연결
- [Java Garbage Collector 불변 객체에 대한 결정을 내리는 것이 더 간단한 이유](http://wiki.c2.com/?ImmutableObjectsAndGarbageCollection)
- [불변 객체는 Garbage Collection으로 인해 처리비용(overhead)을 줄이는 데 어떻게 도움이 되나요?](https://stackoverflow.com/questions/35384393/how-do-immutable-objects-help-decrease-overhead-due-to-garbage-collection)

__변경되지 않은 객체는 GC에 매우 유리하다는 것이 요점__이라고 한다.

### Not Silver bullet

완벽한 것은 없으며, 불변의 물체를 포함하여 장점만이 존재하는 단일 개념은 없다.

불변 객체의 주요 문제는 성능이다.
(무언가를 변경하는 대신 새로운 것을 만들어야 하기 때문)
애플리케이션이 많은 변경을 한다면,
기존 객체를 수정하는 대신 많은 객체를 만드는 것이 최선의 방법이 아니다. 그래서 우리는 가변성에서 벗어날 수 없지만, 최소 수준으로 유지하는 것이 좋다고 할 수 있다.

그러나, 불변 객체를 더 최적으로 만들기 위해 우리가 할 수 있는 몇 가지가 있다.

- 불변의 필드 유지
  - 불변의 필드만 보유하도록 클래스를 설계한다면 생성자나 getter에서 방어 복사본을 만들 필요가 없다.

- 로컬 메소드에서 가변 가능한 객체를 사용
  - 상태를 불변으로 유지하면서 가변 객체를 변경하는 속도에서 이익을 얻을 수 있는 방법
  - 가변 객체를 메소드에 로컬로 유지하는 동안 우리는 불변성의 모든 장점과 가변성의 속도를 가질 수 있다.
    - 불변 객체를 많이 수정해야 할 경우 : 변경과 수정이 가능하며 이를 기반으로 다른 불변의 객체를 만들고 반환할 수 있다.
    - 가변 객체가 생성되고 로컬 메서드에만 존재하며 다른 곳에서는 참조되지 않을 경우 : 다른 곳에서 예기치 않게 변경할 수 없으며 수정하는 것은 스레드로부터 안전하다.


```java
    public String basicConcatenation(String[] args){
        String result = "";
        for (String string : strings) {
            result += string;
        }
    }
```

문자열은 불변의 객체이다.
두 문자열을 "+" 연산자와 연결하면 실제로 다른 문자열이 생성된다.
그래서, 위의 구현을 사용하여, 우리는 모든 목록 항목에 대해 많은 문자열 객체를 만들고 있다.
한 문자열만 수정하는 것이 더 효율적이지 않을까요?
맞다. 그리고 우리는 StringBuilder라는 String의 변경 가능한 변형을 사용하여 이것을 할 수 있습니다.

```java
public String efficientConcatenation(String[] args){
        // create a mutable object
        StringBuilder builder = new StringBuilder();
        for (String string : strings) {
            builder.append(string);
        }
        // 가변 객체를 불변 객체로 변환하여 반환
        return builder.toString();
    }
```

- 우리는 불변의 객체 목록을 매개 변수로 받고 수정할 수 없다.
  - 또한, 우리는 원하는 대로 또 다른 불변의 객체를 반환한다.
- 메서드 안에 가변 가능한 객체를 만들었다는 것이 국가의 불변성에 중요한가요? :
  - 그렇지 않다, 하지만 속도에 도움이 됩니다.

### 결론
>
단점을 낮게 유지하면서 장점을 키우는 것의 여부는 불변 객체를 언제 어떻게 사용할지 선택하는 우리의 지혜에 달려 있다.
성능이 떨어질 수 있지만, 변경은 어떤 형태로든 다른 객체의 생성을 의미하기 때문에, "명확한 불변성"이라는 큰 이점을 가져온다.
불변의 객체를 여러 곳에서 자유롭게 공유하거나 멀티스레드 환경에서 사용할 수 있다.
무슨 일이 있어도, 그들의 상태는 절대 변하지 않을 것이고 코드는 훨씬 더 안전할 것이며, 그것에 대해 추론하기가 더 쉬울 것이다.
