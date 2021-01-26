# Dicomproxy

 
No projeto Netbeans, importar biblioteca **\lib\visual\flatlaf-0.46.jar** em biblioteca  classpath.

Ao compilar o projeto, o JAR final estará em **dist** .

> Script para copiar pacotes necessários para empacotamento da aplicação no instalador
```bash
 echo "No Windows "
 echo "execute o arquivo build.bat"

 echo "No Linux"
 echo "execute o arquivo build.sh"
 sh build.sh
```
> Estrutura dos Arquivos
**build.sh**
```bash
cp -R lib dist/lib
cp icon.png dist/icon.png
cp icon.ico dist/icon.ico
cp icon16.ico dist/icon16.ico
```

**build.bat**
```bash
xcopy lib dist/lib /s /e
copy icon.png dist/icon.png
copy icon.ico dist/icon.ico
copy icon16.ico dist/icon16.ico
 
```


## Estrutura da Pasta de Distribuição (**dist** )
- dist/
  - lib/
     - dcm
     - curl
     - rest
     - visual
     - Java (copiar do Sistema operacional, é utilizado para embarcar no APP)
  - icon.png
  - icon.ico
  - icon16.ico
  - incoming
  - DicomProxy.jar
     
## Criando Instalador

  - A aplicação na pasta **dist**  deverá ser empacotada em instalador de aplicações para windows, mac e linux.

  - Você deverá utilizar o installador de sua preferência, os arquivos estáo na pasta installer. Lembrando que o instalador deverá criar atalhos para execução automática no Logon, deve executar a instalação da JRE e realizar uma instalação para todos os usuários do computador.

 ## Aplicações
   [Inno]:<https://jrsoftware.org/isdl.php>
   [Launch4]:<http://launch4j.sourceforge.net/>
 ## Tutoriais:
   [Inno Embarcado com JVM ]: <https://www.youtube.com/watch?v=rqcpqhgLRGM>
   [inno tutorial]: https://randomblog.hu/creating-an-installer-for-windows-from-your-jar-file-to-easily-distribute-your-java-project/
   [advancedinstaller]: <https://www.advancedinstaller.com/user-guide/tutorial-java.html>
   [nsis] <https://nsis.sourceforge.io/Java_Launcher_with_automatic_JRE_installation>
   [advancedinstaller logon]  <https://www.advancedinstaller.com/user-guide/qa-launch-app-logon.html>
   [Best Apps] <https://amp.slant.co/questions/4794>
   [Iniciando no startup INNO] <https://stackoverflow.com/questions/15123421/start-application-with-parameters-on-windows-start>

   [Iniciando no startup INNO deprecate] <https://stackoverflow.com/questions/39107878/creating-shortcut-in-startup-folder-using-inno-setup>





