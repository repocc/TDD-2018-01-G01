TP1 Técnicas de diseño

# Levantar el servidor.

    1) Posicionarse en tp1-rules-facade y correr `mvn clean install` para generar la dependencia.
    2) Posicionarse en tp y correr `mvn clean install` para descargar las dependencias.
    3) Correr con Play en la clase Main.
    
    Eso genera un servidor donde mandarle las reglas en el puerto 8080. 
    Endpoints: 
        /process-data : POST 
        /add-rule: POST 
        /add-dashboard: POST 
        /get-state/{id-dash}: GET 
        /get-signals/{id-dash}: GET 
    Además en el endpoint /processor/websocket tiene un web socket al que se puede conectar. 
        Publica en: topic /topic/messages 
        Se le manda data a través de: /app/process 
        
        Se puede testear el server mediante el script en perl ubicado en /scripts/perl
        Correr: `perl send_post_request.perl`
        
        Se puede testear la conexión con el websocket mediante el proyecto TP1-mock-tickets.
        Correr en la raíz de dicho proyecto mvn clean install.
    

# TP1-web-client
    
    1) (en caso de no tener instalado npm) sudo apt-get install npm 
    2) (en caso de no tener instalado nvm) curl -o- https://raw.githubusercontent.com/creationix/nvm/v0.33.11/install.sh | bash 
    3) sudo npm install -g @angular/cli 
    4) nvm install 8.9 
    5) (en la raíz del proyecto) npm install 
    6) (en la raíz del proyecto) ng serve --open 

# TP1-Ticket-System
    
    1) Ubicarse en la raíz del proyecto y correr `mvn clean install`
    2) Darle Play y seguir la interfaz interactiva.