package es.petclinic.models;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;

public class Utils {

    /**
     * 
     * Genera un hash MD5 a partir de la cadena proporcionada.
     * 
     * @param input Cadena original a la que se aplicará el hash MD5.
     * @return Una cadena hexadecimal que representa el hash MD5 generado.
     */
    public static String hashMD5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            StringBuilder hexString = new StringBuilder();

            for (byte b : messageDigest) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error al calcular MD5", e);
        }
    }

    /**
     * 
     * Verifica si una constraseña proporcionada coincide con el hash almacenado.
     * 
     * @param inputPassword Contraseña introducida por el usuario en texto plano.
     * @param storedHash Hash MD5 almacenado previamente para comparación.
     * @return true si la contraseña introducida coincide con el hash almacenado, false en caso contrario.
     */
    public static boolean verifyPassword(String inputPassword, String storedHash) {
        return hashMD5(inputPassword).equalsIgnoreCase(storedHash);
    }

    /**
     * 
     * Genera un hash MD5 para almacenar contraseñas.
     * 
     * @param input Contraseña en texto plano que será convertida a hash.
     * @return Cadena hexadecimal del hash MD5 resultante.
     */
    public static String hashPassword(String input) {
        return hashMD5(input); // Usa el mismo método de hashing que ya tienes
    }

    /**
     * 
     * Valida los campos del formulario de inicio de sesión no estén vacíos.
     * 
     * @param parametros Enumeración de los nombres de parámetros enviados.
     * @param request Objeto HttpServletRequest desde el cual se obtienen los parámetros del formulario.
     * @return "n" si no hay errores, "v" si falta algún campo obligatorio.
     */
    public static String comprobarCamposLogin(Enumeration<String> parametros, HttpServletRequest request) {

        String error = "n";

        // Campos a validar
        String[] camposCriticos = {
            "email", "password"
        };

        // Verificar campos
        for (String campo : camposCriticos) {
            String valor = request.getParameter(campo);
            if (valor == null || valor.trim().isEmpty()) {
                error = "v"; // Campo vacío
                break;
            }
        }

        return error;
    }

    /**
     * 
     * Valida que los campos del formulario de registro estén completos y que las contraseñas coincidan.
     * 
     * @param parametros Enumeración de los nombre de parámetros enviados.
     * @param request Objeto HttpServletRequest desde el cual se obtienen los parámetros del formulario.
     * @return "n" si todo es correcto, "v" si hay algún campo vacío, "c" si las contraseñas no coinciden.
     */
    public static String comprobarCampos(Enumeration<String> parametros, HttpServletRequest request) {
        String error = "n";

        // Lista de campos obligatorios
        String[] camposCriticos = {
            "nombre", "apellidos", "numIdentificacion", "email", "password", "confirmPassword"
        };

        for (String campo : camposCriticos) {
            String valor = request.getParameter(campo);
            if (valor == null || valor.trim().isEmpty()) {
                error = "v"; // Campo vacío
                break;
            }
        }
        
        // Verificar contraseñas si no hay otros errores
        if (error.equals("n")) {
            String password = request.getParameter("password");
            String confirmPassword = request.getParameter("confirmPassword");

            if (!password.equals(confirmPassword)) {
                error = "c"; // Contraseñas no coinciden
            }
        }

        return error;
    }
    
    
    /**
     * 
     * Valida los campos obligatorios del formulario de registro o edición de mascotas estén completos.
     * 
     * @param parametros Enumeración de los nombres de parámetros enviados.
     * @param request Objeto HttpServletRequest desde el cual se obtienen los parámetros del formulario.
     * @return "n" si no hay errores, "v" si falta algún campo obligatorio.
     */
    public static String comprobarCamposMascota(Enumeration<String> parametros, HttpServletRequest request) {
        String error = "n";

        // Lista de campos obligatorios
        String[] camposCriticos = {
            "nombre", "especie", "raza", "peso", "genero"
        };

        for (String campo : camposCriticos) {
            String valor = request.getParameter(campo);
            if (valor == null || valor.trim().isEmpty()) {
                error = "v"; // Campo vacío
                break;
            }
        }

        return error;
    }
    
    
    /**
     * 
     * Valida que los campos del formulario de registro de historial médico estén completos.
     * 
     * @param parametros Enumeración de los nombres de parámetros enviados.
     * @param request Objeto HttpServletRequest desde el cual se obtienen los parámetros del formulario.
     * @return "n" si no hay errores, "v" si falta algún campo obligatorio.
     */
    public static String comprobarCamposHistorial(Enumeration<String> parametros, HttpServletRequest request) {
        String error = "n";

        // Lista de campos obligatorios
        String[] camposCriticos = {
            "idMascota", "descripcion", "tratamiento"
        };

        for (String campo : camposCriticos) {
            String valor = request.getParameter(campo);
            if (valor == null || valor.trim().isEmpty()) {
                error = "v"; // Campo vacío
                break;
            }
        }

        return error;
    }
    
}