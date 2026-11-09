package proyecto.pkgfinal.recursividad.pkg280736;
public class PROYECTOFINALRECURSIVIDAD280736 {
    public static void main(String[] args) {
          
   String datocuatro = "7+10+2"    ;
   int resultadocuatro = leerstring(datocuatro, 0, datocuatro.length() - 1);
   System.out.println(resultadocuatro);     
        
   String datodos = "1+5*(7-1)+10";
   int resultadodos = leerstring(datodos, 0, datodos.length() - 1);
   System.out.println(resultadodos);
     
   String datotres = "5+7+(1+2*(8-9)+2)-10"   ;
   int resultadotres = leerstring(datotres, 0, datotres.length() - 1);
   System.out.println(resultadotres);
     
   String dato =  "9-2*(75-9+(6+3+(1+2)*1)+7)+9"   ;
   int resultado = leerstring(dato, 0, dato.length() - 1);
   System.out.println(resultado);
   
   String datoerror =  "10+9-A"  ;
   int resultadoerror = leerstring(datoerror, 0, datoerror.length() - 1);
   System.out.println(resultadoerror);
    
   String datoerrordos =  "74-7/7" ;
   int resultadoerrordos = leerstring(datoerrordos, 0, datoerrordos.length() - 1);
   System.out.println(resultadoerrordos);
   
   String datoerrortres =  "-5+10" ;
   int resultadoerrortres = leerstring(datoerrortres, 0, datoerrortres.length() - 1);
   System.out.println(resultadoerrortres);
   
   String datoerrorcuatro = "5++1+10" ;
   int resultadoerrorcuatro = leerstring(datoerrorcuatro, 0, datoerrorcuatro.length() - 1);
   System.out.println(resultadoerrorcuatro);
   
    }
   public static boolean leerstringRecursivo(String dato, int inicio, int fin, int parentesis, boolean esperandoNumero) {

    if (inicio > fin) {
        return parentesis == 0 && !esperandoNumero;
    }

    char character = dato.charAt(inicio);

    if (Character.isDigit(character)) {

        if (!esperandoNumero && inicio > 0 &&
                dato.charAt(inicio - 1) == ')') {
            return false;
        }

        return leerstringRecursivo(dato, inicio + 1, fin, parentesis, false);

    } else if (character == '+' || character == '-' || character == '*') {

        if (esperandoNumero) {
            return false;
        }

        return leerstringRecursivo(dato, inicio + 1, fin, parentesis, true);

    } else if (character == '(') {

        if (!esperandoNumero) {
            return false;
        }

        return leerstringRecursivo(dato, inicio + 1, fin, parentesis + 1, true);

    } else if (character == ')') {

        if (esperandoNumero || parentesis == 0) {
            return false;
        }

        return leerstringRecursivo(dato, inicio + 1, fin, parentesis - 1, false);
    }

    return false;
}
    
   public static int leerstring(String dato, int inicio, int fin) {

    boolean validar = leerstringRecursivo(dato, inicio, fin, 0, true);

    if (!validar) {
        System.out.println("OPERACION NO VALIDA");
        return 0;
    }

    int[] posicion = {inicio};

    return resolveroperacion(dato, posicion);
}
   public static int resolveroperacion(String dato, int[] posicion){
  
    int resultado = 0;
    int termino = 0;
    int signo = 1;
    boolean esperandoNumero = true;
    char operador = '+';
    
    if (dato == null || dato.isEmpty()) {
    throw new IllegalArgumentException("OPERACION NO VALIDA");
}

    while (posicion[0] < dato.length()) {

        char character = dato.charAt(posicion[0]);

        if (esperandoNumero) {

            int numero = 0;

            if (character == '(') {

                posicion[0]++;
                numero = resolveroperacion(dato, posicion);

                if (posicion[0] >= dato.length()
                        || dato.charAt(posicion[0]) != ')') {
                    throw new IllegalArgumentException("PARENTESIS NO VALIDO");
                }

                posicion[0]++;

            } else if (Character.isDigit(character)) {

                while (posicion[0] < dato.length()
                        && Character.isDigit(dato.charAt(posicion[0]))) {

                    numero = numero * 10
                            + Character.getNumericValue(dato.charAt(posicion[0]));

                    posicion[0]++;
                }

            } else {
              System.out.println("Caracter incorrecto: " + character);
              System.out.println("Posicion: " + posicion[0]);
              System.out.println("OPERACION INVALIDA" );
            }

            if (operador == '*') {
                termino *= numero;
            } else {
                termino = numero;
            }

            esperandoNumero = false;

        } else {

            if (character == '*') {

                operador = '*';
                esperandoNumero = true;
                posicion[0]++;

            } else if (character == '+' || character == '-') {

                resultado += signo * termino;

                signo = (character == '+') ? 1 : -1;
                operador = character;

                esperandoNumero = true;
                posicion[0]++;

            } else if (character == ')') {

                break;

            } else {
                throw new IllegalArgumentException("OPERACION NO VALIDA");
            }
        }
    }

    if (esperandoNumero) {
        throw new IllegalArgumentException("OPERACION NO VALIDA");
    }

    resultado += signo * termino;

    return resultado;
   }
   

}
