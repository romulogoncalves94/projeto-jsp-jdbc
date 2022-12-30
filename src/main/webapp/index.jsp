<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <title>Title</title>
    <style type="text/css">
        form{
            position: absolute;
            top: 40%;
            left: 33%;
        }
        h1{
            position: absolute;
            top: 30%;
            left: 33%;
        }
        h5{
            position: absolute;
            top: 80%;
            left: 33%;
        }
        .msg{
            position: absolute;
            top: 80%;
            left: 33%;
            font-size: 15px;
            color: #664d03;
            background-color: #fff3cd;
            border-color: #ffecb5;
        }
    </style>
</head>
<body>
    <h1>Login</h1>
    <h5 class="msg">${msg}</h5>
    <form action="ServletLogin" method="post" class="row g-3 needs-validation" novalidate>
        <input type="hidden" value="<%= request.getParameter("url") %>" name="url">

        <div class="mb-3">
            <label class="form-label">Login</label>
            <input class="form-control" name="login" type="text" required>
        </div>

        <div class="mb-3">
            <label class="form-label">Senha</label>
            <input class="form-control" name="senha" type="password" required>
        </div>

        <div class="mb-3">
            <input type="submit" value="Entrar" class="btn btn-primary">
        </div>
    </form>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
    <script type="text/javascript">
        (function () {
            'use strict'

            var forms = document.querySelectorAll('.needs-validation')

            Array.prototype.slice.call(forms)
                .forEach(function (form) {
                    form.addEventListener('submit', function (event) {
                        if (!form.checkValidity()) {
                            event.preventDefault()
                            event.stopPropagation()
                        }

                        form.classList.add('was-validated')
                    }, false)
                })
        })()
    </script>
</body>
</html>