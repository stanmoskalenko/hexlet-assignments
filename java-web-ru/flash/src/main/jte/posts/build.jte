@import exercise.dto.posts.BuildPostPage
@param BuildPostPage page

@template.layout.page(

content = @`
    @if(page != null)
        <div class="mb-3">
            @if (page.getError() != null)
                <div class="alert alert-danger" role="alert">
                    ${page.getError()}
                </div>
            @endif
        </div>
        <div class="mx-auto p-4 py-md-5">
            <form action="/posts" method="post">
                <div class="mb-3">
                    <label class="form-label">
                        Название
                        <input required minlength="2" type="text" class="form-control" name="name"
                               value="${page.getName()}"/>
                    </label>
                </div>
                <div class="mb-3">
                    <label class="form-label">Содержание</label>
                    <textarea class="form-control" required minlength="10" rows="20" cols="70" type="text"
                              name="body">${page.getBody()}</textarea>
                </div>
                <input type="submit" class="btn btn-primary" value="Сохранить"/>
            </form>
        </div>
    @endif
`,
)
