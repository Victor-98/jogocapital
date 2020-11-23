package com.example.jogocapital;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private List<String> estados;
    private List<String> capitais;
    private String resposta;
    private int pontos;
    private int qtdPerguntas;
    private Boolean perguntaEmProgresso;

    private void gerarEstados () {
        estados = new ArrayList<String>();
        capitais = new ArrayList<String>();

        // Estado 1
        estados.add("Parana");
        capitais.add("curitiba");

        // Estado 2
        estados.add("Amazonas");
        capitais.add("manaus");

        // Estado 3
        estados.add("Bahia");
        capitais.add("salvador");

        // Estado 4
        estados.add("Pernambuco");
        capitais.add("recife");

        // Estado 5
        estados.add("Sergipe");
        capitais.add("aracaju");

        // Estado 6
        estados.add("Tocantins");
        capitais.add("palmas");

        // Estado 7
        estados.add("Roraima");
        capitais.add("boa vista");

        // Estado 8
        estados.add("Mato Grosso do Sul");
        capitais.add("campo grande");

        // Estado 9
        estados.add("Minas Gerais");
        capitais.add("belo horizonte");

        // Estado 10
        estados.add("Acre");
        capitais.add("rio branco");

        // Estado 11
        estados.add("Rio Grande do Sul");
        capitais.add("porto alegre");

        // Estado 12
        estados.add("Rio Grande do Norte");
        capitais.add("natal");

        // Estado 13
        estados.add("Rio de Janeiro");
        capitais.add("rio de janeiro");

        // Estado 14
        estados.add("Sao Paulo");
        capitais.add("sao paulo");

        // Estado 15
        estados.add("Santa Catarina");
        capitais.add("florianopolis");
    }

    private void criarPergunta () {

        // Gera um Estado e Capital Aleatórios
        Random rand = new Random();
        int randomNumber = rand.nextInt(this.estados.size());

        // Preenche o nome do estado.
        TextView nomeEstado  = findViewById(R.id.nomeEstado);
        nomeEstado.setText( estados.get(randomNumber) );

        // Preenche o campo que recebe respostas.
        EditText inputCapital = findViewById(R.id.campoCapital);
        inputCapital.setText("");

        // Preenche a resposta esperada na variável.
        resposta = capitais.get(randomNumber);
    }

    public void ajustarPontos () {
        TextView pontuacaoAtual = findViewById(R.id.pontuacaoAtual);
        pontuacaoAtual.setText(getString(R.string.pontuacao_atual) + " " + String.valueOf(this.pontos));
    }

    public void confirmarResposta(View view) {

        // Receber resposta do usuário
        EditText inputCapital = findViewById(R.id.campoCapital);
        String respostaRecebida = inputCapital.getText().toString();

        // Muda para lowercase
        respostaRecebida = respostaRecebida.toLowerCase();

        // Texto a ser alterado dependendo do resultado
        TextView resultadoPergunta = findViewById(R.id.resultadoPergunta);

        System.out.println("Resposta Recebida : " + respostaRecebida);
        System.out.println("Resposta Esperada : " + this.resposta);
        System.out.println("Equals : " + respostaRecebida.equals(this.resposta));

        if ( respostaRecebida.equals(this.resposta) ) {
            // Resposta certa
            resultadoPergunta.setText("Acertou!");
            this.pontos += 10;
            this.ajustarPontos();

        } else {
            // Resposta errada
            resultadoPergunta.setText("Resposta incorreta...");
        }

        // Atualiza a quantidade de perguntas que já foram feitas, e permite ir a proxima.
        this.qtdPerguntas++;
        this.perguntaEmProgresso = false;

    }

    public void proximaPergunta(View view) {
        // Não permite ir a próxima pergunta enquanto não responder a atual.
        if (this.perguntaEmProgresso)
            return;

        // Reseta "Aguardando resposta..."
        TextView resultadoPergunta = findViewById(R.id.resultadoPergunta);
        resultadoPergunta.setText(getString(R.string.resultado_pergunta));


        // Só cria uma nova pergunta se não tiverem sido feitas 5.
        if (this.qtdPerguntas < 5)
            this.criarPergunta();

        this.perguntaEmProgresso = true;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Seta variável inicial.
        this.pontos = 0;
        this.qtdPerguntas = 0;
        this.perguntaEmProgresso = true;
        this.ajustarPontos();

        // Gera os Estados na Lista.
        this.gerarEstados();

        // Cria uma pergunta.
        this.criarPergunta();


    }
}