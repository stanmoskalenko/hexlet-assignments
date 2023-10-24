@import exercise.dto.MainPage
@import exercise.util.NamedRoutes
@param MainPage page

<!-- BEGIN -->
@template.layout.page(
content = @`
@if(page != null)
    <header class="d-flex py-3 mb-4 text-end border-bottom">
        <div class="container">
            <h1 class="text-center text-success">
                ${page.getName().toString()}
            </h1>
        </div>
    </header>
    <div class="d-flex justify-content-center container">
        <form action="${NamedRoutes.logoutPath()}" method="post">
            <input type="submit" class="btn btn-outline-primary me-2" value="Выйти"/>
        </form>
    </div>
@else
    <div class="col-md-3 mx-auto mt-5 text-center">
        <a href="${NamedRoutes.buildSessionPath()}" type="button" class="btn btn-outline-primary mt-5 me-2">
            Войти
        </a>
    </div>
@endif

`
)
<!-- END -->
