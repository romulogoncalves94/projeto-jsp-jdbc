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
                                                    <form class="form-material" action="<%= request.getContextPath() %>/ServletUsuarioController" method="post">
                                                        <div class="form-group form-default">
                                                            <input type="text" name="id" id="id" class="form-control" readonly="readonly" value="${usuario.id}">
                                                            <span class="form-bar"></span>
                                                            <label class="float-label">ID</label>
                                                        </div>
                                                        <div class="form-group form-default">
                                                            <input type="text" name="nome" id="nome" class="form-control" required="required" value="${usuario.nome}">
                                                            <span class="form-bar"></span>
                                                            <label class="float-label">Nome</label>
                                                        </div>
                                                        <div class="form-group form-default">
                                                            <input type="email" name="email" id="email" class="form-control" required="required" autocomplete="off" value="${usuario.email}">
                                                            <span class="form-bar"></span>
                                                            <label class="float-label">Email</label>
                                                        </div>
                                                        <div class="form-group form-default">
                                                            <input type="text" name="login" id="login" class="form-control" required="required" autocomplete="off" value="${usuario.login}">
                                                            <span class="form-bar"></span>
                                                            <label class="float-label">Login</label>
                                                        </div>
                                                        <div class="form-group form-default">
                                                            <input type="password" name="senha" id="senha" class="form-control" required="required" autocomplete="off" value="${usuario.senha}">
                                                            <span class="form-bar"></span>
                                                            <label class="float-label">Senha</label>
                                                        </div>
                                                        <button class="btn btn-primary waves-effect waves-light">Novo</button>
                                                        <button class="btn btn-warning btn-round waves-effect waves-light">Salvar</button>
                                                        <button class="btn btn-danger btn-round waves-effect waves-light">Excluir</button>
                                                    </form>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <span>${msg}</span>
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
</body>
</html>
