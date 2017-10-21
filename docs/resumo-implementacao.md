# Resumo da Implementação

Um pequeno resumo do que foi implementado. Os requisitos foram divididos em básicos e melhorias para melhor organização e também foi a ordem de implementação.

## Requisitos Básicos

### Cadastro de usuários
O cadastro de usuários é praticamente idêntico ao de posts, sendo diferente apenas os campos.

### Adicionar Tags aos Posts
De início foi implementado como uma lista de Strings na entidade Post. Mas decidi que as tags poderiam ser expandidas no futuro e era melhor que fossem entidades independentes dos posts. Além disso, poderiam ser cadastradas no banco antes do resto da aplicação.


## Melhorias

### Tags como filtros
Clicando nas tags, os posts são filtrados de acordo com essa tag.

### Busca
A busca é feita por uma string e pesquisa por título ou autor.

### Validação
O cadastro dos posts e usuários não dava um feedback para o usuário caso algum campo estivesse faltando e simplesmente retornava para a homepage, parecendo que a operação foi realizada com sucesso. Então, os campos foram definidos como obrigatórios e o usuário não pode enviar sem preenchê-los. A validação também é feita no lado do servidor e caso algum usuário consiga mandar informações incompletas, elas não são salvas.

### Comentários
Como muitos sites, foi adicionado uma seção de comentários em cada post. Para simplificar a implementação, qualquer um pode postar uma mensagem sem identificação. Dessa maneira, o foco foi no relacionamento de 1 para n entre posts e comments.
