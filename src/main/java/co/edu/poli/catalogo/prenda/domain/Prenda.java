package co.edu.poli.catalogo.prenda.domain;

import java.math.BigDecimal;

/**
 * Prenda del catálogo. Modelo de negocio puro (sin Spring ni JPA).
 * La talla se guarda como texto con el rango disponible, por ejemplo "S-XL" o "28-36".
 */
public record Prenda(
        Long id,
        String nombre,
        String categoria,
        String genero,
        String talla,
        String color,
        String material,
        String marca,
        BigDecimal precio,
        int stock,
        String imagenUrl,
        String descripcion) {

    public boolean admiteTalla(String solicitada) {
        if (solicitada == null || solicitada.isBlank()) return false;
        String valor=solicitada.trim();
        if (talla.equalsIgnoreCase("Única")) return valor.equalsIgnoreCase("Única");
        String[] rango=talla.split("-");
        if (rango.length!=2) return talla.equalsIgnoreCase(valor);
        try {
            int numero=Integer.parseInt(valor);
            return numero>=Integer.parseInt(rango[0]) && numero<=Integer.parseInt(rango[1]);
        } catch (NumberFormatException ignored) {
            java.util.List<String> orden=java.util.List.of("XXS","XS","S","M","L","XL","XXL","XXXL");
            int min=orden.indexOf(rango[0].toUpperCase());
            int max=orden.indexOf(rango[1].toUpperCase());
            int actual=orden.indexOf(valor.toUpperCase());
            return min>=0 && max>=min && actual>=min && actual<=max;
        }
    }
}
