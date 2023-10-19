@import exercise.dto.articles.ArticlesPage
@param ArticlesPage page

@template.layout.page(
    content = @`
        <div class="mx-auto p-4 py-md-5">
            <main>
                <header>
                    <nav class="navbar navbar-light bg-light">
                        <div class="container-fluid">
                            <h1 class="navbar-brand">Все статьи</h1>
                            <form class="d-flex">
                                <a class="btn btn-primary" type="submit" href="/articles/new">
                                    Создать новую статью
                                </a>
                            </form>
                        </div>
                    </nav>
                </header>
                @if (page != null)
                    <div class="table">
                        <table class="table table-hover table-sm">
                            <thead class="table-light">
                            <tr>
                                <th scope="col">ID</th>
                                <th scope="col">Заголовок</th>
                                <th scope="col">Контент</th>
                            </tr>
                            </thead>
                            <tbody>
                            @for(var user : page.getArticles())
                                <tr>
                                    <th scope="row">
                                        ${user.getId()}
                                    </th>
                                    <td>${user.getTitle()}</td>
                                    <td>${user.getContent()}</td>
                                </tr>
                            @endfor
                            </tbody>
                        </table>
                    </div>
                @endif
            </main>
        </div>
    `
)
