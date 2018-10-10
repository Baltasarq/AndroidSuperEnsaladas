package com.devbaltasarq.superensaladas.ui;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.devbaltasarq.superensaladas.R;
import com.devbaltasarq.superensaladas.core.Ensalada;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        this.setContentView( R.layout.activity_main );

        final Button btEligeTipo = this.findViewById( R.id.btEligeTipo );
        final Button btEligeSalsa = this.findViewById( R.id.btEligeSalsa );
        final Button btEligeEspecial = this.findViewById( R.id.btEligeEspecial );

        // Establecer los gestores de eventos
        btEligeTipo.setOnClickListener( (v) -> this.seleccionaTipo() );
        btEligeSalsa.setOnClickListener( (v) -> this.seleccionaSalsa() );
        btEligeEspecial.setOnClickListener( (v) -> this.seleccionaEspecial() );

        // Mostrar las selecciones y el total
        this.ensalada = new Ensalada();
        this.actualizaTodo();
    }

    /** Selecciona el tipo de ensalada. */
    private void seleccionaTipo()
    {
        final Ensalada.TipoEnsalada[] values = Ensalada.TipoEnsalada.values();
        final AlertDialog.Builder dlg = new AlertDialog.Builder( this );
        final String[] strValues = new String[ values.length ];

        int i = 0;
        for(Ensalada.TipoEnsalada tipoEnsalada: values) {
            strValues[ i ] = tipoEnsalada.toString();
            ++i;
        }

        dlg.setTitle( "Tipo de ensalada" );
        dlg.setItems( strValues, (d, opc) -> {
            this.ensalada.setTipoEnsalada( values[ opc ] );
            this.actualizaTipoEnsalada();
            this.actualizaFinal();
            this.actualizaTotal();
        });

        dlg.create().show();
    }

    /** Selecciona la salsa. */
    private void seleccionaSalsa()
    {
        final Ensalada.Salsa[] values = Ensalada.Salsa.values();
        final AlertDialog.Builder dlg = new AlertDialog.Builder( this );
        final String[] strValues = new String[ values.length ];

        int i = 0;
        for(Ensalada.Salsa salsa: values) {
            strValues[ i ] = salsa.toString();
            ++i;
        }

        dlg.setTitle( "Tipo de ensalada" );
        dlg.setItems( strValues, (d, opc) -> {
            this.ensalada.setSalsa( values[ opc ] );
            this.actualizaSalsa();
            this.actualizaFinal();
            this.actualizaTotal();
        });

        dlg.create().show();
    }

    /** Selecciona los condimentos. */
    private void seleccionaEspecial()
    {
        final AlertDialog.Builder dlg = new AlertDialog.Builder( this );

        dlg.setTitle( "Condimentos" );
        dlg.setMultiChoiceItems(Ensalada.CONDIMENTO,
                                this.ensalada.getCondimentosSeleccionados(),
                                (d, opc, b) -> {
                                    this.ensalada.setCondimentoSeleccionado( opc, b );
                                    this.actualizaEspecial();
                                    this.actualizaFinal();
                                    this.actualizaTotal();
                                });

        dlg.create().show();
    }

    /** Actualiza todas las posibles selecciones. */
    private void actualizaTodo()
    {
        this.actualizaTipoEnsalada();
        this.actualizaSalsa();
        this.actualizaEspecial();
        this.actualizaFinal();
        this.actualizaTotal();
    }

    /** Actualiza el tipo de ensalada. */
    private void actualizaTipoEnsalada()
    {
        final TextView lblTipo = this.findViewById( R.id.lblTipo );

        lblTipo.setText( this.ensalada.getTipoEnsalada().toString() );
    }

    /** Actualiza la salsa. */
    private void actualizaSalsa()
    {
        final TextView lblTipo = this.findViewById( R.id.lblSalsa );

        lblTipo.setText( this.ensalada.getSalsa().toString() );
    }

    /** Actualiza los condimentos. */
    private void actualizaEspecial()
    {
        final TextView lblEspecial = this.findViewById( R.id.lblEspecial );

        lblEspecial.setText( this.ensalada.getCondimentos() );
    }

    /** Muestra la ensalada seleccionada final. */
    private void actualizaFinal()
    {
        final TextView lblFinal = this.findViewById( R.id.lblFinal );

        lblFinal.setText( this.ensalada.toString() );
    }

    /** Muestra el precio final. */
    private void actualizaTotal()
    {
        final TextView lblTotal = this.findViewById( R.id.lblTotal );


        lblTotal.setText( String.format(
                            Locale.getDefault(),
                     "%05.2f€",
                            this.ensalada.calculaPrecio() ) );
    }

    private Ensalada ensalada;
}
