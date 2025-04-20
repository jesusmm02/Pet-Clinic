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
     * Valida que los campos obligatorios del formulario de login estén completos.
     * 
     * @param parametros Enumeración de los nombres de parámetros del formulario.
     * @param request Objeto HttpServletRequest que contiene los parámetros enviados por el usuario.
     * @return "n" si todos los campos obligatorios están completos, "v" si alguno está vacío.
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
     * Valida que los campos obligatorios del formulario de registro o edición de un tutor estén completos.
     *
     * @param parametros Enumeración de los nombres de parámetros del formulario.
     * @param request Objeto HttpServletRequest que contiene los parámetros enviados por el usuario.
     * @return "n" si todos los campos obligatorios están completos, "v" si alguno está vacío.
     */
    public static String comprobarCamposTutor(Enumeration<String> parametros, HttpServletRequest request) {
        String error = "n";

        // Lista de campos obligatorios
        String[] camposCriticos = {
            "nombre", "apellidos", "password", "email", "dni"
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
     * Valida que los campos obligatorios del formulario de edición de un tutor (sin tener en cuenta contraseña) estén completos.
     *
     * @param parametros Enumeración de los nombres de parámetros del formulario.
     * @param request Objeto HttpServletRequest que contiene los parámetros enviados por el usuario.
     * @return "n" si todos los campos obligatorios están completos, "v" si alguno está vacío.
     */
    public static String comprobarCamposTutorSinPasswrd(Enumeration<String> parametros, HttpServletRequest request) {
        String error = "n";

        // Lista de campos obligatorios
        String[] camposCriticos = {
            "nombre", "apellidos", "email", "dni"
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
     * Valida que los campos obligatorios del formulario de registro o edición de un alumno estén completos.
     * 
     * @param parametros Enumeración de los nombres de parámetros del formulario.
     * @param request Objeto HttpServletRequest que contiene los parámetros enviados por el usuario.
     * @return "n" si todos los campos obligatorios están completos, "v" si alguno está vacío.
     */
    public static String comprobarCamposAlumno(Enumeration<String> parametros, HttpServletRequest request) {
        String error = "n";

        // Lista de campos obligatorios
        String[] camposCriticos = {
            "nombre", "apellidos", "password", "email", "dni", "genero"
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
     * Valida que los campos obligatorios del formulario de edición de un alumno (sin tener en cuenta contraseña) estén completos.
     * 
     * @param parametros Enumeración de los nombres de parámetros del formulario.
     * @param request Objeto HttpServletRequest que contiene los parámetros enviados por el usuario.
     * @return "n" si todos los campos obligatorios están completos, "v" si alguno está vacío.
     */
    public static String comprobarCamposAlumnoSinPasswrd(Enumeration<String> parametros, HttpServletRequest request) {
        String error = "n";

        // Lista de campos obligatorios
        String[] camposCriticos = {
            "nombre", "apellidos", "email", "dni", "genero"
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
