<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
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
                                                    <form class="form-material" action="<%= request.getContextPath() %>/ServletTelefone" method="post" id="formFone" >
                                                        <div class="form-group form-default form-static-label">
                                                            <input type="text" name="id" id="id" class="form-control"  readonly="readonly" value="${modelLogin.id}">
                                                            <span class="form-bar"></span>
                                                            <label class="float-label">ID User</label>
                                                        </div>
                                                        <div class="form-group form-default form-static-label">
                                                            <input readonly type="text" name="nome" id="nome" class="form-control" required="required" value="${modelLogin.nome}">
                                                            <span class="form-bar"></span>
                                                            <label class="float-label">Nome</label>
                                                        </div>
                                                        <div class="form-group form-default form-static-label">
                                                            <input type="text" name="numero" id="numero" class="form-control" required="required">
                                                            <span class="form-bar"></span>
                                                            <label class="float-label">Número Telefone</label>
                                                        </div>
                                                        <button class="btn btn-primary waves-effect waves-light">Salvar</button>
                                                    </form>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <span id="msg">${msg}</span>
                                    <div style="height: 300px; overflow: scroll">
                                        <table class="table" id="tabelaResultadosView">
                                            <thead>
                                            <tr>
                                                <th scope="col">ID</th>
                                                <th scope="col">Número</th>
                                                <th scope="col">Excluir</th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <c:forEach items="${modelTelefones}" var="f">
                                                <tr>
                                                    <td><c:out value="${f.id}"></c:out></td>
                                                    <td><c:out value="${f.numero}"></c:out></td>
                                                    <td><a class="btn btn-outline-secondary" href="<%= request.getContextPath() %>/ServletTelefone?acao=excluir&id=${f.id}&userpai=${modelLogin.id}">Excluir</a></td>
                                                </tr>
                                            </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                            <div id="styleSelector"> </div>
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

