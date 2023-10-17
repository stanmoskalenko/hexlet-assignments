@import gg.jte.Content
@import exercise.dto.users.UsersPage
@param UsersPage page



@template.layout.page(
usersTemplate = @`
    <div class="table">
    <table class="table">
        <thead>
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
                    <a href="${user.getId()}">${user.getId()}</a>
                </th>
                <td>${user.getFirstName()} ${user.getLastName()}</td>
                <td>${user.getEmail()}</td>
            </tr>
        @endfor
        </tbody>
    </table>
    </div>`
)
