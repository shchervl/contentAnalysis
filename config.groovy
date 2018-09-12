grid {
//    isUsed = true
    isUsed = false
    seleniumHub = 'localhost:4455'
}

users {
    admin.login = 'admin'
}

environments {
    prod {
        url = 'https://www.walmart.com'
    }
    staging {
        url = 'http://google.ru'
    }

}