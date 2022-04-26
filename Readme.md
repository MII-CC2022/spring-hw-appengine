# Paas con App Engine

Doc: https://cloud.google.com/appengine/#section-4


## Crear Aplicación

Vamos a crear una aplicación Spring Boot usando Spring Initializr

- Utiliza Spring Initializr

    Maven, Java, versión 11. Group: edu.cc.paas   Artifac: holamundo   Jar
-- Dependencias: Spring Web y Thymeleaf

- Genera el proyecto y descomprime en tu IDE (AWS Cloud9)

- Crear un controlador y una vista

Controlador: Fichero HolaController.java en el directorio src/main/java/edu/cc/paas/holamundo

```
package edu.cc.paas.holamundo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HolaController {
    
    @GetMapping("/")
	public String index() {
	    
		return "index";
	}
    
}
```
Vista: fichero index.html en src/main/resource/templates

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">

    <head>
        <title>Hola Mundo</title>
    </head>
    
    <body>
        <h1>Hola Mundo!!</h1>
	
	</body>
</html>

```

## Construye el proyecto

- Crea el paquete .jar del proyecto

```
./mvnw clean package
```

- Comprueba la aplicación de forma local
 
```
./mvnw spring-boot:run

```

En AWS Cloud9 verás una URl similar a la siguiente con la que podrás acceder a la aplicación:

https://d5fb5be6358943a8922adb524bb8819f.vfs.cloud9.us-east-1.amazonaws.com





## Despliega a producción

- Si es necesario, habilita Cloud Build API. 

- Instala Google Cloud CLI. 

En el siguiente enlace tienes la documentación más detallada: https://cloud.google.com/sdk/docs/install

```

$ curl -O https://dl.google.com/dl/cloudsdk/channels/rapid/downloads/google-cloud-cli-382.0.0-linux-x86_64.tar.gz

$ tar xzvf google-cloud-cli-382.0.0-linux-x86_64.tar.gz 

$ ./google-cloud-sdk/install.sh


$ gcloud --version
Google Cloud SDK 382.0.0
bq 2.0.74
core 2022.04.15
gsutil 5.9
```


- Inicializa para autorizar y establecer la configuración


En el siguiente enlace tienes la documentación más detallada: https://cloud.google.com/sdk/docs/initializing

```
$ gcloud init --console-only

$ gcloud config list
```

## Crea el entorno en App Engine

```
$ gcloud app create --project=apptest-cc-2022
You are creating an app for project [apptest-cc-2022].
WARNING: Creating an App Engine application for a project is irreversible and the region
cannot be changed. More information about regions is at
<https://cloud.google.com/appengine/docs/locations>.

Please choose the region where you want your App Engine application located:

 [1] asia-east1    (supports standard and flexible)
 [2] asia-east2    (supports standard and flexible and search_api)
 [3] asia-northeast1 (supports standard and flexible and search_api)
 [4] asia-northeast2 (supports standard and flexible and search_api)
 [5] asia-northeast3 (supports standard and flexible and search_api)
 [6] asia-south1   (supports standard and flexible and search_api)
 [7] asia-southeast1 (supports standard and flexible)
 [8] asia-southeast2 (supports standard and flexible and search_api)
 [9] australia-southeast1 (supports standard and flexible and search_api)
 [10] europe-central2 (supports standard and flexible)
 [11] europe-west   (supports standard and flexible and search_api)
 [12] europe-west2  (supports standard and flexible and search_api)
 [13] europe-west3  (supports standard and flexible and search_api)
 [14] europe-west6  (supports standard and flexible and search_api)
 [15] northamerica-northeast1 (supports standard and flexible and search_api)
 [16] southamerica-east1 (supports standard and flexible and search_api)
 [17] us-central    (supports standard and flexible and search_api)
 [18] us-east1      (supports standard and flexible and search_api)
 [19] us-east4      (supports standard and flexible and search_api)
 [20] us-west1      (supports standard and flexible)
 [21] us-west2      (supports standard and flexible and search_api)
 [22] us-west3      (supports standard and flexible and search_api)
 [23] us-west4      (supports standard and flexible and search_api)
 [24] cancel
Please enter your numeric choice:  10


Creating App Engine application in project [apptest-cc-2022] and region [europe-central2]....done.                                                                                          
Success! The app is now created. Please use `gcloud app deploy` to deploy your first app.
```


## Añadimos el fichero de configuración del entorno app.yaml

En el directorio src/main creamos el directorio appengine y dentro el fichero app.yaml con el siguiente contenido:

```
runtime: java11
``` 

## Desplegamos la app a producción

```
$  gcloud app deploy

Services to deploy:

descriptor:                  [/home/ubuntu/environment/holamundo/pom.xml]
source:                      [/home/ubuntu/environment/holamundo]
target project:              [apptest-cc-2022]
target service:              [default]
target version:              [20220425t163755]
target url:                  [https://apptest-cc-2022.lm.r.appspot.com]
target service account:      [App Engine default service account]


Do you want to continue (Y/n)?  

Beginning deployment of service [default]...
╔════════════════════════════════════════════════════════════╗
╠═ Uploading 1 file to Google Cloud Storage                 ═╣
╚════════════════════════════════════════════════════════════╝
File upload done.
Updating service [default]...done.                                                                                                                                                          
Setting traffic split for service [default]...done.                                                                                                                                         
Deployed service [default] to [https://apptest-cc-2022.lm.r.appspot.com]

You can stream logs from the command line by running:
  $ gcloud app logs tail -s default

To view your application in the web browser run:
  $ gcloud app browse
``` 

