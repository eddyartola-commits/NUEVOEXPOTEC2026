package Alerta;

import java.util.Properties;
import jakarta.mail.*;
import jakarta.mail.internet.*;

public class ServicioNotificaciones {

    public static void enviarAlertaMasivaAsync(String listaDestinatarios, String idMantenimiento) {
        if (listaDestinatarios == null || listaDestinatarios.trim().isEmpty()) {
            System.out.println("No se encontraron correos de administradores para notificar.");
            return;
        }

        // Hilo paralelo para que la interfaz de Java Swing no sufra retrasos
        new Thread(new Runnable() {
            @Override
            public void run() {
                // 1. Propiedades del Servidor de Google (Puerto SSL 465)
                Properties props = new Properties();
                props.put("mail.smtp.host", "smtp.gmail.com");
                props.put("mail.smtp.port", "465");
                props.put("mail.smtp.auth", "true");
                props.put("mail.smtp.socketFactory.port", "465");
                props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
                props.put("mail.smtp.socketFactory.fallback", "false");
                
                // 2. Credenciales de la cuenta emisora del sistema
                final String correoEmisor = "powerfitstrike@gmail.com";
                final String contrasenaApp = "zfhbhtbaoiqdcssw"; 

                Session session = Session.getInstance(props, new jakarta.mail.Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(correoEmisor, contrasenaApp);
                    }
                });

                try {
                    // ============================================================
                    // 🌟 LIMPIEZA DE CORREOS AUTOMÁTICA
                    // ============================================================
                    String[] correosSucios = listaDestinatarios.split(",");
                    StringBuilder listaLimpia = new StringBuilder();

                    for (String correo : correosSucios) {
                        String correoLimpio = correo.trim();
                        if (!correoLimpio.isEmpty() && correoLimpio.contains("@")) {
                            if (listaLimpia.length() > 0) {
                                listaLimpia.append(",");
                            }
                            listaLimpia.append(correoLimpio);
                        }
                    }

                    if (listaLimpia.length() == 0) {
                        System.err.println("Envío cancelado: Ningún correo válido.");
                        return;
                    }
                    // ============================================================

                    // 3. Crear el mensaje
                    Message message = new MimeMessage(session);
                    message.setFrom(new InternetAddress(correoEmisor));
                    message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(listaLimpia.toString()));
                    message.setSubject("🚨 ALERTA GLOBAL: Nuevo Reporte de Mantenimiento Iniciado");

                    String urlLogo = "https://i.postimg.cc/qB6Z04gS/Logo-(2).png";
                    
                    // Cuerpo del correo estilizado
                    String cuerpoHtml = "<div style='font-family: sans-serif; border: 2px solid #FF0055; padding: 25px; border-radius: 8px; max-width: 600px; margin: 0 auto;'>"
                            + "<div style='text-align: center; margin-bottom: 20px;'>"
                            + "  <img src='" + urlLogo + "' alt='Power Fit Strike' style='width: 85%; max-width: 450px; height: auto; display: inline-block;' border='0'>"
                            + "</div>"
                            + "<h2 style='color: #FF0055; margin-top: 0; text-align: center;'>Atención Administradores de Power Fit Strike</h2>"
                            + "<p>Se ha generado y guardado un nuevo reporte técnico en el sistema <b>Power Fit Strike</b>.</p>"
                            + "<p style='font-size: 16px; background-color: #f4f4f4; padding: 10px; border-left: 5px solid #FF0055;'>"
                            + "<b>ID del Mantenimiento registrado:</b> " + idMantenimiento + "</p>"
                            + "<p>Por favor, revisen el panel de control técnico en el sistema de Power Fit Strike para una revision de la pieza afectada y la solución correspondiente.</p>"
                            + "<hr style='border:0; border-top: 1px solid #ddd;'>"
                            + "<p style='font-size: 11px; color: #777; text-align: center;'>Mensaje automatizado del sistema de alertas distribuidas.</p>"
                            + "</div>";

                    message.setContent(cuerpoHtml, "text/html; charset=utf-8");

                    // 4. Enviar correos
                    Transport.send(message);
                    System.out.println("Notificación enviada con éxito a los administradores: " + listaLimpia.toString());

                } catch (MessagingException e) {
                    System.err.println("Error crítico al despachar los correos: " + e.getMessage());
                }
            }
        }).start();
    }
    
    public static void enviarCorreoBienvenidaPremiumAsync(String correoUsuario, String nombreUsuario) {
        if (correoUsuario == null || correoUsuario.trim().isEmpty()) {
            return;
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                // 1. Propiedades del Servidor de Google (Puerto SSL 465)
                Properties props = new Properties();
                props.put("mail.smtp.host", "smtp.gmail.com");
                props.put("mail.smtp.port", "465");
                props.put("mail.smtp.auth", "true");
                props.put("mail.smtp.socketFactory.port", "465");
                props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
                props.put("mail.smtp.socketFactory.fallback", "false");
                
                final String correoEmisor = "powerfitstrike@gmail.com";
                final String contrasenaApp = "zfhbhtbaoiqdcssw"; 

                Session session = Session.getInstance(props, new jakarta.mail.Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(correoEmisor, contrasenaApp);
                    }
                });

                try {
                    Message message = new MimeMessage(session);
                    message.setFrom(new InternetAddress(correoEmisor));
                    message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(correoUsuario.trim()));
                    message.setSubject("💪 ¡Bienvenido a la comunidad Power Fit Strike, " + nombreUsuario + "!");

                    String urlLogo = "https://i.postimg.cc/fRyzTVXC/LOGO-(1).png";
                    String linkGitHub = "https://github.com/tu-usuario/power-fit-strike"; 

                    String scriptBD = "-- Estructura básica de la base de datos DB_Power\n"
                                    + "CREATE DATABASE IF NOT EXISTS DB_Power;\n"
                                    + "USE DB_Power;\n\n"
                                    + "CREATE TABLE IF NOT EXISTS Login (\n"
                                    + "    id INT AUTO_INCREMENT PRIMARY KEY,\n"
                                    + "    Usuario VARCHAR(50),\n"
                                    + "    Correo VARCHAR(100),\n"
                                    + "    Contrasena VARCHAR(255),\n"
                                    + "    rol VARCHAR(20)\n"
                                    + ");";

                    String cuerpoHtml = "<div style='font-family: Arial, sans-serif; background-color: #13031b; padding: 30px; border-radius: 12px; max-width: 600px; margin: 0 auto; box-shadow: 0 4px 15px rgba(0,0,0,0.5); text-align: center; color: #e0d0e6;'>"
                            + "  <div style='margin-bottom: 25px;'> "
                            + "    <img src='" + urlLogo + "' alt='Power Fit Strike' style='width: 75%; max-width: 360px; height: auto; display: inline-block;' border='0'>"
                            + "  </div>"
                            + "  <h2 style='font-family: Archivo Black, Arial Black, sans-serif; color: #ffffff; margin-top: 0; font-size: 32px; letter-spacing: 2px;'>¡BIENVENIDO A BORDO!</h2>"
                            + "  <p style='font-size: 16px; color: #ffffff;'>Hola <b style='color: #FF0055;'>" + nombreUsuario + "</b>, tu registro en <b>Power Fit Strike</b> ha sido completado con éxito.</p>"
                            + "  <hr style='border: 0; border-top: 1px solid rgba(255, 255, 255, 0.1); margin: 25px 0;'>"
                            + "  <div style='text-align: left; padding: 0 10px;'>"
                            + "    <h3 style='color: #FF0055; font-size: 18px; margin-bottom: 10px;'>¿Quiénes Somos?</h3>"
                            + "    <p style='font-size: 14px; line-height: 1.6; color: #a192a8;'>"
                            + "      Somos un groupo de estudiantes que piensan de manera diferente y que quieren sobre salir de los demas, nuestros objetivos siempre fue un proyecto innovador y que sea capaz de ser entretenido."
                            + "    </p>"
                            + "  </div>"
                            + "  <hr style='border: 0; border-top: 1px solid rgba(255, 255, 255, 0.1); margin: 25px 0;'>"
                            + "  <div style='text-align: left; padding: 0 10px;'>"
                            + "    <h3 style='color: #FF0055; font-size: 18px; margin-bottom: 10px;'>Código Fuente del Proyecto</h3>"
                            + "    <p style='font-size: 14px; color: #a192a8;'>Puedes realizar el seguimiento de versiones, consultar el progreso del desarrollo y revisar las ramas de código directamente en nuestro repositorio público:</p>"
                            + "    <div style='text-align: center; margin: 20px 0;'>"
                            + "      <a href='" + linkGitHub + "' target='_blank' style='background-color: #FF0055; color: #ffffff; padding: 12px 30px; text-decoration: none; font-weight: bold; border-radius: 25px; display: inline-block; box-shadow: 0 4px 10px rgba(255,0,85,0.3); font-size: 14px;'>🔗 VER PROYECTO EN GITHUB</a>"
                            + "    </div>"
                            + "  </div>"
                            + "  <hr style='border: 0; border-top: 1px solid rgba(255, 255, 255, 0.1); margin: 25px 0;'>"
                            + "  <div style='text-align: left; padding: 0 10px;'>"
                            + "    <h3 style='color: #FF0055; font-size: 18px; margin-bottom: 10px;'>Estructura de la Base de Datos (MYSQL)</h3>"
                            + "    <p style='font-size: 14px; color: #a192a8;'>Para desplegar o auditar el entorno local de MySQL de forma inmediata, puedes utilizar el siguiente bloque script:</p>"
                            + "    <pre style='background-color: #1c0529; border: 1px solid rgba(255,0,85,0.3); border-radius: 6px; padding: 15px; color: #00ffaa; font-family: Consolas, Monaco, monospace; font-size: 12px; overflow-x: auto; line-height: 1.4;'>" + scriptBD + "</pre>"
                            + "  </div>"
                            + "  <hr style='border: 0; border-top: 1px solid rgba(255, 255, 255, 0.1); margin: 30px 0;'>"
                            + "  <p style='font-size: 11px; color: #6d5875; margin: 0;'>© 2026 Power Fit Strike. Todos los derechos reservados.</p>"
                            + "  <p style='font-size: 9px; color: #534259; margin-top: 5px;'>Este mensaje contiene información técnica automatizada del ecosistema de distribución.</p>"
                            + "</div>";

                    message.setContent(cuerpoHtml, "text/html; charset=utf-8");

                    Transport.send(message);
                    System.out.println("Correo de bienvenida corporativo enviado con éxito a: " + correoUsuario);

                } catch (MessagingException e) {
                    System.err.println("Error al despachar el correo de bienvenida: " + e.getMessage());
                }
            }
        }).start();
    }
    
    public static void enviarNotificacionNuevoUsuario(String idUsuario, String nombreUsuario, String rolUsuario, String listaDestinatarios) {
        if (listaDestinatarios == null || listaDestinatarios.trim().isEmpty()) {
            System.out.println("No se encontraron correos de administradores para notificar.");
            return;
        }
        
        new Thread(new Runnable() {
            @Override
            public void run() {
                // 1. Propiedades del Servidor de Google (Puerto SSL 465)
                Properties props = new Properties();
                props.put("mail.smtp.host", "smtp.gmail.com");
                props.put("mail.smtp.port", "465");
                props.put("mail.smtp.auth", "true");
                props.put("mail.smtp.socketFactory.port", "465");
                props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
                props.put("mail.smtp.socketFactory.fallback", "false");

                final String correoEmisor = "powerfitstrike@gmail.com";
                final String contrasenaApp = "zfhbhtbaoiqdcssw"; 

                Session session = Session.getInstance(props, new jakarta.mail.Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(correoEmisor, contrasenaApp);
                    }
                });

                try {
                    String[] correosSucios = listaDestinatarios.split(",");
                    StringBuilder listaLimpia = new StringBuilder();
                    for (String correo : correosSucios) {
                        String correoLimpio = correo.trim();
                        if (!correoLimpio.isEmpty() && correoLimpio.contains("@")) {
                            if (listaLimpia.length() > 0) {
                                listaLimpia.append(",");
                            }
                            listaLimpia.append(correoLimpio);
                        }
                    }

                    if (listaLimpia.length() == 0) return;

                    Message message = new MimeMessage(session);
                    message.setFrom(new InternetAddress(correoEmisor));
                    message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(listaLimpia.toString()));
                    message.setSubject("⚡ POWER FIT STRIKE - Nuevo Registro");

                    String urlLogo = "https://i.postimg.cc/qB6Z04gS/Logo-(2).png";
                    String colorFucsia = "#FF0055"; 

                    String cuerpoHtml = "<div style=\"max-width: 600px; margin: 0 auto; font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; background: linear-gradient(145deg, #160b22, #0d0614); border: 2px solid " + colorFucsia + "; border-radius: 16px; padding: 40px; box-shadow: 0 10px 30px rgba(255, 0, 85, 0.2); color: #ffffff;\">"
                        + "  <div style=\"text-align: center; margin-bottom: 35px; border-bottom: 1px solid rgba(255, 255, 255, 0.1); padding-bottom: 20px;\">"
                        + "    <img src=\"" + urlLogo + "\" alt=\"Power Fit Strike\" style=\"width: 80%; max-width: 380px; height: auto; display: inline-block;\" border=\"0\">"
                        + "  </div>"
                        + "  <h2 style=\"color: #ffffff; font-size: 22px; font-weight: 700; margin-top: 0; margin-bottom: 15px; letter-spacing: 0.5px;\">"
                        + "    ⚠️ Usuario Nuevo"
                        + "  </h2>"
                        + "  <p style=\"color: #cbd5e1; font-size: 15px; line-height: 1.6; margin-bottom: 25px;\">"
                        + "    Se ha registrado correctamente una nueva credencial a través del módulo de gestión de usuarios. Los detalles del registro se muestran a continuación:"
                        + "  </p>"
                        + "  <div style=\"background-color: rgba(255, 0, 85, 0.04); border-left: 4px solid " + colorFucsia + "; border-radius: 4px 12px 12px 4px; padding: 25px; margin-bottom: 35px;\">"
                        + "    <table style=\"width: 100%; border-collapse: collapse; font-size: 15px;\">"
                        + "      <tr>"
                        + "        <td style=\"padding: 10px 0; color: #a594b8; width: 35%; font-weight: 600;\">ID Usuario:</td>"
                        + "        <td style=\"padding: 10px 0; color: #ffffff; font-weight: bold; font-size: 16px;\">#" + idUsuario + "</td>"
                        + "      </tr>"
                        + "      <tr>"
                        + "        <td style=\"padding: 10px 0; color: #a594b8; font-weight: 600;\">Nombre Completo:</td>"
                        + "        <td style=\"padding: 10px 0; color: #ffffff;\">" + nombreUsuario + "</td>"
                        + "      </tr>"
                        + "      <tr>"
                        + "        <td style=\"padding: 10px 0; color: #a594b8; font-weight: 600;\">Rol Asignado:</td>"
                        + "        <td style=\"padding: 10px 0;\">"
                        + "          <span style=\"background-color: " + colorFucsia + "; color: #ffffff; padding: 4px 14px; border-radius: 20px; font-size: 11px; font-weight: bold; text-transform: uppercase; letter-spacing: 0.5px;\">" + rolUsuario + "</span>"
                        + "        </td>"
                        + "      </tr>"
                        + "    </table>"
                        + "  </div>"
                        + "  <div style=\"border-top: 1px solid rgba(255, 255, 255, 0.1); padding-top: 20px; text-align: center; color: #64748b; font-size: 11px; line-height: 1.6;\">"
                        + "    Este es un servicio automatizado de alerta distribuida perteneciente a <strong>Power Fit Strike</strong>.<br>"
                        + "    Si no reconoces este movimiento, ingresa de inmediato para revocar los accesos corporativos."
                        + "  </div>"
                        + "</div>";

                    message.setContent(cuerpoHtml, "text/html; charset=utf-8");

                    Transport.send(message);
                    System.out.println("Notificación de nuevo usuario enviada con éxito.");

                } catch (MessagingException e) {
                    System.err.println("Error crítico al configurar el correo de usuario: " + e.getMessage());
                }
            }
        }).start();
    }
}