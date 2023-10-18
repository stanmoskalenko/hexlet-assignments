@import exercise.dto.users.UsersPage
@param UsersPage page

@template.layout.page(
    content = @`
    <header>
        <nav class="navbar navbar-light bg-light">
            <div class="container-fluid">
                <h1 class="navbar-brand">Пользователи</h1>
                <form class="d-flex">
                    <a class="btn btn-primary" type="submit" href="/users/build">
                        Зарегистрировать нового пользователя
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
                        <th scope="col">Name</th>
                        <th scope="col">Email</th>
                    </tr>
                </thead>
                <tbody>
                    @for(var user : page.getUsers())
                        <tr>
                            <th scope="row">
                                ${user.getId()}
                            </th>
                            <td>${user.getFirstName()} ${user.getLastName()}</td>
                            <td>${user.getEmail()}</td>
                        </tr>
                    @endfor
                </tbody>
            </table>
        </div>
    @endif
    `
)
