@import exercise.dto.articles.NewArticlePage
@import io.javalin.validation.ValidationError
@import gg.jte.Content
@import java.util.Map
@import java.util.stream.Collectors
@param NewArticlePage invalidPage


@template.layout.page(
content = @`
    <div class="content mt-5 mb-5">
        <div class="header">
            <h5 class="title text-center">Создать новую статью</h5>
        </div>
        <div class="container">
            <form class= "needs-validation row g-3" action="/articles" method="post">
                <div class="mb-3">
                    <label for="title" class="col-form-label">Заголовок</label>
                        @if(invalidPage != null)
                            <input required type="text"
                                   placeholder="Укажите заголовок"
                                   class="form-control text-truncate"
                                   value="${invalidPage.getTitle()}"
                                   name="title"
                                   id="title"/>
                        @else
                            <input placeholder="Заголовок" required type="text" class="form-control text-truncate" name="title" id="title"/>
                        @endif
                    <div class="valid-feedback">
                        Заголовок не может быть пустым
                    </div>
                </div>
                <div class="mb-3">
                    <label for="content" class="col-form-label">Контент</label>
                        <textarea required placeholder="Добавьте контент" role="textbox" class="form-control" name="content" id="content">
                            @if(invalidPage != null)
                                ${invalidPage.getContent()}
                            @endif
                        </textarea>
                    <div class="valid-feedback">
                        Контент не может быть пустым
                    </div>
                </div>
                @if(invalidPage != null)
                    <div class="alert alert-danger d-flex align-items-center" role="alert">
                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-exclamation-octagon" viewBox="0 0 16 16">
                            <path d="M4.54.146A.5.5 0 0 1 4.893 0h6.214a.5.5 0 0 1 .353.146l4.394 4.394a.5.5 0 0 1 .146.353v6.214a.5.5 0 0 1-.146.353l-4.394 4.394a.5.5 0 0 1-.353.146H4.893a.5.5 0 0 1-.353-.146L.146 11.46A.5.5 0 0 1 0 11.107V4.893a.5.5 0 0 1 .146-.353L4.54.146zM5.1 1 1 5.1v5.8L5.1 15h5.8l4.1-4.1V5.1L10.9 1H5.1z"/>
                            <path d="M7.002 11a1 1 0 1 1 2 0 1 1 0 0 1-2 0zM7.1 4.995a.905.905 0 1 1 1.8 0l-.35 3.507a.552.552 0 0 1-1.1 0L7.1 4.995z"/>
                        </svg>
                        <div class="ms-5">
                            ${invalidPage.getErrors().entrySet().stream()
                                .map(Map.Entry::getValue)
                                .map(error -> error.stream()
                                        .map(ValidationError::getMessage)
                                        .collect(Collectors.joining("; ")))
                                .collect(Collectors.joining())}
                        </div>
                    </div>
                @endif
                <div class="mb-3 text-end">
                    <a class="btn btn-secondary" type="cancel" href="/articles">
                        Отмена
                    </a>
                    <input class="btn btn-primary" type="submit" value="Зарегистрировать"/>
                </div>
            </form>
        </div>
    </div>
`
)

