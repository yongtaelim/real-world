# 입출금 내역 분석기
## 목표
- SRP ( single responsibility principle )
  - 응집도, 결합도

## KISS 원칙
- keep it short and simple
- 응용프로그램 코드를 한 개의 클래스로 구현한다.

## 코드 유지보수성과 안티 패턴
- 코드 유지보수성을 높이기 위해 노력해야한다.
  - 특정 기능을 담당하는 코드를 쉽게 찾을 수 있어야 한다.
  - 코드가 어떤 일을 수행하는지 쉽게 이해할 수 있어야 한다.
  - 새로운 기능을 쉽게 추가하거나 기존 기능을 쉽게 제거할 수 있어야 한다.
  - ```캡슐화```(encapsulation)가 잘 되어 있어야 한다. 즉 코드 사용자에게는 세부 구현 내용이 감춰져 있으므로 사용자가 쉽게 코드를 이해하고, 기능을 바꿀 수 있어야한다.

- 새로운 요구 사항이 생길 때마다 복사, 붙여넣기로 해결한다면 하기와 같은 문제점이 생길 수 있다. 이는 효과적이지 않은 해결 방법으로 ```안티 패턴```이라고 부른다.
  - 한 개의 거대한 ```갓 클래스``` 때문에 코드를 이해하기 어렵다.
  - ```코드 중복``` 때문에 코드가 불안정하고 변화에 쉽게 망가진다.
  
### 갓 클래스
한 개의 파일에 모든 코드를 구현하다 보면 결국 하나의 거대한 클래스가 탄생하면서 클래스의 목적이 무엇인지 이해하기 어려워진다. 이런 문제를 갓 클래스 안티 패턴이라 부른다.

### 코드 중복
여러 곳에 코드가 중복되어 있는 현상

```중복 코드 배제(DRY) : 반복을 제거하면 로직을 바꿔도 여러 곳의 코드를 바꿔야 할 필요성이 없어진다.```

## 단일 책임 원칙 (SRP)
- 한 클래스는 한 기능만 책임진다.
- 클래스가 바뀌어야 하는 이유는 오직 하나여야 한다.

### 클래스 수준 응집도
- 기능
  - 기능 별로 구현.
  - 너무 과도하게 만들면 생각해야 할 클래스가 많아지므로 코드가 장황해지고 복잡해진다.
- 정보
  - 같은 데이터나 도메인 객체를 처리하는 메서드를 그룹화하는 방법.
  - 예를 들어 BankTransaction 객체를 만들고 CRUD 연산이 필요하면 이런 기능만 제공하는 클래스를 만들어야한다.
  - 여러 기능을 그룹화하면서, 필요한 일부 기능을 ㅗ함하는 클래스 전체를 디펜던시로 추가한다는 약점이 있다.
- 유틸리티
  - 떄로는 관련성이 없는 메서드를 한 클래스로 포함시켜야한다. 메서드가 어디에 속해야할지 결정하기 어려울 때는 유틸리트 클래스에 추가하기도 한다.
  - 유틸리티 클래스 사용은 낮은 응집도로 이어지므로 자제해야한다.
- 논리
  - CSV, JSON, XML의 자료를 파싱하는 코드로 예를 들어본다. 메서드는 ```파싱```이라는 논리로 그룹화되었다.하지만 이 메서드들은 본직적으로 다르며 서로 관련이 없다. 또한 이렇게 그룹화하면, 클래스는 네가지 책임을 갖게 되므로 SRP를 위배된다.
- 순차
  - 파일을 읽고, 파싱하고, 처리하고, 정보를 저장하는 메서드들은 한 클래스로 그룹화한다. 파일을 읽은 결과는 파싱의 입력이 되고, 파싱의 결과는 처리 과정의 입력이 되는 등의 과정이 반복된다.
  - 입출력이 순차적으로 흐르는 것을 순차 응집이라 부른다.
  - 실전에서는 순차 응집을 적용하면 한 클래스를 바꿔야 할 여러 이유가 존재하므로 SRP를 위배된다. 
  - 따라서 각 책임을 개별적으로 응집된 클래스로 분리하는 것이 더 좋다.
- 시간
  - 여러 연산 중 시간과 관련된 연산을 그룹화한다.
  - 어떤 처리 작업을 시작하기 전과 뒤에 초기화, 뒷정리 작업(e.q. 데이터베이스 연결과 종료)을 담당하는 메서드를 포함하는 클래스가 그 예다.
  - 초기화 작업은 다른 작업과 관련이 없지만, 다른 작업보다 먼저 실행되어야 한다.
  
