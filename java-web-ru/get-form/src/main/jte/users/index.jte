@import exercise.dto.users.UsersPage
@param UsersPage page

<!-- BEGIN -->
@template.layout.page(
content = @`
    <div class="container">
        <form action="/users" method="get">
            <div class="input-group">
                @if(page.getTerm() != null)
                    <input class="form-control" type="search" required name="term" value="${page.getTerm()}">
                @else
                    <input class="form-control" type="search" required name="term">
                @endif
                <input class="btn btn-primary" type="submit" value="Искать">
            </div>
        </form>
    </div>
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
<!-- END -->
