# Golden selenide. My best testing practices. Part 2

#### ЕСЛИ У ВАС ЕСТЬ ПРЕДЛОЖЕНИЯ, PLEASE CONTACT ME ON <a href="https://t.me/dark_tulip">TELEGRAM</a>

Это черновик возможного продолжения



### ИНСТРУКЦИЯ ПО ПЕРВОМУ КОММИТУ  
1) Сначала клонируем репозиторий (репо - это удаленный проект над которым мы все работаем)
```
git clone https://github.com/dark-tulip/aknbt
```
Дождитесь пока IDEA установит за нас все необходимые плагины и зависимости - 
справа нажмите на Gradle (cлоник) - это сборщик проектов, вам ничего не нужно будет устанавливать, IDEA все сделает за вас
(Reload All Projects)

2) Внесите изменения в РИДМИ файл, затем отправьте изменения, для этого
```
git pull (применить все изменения, когда кто-то что-то запушил)

git add . (точка чтобы подготовить (добавить) все файлы для коммита)
git commit -m "Что вы добавили"
git push (отправить изменнения)
```

можно запушить без никаких комманд
```
1) В правом верхнем углу зеленая галочка Commit
2) В окне выбираем файлы для публикации
3) Снизу пишем сообщение - какое изменение добавили в проект (Commit Message)
4) Commit and Push
5) Ничего не трогаем, еще раз нажимаем пуш
```

- Merge - объединение проектов (например слить с dev на master ветку)
- Cherry pick - вынуть изменения (некоторые коммиты из другой ветки)
- Revert - сбросить все что вы написали до последнего pull-a

## Запуск набора тестов через пакет
Про test-suit-ы на testNG. test-suit - тестовый костюм или сборка для запуска тестовой группы. На конце названия пакета должно быть `(.*)` иначе не увидит всех классов внутри пакета.
``` xml
<!-- test-suit.xml -->
<!DOCTYPE suite SYSTEM "https://testng.org/testng-1.0.dtd" >
<suite name="exampleSuit" parallel="false">
 <test name="example-test" preserve-order="true" verbose="2">
   <packages>
     <package name="tests.my_tests_pkg.*"/>
   </packages>
 </test>
</suite>
```
<b>verbosity</b>. The verbosity level is 0 to 10, where 10 is most detailed. -1 will put TestNG in debug mode.<br>
<b>reserve-order</b>. True (по умолчанию). TestNG will run your tests in the order they are found in the XML file. False для хаотичного порядка.


## Повторитель упавших тестов, атррибут внутри аннотации
``` Java
// RetryAnalyzer.java
public class RetryAnalyzer implements IRetryAnalyzer {
  int counter = 0;
  int retryLimit = 2;
  /*
   * (non-Javadoc)
   * @see org.testng.IRetryAnalyzer#retry(org.testng.ITestResult)
   *
   * This method decides how many times a test needs to be rerun.
   * TestNg will call this method every time a test fails. So we
   * can put some code in here to decide when to rerun the test.
   *
   * Note: This method will return true if a tests needs to be retried
   * and false it not.
   *
   */
  @Override
  public boolean retry(ITestResult result) {
    if(counter < retryLimit)
    {
      counter++;
      return true;
    }
    return false;
  }
}
```
### How to use?
``` Java
@Test(retryAnalyzer = RetryAnalyzer.class)
public void checkPassword() {
  // here goes your steps
}
```

## Повторитель упавших тестов для целого набора
Внутри xml test suit file добавляем листенер
``` xml
<!-- test-suit.xml -->
<!DOCTYPE suite SYSTEM "https://testng.org/testng-1.0.dtd" >

<suite name="exampleSuit" parallel="false">
 
  <!-- повторитель упавших тестов -->
  <listeners>
    <listener class-name="test_utils.AnnotationTransformer"/>
  </listeners>

 <test name="example-test" preserve-order="true" verbose="2">
   <packages>
     <package name="tests.my_tests_pkg.*"/>
   </packages>
 </test>
 
</suite>
```
``` Java
// AnnotationTransformer.java
/**
 * Этот класс нужен для применения retry analyzer на xml-test-suit файл с помощью listener
 * we just need to add it as a listener in the testng run xml.
 * <p>
 * <a href="https://www.toolsqa.com/testng/retry-failed-tests-testng/"></a>
 */
public class AnnotationTransformer implements IAnnotationTransformer {
  @Override
  public void transform(ITestAnnotation annotation,
                        Class testClass,
                        Constructor testConstructor,
                        Method testMethod) {
    annotation.setRetryAnalyzer(RetryAnalyzer.class);
  }
}
```

## Кронтабы. Plan your tests
Кронтабы это планировщики задач, которые запускают таску в определенное время. Хорошо бы им научиться чтобы запускать тесты каждый час днем, каждый месяц или каждый час

Вот некоторые шаблоны
``` bash
* * * * * выполняемая команда
- - - - -
| | | | |
| | | | ----- день недели (0—7) (воскресенье = 0 или 7)
| | | ------- месяц (1—12)
| | --------- день (1—31)
| ----------- час (0—23)
------------- минута (0—59)

Каждый день в 18:30
30 18 * * *

Каждый час в hh:00
0 * * * *
```

# TODO
- СМАТЧИТЬ СО СТАТЬЕЙ НА ХАБРЕ
- Написать пример как я пишу тест на POM
- добавить больше short-selenide-tips (плюшек селенида)
- Написать обычный тест (без POM)
- Мантра автотестера 
- Настройка гитлаб раннера (кастомный) на локальном сервере
- Посвятить отдельную тему загрузке файлов (HTTP GET, PROXY, With extension or without)

### Gradle-TestNG Не запускает определенный test-suit.xml task

Build project with TestNg 7.1.0 failed
Внимательно проверьте путь к запускаемому пакету внутри gradle task-и. Все утилиты и настройки для тестов, данные для подготовки и мокапы нужно хранить в отдельном пакете `test_utils` или `utils`. Иначе при запуске отдельного test-suit градл может подумать что классы без аннотации Test инвалидные и не запуститься


### Как убрать SLF4J 
```
SLF4J: No SLF4J providers were found.
SLF4J: Defaulting to no-operation (NOP) logger implementation
SLF4J: See http://www.slf4j.org/codes.html#noProviders for further details.
```
у вас не настроен или не найден логгер
Внутри Build Gradle добавляем dependencies для логгера:
``` 
dependencies {
  testImplementation group: 'org.slf4j', name: 'slf4j-simple', version: '2.0.0'
}
```
