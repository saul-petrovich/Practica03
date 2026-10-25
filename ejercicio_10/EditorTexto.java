public class EditorTexto {
    private StringBuilder contenido;
    private StringBuilder anterior;

    public EditorTexto() {
        contenido = new StringBuilder();
        anterior = null;
    }

    public void escribir(String texto) {
        StringBuilder nuevo = new StringBuilder(contenido.toString());
        nuevo.append(texto);
        guardarSiCambio(nuevo);
    }

    public void insertar(int posicion, String texto) {
        if (!valido(posicion, posicion)) return;
        StringBuilder nuevo = new StringBuilder(contenido.toString());
        nuevo.insert(posicion, texto);
        guardarSiCambio(nuevo);
    }

    public void eliminar(int inicio, int fin) {
        if (!valido(inicio, fin)) return;
        StringBuilder nuevo = new StringBuilder(contenido.toString());
        nuevo.delete(inicio, fin);
        guardarSiCambio(nuevo);
    }

    public void reemplazar(int inicio, int fin, String texto) {
        if (!valido(inicio, fin)) return;
        StringBuilder nuevo = new StringBuilder(contenido.toString());
        nuevo.replace(inicio, fin, texto);
        guardarSiCambio(nuevo);
    }

    public void deshacer() {
        if (anterior == null) return;
        contenido = anterior;
        anterior = null;
    }

    public String obtenerTexto() {
        return contenido.toString();
    }

    private boolean valido(int inicio, int fin) {
        return inicio >= 0 && inicio <= fin && fin <= contenido.length();
    }

    private void guardarSiCambio(StringBuilder nuevo) {
        if (nuevo.toString().equals(contenido.toString())) return;
        anterior = contenido;
        contenido = nuevo;
    }
}