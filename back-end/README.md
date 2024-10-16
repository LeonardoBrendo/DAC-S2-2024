# Configurações para excutar a aplicação back-end.

1 - Javas 8+, PgAdmin 3+ e Maven 3+, git 2+ instalados.

2 - Para baixar o projeto, execute o comando "git clone https://github.com/LeonardoBrendo/mobit-desafio-back-end.git" (sem aspas).

3 - Em seguida, deve-se criar uma base de dados, onde a tabela utilizada pela aplicação será persistida. Após a criação da base de dados, faz-se necessário informar as credenciais da conexão no arquivo application.properties, localizado em src/main/resources. Dentre as informações estão o nome do usuário e senha do banco, assim como o nome da base de dados alocada para o proejto, dentre outras especificações técnicas.

4 - Para executar o projeto, levando em consideração a utilização do software STS, clique com o botão direito do mouse sobre o projeto e na alternativa Run As -> Spring boot App.

5 - Se surgir algum problema em decorrência da sincronização com o maven, execute "mvn clean install compile" (sem aspas) na pasta principal do projeto.

# Informação adicional.

1 - Estar sendo utilizado o swagger para descrição, consumo e visualização de serviços RESTful, assim como facilitar a documentação e entendimento da API. Para visitar a página do swagger da aplicação em questão, vá à página "https://cadastro-contato.herokuapp.com/swagger-ui.html" (sem aspas) e veja os endpoints.


