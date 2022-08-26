# Golden selenide. My best testing practices.

in progress...

Золотой селенид. Мои лучшие практики по тестированию. (в процессе)

!!! Внимание автор не разделяет понятий чистый автоматизатор или мануальщик, он является Инженером, инженером по тестированию который отвечает за то и другое, являясь истинным QA !!!
Но упор пойдет по практикам автоматизации


<i>Тестирование для меня как целая наука, имеющая множество направлений.</i>
<p>Что такое тест? </p>
Тест - это проверка сценария, expected === actual

Какие тесты бывают?

- Юнит тесты (их пишут разработчики) 
- UI тесты (их пишу я, и мы остановимся на этом)

Как я разделяю типы UI тестов
- Пользовательские сценарии (шаги в системе, приводящие к определенному резульату)
- Сочетания настроек (уместно применить - техники тест дизайна)

Тестировщик, программист или аналитик, вопрос, кто живет в доме? - я скажу тестировщик - любому автоматизатору придется там жить, и далее наша речь пойдет о локаторах...

Распределение локаторов
Локатор - адрес/путь веб элемента на странице
- храните в отдельном файле, так и назовите, Локаторы 
- отдельный файл с локаторами для каждой страницы - забудьте про это. Потому что вам запарится разделять локаторы по страницам, веб элемнеты состоят из идентичных форм, CSS классов, и один локатор часто будет повторяться на разных страницах приложения. Думаете что файл взорвется? Нет, благо в современном мире есть мощные IDE, упрощающих поиск. ~500 локаторов достаточно покрывают все потребности по нашему проекту

Но возникает еще одна проблема, разгромождение локаторов в самих тест кейсах. Решение: POM, POM, POM...
Page Object Model - standart test automation pattern. Паттерн это набор практик, проверенных временем, и прошедших множество костылей. Нельзя просто так взять и понять POM. Напишите свою первую, вторую, третью сотку тестов (я внедрила после четырехсотки), и вы поймете что в них что то не так, придя к моменту изучите POM.

<i>P.S. сначала нужно наступить на грабли, перед тем как поДнять их</i>

IDE trips, на своем проекте я использую идею, Idea 

Переход между вкладками
- ALT + (->, <-)
To open any file
- CTRL + SHIFT + N
Find in files
- CTRL + SHIFT + F 
Format current page
- CTRL + ALT + O
- CTRL + ALT + L
## Difference between isBlank, isEmpty
разница в проверке пробела:
```Java
StringUtils.isBlank(" ") = true
StringUtils.isEmpty(" ") = false
```
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

 # ИНСТРУКЦИЯ ПО ПЕРВОМУ КОММИТУ  
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