### 메서드 수준 응집도
- 메서드가 다양한 기능을 수행할수록 메서드가 어떤 동작을 하는지 이해하기가 점점 어려워진다.
- 메서드가 연관이 없는 여러일을 처리한다면 응집도가 낮아진다. 응집도가 낮은 메서드는 여러 책임을 포함하기 때문에 각 책임을 테스트하기가 어렵고, 메서드의 책임도 테스트하기가 어렵다. 
- 일반적으로 클래스나 메서드 파라미터의 여러 필드를 바꾸는 if/else 블록이 여러개 포함되어 있다면, 이는 응집도 문제가 있음을 의미하므로 응집도가 높은 더 작은 조각으로 메서드를 분리해야한다.

## 결합도
- 응집도는 클래스, 패키지, 메서드 등의 동작이 얼마나 관련되어 있는가를 가리키는 반면, 결합도는 한 기능이 다른 클래스에 얼마나 의존하고 있는지를 가늠한다.
- 어떤 클래스를 구현하는 데 얼마나 많은 지식(다른 클래스)을 참조했는가로 설명할 수 있다. 
- 많은 클래스를 참조했다면 기능을 변경할 때 그만큼 유연성이 떨어지고 클래스의 코드를 바꾸면 이 클래스에 의존하는 모든 클래스가 영향을 받는다.
- 보통 코드를 구현할 때는 결합도를 낮춰야 한다. 이는 코드의 다양한 컴포넌트가 내부와 세부 구현에 의존하지 않아야함을 의미한다.

## 코드 커버리지
- 테스트 집합이 소프트웨어의 소스코드를 얼마나 테스트했는가를 가리키는 척도다.
- 자바에서는 ```자코코(JaCoCo), 에마(Emma), 코베르투라(Cobertura)``` 같은 코드 커버리지 도구를 많이 사용한다.
- 사람들은 얼마나 많은 구문의 코드를 커버했는지를 의미하는 구문 커버리지에 대해 자주 애기한다. 이 기법에는 분기문(if, while, for)을 한 구문으로 취급해버리는 치명적인 약점이 있다. 따라서 구문 커버리지보다 각 분기문을 확인하는 ```분기 커버리지```를 사용하는 것이 좋다.

## 총정리
1. 갓 클래스와 코드 중복은 코드를 추론하고 유지보수하기 어렵게 만ㄷ는 요인이다.
2. 단일 책임 원칙은 관리하고 유지보수하기 쉬운 코드를 구현하는데 도움을 준다.
3. 응집도는 클래스나 메서드의 책임이 얼마나 강하게 연관되어 있는지를 가리킨다.
4. 결합도는 클래스가 다른 코드 부분에 얼마나 의존하고 있는지를 가리킨다.
5. 높은 응집도와 낮은 결합도는 유지보수가 가능한 코드가 가져야 할 특징이다.
6. 자동화된 테스트 스위트는 소프트웨어가 올바로 동작하며, 코드를 수정해도 잘 동작할 것임을 확신할 수 있고, 프로그램을 쉽게 이해할 수 있도록 도움을 준다.
7. 자바 테스트 프레임워크로 제이유닛을 활용해 메서드와 클래스의 동작을 테스트하는 유닛테스트를 만든다.
8. 테스트를 쉽게 이해할 수 있도록 Given-When-Then 패턴으로 유닛 테스트를 세부분으로 분리하는 것이 좋다. 


# 입출금 내역 부넉기 확장판
## 목표
- 코드베이스에 유연성을 추가하고 유지보수성을 개선하는 데 도움을 주는 개방/패쇄 원칙(open/closed principle (OCP)) 을 배운다. 
- 언제 인터페이스를 사용해야 좋을지를 설명하는 일반적인 가이드라인과 높은 결합도를 피할 수 있는 기법도 배운다. 
- 자바에서 언제 API에 예외를 포함하거나 포함하지 않을지를 결정하는 자바의 예외 처리 방법을 배운다.
- 메이븐, 그레이들 같은 검증된 빌드 도구를 이용해 자바 프로젝트를 시스템적으로 빌드하는 방법도 배운다.

