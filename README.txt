Integrantes de la asociación:
Juan José Andrade Pardo
Daniel Felipe Moreno D’aleman
Daniel Castiblanco
En el siguiente repositorio encontrarán un servidor WEB que permite generar facturas electrónicas llenando un determinado formulario. En este caso se ingresan los datos pertinentes al formulario y esto crea una respuesta html al usuario de los datos de su factura. Por otro lado se envía  la factura que se acabó de mostrar y adicionalmente una factura en formato xml con la declaración de impuestos pertinentes de la DIAN a un correo determinado.
Esta aplicación usa una API para transformar un JSON  en facturas distintas. En este caso está disponible una factura sencilla para un cliente, y una factura en formato XML que se le enviaría a la DIAN en caso de declaración de impuestos.
Link de la API:
https://aremfinalapi.herokuapp.com/
Ejemplo de como usar la API:
Existen dos posibilidades: 
Para la factura de la DIAN https://aremfinalapi.herokuapp.com/dian?factura= seguido del JSON usando como mínimo los parámetros mostrados en el link de abajo.
https://aremfinalapi.herokuapp.com/dian?factura={%22numfac%22:%221234%22,%22numempresa%22:%221%22,%22empresa%22:%22Loscracks%22,%22valor%22:%22500000%22}
Para la factura del cliente https://aremfinalapi.herokuapp.com/cliente?factura= seguido del JSON.
La aplicación WEB se encuentra corriendo en una máquina virtual de AWS, donde se puede hacer uso del aplicativo.
Link para acceder al servidor web:
http://ec2-18-237-88-0.us-west-2.compute.amazonaws.com:8080/
