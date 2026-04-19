## Design Patterns

### SOLID

1. Principio básico e imutável q estabelece regras para criação de um código melhor 
2. Pensar em formas de desenvolvimento que tornem flexível e a mudança seja fácil 
***
Não se repita, ou seja, se um código se repete várias vezes, ele vira um método
***
3. Single responsability 
4. Open closed
5. Liskov
6. Interface agregation
7. Dependecy inversion


#### Single Responsability

1. Única responsabilidade
2. Classe devem ter somente o necessário

	Ex: 5 métodos precisam ser criados
		- List client
		- create client
		- remove client
		- update client
		- notify client
		Perceba que os primeiros manipulam o cliente e o outro interage, então, levando pra abstração da paytech, ficaria customerService com os 4 primeiros e o ultimo teria sido na UserService
		
#### Open/Closed
1. Aberto e fechado
2. Aberto para extensão e Fechado para modificação.
3. Fechado para modificação pois pode quebrar algo que já foi implementado. Aberto para extensão pois pode crescer
4. Caso precise fazer uma modificação grande, criar uma classe a parte, para não quebrar a existente
5. Minimiza o risco de bugs regressivos
6. Reforça outros principios do solid, como a letra s "Single responsability", pois acabou tendo classe com uma menor responsabilidade

### Liskov Substituition Principle
1. Barbara liskov definiu esse principio
2. Uma classe derivada pode e deve ser substituível por sua classe base
3. Ou seja, caso ela deixe de ser derivada, ela continue funcionando

