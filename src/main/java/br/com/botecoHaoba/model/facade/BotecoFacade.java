package br.com.botecoHaoba.model.facade;

import java.util.ArrayList;
import java.util.List;


import br.com.botecoHaoba.model.entidades.Comanda;

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


      comanda.setValorTotal( 0 );
   }

   public void calculaValorComissao( Comanda comanda ) {


      comanda.setValorTotal( 0 );
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
