# nfc-restaurant


![Github](https://github.com/eclair11/nfc-restaurant/blob/master/mobile/app/src/main/res/drawable/app.png)


Membres du groupe :
* MKENINI Ismael
* ROMDAN Elias


Pour lancer le projet il faut avoir :
* Smartphone Android (Version minimale 4.0.3)
* Apache Maven pour lancer le serveur (https://www.mkyong.com/maven/how-to-install-maven-in-windows/)
* Android Studio pour lancer le client (https://developer.android.com/studio/index.html)
* Un logiciel pour manipuler une base de données MySQL comme MySQL Workbench (https://www.mysql.com/fr/products/workbench/)


Pour lancer le serveur :
* Ouvrir une ligne de commande dans le dossier server
* Taper mvn clean package
* Taper mvn jetty:run
=> Si vous avez dans votre ligne de commande, [INFO] Started Jetty Server, ça veut dire que le serveur est bien lancé


Pour lancer le client :
* Ouvrir Android Studio
* Choisir Open an existing Android Studio Project
* Choisir le dossier mobile
* Brancher le smartphone Android au PC (s'assurer que le Smartphone est en mode développeur)
* Choisir dans la barre de tâche d'Android Studio : Run puis Run 'app' (vous pouvez aussi utiliser Maj + F10)
* Choisir votre Smartphone qui se trouve dans Connected Devices
=> Attendre que l'apk s'installe sur votre Smartphone, puis l'application s'ouvrira automatiquement


Pour la synchronisation client/serveur :
* Vu qu'on fasse les tests en local, il faut changer l'URL vers l'adresse IP du serveur
* Ouvrir (sur Android Studio) le fichier Requests.java qui se trouve dans mobile\app\src\main\java\fr\unice\iut\resto
* Changer XXX.XXX.XXX.XXX avec l'adresse IP du PC où le serveur est lancé
* Relancer le projet avec Run -> Run 'app' ou Maj + F10
=> Pour que les appels aux Web Services passent bien, il faut s'assurer que le client Android et le serveur se trouvent sur le même réseau


Pour installer la base de données :
* Créer une base de données avec le nom nfc_resto
* Importer le fichier nfc_resto.sql qui se trouve dans le dossier database vers un éditeur de base de données MySQL
=> Un utilisateur de test devra apparaître dans la table users, vous pouvez l'utiliser pour tester l'application


Pour utiliser le client Web :
* Ouvrir le fichier login.html qui se trouve dans le dossier client\html avec votre navigateur favori