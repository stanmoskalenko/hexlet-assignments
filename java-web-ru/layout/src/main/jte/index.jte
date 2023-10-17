@import gg.jte.Content
@param Content content


@template.layout.page(
content = @`
    <div class="text-center">
        <figure class="figure">
            <img class="figure-img" style="width:400px; height: 400px"
                 src="https://yt3.googleusercontent.com/2kDy-UYCqoz4N_CnFmi09Qwoj61GGvK2q2EpTML9agLMNSEGNXg_QAmuAlv3-PydU8Vy3VCkZQ=s900-c-k-c0x00ffffff-no-rj"
                 alt="hexlet-img">
            <figcaption class="figure-caption text-center">
                <a class="icon-link icon-link-hover" href="users/">
                    Show all users
                    <svg class="bi" aria-hidden="true"><use xlink:href="#arrow-right"></use></svg>
                </a>
            </figcaption>
        </figure>
    </div>
`)