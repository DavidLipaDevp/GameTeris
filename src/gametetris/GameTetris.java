
package gametetris;
import java.util.Scanner;
public class GameTetris {
    
    // unicos movimientos
    public enum Movement {
        DOWN, RIGHT, LEFT, ROTATE;

    }

    public static void main(String[] args) {
        
        //matriz inicial
        String screen[][] = {{"🔳", "🔲", "🔲", "🔲", "🔲", "🔲", "🔲", "🔲", "🔲", "🔲"},
        {"🔳", "🔳", "🔳", "🔲", "🔲", "🔲", "🔲", "🔲", "🔲", "🔲"},
        {"🔲", "🔲", "🔲", "🔲", "🔲", "🔲", "🔲", "🔲", "🔲", "🔲"},
        {"🔲", "🔲", "🔲", "🔲", "🔲", "🔲", "🔲", "🔲", "🔲", "🔲"},
        {"🔲", "🔲", "🔲", "🔲", "🔲", "🔲", "🔲", "🔲", "🔲", "🔲"},
        {"🔲", "🔲", "🔲", "🔲", "🔲", "🔲", "🔲", "🔲", "🔲", "🔲"},
        {"🔲", "🔲", "🔲", "🔲", "🔲", "🔲", "🔲", "🔲", "🔲", "🔲"},
        {"🔲", "🔲", "🔲", "🔲", "🔲", "🔲", "🔲", "🔲", "🔲", "🔲"},
        {"🔲", "🔲", "🔲", "🔲", "🔲", "🔲", "🔲", "🔲", "🔲", "🔲"},
        {"🔲", "🔲", "🔲", "🔲", "🔲", "🔲", "🔲", "🔲", "🔲", "🔲"}
        };

        Imprimir(screen);
        int rotation = 0;
        Scanner Key=new Scanner(System.in);
        String  tecla=Key.nextLine();
       while (true) {
            if(tecla.equalsIgnoreCase("t")){
            break;
            }else if(tecla.equalsIgnoreCase("d")){
              screen = move_piece(screen, Movement.RIGHT, rotation);
              tecla=Key.nextLine();
            }else if(tecla.equalsIgnoreCase("a")){
              screen = move_piece(screen, Movement.LEFT, rotation);
              tecla=Key.nextLine();
            }else if(tecla.equalsIgnoreCase("s")){
              screen = move_piece(screen, Movement.DOWN, rotation);
              tecla=Key.nextLine();
            }else if(tecla.equalsIgnoreCase("w")){
              screen = move_piece(screen, Movement.ROTATE, rotation);
              tecla=Key.nextLine();
            rotation=(rotation == 3)?0:rotation+1;
            }else{
            break;
            }
        }
       
       
    }
    //imprimir de matriz 2d
    public static void Imprimir(String[][] screen) {
        System.out.println("TETRIS GAME");
        for (int i = 0; i < screen.length; i++) {
            for (int j = 0; j < screen.length; j++) {
                System.out.print(screen[i][j] + " ");
            }
            System.out.println(" ");
        }
    }
    /*parametros :  matriz, movimientos permitidos , rotacion de pieza String[][] item, Movement movement, int rotation
    Resultado : Matriz new_screen
    funcion : Mover pieza de tetris ,insertar las partes de la pieza a la nueva matriz new_screen
      
    */

    public static String[][] move_piece(String[][] item, Movement movement, int rotation) {

        String new_screen[][] = {{"🔲", "🔲", "🔲", "🔲", "🔲", "🔲", "🔲", "🔲", "🔲", "🔲"},
        {"🔲", "🔲", "🔲", "🔲", "🔲", "🔲", "🔲", "🔲", "🔲", "🔲"},
        {"🔲", "🔲", "🔲", "🔲", "🔲", "🔲", "🔲", "🔲", "🔲", "🔲"},
        {"🔲", "🔲", "🔲", "🔲", "🔲", "🔲", "🔲", "🔲", "🔲", "🔲"},
        {"🔲", "🔲", "🔲", "🔲", "🔲", "🔲", "🔲", "🔲", "🔲", "🔲"},
        {"🔲", "🔲", "🔲", "🔲", "🔲", "🔲", "🔲", "🔲", "🔲", "🔲"},
        {"🔲", "🔲", "🔲", "🔲", "🔲", "🔲", "🔲", "🔲", "🔲", "🔲"},
        {"🔲", "🔲", "🔲", "🔲", "🔲", "🔲", "🔲", "🔲", "🔲", "🔲"},
        {"🔲", "🔲", "🔲", "🔲", "🔲", "🔲", "🔲", "🔲", "🔲", "🔲"},
        {"🔲", "🔲", "🔲", "🔲", "🔲", "🔲", "🔲", "🔲", "🔲", "🔲"}};
        int rotation_position = 0;
        //rotacion de unica pieza del ejecutable
        int[][][] rotations = {{{1,1},{0,0},{-2,0},{-1,-1}},
                                {{0,1},{-1,0},{0,-1},{1,-2}},
                                {{0,2},{1,1},{-1,1},{-2,0}},
                                {{0,1},{1,0},{2,-1},{1,-2}}};

        int new_rotation=0;
        
        if (movement.equals( Movement.ROTATE )){
            new_rotation =(rotation == 3)?0:rotation+1;
        }

        for (int i = 0; i < item.length; i++) {
            for (int j = 0; j < item.length; j++) {

                if (item[i][j] == "🔳") {
                    int new_row_index = 0;
                    int new_column_index = 0;
                    switch (movement) {
                        case DOWN:
                            new_row_index = i + 1;
                            new_column_index = j;
                            break;
                        case RIGHT:
                            new_row_index = i;
                            new_column_index = j + 1;
                            break;
                        case LEFT:
                            new_row_index = i;
                            new_column_index = j - 1;
                            break;
                        case ROTATE:
                            new_row_index = i + rotations[new_rotation][rotation_position][0];
                            new_column_index = j + rotations[new_rotation][rotation_position][1];
                            rotation_position ++;
                            
                            break;

                    }
                    if (new_row_index > 9 || new_column_index > 9 || new_column_index < 0) {
                        System.out.println("No se puede realizar el movimiento");
                        return item;
                    } else {
                        new_screen[new_row_index][new_column_index] = "🔳";
                    }

                }
            }
        }
        Imprimir(new_screen);

        return (new_screen);
    }
}
