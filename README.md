# TP-Java
Proyecto para la materia "Lenguaje de programación Java" de la UTN FRRo

# Integrantes
Germán Tano - Legajo 46873  
Franco Fonzo - Legajo 46882  
Federico Colarte - Legajo 47536

## **Enunciado General**

Sistema de gestion de reservas de un hotel. 

### **Alcance**
Desde que el usuario se registra/loguea en el dominio del sitio, elige fecha de entrada, salida, cantidad de personas y tipo de habitación, y el sistema muestra las habitaciones disponibles que cumplan con esos parámetros de entrada. Cuando el usuario elige la habitación deseada hace la reserva y el sistema le envia por e-mail el comprobante de reserva

ABMC:
- Simples: TipoPersona, TipoHabitación, Servicios
- Complejos: Persona, Habitación

Listados:
- Simples: -
- Complejos: Reserva (filtrado por cliente, fechas)

## **Modelo de Datos**

![DER](https://user-images.githubusercontent.com/81445495/163038762-b9ba03ca-673c-4a73-84a8-2f6e1b9c38c1.jpg)

### Casos de uso (Resumen)
- **Reservar estadia**
  - Incluye Solicitar, Modificar, Cancelar, Confirmar la reserva.
- **Gestionar reservas**
  - Incluye Remover reservas caducadas, y Procesar clientes no presentados.
