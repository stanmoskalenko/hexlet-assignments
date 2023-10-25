@import exercise.dto.posts.PostsPage
@import exercise.util.NamedRoutes
@param PostsPage page

@template.layout.page(

content = @`
    <div class="mx-auto p-4 py-md-5">
        <main>
            <header>
                <nav class="navbar navbar-light bg-light">
                    <div class="container-fluid">
                        <h1 class="navbar-brand">Все посты</h1>
                    </div>
                </nav>
            </header>
            @if (page != null)
                @if (page.getFlash() != null)
                    <div class="alert alert-success" role="alert">
                        ${page.getFlash()}
                    </div>
                @endif
                <div class="mb-3">
                        <a type="btn btn-primary"href="${NamedRoutes.buildPostPath()}">Создать</a>
                </div>
                <div class="table">
                    <table class="table table-hover table-sm">
                        <thead class="table-light">
                        <tr>
                            <th scope="col">ID</th>
                            <th scope="col">Наименование</th>
                            <th scope="col">Контент</th>
                        </tr>
                        </thead>
                        <tbody>
                        @for(var post : page.getPosts())
                            <tr>
                                <th scope="row">
                                    <a href="${NamedRoutes.postPath(post.getId())}">${post.getId()}</a>
                                </th>
                                <td>${post.getName()}</td>
                                <td>${post.getBody()}</td>
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
