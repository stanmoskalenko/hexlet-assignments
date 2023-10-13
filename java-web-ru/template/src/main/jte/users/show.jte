@import exercise.model.User
@param User user


<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/antd/4.17.2/antd.min.css"
              rel="stylesheet"
              crossorigin="anonymous">
        <link rel="icon"
              type="image/svg+xml"
              href="https://c.staticblitz.com/assets/favicon_sb-861fe1b85c0dc928750c62de15fed96fc75e57ee366bd937bad17a3938917b3f.svg">
    <title>${user.getFirstName()} ${user.getLastName()}</title>
</head>
<body>
    <div id="root">
            <div class="ant-space-item" style="padding: 24px">
                <button type="button"
                        class="ant-btn css-dev-only-do-not-override-amq5gd ant-btn-primary ant-btn-lg"
                        onclick="location.href = document.referrer; return false;">
                    <span class="ant-btn-icon">
                        <span role="img"
                              aria-label="step-backward"
                              class="anticon anticon-step-backward">
                            <svg viewBox="0 0 1024 1024"
                                 focusable="false"
                                 data-icon="step-backward"
                                 width="1em"
                                 height="1em"
                                 fill="currentColor"
                                 aria-hidden="true">
                                <path d="M347.6 528.95l383.2 301.02c14.25 11.2 35.2 1.1 35.2-16.95V210.97c0-18.05-20.95-28.14-35.2-16.94L347.6 495.05a21.53 21.53 0 000 33.9M330 864h-64a8 8 0 01-8-8V168a8 8 0 018-8h64a8 8 0 018 8v688a8 8 0 01-8 8"></path>
                            </svg>
                        </span>
                    </span>
                    Назад
                </button>
            </div>
            <div class="ant-spin-container">
                <h1 style="text-align: center">${user.getFirstName()} ${user.getLastName()}</h1>
                <div class="ant-table-container">
                    <div class="ant-table">
                        <table class="ant-table-small">
                            <thead class="ant-table-thead">
                                <tr>
                                    <th class="ant-table-cell" scope="col">id</th>
                                    <th class="ant-table-cell" scope="col">email</th>
                                </tr>
                            </thead>
                            <tbody class="ant-table-tbody">
                                <tr class="ant-table-row ant-table-row-level-0">
                                    <td class="ant-table-cell">${user.getId()}</td>
                                    <td class="ant-table-cell">${user.getEmail()}</td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
    </div>
</body>
</html>
