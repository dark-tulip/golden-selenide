# Golden selenide. My best testing practices.

in progress...

Золотой селенид. Мои лучшие практики по тестированию. (в процессе)

<i>Тестирование для меня как целая наука, имеющая множество направлений.</i>
<p>Что такое тест? </p>
Тест - это проверка сценария, expected === actual

Какие тесты бывают?

- Юнит тесты (их пишут разработчики) 
- UI тесты (их пишу я, и мы остановимся на этом)

Как я разделяю типы UI тестов
- Пользовательские сценарии (шаги в системе, приводящие к определенному резульату)
- Сочетания настроек (уместно применить - техники тест дизайна)

Тестировщик, программист или аналитик, вопрос, кто живет в доме? - я скажу тестировщик - любому автоматизатору придется там жить

Распределение локаторов
Локатор - адрес/путь веб элемента на странице
- храните в отдельном файле, так и назовите, Локаторы 
- отдельный файл с локаторами для каждой страницы - забудьте про это (потому что вам запарится разделять локаторы по страницам, в большинстве сайтов веб элемнеты состоят из идентичных форм, CSS классов, таким образом один локатор часто будет повторяться на разных страницах приложения. Благо в современном мире есть мощные IDE)

IDE trips, на своем проекте я использую идею, Idea 

CTRL + ALT + O
CTRL + ALT + L
- To open any file
CTRL + SHIFT + N
- Find in files
CTRL + SHIFT + F 



#### Колекция элементов, не входящих в заданный класс 
``` Java
ElementsCollection notCheckedCheckboxes = $$("div.checkboxes").filterBy(not(cssClass("checked")));
```

#### Когда чекбокс включен, но выглядит как readonly
``` Java  
$("input:checked").should(exist)
```
#### Useful webElement Locator generators 
``` Java
  public static String addCodeToLocator(String SelenideLocatorWithDash, String CodeElement) {
    return String.format("*[selenide='%s%s']", SelenideLocatorWithDash, CodeElement);
  }

  public static String selenideElemsStartsWith(String SelenideLocatorWithDash) {
    return String.format("*[selenide^='%s']", SelenideLocatorWithDash);
  }

  public static String insertIntoTitleStartsWith(String textInTitle) {
    return String.format("*[title^='%s']", textInTitle);
  }

  public static String insertIntoTitle(String textInTitle) {
    return String.format("*[title='%s']", textInTitle);
  }

  public static String insertIntoSelenide(String SelenideLocator) {
    return String.format("*[selenide='%s']", SelenideLocator);
  }
```
