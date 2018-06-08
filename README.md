TP1 Técnicas de diseño <br />

# Levantar el servidor.

    1) Posicionarse en tp1-rules-facade y correr `mvn clean install` para generar la dependencia. <br />
    2) Posicionarse en tp y correr `mvn clean install` para descargar las dependencias. <br />
    3) Correr con Play en la clase Main.<br />
    
    Eso genera un servidor donde mandarle las reglas en el puerto 8080. <br />
    Endpoints: <br />
        /process-data : POST <br />
        /add-rule: POST <br />
        /add-dashboard: POST <br />
        /get-state/{id-dash}: GET <br />
        /get-signals/{id-dash}: GET <br />
    Además en el endpoint /processor/websocket tiene un web socket al que se puede conectar. <br />
        Publica en: topic /topic/messages <br />
        Se le manda data a través de: /app/process <br />
        
        Se puede testear el server mediante el script en perl ubicado en /scripts/perl
        Correr: `perl send_post_request.perl`
        
        Se puede testear la conexión con el websocket mediante el proyecto TP1-mock-tickets.
        Correr en la raíz de dicho proyecto mvn clean install.
    

# TP1-web-client
    
    1) (en caso de no tener instalado npm) sudo apt-get install npm <br />
    2) (en caso de no tener instalado nvm) curl -o- https://raw.githubusercontent.com/creationix/nvm/v0.33.11/install.sh | bash <br />
    3) sudo npm install -g @angular/cli <br />
    4) nvm install 8.9 <br />
    5) (en la raíz del proyecto) npm install <br />
    6) (en la raíz del proyecto) ng serve --open <br />

# TP1-Ticket-System
    
    1) Ubicarse en la raíz del proyecto y correr `mvn clean install`
    2) Darle Play y seguir la interfaz interactiva.