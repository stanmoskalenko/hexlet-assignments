@import exercise.util.NamedRoutes

@template.layout.page(
    content = @`
        <h2>Welcome to Hexlet</h2>
        <a href="${NamedRoutes.postsPath()}">Показать посты</a>
    `
)
