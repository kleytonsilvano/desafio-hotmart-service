# DESAFIO-HOTMART-SERVICE

<p> As variáveis necessárias para o projeto estão no arquivo de configurações do spring em: 
<strong> src/main/resources/application.properties. Só rodar :) </strong>
</p>
<p> Acessar o swagger local: <a> http://localhost:8080/swagger-ui.html </a> </p>
<p> Na pasta doc/ contém a collection para ser importada dentro do software do Postman e ser testado diretamente </p>
<p> Necessário configurar o banco de dados, está configurado para utilizar my sql na porta 3306 </p>
<p> Pode ser utilizado qualquer outro banco compatível com o JPA. Para continuar utilizando o MySQL seguir: </p>
<p> Configuração: Dentro da pasta sql/ existe um arquivo ( backup.sql ) que contem todas as tabelas configuradas para rodar o projeto.</p>
<p> Dentro da pasta do MySql/bin (C:\Program Files\MySQL\MySQL Server 8.0\bin), para importar o banco, colocar o arquivo backup.sql dentro da pasta e rodar o comando:
<p> <strong><i>	> mysql -u root -p marketplace < backup.sql </strong></i> </p>
<p> Colocar a senha do usuário root do MYSQL . </p>
<p> * O banco de dados foi exportado utilizando o comando: </p>
<p> <strong><i>	> mysqldump -u root -p marketplace > backup.sql </strong></i> </p>

<h1>Funcionamento</h1>
<p> Esta API é feita utilizando as tecnologias: Java8 / Spring / Spring Data / Spring WEB</p>
