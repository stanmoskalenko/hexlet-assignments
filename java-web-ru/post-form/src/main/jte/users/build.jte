@import gg.jte.Content
@param Content content


@template.layout.page(
content = @`
    <div class="content">
        <div class="header">
            <h5 class="title text-center">Зарегистрировать нового пользователя</h5>
        </div>
        <div class="container">
            <form action="/users" method="post">
                <div class="mb-3">
                    <label for="firstName"  class="col-form-label">Имя</label>
                    <input required type="text" class="form-control" name="firstName" id="firstName"/>
                </div>
                <div class="mb-3">
                    <label for="lastName" class="col-form-label">Фамилия</label>
                    <input required  type="text" class="form-control" name="lastName" id="lastName"/>
                </div>
                <div class="mb-3">
                    <label for="email" class="col-form-label">Email</label>
                    <input required type="email" class="form-control" name="email" id="email"/>
                </div>
                <div class="mb-3">
                    <label for="password" class="col-form-label">Пароль</label>
                    <input required type="password" class="form-control" name="password" id="password"/>
                </div>
                <div class="mb-3">
                    <a class="btn btn-secondary" type="cancel" href="/users">
                        Отмена
                    </a>
                    <input class="btn btn-primary" type="submit" value="Зарегистрировать"/>
                </div>
            </form>
        </div>
    </div>
`
)