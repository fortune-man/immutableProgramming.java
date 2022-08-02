
## Reference

다음 내용들을 참고하였습니다. 🙇🏻‍♂️
- [java-using-immutable-classes-for-concurrent-programming)](https://dzone.com/articles/java-using-immutable-classes-for-concurrent-programming)
- [compute method](https://jakubmarian.com/difference-between-compute-and-calculate/)
- [java-synchronizedmap-vs-concurrenthashmap](https://www.baeldung.com/java-synchronizedmap-vs-concurrenthashmap)
[Why ConcurrentHashMap is faster than HashTable in Java??](https://stackoverflow.com/questions/12646404/concurrenthashmap-and-hashtable-in-java)
- [Synchronized?](https://www.mygreatlearning.com/blog/synchronization-in-java/)

---

### Immutable Class 사용법

java.util.concurrent.ConcurrentHashMap을 사용하여 
여러 스레드 간에 불변의 로그인 데이터를 공유하는 방법을 알아보려 한다. 
로그인 데이터를 변경하려면,다음 계산 메서드([compute method](https://jakubmarian.com/difference-between-compute-and-calculate/))를 사용한다.

```java
public class useImmutableClass {

    private final ConcurrentHashMap<String, ImmutableLogin> mapImmutableLogin =
            new ConcurrentHashMap<String, ImmutableLogin>();

    public void changeImmutableLogin(){
        mapImmutableLogin.compute("loginA", (String key, ImmutableLogin login) -> {
            return new ImmutableLogin(login.getUserName(), "newPassword");
        });
    }
}
```

예상대로, 변경을 위해선 ImmutableLogin을 복사해야 한다. 
복잡성 계산 방법은 내부적으로 동기화된 블록을 사용하여 주어진 키의 값이 여러 스레드에 의해 병렬로 변경되지 않도록 해야 한다.

---

### Get을 사용하여 변경된 로그인 데이터를 읽기

```java
    public void readImmutableLogin(){
        ImmutableLogin immutableLogin = mapImmutableLogin.get("loginA");
    }
```

데이터를 읽는 것은 동기화 블록 없이 ImmutableLogin 클래스에서 직접 작동할 수 있다.

이제 우리는 가변 로그인 클래스를 사용하면 어떻게 같은 것을 달성할 수 있는지 알 수 있다. (다시 말하지만, 비밀번호 변경은 ConcurrentHashMap에서)
  >- [java-synchronizedmap-vs-concurrenthashmap](https://www.baeldung.com/java-synchronizedmap-vs-concurrenthashmap) : synchronizedMap()은 각 스레드가 읽기/쓰기 작업에 대해 전체 객체의 잠금을 획득하도록 요구, 이에 비해 ConcurrentHashMap을 사용하면 스레드가 컬렉션의 별도의 세그먼트에서 잠금을 획득하고 동시에 수정할 수 있다.
  - [Why ConcurrentHashMap is faster than HashTable in Java??](https://stackoverflow.com/questions/12646404/concurrenthashmap-and-hashtable-in-java)  : 
ConcurrentHashMap이 여러 버킷을 사용하여 데이터를 저장하므로 읽기 잠금을 피하고 해시테이블보다 성능을 크게 향상시킨다.
  

```java
    private  final ConcurrentHashMap<String,MutableLogin> mapMutableLogin
            = new ConcurrentHashMap<String,MutableLogin>();

    public void changeMutableLogin(){
        MutableLogin mutableLogin = mapMutableLogin.get("loginA");
        synchronized (mutableLogin){
            mutableLogin.setPassword("newPassword");
        }
    }
```
- [Synchronized?](https://www.mygreatlearning.com/blog/synchronization-in-java/)

읽는 동안 MutableLogin 객체가 변경되지 않도록 하려면 동일한 모니터(MutableLogin 객체)를 사용하여 읽기와 쓰기를 동기화해야 한다. 
중첩된 동기화 블록을 피하기 위해, 우리는 계산 방법 대신 MutableLogin 변경 목적 -> get 메서드를 사용했다.

---

### 정체성(identify)과 상태(state) 분리

ConcurrentHashMap의 키는 다른 로그인의 ID와 로그인의 현재 상태의 값을 정의했음 ->
MutableLogin 클래스의 경우, 각 키에는 정확히 하나의 MutableLogin 객체가 있다. 
ImmutableLogin의 경우, 각 키는 다른 시점에서 다른 ImmutableLogin 객체를 가지고 있다. 

가변 클래스는 정체성과 상태를 모두 나타내는 반면, 불변 클래스는 상태만을 나타냄을 의미한다. -> 정체성을 나타내기 위흔 별도의 클래스가 필요하다. 
불변의 클래스를 사용하면 정체성과 상태가 분리됨을 의미하기도 한다.

#### Login ID와 ImmutableLogin Class의 상태를 변경하는 방법
```java
public class Login {
    private volatile ImmutableLogin state;

    public Login(ImmutableLogin state) {
        super();
        this.state = state;
    }

    public synchronized void change(Function<ImmutableLogin, ImmutableLogin> update){
        state = update.apply(state);
    }

    public ImmutableLogin getState() {
        return state;
    }
}
```
변경된 함수는 동기화된 블록을 사용하여 주어진 시간에 하나의 스레드만 로그인 객체를 업데이트하고 있는지 확인한다. 
필드 상태는 항상 최신에 작성된 값을 읽을 수 있도록 휘발성으로 선언되어야 한다.

---

### Immutable Class를 사용해야 할 때

불변의 클래스를 사용할 때

불변 상태 변경은 불변 객체를 복사하는 것으로 구성된다. 
이것은 우리가 동기화 블록 없이 내부 상태를 읽을 수 있게 해준다. 
따라서 __모든 변경에 대해 객체를 복사할 수 있고 데이터에 대한 읽기 전용 접근이 필요할 때 불변 클래스를 사용해야 한다.__
