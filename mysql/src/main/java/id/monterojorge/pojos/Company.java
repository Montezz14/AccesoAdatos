package id.monterojorge.pojos;

/**
 * ACTIVIDAD 4.6 - POJO que representa una compaÃ±Ã­a.
 * 
 * Esta clase encapsula los datos de una empresa con sus principales atributos:
 * - CIF: CÃ³digo de IdentificaciÃ³n Fiscal (8 nÃºmeros + 1 letra)
 * - NOMBRE: RazÃ³n social de la compaÃ±Ã­a
 * - SECTOR: Sector econÃ³mico al que pertenece
 * 
 * @author monterojorge
 * @version 1.0
 */
public class Company {
    
    // Atributos de la compaÃ±Ã­a
    private String cif;     // CIF: 8 nÃºmeros + 1 letra (ej: "12345678A")
    private String nombre;  // Nombre de la compaÃ±Ã­a
    private String sector;  // Sector al que se dedica la compaÃ±Ã­a
    
    /**
     * Constructor por defecto.
     * Necesario para frameworks que requieren instanciaciÃ³n sin parÃ¡metros.
     */
    public Company() {
    }
    
    /**
     * Constructor con todos los parÃ¡metros.
     * 
     * @param cif CIF de la compaÃ±Ã­a (8 nÃºmeros + 1 letra)
     * @param nombre Nombre de la compaÃ±Ã­a
     * @param sector Sector al que pertenece la compaÃ±Ã­a
     */
    public Company(String cif, String nombre, String sector) {
        this.cif = cif;
        this.nombre = nombre;
        this.sector = sector;
    }
    
    // === GETTERS Y SETTERS ===
    
    /**
     * Obtiene el CIF de la compaÃ±Ã­a.
     * 
     * @return CIF de la compaÃ±Ã­a
     */
    public String getCif() {
        return cif;
    }
    
    /**
     * Establece el CIF de la compaÃ±Ã­a.
     * 
     * @param cif CIF de la compaÃ±Ã­a (debe seguir formato: 8 nÃºmeros + 1 letra)
     */
    public void setCif(String cif) {
        this.cif = cif;
    }
    
    /**
     * Obtiene el nombre de la compaÃ±Ã­a.
     * 
     * @return Nombre de la compaÃ±Ã­a
     */
    public String getNombre() {
        return nombre;
    }
    
    /**
     * Establece el nombre de la compaÃ±Ã­a.
     * 
     * @param nombre Nombre de la compaÃ±Ã­a
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    /**
     * Obtiene el sector de la compaÃ±Ã­a.
     * 
     * @return Sector de la compaÃ±Ã­a
     */
    public String getSector() {
        return sector;
    }
    
    /**
     * Establece el sector de la compaÃ±Ã­a.
     * 
     * @param sector Sector de la compaÃ±Ã­a
     */
    public void setSector(String sector) {
        this.sector = sector;
    }
    
    // === MÃ‰TODOS UTILITARIOS ===
    
    /**
     * Valida si el CIF tiene un formato correcto (8 nÃºmeros + 1 letra).
     * 
     * @return true si el CIF es vÃ¡lido, false en caso contrario
     */
    public boolean isValidCif() {
        if (cif == null || cif.length() != 9) {
            return false;
        }
        
        // Verificar que los primeros 8 caracteres sean nÃºmeros
        String numeros = cif.substring(0, 8);
        if (!numeros.matches("\\d{8}")) {
            return false;
        }
        
        // Verificar que el Ãºltimo carÃ¡cter sea una letra
        char letra = cif.charAt(8);
        return Character.isLetter(letra);
    }
    
    /**
     * Verifica si todos los campos obligatorios estÃ¡n completados.
     * 
     * @return true si todos los campos estÃ¡n completos, false en caso contrario
     */
    public boolean isComplete() {
        return cif != null && !cif.trim().isEmpty() &&
               nombre != null && !nombre.trim().isEmpty() &&
               sector != null && !sector.trim().isEmpty();
    }
    
    /**
     * RepresentaciÃ³n en cadena de la compaÃ±Ã­a.
     * 
     * @return RepresentaciÃ³n textual de la compaÃ±Ã­a
     */
    @Override
    public String toString() {
        return String.format("Company{CIF='%s', Nombre='%s', Sector='%s'}", 
                           cif, nombre, sector);
    }
    
    /**
     * Compara dos objetos Company por igualdad.
     * Dos compaÃ±Ã­as son iguales si tienen el mismo CIF.
     * 
     * @param obj Objeto a comparar
     * @return true si son iguales, false en caso contrario
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        
        Company company = (Company) obj;
        return cif != null ? cif.equals(company.cif) : company.cif == null;
    }
    
    /**
     * Calcula el hash code basado en el CIF.
     * 
     * @return Hash code del objeto
     */
    @Override
    public int hashCode() {
        return cif != null ? cif.hashCode() : 0;
    }
}
