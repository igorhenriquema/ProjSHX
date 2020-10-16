package br.com.botecoHaoba.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

import br.com.botecoHaoba.gui.grid.MesasColumnModel;
import br.com.botecoHaoba.gui.grid.MesasTableModel;
import br.com.botecoHaoba.model.entidades.Comanda;
import br.com.botecoHaoba.model.entidades.ItemComanda;
import br.com.botecoHaoba.model.facade.BotecoFacade;

public class FrmBoteco extends JFrame {


   private final BotecoFacade     facade                = new BotecoFacade();



   private final MesasColumnModel columnModel           = new MesasColumnModel();
   private final MesasTableModel  tableModel            = new MesasTableModel( facade, columnModel );
   private final JTable           gridMesas             = new JTable( tableModel, columnModel, null );
   private final JScrollPane      pane                  = new JScrollPane( gridMesas );



   private final JButton          botaoAdicionarComanda = new JButton( "Adicionar comanda" );
   private final JButton          botaoRemoverComanda   = new JButton( "Remover comanda" );
   private final JButton          botaoAdicionarItem    = new JButton( "Adicionar item" );

   public FrmBoteco() {

      setTitle( "Boteco Haoba!" );
      setLayout( null );
      setSize( 800, 600 );
      setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
      setResizable( false );

      pane.setSize( 770, 500 );
      pane.setLocation( 0, 0 );

      adicionaColunasGrid();

      gridMesas.getTableHeader().setReorderingAllowed( false );


      add( pane );


      configuraBotoes();

      add( botaoAdicionarComanda );
      add( botaoRemoverComanda );
      add( botaoAdicionarItem );

      atualizaTitulo();
   }


   private void atualizaTitulo() {

      setTitle( "Boteco Haoba! ## Mesas: " + facade.getQuantidadeMesas() + " ## Clientes: " + facade.getQuantidadeClientes() + " ## Itens sendo consumidos " + facade.getQuantidadeItensConsumo() );
   }

   private void configuraBotoes() {

      botaoAdicionarComanda.setSize( 140, 30 );
      botaoAdicionarComanda.setLocation( 1, 520 );
      botaoAdicionarComanda.addActionListener( new ActionAdicionarComanda() );

      botaoRemoverComanda.setSize( 140, 30 );
      botaoRemoverComanda.setLocation( 140, 520 );
      botaoRemoverComanda.addActionListener( new ActionRemoverComanda() );

      botaoAdicionarItem.setSize( 140, 30 );
      botaoAdicionarItem.setLocation( 630, 520 );
      botaoAdicionarItem.addActionListener( new ActionAdicionarItem() );
   }


   private void adicionaColunasGrid() {

      columnModel.addColumn( criaColuna( "Mesa", 40 ) );
      columnModel.addColumn( criaColuna( "Cliente", 260 ) );
      columnModel.addColumn( criaColuna( "Pessoas na mesa", 120 ) );
      columnModel.addColumn( criaColuna( "Itens consumidos", 110 ) );
      columnModel.addColumn( criaColuna( "Valor total", 110 ) );
      columnModel.addColumn( criaColuna( "Comissão garçom", 120 ) );
   }


   private TableColumn criaColuna( String titulo, int tamanho ) {

      TableColumn column = new TableColumn();
      column.setHeaderValue( titulo );
      column.setResizable( false );
      column.setWidth( tamanho );
      column.setMaxWidth( tamanho );
      column.setMinWidth( tamanho );
      column.setModelIndex( columnModel.getColumnCount() );
      return column;
   }

   private class ActionAdicionarComanda implements ActionListener {

      @Override
      public void actionPerformed( ActionEvent e ) {

         try {
            DlgAdicionarComanda dialog = new DlgAdicionarComanda(facade);
            dialog.setVisible( true );

            if ( !dialog.pressionouOk() ) {
               return;
            }

            Comanda comanda = dialog.getComanda();

            facade.addComanda( comanda );

            facade.calculaValorTotal( comanda );
            facade.calculaValorComissao( comanda );

            tableModel.fireTableDataChanged();

            atualizaTitulo();
         }
         catch ( Throwable ex ) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog( botaoRemoverComanda, ex.getLocalizedMessage() );
         }
      }

   }

   private class ActionRemoverComanda implements ActionListener {

      @Override
      public void actionPerformed( ActionEvent e ) {

         try {
            Comanda comanda = facade.getComandas().get( gridMesas.getSelectedRow() );


            facade.remove( comanda );
            tableModel.fireTableDataChanged();

            atualizaTitulo();
         }
         /*catch ( Throwable ex ) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog( botaoRemoverComanda, ex.getLocalizedMessage() );
         }*/
         catch ( Exception ex ) {
             JOptionPane.showMessageDialog(null,"Favor insira uma comanda antes de tentar excluir! ");
         }
      }

   }

   private class ActionAdicionarItem implements ActionListener {

      @Override
      public void actionPerformed( ActionEvent e ) {

         try {
            Comanda comanda = facade.getComandas().get( gridMesas.getSelectedRow() );


            DlgAdicionarItem dialog = new DlgAdicionarItem();
            dialog.setVisible( true );

            if ( !dialog.pressionouOk() ) {
               return;
            }

            ItemComanda itemComanda = dialog.itemComanda;

            comanda.addItem( itemComanda );
            facade.calculaValorTotal( comanda );
            facade.calculaValorComissao( comanda );
            tableModel.fireTableDataChanged();
            
            //System.out.println("Valor: " + comanda.getValorTotal()); 

            
            atualizaTitulo();
         }
         catch ( Exception ex ) {
             JOptionPane.showMessageDialog(null,"Favor selecione ou insira uma comanda antes de tentar inserir um item ! ");
         }

      }

   }

}
