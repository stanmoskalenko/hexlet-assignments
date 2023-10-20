@import exercise.dto.posts.PostsPage
@param PostsPage page

@template.layout.page(
content = @`
    !{var currentPage = 1;}
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
                                    <a href="/posts/${post.getId()}">${post.getId()}</a>
                                </th>
                                <td>${post.getName()}</td>
                                <td>${post.getBody()}</td>
                            </tr>
                        @endfor
                        </tbody>
                    </table>
                </div>
                <nav>
                    <ul class="pagination">
                        @if(page.getCurrentPage() != 1)
                            <li class="page-item"><a class="page-link" href="?page=${page.getCurrentPage() - 1}"> < Назад</a></li>
                        @endif

                        @if(page.getCurrentPage() < page.getPageCount())
                            <li class="page-item"><a class="page-link" href="?page=${page.getCurrentPage() + 1}">Вперед > </a></li>
                        @endif
                    </ul>
                </nav>
            @endif
        </main>
    </div>
`
)