## 개방/패쇄 원칙
- 특정 금액 이상의 모든 입출금 내역을 검색하는 메서드를 구현해보자.
- 간단한 findTransactions() 메서드를 포함하는 BankTransactionFinder 클래스를 따로 만들 수 있다. 하지만 2장에서 이미 BankTransactionProcessor 클래스를 선언했다. 그럼 어떻게 해야할까? 지금과 같은 상황에서는 메서드를 추가하려고 클래스를 새로 만들어도 크게 좋은 점이 없다. 새로 클래스를 추가한 탓에 여러 이름이 생기면서 다양한 동작 간의 관계를 이해하기가 어려워지고 전체 프로젝트가 복잡해지기 때문이다.
- 이런 메서드는 일종의 처리 기능을 담당하므로 BankTransactionProcessor 클래스 안에 정의하면 나중에 관련 메서드를 조금 더 쉽게 찾을 수 있다.
- BankTranscationFilter 인터페이스는 BankTransaction의 선택 조건을 결정한다. BankTransactionFilter 인터페이스를 이용하도록 findTransactions() 메서드를 리팩터링한다. 
- 변경 없이도(Closed) 확장성은 개방(Open)된다.

### 함수형 인터페이스
- 한 개의 추상 메서드를 포함하는 인터페이스를 함수형 인터페이스라고 한다.
- 자바 8에서는 이와 같은 문제를 더욱 쉽게 해결할 수 있도록 ```java.util.function.Predicate<T>```라는 제네릭 인터페이스를 제공한다. 

## 인터페이스 문제
### 갓 인터페이스
- 자바의 인터페이스는 모든 구현이 지켜야 할 규칙을 정의한다. 즉 구현 클래스는 인터페이스에서 정의한 모든 연산의 구현 코드를 제공해야 한다. 따라서 인터페이스를 바꾸면 이를 구현한 코드도 바뀐 내용을 지원하도록 갱신되어야한다. 더 많은 연산을 추가할수록 더 자주 코드가 바뀌며, 문제가 발생할 수 있는 범위도 넓어진다.
- 월, 카테고리 같은 BankTransaction의 속성이 calculateAverageForCategory().calculateTotalInJanuary()처럼 메서드 이름의 일부로 사용되었다. 인터페이스가 도메인 객체의 특정 접근자에 종속 되는 문제가 생겼다. 도메인 객체의 세부 내용이 바뀌면 인터페이스도 바뀌어야 하며 결과적으로 구현코드도 바뀌어야 한다.
### 지나친 세밀함
- 인터페이스는 작을수록 좋은 걸까???
- 지나치게 인터페이스가 세밀해도 코드 유지보수에 방해가 된다. 기능이 여러 인터페이스로 분산되므로 필요한 기능을 찾기 어려운 현상을 안티 응집도라 한다. 자주 사용하는 기능을 쉽게 찾을 수 있어야 유지보수성도 좋아진다. 
- 인터페이스가 너무 세밀하면 복잡도가 높아지며, 새로운 인터페이스가 계속해서 프로젝트에 추가된다.

## 명시적 API vs 암묵적 API
개방/패쇄 원칙을 적용하면 연산에 유연성을 추가하고 가장 공통적인 상황을 클래스로 정의할 수 있다.
- 명시적 API
  - findTransactionsGreaterThanEqual() 같은 메서드는 자체적으로 어떤 동작을 수행하는지 잘 설명되어 있고, 사용하기 쉽다. API의 가독성을 높이고 쉽게 이해하도록 메서드 이름을 서술적으로 만들었다. 하지만 이 메서드의 용도가 특정상황에 국한되어 각 상황에 맞는 새로운 메서드를 많이 만들어야 하는 상황이 벌어진다.
- 암묵적 API
  - findTransactions() 같은 메서드는 처음 사용하기가 어렵고, 문서화를 잘해놓아야한다. 하지만 거래 내역을 검색하는 데 필요한 모든 상황을 단순한 API로 처리할 수 있다.

### 도메인 클래스 vs 원싯값
- 원싯값 
  - BankTransactionSummarizer의 인터페이스를 간단하게 정의하면서 double이라는 원싯값을 결과로 반환하는데, 이는 일반적으로 좋은 방법이 아니다. 원싯값은 다양한 결과를 반환할 수 없어 유연성이 떨어지기 떄문이다.

- 도메인 클래스
  - double 값싸는 새 도메인 클래스 Summary를 만들면 원싯값 문제를 해결할 수 있다. 새 클래스에서 필요한 필드와 결과를 언제든 추가할 수 있다. 또한 이 기법을 이용하면 도메인의 다양한 개념간의 결합을 줄이고, 요구 사항이 바뀔 때 연쇄적으로 코드가 바뀌는 일도 최소화할 수 있다.

## 도메인 객체 소개
### 숫자
- 결과값으로 double을 반환하면 가장 간단하게 프로그램을 구현할 수 있지만 요구 사항이 바뀔 떄 유연하게 대처할 수 없다.

