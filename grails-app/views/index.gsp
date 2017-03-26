<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Welcome to Investill</title>

    <asset:link rel="icon" href="favicon.ico" type="image/x-ico" />
</head>
<body>
    <div id="content" role="main">
        <section class="row colset-2-its">
            <h1>Bem vindo visitante!</h1>

            <div id="controllers" role="navigation">
                <ul class="list-unstyled">
                  <li>
                    <g:link controller="login" action="index">Entrar no sistema</g:link>
                  </li>
                  <li>
                    <g:link controller="logout" action="index">Logout</g:link>
                  </li>
                  <li>
                    <g:link controller="secUser" action="create">Cadastrar-se</g:link>
                  </li>
                </ul>
            </div>
        </section>
    </div>

</body>
</html>
