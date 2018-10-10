package com.devbaltasarq.superensaladas.core;

import java.util.Arrays;


public class Ensalada {
    public enum TipoEnsalada {
        MEDITERRANEA {
            public String toString()
            {
                return "Mediterránea";
            }
        }, CESAR {
            public String toString()
            {
                return "César";
            }
        }, PASTA {
            public String toString()
            {
                return "Pasta";
            }
        }}
    public enum Salsa {
        ACEITE_VINAGRE {
            public String toString()
            {
                return "Aceite y vinagre";
            }
        }, CESAR {
            public String toString()
            {
                return "César";
            }
        }, ROSA {
            public String toString()
            {
                return "Rosa";
            }
        }}

    public static final double[] PRECIOS_TIPO_ENSALADA = { 3.0, 5.0, 4.0 };
    public static final double[] PRECIOS_SALSA = { 1.0, 1.5, 1.25 };
    public static final double[] PRECIOS_CONDIMENTOS = { 0.75, 1, 1.5 };
    public static final String[] CONDIMENTO = { "Picatostes", "Queso", "Nueces" };

    public Ensalada()
    {
        // Inicializar los condimentos
        this.condimentosSeleccionados = new boolean[ CONDIMENTO.length ];
        Arrays.fill( this.condimentosSeleccionados, false );

        // Inicializar el resto de selecciones
        this.tipoEnsalada = Ensalada.TipoEnsalada.MEDITERRANEA;
        this.salsa = Ensalada.Salsa.ACEITE_VINAGRE;
    }

    /** @return el tipo de la enslada. */
    public TipoEnsalada getTipoEnsalada()
    {
        return this.tipoEnsalada;
    }

    /** Cambia el tipo de ensalada.
      * @param tipo el nuevo tipo de la ensalada.
      * @see TipoEnsalada
      */
    public void setTipoEnsalada(TipoEnsalada tipo)
    {
        this.tipoEnsalada = tipo;
    }

    /** @return la salsa. */
    public Salsa getSalsa()
    {
        return this.salsa;
    }

    /** Cambia la salsa.
      * @param salsa la nueva salsa
      * @see Salsa
      */
    public void setSalsa(Salsa salsa)
    {
        this.salsa = salsa;
    }

    /** @return un array de booleanos indicando los componentes seleccionados. */
    public boolean[] getCondimentosSeleccionados()
    {
        return Arrays.copyOf( this.condimentosSeleccionados , this.condimentosSeleccionados.length );
    }

    /** Cambia un condimento seleccionado.
      * @param i la pos. del condimento.
      * @param value true si pasa a estar seleccionada, false en otro caso.
      */
    public void setCondimentoSeleccionado(int i, boolean value)
    {
        this.condimentosSeleccionados[ i ] = value;
    }

    /** @return una cadena con todos los condimentos seleccionados. */
    public String getCondimentos()
    {
        final StringBuilder strBuilder = new StringBuilder();

        for(int i = 0; i < this.condimentosSeleccionados.length; ++i) {
            if ( this.condimentosSeleccionados[ i ] ) {
                strBuilder.append( CONDIMENTO[ i ] );
                strBuilder.append( ' ' );
            }
        }

        String toret = strBuilder.toString().trim();

        if ( toret.isEmpty() ) {
            toret = "ninguno";
        }

        return toret;
    }

    /** @return el precio total de la ensalada seleccionada. */
    public double calculaPrecio()
    {
        final boolean[] condimentosSeleccionados = this.getCondimentosSeleccionados();
        double total = 0;

        total += PRECIOS_TIPO_ENSALADA[ this.tipoEnsalada.ordinal() ];
        total += PRECIOS_SALSA[ this.salsa.ordinal() ];

        for(int i = 0; i < condimentosSeleccionados.length; ++i) {
            if ( condimentosSeleccionados[ i ] ) {
                total += PRECIOS_CONDIMENTOS[ i ];
            }
        }

        return total;
    }

    public String toString()
    {
        return String.format( "%s (%s): %s",
                                        this.getTipoEnsalada().toString(),
                                        this.getSalsa().toString(),
                                        this.getCondimentos() );
    }

    private boolean[] condimentosSeleccionados;
    private TipoEnsalada tipoEnsalada;
    private Salsa salsa;
}
