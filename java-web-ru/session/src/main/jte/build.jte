@import exercise.dto.LoginPage
@import exercise.util.NamedRoutes
@param LoginPage page

<!-- BEGIN -->
@template.layout.page(
content = @`
    @if (page != null)
        <div class="container d-flex justify-content-center p-4 py-md-5 w-50 text-center">
            <h1 class="text-danger">${page.getError()}</h1>
        </div>
    @endif
    <div class="container d-flex justify-content-center p-4 py-md-5 w-50 text-center">
        <form action="${NamedRoutes.loginPath()}" method="post">
            <div class="text-center mb-3">
                <input type="text" placeholder="Логин" id="name" class="form-control" name="name"/>
            </div>
            <div class="text-center mb-3">
                <input type="password" id="psw" placeholder="Пароль" class="form-control" name="password"/>
            </div>
            <input type="submit" class="btn btn-primary" value="Войти"/>
        </form>
    </div>
`
)

<!-- END -->
