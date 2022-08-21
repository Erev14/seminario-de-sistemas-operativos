<h1 align="center">Seminario de Sistemas Operativos</h1>

<p align="center">
<!-- <a href="https://travis-ci.org/laravel/framework"><img src="https://travis-ci.org/laravel/framework.svg" alt="Build Status"></a>
<a href="https://packagist.org/packages/laravel/framework"><img src="https://img.shields.io/packagist/dt/laravel/framework" alt="Total Downloads"></a>
<a href="https://packagist.org/packages/laravel/framework"><img src="https://img.shields.io/packagist/v/laravel/framework" alt="Latest Stable Version"></a>
<a href="https://packagist.org/packages/laravel/framework"><img src="https://img.shields.io/packagist/l/laravel/framework" alt="License"></a> -->
</p>

## About
#TODO: add about

***
## Starting 🚀

### Important NOTE ‼
All these implementations use graphical interfaces with JavaFx and Java 11, 
needs to had attached a DISPLAY (WSL can't be used, untill now with this workarround).

***
### Pre-requirements 📋

1. Dev container
    - **``Docker``**
    - **``WSL 2 installed (any distribution works, after install the distribution launch it to set user and password, if you don't do this It wouldn't work)``**
    - **``Visual Studio Code Remote - Containers (VSCode Extension)``**

2. Without devcontainer
    - **``java 11``**
    - **``maven``**
    - **``javafx``**

***
## Usage
### Devcontainer (Only)
Inside the devcontainer:
- Install xserver-xorg lightdm: 
  
  ``sh install_xserver.sh``
  
  You need to do it this way to properly install the xserver properly (need this to launch the application as need a X server to create the window where will be create)

  Note: for fast install follow next inputs requested on the instalation:
  1. Hit enter to show more (needed to read input)
  2. 22 will display more Keyboard lenguajes
  3. Hit enter to show more (needed to read input)
  4. 85 is Spanish (Latinoamerica)
  5. 1 is Basic Spanish distribution.
- Viewing the DISPLAY
  - Linux

    Refer [this](https://gursimar27.medium.com/run-gui-applications-in-a-docker-container-ca625bad4638)
    Basically, you need to 
    - Forwarding An X Socket to A Docker Container
    - Handling X Authentication (optional)
  
    Or use VNC Server (this option will be specify on windows version)
  
    NOTE: Is important to read the X11 risks mention into the publication [publication](https://gursimar27.medium.com/run-gui-applications-in-a-docker-container-ca625bad4638)
  
  - Windows has 2 approches too
    1. Install VcXsrv (Windows X Server) [install](https://sourceforge.net/projects/vcxsrv/) and manually execute to start it before to use it.
    2. VNC Server (this is already implemented into a container from [devrt](https://github.com/devrt/docker-xserver), so you just need to connect to http://localhost:3000/) (port could be change into .devconatiner/docker-compose.yml)


### General
In every project (Folder), will contain a pom.xml file with the needed dependencies:

#### Install dependencies
`` mvn install -f sso/pom.xml``

#### Run program
``mvn clean javafx:run -f sso/pom.xml``

# Build in 🛠️

- [**``Docker``**](https://www.docker.com/) -  Is an open platform for developing, shipping, and running applications. Docker enables you to separate your applications from your infrastructure so you can deliver software quickly. With Docker, you can manage your infrastructure in the same ways you manage your applications. By taking advantage of Docker’s methodologies for shipping, testing, and deploying code quickly, you can significantly reduce the delay between writing code and running it in production.
- [**``vscode``**](https://code.visualstudio.com/) - lightweight but powerful source code editor microsoft. 
- [**``devconainers``**](https://code.visualstudio.com/docs/remote/containers) - The Visual Studio Code Remote - Containers extension lets you use a Docker container as a full-featured development environment. 
- [**``java 11``**](https://devdocs.io/openjdk~11/) - lenguaje use for the code in general.
- [**``javaFX``**](https://openjfx.io/) - next generation client application platform for desktop, mobile and embedded systems built on Java.

## Wiki 📖

Find the key functions that makes every project work [Wiki](https://github.com/Erev14/SSO/wiki/)
- [Procesamiento por lotes y multiprogramación](https://github.com/Erev14/SSO/wiki/lotes) In progres...

## Authors ✒️
* **Edgar Arévalo** - *Main contributor Developer/Documenteation* - [Erev14](https://github.com/Arevalo-Edgar14/)

## Licencia 📄
This project is open-sourced software licensed under the [MIT license](https://opensource.org/licenses/MIT).