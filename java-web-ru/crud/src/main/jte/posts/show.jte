@import exercise.dto.posts.PostPage
@param PostPage page

@template.layout.page(
content = @`
    <div id="content mt-5 mb-5">
        <div class="table mt-5 mb-5">
            <table class="table table-hover table-xl">
                <thead class="table-light">
                <tr>
                    <th scope="col"></th>
                    <th scope="col">ID</th>
                    <th scope="col">Наименование</th>
                    <th scope="col">Контент</th>
                </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>
                            <a class="btn btn-primary btn-sm" type="cancel" href="/posts">
                                < Назад
                            </a>
                        </td>
                        <th scope="row">
                            ${page.getPost().getId()}
                        </th>
                        <td>${page.getPost().getName()}</td>
                        <td>${page.getPost().getBody()}</td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
`
)