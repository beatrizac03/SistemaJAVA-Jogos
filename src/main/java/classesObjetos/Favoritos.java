package classesObjetos;

import java.util.ArrayList;
import java.util.List;

public class Favoritos {
    private static List<Jogo> jogosFavoritos = new ArrayList<>();
    private static List<FavoritosListener> listeners = new ArrayList<>();

    public static void adicionarAosFavoritos(Jogo jogo) {
        jogosFavoritos.add(jogo);
        for (FavoritosListener listener : listeners) {
            listener.favoritosAtualizados(jogosFavoritos);
        }
    }

    public static List<Jogo> getJogosFavoritos() {
        return new ArrayList<>(jogosFavoritos);
    }

    public static void adicionarListener(FavoritosListener listener) {
        listeners.add(listener);
    }

    public interface FavoritosListener {
        void favoritosAtualizados(List<Jogo> favoritos);
    }

}
