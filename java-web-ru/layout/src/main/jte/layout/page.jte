@import gg.jte.Content
@param Content usersTemplate = null
@param Content userTemplate = null
@param Content content = null


<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
          rel="stylesheet"
          crossorigin="anonymous">
    <title>Layout</title>
</head>
<body>
    <div class="container">
        <header class="d-flex justify-content-center py-3">
            <ul class="nav nav-pills">
                <li class="nav-item"><a href="/" class="nav-link active" aria-current="page">Home</a></li>
                <li class="nav-item"><a href="/users/" class="nav-link">Users</a></li>
            </ul>
        </header>
    </div>
    <div class="container">
        @if(userTemplate != null)
            ${userTemplate}
        @elseif(usersTemplate != null)
            ${usersTemplate}
        @else
            ${content}
        @endif
    </div>
    <div class="container">
        <footer class="py-3 my-4">
            <p class="text-center text-muted">© 2023 Company, Inc</p>
        </footer>
    </div>
</body>
</html>