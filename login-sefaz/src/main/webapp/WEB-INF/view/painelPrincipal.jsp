<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="com.menino.loginsefaz.domain.model.Telefone"%>
<jsp:useBean id="lista" class="java.util.ArrayList" />

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Painel Principal</title>
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>

<script>
	//Promise padrão de requisição http
	function httpRequest(method, url, data){
		const promise = new Promise((resolve, reject) => {
			var token = "${tipoToken}" + "${token}";
			var xhr = new XMLHttpRequest();
			xhr.open(method, url);
			xhr.setRequestHeader("Authorization", token);
			xhr.responseType = 'json';
			if(data){
				xhr.setRequestHeader('Content-Type', 'application/json');
			}
			xhr.onload = () => {
				resolve(xhr.response);
			};
			xhr.send(JSON.stringify(data));
		});
		return promise;
	};

	//Requisita a lista de usuários
	function listarUsuarios(){
		httpRequest('GET', '/usuarios').then(dados =>{
			for(var i = 0; i < dados.length; i++){
				$(".tabela-usuarios").append("<tr><td>" + dados[i].nome + "</td><td>" + dados[i].email + "</td><td></td></tr>");
			}
		});
	};


	function alterar1Usuario(){
		var url = '/usuarios/' + "${id}";
		httpRequest('PUT', url, {
			
			id: "${id}",
			email: $("#alterar-email").val(),
			nome: $("#alterar-nome").val(),
			senha: $("#alterar-senha").val(),
			telefones: {
				ddd: $("#alterar-ddd").val(),
				numero: $("#alterar-numero").val(),
				senha: $("#alterar-tipo").val()
			}
		});
	}

	function alterarUsuario(){
		console.log($("#alterar-ddd").val());
	}

	function deletarUsuario(){
		var url = '/usuarios/' + "${id}";
		httpRequest('DELETE', url);
	}
	
</script>

<script>
	//Preenche os dados do usuário na aba Alterar dados
	function preencherAlterarDadosUsuario(){
		$("#alterar-nome").val("${nome}");
		$("#alterar-email").val("${email}");
		$("#alterar-senha").val("${senha}");

		$(document).ready(function(){
			var contador = ${telefones.size()};
			for(var i = 0; i < contador; i++){
				$(".tabela-alterar-telefones").append("<tr><td><input type='number' name='ddd' placeholder='DDD' required id='alterar-ddd' value='" + "${telefones.get(i).getDdd()}" + "'/></td> <td><input type='number' name='numero' placeholder='Numero' id='alterar-numero' required value='" + "${telefones.get(i).getNumero()}" + "'/></td> <td><input type='text' name='tipo' required id='alterar-tipo' value='" + "${telefones.get(i).getTipo()}" + "' /></td></tr>");
			}
	 	});
	};
	
	//Adiciona mais uma linha de telefone
	function adicionarTelefone() {
		$(".tabela-alterar-telefones").append("<tr><td><input type='number' name='ddd' placeholder='DDD'  required/></td>" +
												  "<td><input type='number' name='numero' placeholder='Numero'  required/></td>" +
												  "<td><input type='text' name='tipo' required/></td>" +
												  "<td><button class='remover'>-</button></td>");
		$(".remover").on("click",function(e){
	        $(this).closest('tr').remove();
 	   });
	}

	//Lista os telefones ao carregar a página
	$(document).ready(function(){
		var contador = ${telefones.size()};
		for(var i = 0; i < contador; i++){
			$(".tabela-telefones").append("<tr><td>" + ${telefones.get(i).getDdd()} + " " + ${telefones.get(i).getNumero()} + "</td></tr>")
		}
 	});

	//Lista os usuários e preenche os dados para alteração ao carregar a página
	$(document).ready(function(){
		listarUsuarios();
		preencherAlterarDadosUsuario();
	});
</script>

</head>

