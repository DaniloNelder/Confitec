# Confitec
<h2>Calculo de Juros com Spring Boot</h2>

```
O valor pago por uma apólice de seguro é composto, basicamente, pela soma das coberturas contratadas. 
Com base nisso, elabore uma solução capaz de retornar o plano de pagamento de uma apólice de acordo 
com as opções solicitadas, aplicando ou não um percentual de juros no parcelamento.
A implementação desse teste deve ser disponibilizada em um repositório Git com acesso público.

Regras de negócio:
O resto da divisão do rateio é alocado na primeira parcela;
A taxa de juros deverá ser aplicado ao valor total (soma das coberturas),
utilizando a regra juros compostos:
P = V*(1 + i)^t, onde:
P = Valor a ser pago 
V = Valor total
i = Taxa de juros
t = Quantidade de parcelas
O parcelamento não se aplica para taxas de juros ou quantidade de parcelas negativas.
```

Para executar o projeto no terminal, digite o seguinte comando:

```
mvn spring-boot:run 
```

Endereço do projeto:
```
http://localhost:8081/
```

### Pré-requisitos:

* Java 11 ou versões superiores.
* Maven 3.8.1 ou versões superiores.
* Uma IDE exemplo Eclipse ou qualquer outra de sua escolha.

### Endpoint:
* POST http://localhost:8081/confitec/teste/parcelamento 


### Request:
```
{
    "listCobertura": [
        {
            "cobertura": 1,
            "valor": 123.12
        },
        {
            "cobertura": 4,
            "valor": 345.45
        }
    ],
    "listOpcaoParcelamento": [
        {
            "quantidadeMinimaParcelas": 1,
            "quantidadeMaximaParcelas": 6,
            "juros": 0
        },
        {
            "quantidadeMinimaParcelas": 7,
            "quantidadeMaximaParcelas": 9,
            "juros": 0.01
        },
        {
            "quantidadeMinimaParcelas": 10,
            "quantidadeMaximaParcelas": 12,
            "juros": 0.03
        }
    ]
}
```
