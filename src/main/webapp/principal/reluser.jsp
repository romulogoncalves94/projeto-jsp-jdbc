<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
                                                    <h4 class="sub-title">Rel. Usuário</h4>
                                                    <form class="form-material" action="<%= request.getContextPath() %>/ServletUsuarioController?acao=imprimirRelatorioUser" method="post" id="formUser" >
                                                        <div class="form-row align-items-center">
                                                            <div class="col-auto">
                                                                <label class="sr-only" for="dataInicial">Data Inicial</label>
                                                                <input value="${dataInicial}" type="text" class="form-control mb-4" id="dataInicial" name="dataInicial">
                                                            </div>
                                                            <div class="col-auto">
                                                                <label class="sr-only" for="dataFinal">Data Final</label>
                                                                <input value="${dataFinal}" type="text" class="form-control mb-4" id="dataFinal" name="dataFinal">
                                                            </div>

                                                            <div class="col-auto">
                                                                <button type="submit" class="btn btn-primary mb-4">Imprimir</button>
                                                            </div>
                                                        </div>
                                                    </form>
                                                    <div style="height: 300px; overflow: scroll">
                                                        <table class="table" id="tabelaResultadosView">
                                                            <thead>
                                                            <tr>
                                                                <th scope="col">ID</th>
                                                                <th scope="col">Nome</th>
                                                            </tr>
                                                            </thead>
                                                            <tbody>
                                                            <c:forEach items="${listaUser}" var="f">
                                                                <tr>
                                                                    <td><c:out value="${f.id}"></c:out></td>
                                                                    <td><c:out value="${f.nome}"></c:out></td>
                                                                </tr>
                                                            </c:forEach>
                                                            </tbody>
                                                        </table>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
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
<script type="text/javascript">

    $( function() {
        $("#dataInicial").datepicker({
            dateFormat: 'dd/mm/yy',
            dayNames: ['Domingo','Segunda','Terça','Quarta','Quinta','Sexta','Sábado'],
            dayNamesMin: ['D','S','T','Q','Q','S','S','D'],
            dayNamesShort: ['Dom','Seg','Ter','Qua','Qui','Sex','Sáb','Dom'],
            monthNames: ['Janeiro','Fevereiro','Março','Abril','Maio','Junho','Julho','Agosto','Setembro','Outubro','Novembro','Dezembro'],
            monthNamesShort: ['Jan','Fev','Mar','Abr','Mai','Jun','Jul','Ago','Set','Out','Nov','Dez'],
            nextText: 'Próximo',
            prevText: 'Anterior'
        });
    } );

    $( function() {
        $("#dataFinal").datepicker({
            dateFormat: 'dd/mm/yy',
            dayNames: ['Domingo','Segunda','Terça','Quarta','Quinta','Sexta','Sábado'],
            dayNamesMin: ['D','S','T','Q','Q','S','S','D'],
            dayNamesShort: ['Dom','Seg','Ter','Qua','Qui','Sex','Sáb','Dom'],
            monthNames: ['Janeiro','Fevereiro','Março','Abril','Maio','Junho','Julho','Agosto','Setembro','Outubro','Novembro','Dezembro'],
            monthNamesShort: ['Jan','Fev','Mar','Abr','Mai','Jun','Jul','Ago','Set','Out','Nov','Dez'],
            nextText: 'Próximo',
            prevText: 'Anterior'
        });
    } );
</script>
</body>
</html>

