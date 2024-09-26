package Controller;


import Model.Pessoa;
import View.PessoaView;

public class PessoaController {
 private Pessoa model;
 private PessoaView view;

 public PessoaController(Pessoa model, PessoaView view) {
     this.model = model;
     this.view = view;
 }

 public void setNomePessoa(String nome) {
     model.setNome(nome);
 }

 public String getNomePessoa() {
     return model.getNome();
 }

 public void setIdadePessoa(int idade) {
     model.setIdade(idade);
 }

 public int getIdadePessoa() {
     return model.getIdade();
 }

 public void atualizarView() {
     view.exibirDetalhesPessoa(model.getNome(), model.getIdade());
 }
}

