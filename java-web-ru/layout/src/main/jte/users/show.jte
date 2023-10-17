@import exercise.dto.users.UserPage
@import gg.jte.Content
@param UserPage page
<!-- BEGIN -->

@template.layout.page(
    userTemplate = @`
    <div class="table-container">
        <div class="table">
            <table class="table">
                <thead>
                <tr>
                    <th scope="col">ID</th>
                    <th scope="col">First Name</th>
                    <th scope="col">Last Name</th>
                    <th scope="col">Email</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <th scope="row">
                        ${page.getUser().getId()}
                    </th>
                    <td>${page.getUser().getFirstName()}</td>
                    <td>${page.getUser().getLastName()}</td>
                    <td>${page.getUser().getEmail()}</td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>`
)
<!-- END -->
