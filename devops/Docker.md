

## O Docker

1. Construir, rodar e transferir ambientes de testes e produção 
2. Colocar aplicação dentro de um container e enviar para WEB
##### Container
  Já vai ter tudo, versão, enviroments, tudo lá dentro, aí é só transportar pro servidor. 
###### Diferença entre Virtualização e Docker
 Docker consegue rodar várias aplicações de formas isoladas em 1 unico sistema operacional sem hyperdivisor, com maior eficacia em questão de recursos e melhor performance

### Comandos

![[Pasted image 20260414104722.png]]

### Como funciona o docker ?

Criamos docker file dentro da aplicação
 1. Ele é responsável por criar uma imagem dela
 2. Imagem será gerada apartir das configurações do dockerfile
 3. Dentro dessa imagem esta um pedaço do ambiente
 4. Dentro dessa imagem vai os arquivos do back/front tb
 5. Libraries e variaveis também vão
 6. Imagem sendo então a copia perfeita do projeto

Após criar a imagem, teria de passar para o container, que seria o processo, que armazena a imagem e a partir daí o container pode ser transferido para o servidor e rodar a aplicação

Assim é possivel enviar o container para ambiente de PRODUÇÃO ou TESTE

### DockerHUB 

Caso sua aplicação já esteja dentro de um container, você pode disponibilizar ela no docker hub para ser baixado no servidor de produção

1. Registry, que é o dockerHUB, é um repositório de containers
2. Você pode mandar sua imagem para lá e transferir para o servidor de produção ou teste
3. Caso vc perca sua maquina, suas imagens estarão disponíveis no repositório 

### Visual Code

IDE Para edição de arquivos docker


## Image em docker

- Cut - Down - OS (Ou seja, tem sistem operacional dentro dela)
- Tem todas Libraries, sdks, tudo do projeto
- Arquivos do APP
- Enviroment variables

Ou seja, Uma imagem contém tudo q é necessário pra uma aplicação funcionar, logo, se você tem uma imagem, você consegue transferir ela pra um container e rodar em qualquer lugar, outro PC ou ambiente

### Containers 
- Ambiente isolado (quase uma VM)
- Pode ser iniciado e pode ser parado. Você pode controlar pelo terminal ou pelo docker desktop
- É considerado um processo. Pois ele pode rodar dentro de uma máquina

Processo para criação do container
1. Aplicativo 
2. Cria imagem apartir do aplicativo
3. Cria um container apartir da imagem

Dessa forma, a o container consegue rodar a imagem


## Como criar docker file

1. FROM, tenho q especificar qual imagem ele vai carregar, linux, ubuntu, etc. e qual plataforma, nodejs, python, java etc
2. WORKDIR, especificação de onde vai ser trabalhado
3. COPY / ADD, servem para copiar ou adcionar todos os arquivos q fazem parte da aplicação para a imagem
4. RUN, usado para rodar
5. ENV, para variaveis
6. EXPOSE, porta q vai ser usada
7. USER, usuario q vai rodar
8. cmd, comando para rodar algo apos a aplicação ter dado bom


## YAML
- Data serialization
- usada para configurações
- Ele passa instruções para o docker no docker compose
- de cima para baixo e em sequencia lógica