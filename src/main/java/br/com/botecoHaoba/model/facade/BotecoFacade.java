package br.com.botecoHaoba.model.facade;

import java.util.ArrayList;
import java.util.List;

import br.com.botecoHaoba.model.entidades.Cardapio;
import br.com.botecoHaoba.model.entidades.Comanda;
import br.com.botecoHaoba.model.entidades.ItemComanda;

public class BotecoFacade {


   private List<Comanda> comandas = new ArrayList<Comanda>();

   public void addComanda( Comanda comanda ) {
	   
      calculaValorTotal( comanda );
      calculaValorComissao( comanda );
      this.comandas.add( comanda );
   }

   public List<Comanda> getComandas() {

      return new ArrayList<>( comandas );
   }

   public void remove( Comanda comanda ) {

      comandas.remove( comanda );
   }


   public void calculaValorTotal( Comanda comanda ) {
	   
	   comanda.getValorTotal();
   }

   public void calculaValorComissao( Comanda comanda ) {

	  
	  double valor = 0;
	  double porc = 0;
	  
	  for (ItemComanda item : comanda.getItensComanda()){
		  
          if (item.getItem().getDescricao() == Cardapio.CERVEJA_PURO_MALTE.getDescricao()) {
          porc = 0.076;
          }
          else if (item.getItem().getDescricao() == Cardapio.CERVEJA_PURO_MILHO.getDescricao()) {
        	  porc = 0.065;
          }
          else if (item.getItem().getDescricao() == Cardapio.TACA_VINHO.getDescricao()) {
        	  porc = 0.09;
          }
          else if (item.getItem().getDescricao() == Cardapio.REFRIGERANTE_LATA.getDescricao()) {
        	  porc = 0.08;
          }          
          else if (item.getItem().getDescricao() == Cardapio.AGUA.getDescricao()) {
        	  porc = 0.025;
          }          
          else if (item.getItem().getDescricao() == Cardapio.BOLINHO_BACALHAU.getDescricao()) {
        	  porc = 0.095;
          }          
          else if (item.getItem().getDescricao() == Cardapio.BATATA_FRITA.getDescricao()) {
        	  porc = 0.065;
          }          
          else if (item.getItem().getDescricao() == Cardapio.SALADA.getDescricao()) {
        	  porc = 0.055;
          }
          else if (item.getItem().getDescricao() == Cardapio.PORCAO_FRANGO.getDescricao()) {
        	  porc = 0.074;
          }
          else if (item.getItem().getDescricao() == Cardapio.SORVETE.getDescricao()) {
        	  porc = 0.032;
          }
          
          valor = valor + ((item.getItem().getPreco() * item.getQuantidade()) * porc);
          
      }
	  comanda.setValorComissao( valor );
   }

   public int getQuantidadeItensConsumo() {
	   
	   int i;
	   int cont = 0;
	   int n = comandas.size();
	   	  for (i = 0; i < n; i++) {
	   		  cont = cont + comandas.get(i).getQuantidadeItensConsumidos();
	   	  }
	   	  return cont;
   }

   public int getQuantidadeClientes() {

	   int i;
	   int cont = 0;
	   int n = comandas.size();
	   	  for (i = 0; i < n; i++) {
	   		  cont = cont + comandas.get(i).getQuantidadePessoasMesa();
	   	  }
	   	  return cont;
   }

   public int getQuantidadeMesas() {

	   int i;
	   int cont = 0;
	   int n = comandas.size();
	   	  for (i = 0; i < n; i++) {
	   		  cont ++;
	   	  }
	   	  return cont;
   }

}
