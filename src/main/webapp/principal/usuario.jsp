<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<jsp:include page="head.jsp"></jsp:include>
<body>
<jsp:include page="theme-load.jsp"></jsp:include>
<div id="pcoded" class="pcoded">
    <div class="pcoded-overlay-box"></div>
    <div class="pcoded-container navbar-wrapper">
    <jsp:include page="navbar.jsp"></jsp:include>
        <div class="pcoded-main-container">
            <div class="pcoded-wrapper">
                <jsp:include page="navbar-main.jsp"></jsp:include>
                <div class="pcoded-content">
                    <jsp:include page="page-header.jsp"></jsp:include>   <!-- Page-header start -->
                    <!-- Page-header end -->
                    <div class="pcoded-inner-content">
                        <!-- Main-body start -->
                        <div class="main-body">
                            <div class="page-wrapper">
                                <!-- Page-body start -->
                                <div class="page-body">
                                    <div class="row">
                                        <div class="col-sm-12">
                                            <div class="card">
                                                <div class="card-block">
                                                    <form class="form-material" action="<%= request.getContextPath() %>/ServletUsuarioController" method="post" id="formUser">
                                                        <input type="hidden" name="acao" id="acao" value="">
                                                        <div class="form-group form-default form-static-label">
                                                            <input type="text" name="id" id="id" class="form-control" readonly="readonly" value="${usuario.id}">
                                                            <span class="form-bar"></span>
                                                            <label class="float-label">ID</label>
                                                        </div>
                                                        <div class="form-group form-default form-static-label">
                                                            <input type="text" name="nome" id="nome" class="form-control" required="required" value="${usuario.nome}">
                                                            <span class="form-bar"></span>
                                                            <label class="float-label">Nome</label>
                                                        </div>
                                                        <div class="form-group form-default form-static-label">
                                                            <input type="email" name="email" id="email" class="form-control" required="required" autocomplete="off" value="${usuario.email}">
                                                            <span class="form-bar"></span>
                                                            <label class="float-label">Email</label>
                                                        </div>
                                                        <div class="form-group form-default form-static-label">
                                                            <input type="text" name="login" id="login" class="form-control" required="required" autocomplete="off" value="${usuario.login}">
                                                            <span class="form-bar"></span>
                                                            <label class="float-label">Login</label>
                                                        </div>
                                                        <div class="form-group form-default form-static-label">
                                                            <input type="password" name="senha" id="senha" class="form-control" required="required" autocomplete="off" value="${usuario.senha}">
                                                            <span class="form-bar"></span>
                                                            <label class="float-label">Senha</label>
                                                        </div>
                                                        <button type="button" class="btn btn-primary waves-effect waves-light" onclick="limparForm()">Novo</button>
                                                        <button class="btn btn-primary waves-effect waves-light">Salvar</button>
                                                        <button type="button" class="btn btn-primary waves-effect waves-light" onclick="criarDeleteAjax()">Excluir</button>
                                                        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal">Consultar</button>
                                                    </form>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <span id="msg">${msg}</span>
                                </div>
                            </div>
                            <div id="styleSelector"></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="javascriptfile.jsp"></jsp:include>

<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Consultar Usuário</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <div class="input-group mb-3">
                    <input type="text" class="form-control" placeholder="Nome" aria-label="nome" id="nomeBusca" aria-describedby="basic-addon2">
                    <div class="input-group-append">
                        <button class="btn btn-outline-secondary" type="button" onclick="buscarUsuario()">Buscar</button>
                    </div>
                </div>
                <div style="height: 300px; overflow: scroll">
                    <table class="table" id="tabelaResultados">
                        <thead>
                        <tr>
                            <th scope="col">ID</th>
                            <th scope="col">Nome</th>
                            <th scope="col">Ver</th>
                        </tr>
                        </thead>
                        <tbody>

                        </tbody>
                    </table>
                </div>
                <span id="totalResultados"></span>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Fechar</button>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">

    function buscarUsuario() {
        var nomeBusca = document.getElementById('nomeBusca').value;

        if(nomeBusca != null && nomeBusca !== '' && nomeBusca.trim() !== '') {
            var urlAction = document.getElementById('formUser').action;
            $.ajax({
                method: "get",
                url: urlAction,
                data: "nomeBusca=" + nomeBusca + '&acao=buscarUserAjax',
                success: function (response) {
                    var json = JSON.parse(response);
                    $('#tabelaResultados > tbody > tr').remove();
                    for (var p = 0; p < json.length; p++) {
                        $('#tabelaResultados > tbody').append('<tr><td>'+json[p].id+'</td> <td>'+json[p].nome+'</td><td><button type="button" class="btn btn-info">Ver</button></td></tr>')
                    }
                    document.getElementById('totalResultados').textContent = 'Resultados: ' + json.length;
                }
            }).fail(function(xhr, status, errorThrown){
                alert('Erro ao buscar o usuário' + xhr.responseText);
            });
        }
    }

    function criarDeleteAjax() {

        if(confirm('Deseja realmente excluir os dados?')){
           var urlAction = document.getElementById('formUser').action;
           var idUser = document.getElementById('id').value;

           $.ajax({
               method: "get",
               url: urlAction,
               data: "id=" + idUser + '&acao=deletarajax',
               success: function (response) {
                   limparForm();
                   document.getElementById('msg').textContent = response;
               }
           }).fail(function(xhr, status, errorThrown){
              alert('Erro ao deletar o usuario' + xhr.responseText);
           });
        }
    }

    function criarDelete() {

        if(confirm('Deseja realmente excluir os dados?')) {
            document.getElementById("formUser").method = 'get';
            document.getElementById("acao").value = 'deletar';
            document.getElementById("formUser").submit();
        }
    }

    function limparForm() {
        var elementos = document.getElementById("formUser").elements;

        for(p = 0; p < elementos.length; p++){
            elementos[p].value = '';
        }
    }
</script>
</body>
</html>