### 컬렉션
- Iterable을 반환하면 상황에 맞춰서 처리하기 때문에 유연성을 높일 수 있다. 이때 유연성은 좋아지지만 오직 컬렉션만 반환해야 하는 제약이 따른다.

### 특별한 도메인 객체
- 사용자가 내보내려는 요약 정보를 대표하는 새로운 개념의 객체를 만들 수 있다. 도메인 객체는 자신의 도메인과 관련된 클래스의 인스턴스다. 도메인 객체를 이용하면 결합을 꺨 수 있다. 새로운 요구 사항이 생겨서 추가 정보를 내보내야 한다면 기존 코드를 바꿀 필요 없이 새로운 클래스의 일부로 이를 구현할 수 있다.

### 더 복잡한 도메인 객체
- Report 처럼 조금 더 일반적이며 거래 내역 컬렉션 등 다양한 결과를 저장하는 필드를 포함하는 개념을 만들 수 있다. 사용자의 요구 사항이 무엇이며 더 복잡한 정보를 내보내야 하는지 여부에 따라 사용할 도메인 객체가 달라진다. 어떤 상황이든 Report 객체를 생산하는 부분과 이를 소비하는 부분이 서로 결합하지 않는다는 큰 장점이 있다.

## 적절하게 인터페이스를 정의하고 구현하기
### 인터페이스의 나쁜 예
```java
public interface Exporter{
  void export(SummaryVo summaryVo);
}
```
이렇게 정의하면 다음과 같은 문제가 발생한다.
- void 반환 형식은 아무 도움이 되지 않고, 기능을 파악하기도 어렵다. 메서드가 무엇을 반환하는지 알 수 없기 때문이다. export() 메서드 자체가 아무것도 반환하지 않으므로 다른 구현 메서드에서 어떤 작업을 진행하고, 이를 기록하거나 화면에 출력할 가능성이 크다. 인터페이스로부터 얻을 수 있는 정보가 아무것도 없다.
- void를 반환하면 어서션으로 결과를 테스트하기도 매우 어렵다. 예상한 결과 실제 결괏값을 어떻게 비교할까? 안타깝게도 void를 반환하면 아무 결과도 없다.

### 인터페이스의 좋은 예
```java
public interface Exporter {
  String export(SummaryVo summaryVo);
}
```

## 예외 처리
### 예외를 사용해야 하는 이유
- 고전적인 C 프로그래밍에서는 수많은 if 조건을 추가해 암호 같은 오류 코드를 반환했다. 하지만 그 방법에는 여러 단점이 존재한다. 
  - 전역으로 공유된 가변 상태에 의존해 최근에 발생한 오류를 검색해야한다.
  - 어떤 값이 실제 값인지 아니면 오류를 가리키는 값인지 구분하기가 어렵다.
  - 제어 흐름이 비즈니스 로직과 섞이면서 코드를 유지보수하거나 프로그램의 일부를 따로 테스트하기도 어려워진다.

## 총정리
- 개방/패쇄 원칙을 이용하면 코드를 바꾸지 않고도 메서드나 클래스의 동작을 바꿀 수 있다.
- 개방/패쇄 원칙을 이용하면 기존 코드를 바꾸지 않으므로 코드가 망가질 가능성이 줄어들며, 기존 코드의 재사용성을 높이고, 결합도가 높아지므로 코드 유지보수성이 개선된다.
- 많은 메서드를 포함하는 갓 인터페이스는 복잡도와 결합도를 높인다.
- 너무 세밀한 메서드를 포함하는 인터페이스는 응집도를 낮춘다.
- API의 가족성을 높이고 쉽게 이해할 수 있도록 메서드 이름을 서술적으로 만들어야한다.
- 연산 결과로 void를 반환하면 동작을 테스트하기 어려워진다.
- 자바의 예외는 문서화, 형식 안정성, 관심사 분리를 촉진한다.
- 확인된 예외는 불필요한 코드를 추가해야 하므로 되도록 사용하지 않는다.
- 너무 자세하게 예외를 적용하면 소프트웨어 개발의 생선성이 떨어진다.
- 노티피케이션 패턴을 이용하면 도메인 클래스로 오류를 수집할 수 있다.
- 예외를 무시하거나 일반적인 Exception을 잡으면 근본적인 문제를 파악하기가 어렵다.
- 빌드 도구를 사용하면 응용프로그램 빌드, 테스트, 배포 등 소프트웨어 개발 생명 주기 작업을 자동화할 수 있다.
- 요즘 자바 커뮤니티에서는 빌드 도구로 메이븐과 그레이들을 주로 사용한다.  
