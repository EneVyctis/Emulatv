# Emulatv 

Emulatv is a little project for my personal use (but you are free to use it of course :D). It aims to emulate a TV App and made services (such as Netflix, Youtube etc...) accessible. 
I want it to run on a micro controller such as a Raspberry Pi, my personal phone (or any phones within the same network) would act as a tv controller. 

Another part of this project is for me to familiarize with full stack web development and deploiement. 

# Warning 

--- DO NOT USE OUTSIDE OF LOCAL NETWORK, NO SECURITY FEATURES ARE IMPLEMENTED YET ---

# How to run

Copy this repo on your computer.

First you will need to set-up **your own postgresql database** see [the postgresql documentation](https://www.postgresql.org/docs/18/index.html) for more informations. 

Then go to api/emulatv-api/src/main/resources/application.properties and changes username and password according to your local settings.
Do not forget to change postgresql configuration files in order to accept connections from docker's network. Range of ip may vary for each indivual so check for yourself. 

**When the database set-up is completed**: 

*Make sure you have docker installed on your device.*

```sh
chmod u+x run-emulatv.sh
./run-emulatv.sh
```

# How to use

Here is the main UI that runs directly on the micro-controller/pc/whatever you want. Will act as the smartTV. When the service runs, you can access it at [this link](http://localhost/ui/).

![Main UI](./img/MainUi.png)


You can control it through a web app on you personal phone in the same local network as the TV box, here is the Remote UI.

![Remote on phone](./img/RemoteController.png)

Available at http://your_computer_ip/remote/

Finally, to add services to you database, visit the (yet to be complete) [admin panel](http://localhost/ui/admin/). You will have to provide an svg image for each service and the link of the service.

![Admin panel](img/AdminPanel.png)