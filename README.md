# Dicomproxy

 
No projeto Netbeans, importar biblioteca **\lib\visual\flatlaf-0.46.jar** em biblioteca  classpath.

Ao compilar o projeto, o JAR final estar� em **dist** .

> Script para copiar pacotes necess�rios para empacotamento da aplica��o no instalador
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


## Estrutura da Pasta de Distribui��o (**dist** )
- dist/
  - lib/
     - dcm
     - curl
     - rest
     - visual
     - Java (copiar do Sistema operacional, � utilizado para embarcar no APP)
  - icon.png
  - icon.ico
  - icon16.ico
  - incoming
  - DicomProxy.jar
     
## Criando Instalador

  - A aplica��o na pasta **dist**  dever� ser empacotada em instalador de aplica��es para windows, mac e linux.

  - Voc� dever� utilizar o installador de sua prefer�ncia, os arquivos est�o na pasta installer. Lembrando que o instalador dever� criar atalhos para execu��o autom�tica no Logon, deve executar a instala��o da JRE e realizar uma instala��o para todos os usu�rios do computador.

 ## Aplica��es
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





