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