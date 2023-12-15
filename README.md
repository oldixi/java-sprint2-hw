# java-sprint2-hw
Приложение для бухгалтерии.

## _Описание проекта_
У приложения консольный интерфейс для управления программой.
Имеет следующую функциональность:
* считывание месячных и годового отчётов бухгалтерии из csv-файлов и их перевод в объекты приложения
* сверка данных по месячным и годовому отчётам
* вывод информации о месячных и годовом отчётах

## _Консольный интерфейс_  
Консольный интерфейс приложения предлагает пользователю выбор из пяти действий:
1. Считать все месячные отчёты - считывается информация из всех месячных отчетов. Название отчетов имеет следующий формат: m.YYYYmm.csv (пример: m.202301.csv)
2. Считать годовые отчёты - считывается информация из годовых отчетов. Назание отчетов имеет следующий формат: y.YYYY.csv (пример: y.2021.csv) 
3. Сверить отчёты — по сохранённым данным проверяется, сходятся ли отчёты за месяцы и за год.
4. Вывести информацию обо всех месячных отчётах — по сохранённым данным в консоль выводится имеющаяся информация.
5. Вывести информацию о годовых отчётах — по сохранённым данным в консоль выводится имеющаяся информация.
Для выхода из программы пользователю необходимо ввести числовую команду "0" - выход. 

## _Сверка данных_ 
Сверка данных — это проверка, что информация в годовом отчёте не противоречит информации в месячных отчётах. При обнаружении несоответствия программа выводит месяц и год, где оно обнаружено.
Если несоответствий не обнаружено, приложение информирует пользователя об успешном завершении операции.