<style>
@import url(https://fonts.googleapis.com/css?family=Roboto:300);

/* Style the tab */
.tab {
	overflow: hidden;
	border: 1px solid #ccc;
	background-color: #f1f1f1;
}

/* Style the buttons that are used to open the tab content */
.tab button {
	background-color: inherit;
	float: left;
	border: none;
	outline: none;
	cursor: pointer;
	padding: 14px 16px;
	transition: 0.3s;
}

/* Change background color of buttons on hover */
.tab button:hover {
	background-color: #ddd;
}

/* Create an active/current tablink class */
.tab button.active {
	background-color: #ccc;
}

/* Style the tab content */
.tabcontent {
	display: none;
	padding: 6px 12px;
	border: 1px solid #ccc;
	border-top: none;
}

<!--
///////////////////////
-->
.cadastro-page {
	width: 360px;
	padding: 8% 0 0;
	margin: auto;
}

.form {
	position: relative;
	z-index: 1;
	background: #FFFFFF;
	max-width: 360px;
	margin: 0 auto 100px;
	padding: 45px;
	text-align: center;
	box-shadow: 0 0 20px 0 rgba(0, 0, 0, 0.2), 0 5px 5px 0
		rgba(0, 0, 0, 0.24);
}

.form input {
	font-family: "Roboto", sans-serif;
	outline: 0;
	background: #f2f2f2;
	width: 100%;
	border: 0;
	margin: 0 0 15px;
	padding: 15px;
	box-sizing: border-box;
	font-size: 14px;
}

.form button {
	font-family: "Roboto", sans-serif;
	text-transform: uppercase;
	outline: 0;
	background: #4CAF50;
	width: 100%;
	border: 0;
	padding: 15px;
	color: #FFFFFF;
	font-size: 14px;
	-webkit-transition: all 0.3 ease;
	transition: all 0.3 ease;
	cursor: pointer;
}

.form button:hover, .form button:active, .form button:focus {
	background: #43A047;
}

.form .message {
	margin: 15px 0 0;
	color: #b3b3b3;
	font-size: 12px;
}

.form .message a {
	color: #4CAF50;
	text-decoration: none;
}

.form .register-form {
	display: none;
}

.container {
	position: relative;
	z-index: 1;
	max-width: 300px;
	margin: 0 auto;
}

.container:before, .container:after {
	content: "";
	display: block;
	clear: both;
}

.container .info {
	margin: 50px auto;
	text-align: center;
}

.container .info h1 {
	margin: 0 0 15px;
	padding: 0;
	font-size: 36px;
	font-weight: 300;
	color: #1a1a1a;
}

.container .info span {
	color: #4d4d4d;
	font-size: 12px;
}

.container .info span a {
	color: #000000;
	text-decoration: none;
}

.container .info span .fa {
	color: #EF3B3A;
}

body {
	background: #76b852; /* fallback for old browsers */
	background: -webkit-linear-gradient(right, #76b852, #8DC26F);
	background: -moz-linear-gradient(right, #76b852, #8DC26F);
	background: -o-linear-gradient(right, #76b852, #8DC26F);
	background: linear-gradient(to left, #76b852, #8DC26F);
	font-family: "Roboto", sans-serif;
	-webkit-font-smoothing: antialiased;
	-moz-osx-font-smoothing: grayscale;
}
</style>


<body>
	<!-- Links das abas -->
	<div class="tab">
		<button class="tablinks" onclick="abreAba(event, 'dados')"
			id="paginaInicial">Dados do cadastro</button>
		<button class="tablinks" onclick="abreAba(event, 'lista')">Lista
			de cadastros</button>
		<button class="tablinks" onclick="abreAba(event, 'alterar')">Alterar
			dados</button>
	</div>

	<!-- Conteúdo das abas -->
	<div id="dados" class="tabcontent">
		<div class="cadastro-page">
			<div class="form">
				<table>
					<tr>
						<th>Nome:</th>
						<td>${nome}</td>
					</tr>
					<tr>
						<th>E-mail:</th>
						<td>${email}</td>
					</tr>
					<tr>
						<th>Telefones:</th>
						<td>
							<table class="tabela-telefones"></table>
						</td>
					</tr>
				</table>
				<br> <br>
				<button onclick="deletarUsuario()">Apagar cadastro</button>
			</div>
		</div>
	</div>

	<div id="lista" class="tabcontent">
		<div class="cadastro-page">
			<div class="form">
				<table class="tabela-usuarios">
					<tr>
						<th>Nome:</th>
						<th>E-mail:</th>
					</tr>
				</table>
			</div>
		</div>
	</div>

	<div id="alterar" class="tabcontent">
		<div class="cadastro-page">
			<div class="form">
				<form onsubmit="alterarUsuario()">
					<input type="text" name="nome" id="alterar-nome" placeholder="Nome completo" required /> 
					<input type="email"name="email" id="alterar-email" placeholder="E-mail" required />
					<input type="password" name="senha" id="alterar-senha" placeholder="Senha" required />

					<table class="tabela-alterar-telefones"></table>

					<button onclick="adicionarTelefone()">Adicionar telefone</button>
					<br> <br>
					<button onclick="alterarUsuario()">Alterar cadastro</button>
				</form>
			</div>
		</div>
	</div>

	<!-- JavaScript das abas -->
	<script>

	//Torna visível inicialmente a primeira aba
	document.getElementById("paginaInicial").click();

	//Abre as abas
	function abreAba(event, nomePagina) {
		  var i, tabcontent, tablinks;

		  tabcontent = document.getElementsByClassName("tabcontent");
		  for (i = 0; i < tabcontent.length; i++) {
		    tabcontent[i].style.display = "none";
		  }

		  tablinks = document.getElementsByClassName("tablinks");
		  for (i = 0; i < tablinks.length; i++) {
		    tablinks[i].className = tablinks[i].className.replace(" active", "");
		  }

		  document.getElementById(nomePagina).style.display = "block";
		  event.currentTarget.className += " active";
		}
	</script>

</body>

</html>