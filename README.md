# Test-task-Digital-Chief

Это приложение, предоставляющее возможность поиска данных по запросу посредством поискового движка Elasticsearch, написано в качестве тестового задания от Digital Chief.

Для запуска приложения необходимо выполнить следующее:

- клонировать данный репозиторий;
- использовать терминал Docker;
- перейти в каталог проекта;
- собрать проект;
- перейти в браузере по адресу localhost:8080/search-products

### 1. Клонировать данный репозиторий

Посредством ввода команды в терминал или через интерфейс:

    git clone https://github.com/SneakyElfff/Test-task-Digital-Chief.git

### 2. Открыть терминал Docker

Открыть соответствующую вкладку в Docker Desktop или терминал Linux.

### 3. Перейти в каталог проекта

Скопировать путь до склонированного репозитория и перейти по нему:

    cd /path/to/repository/Test-task-Digital-Chief

### 4. Cобрать проект

Выполнить в терминале команду:

    docker-compose up --build

### 5. Тестировать

Открыть браузер и в поисковой строке ввести URL:

    localhost:8080/search-products