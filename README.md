# DESAFIO-HOTMART

<p> As variáveis necessárias para o projeto estão no arquivo de configurações do spring em: 
<strong> src/main/resources/application.properties. Só rodar :) </strong>
</p>
<p> Acessar o swagger local: <a> http://localhost:8080/swagger-ui.html </a> </p>
<p> Necessário configurar o banco de dados, está configurado para utilizar my sql na porta 3306 </p>
<p> Pode ser utilizado qualquer outro banco compatível com o JPA. Para continuar utilizando o MySQL seguir: </p>

<br/><h1>Funcionamento</h1>
<p> Esta API é feita utilizando as tecnologias: Java8 / Spring / Spring Data </p>


<br/><h1>Pontos de melhoria</h1>
<p> - Segurança utilizando tokens OAuth JWT </p>
<p> - Scope de segurança para cada endpoint </p>
<p> - A parte do Shopping seria uma "parte logada", poderia utilizar token OAuth do tipo password, pegando o cliente comprador pelo username do token </p>
